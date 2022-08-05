import { Participant } from './Participant';
import { WebRtcPeer } from 'kurento-utils';
import store from '@/store';

export function onExistingParticipants(msg, participants, userName, roomName) {
  var constraints = {
    audio: true,
    video: {
      mandatory: {
        maxFrameRate: 15,
        minFrameRate: 15,
      },
    },
  };
  console.log(userName + ' registered in room ' + roomName);

  var participant = new Participant(userName);
  participants[userName] = participant;
  var video = participant.getVideoElement();

  var options = {
    localVideo: video,
    mediaConstraints: constraints,

    dataChannels: true,
    dataChannelConfig: {
      onmessage: onDataChanelMessage,
    },

    onicecandidate: participant.onIceCandidate.bind(participant),
  };
  participant.rtcPeer = new WebRtcPeer.WebRtcPeerSendonly(options, function (
    error
  ) {
    if (error) {
      return console.error(error);
    }
    this.generateOffer(participant.offerToReceiveVideo.bind(participant));
  });

  msg.data.forEach((element) => receiveVideo(element, participants));

  console.log(participants);
}

export function onNewParticipant(request, participants) {
  console.log('New participant ' + request.name + ' has been arived');
  receiveVideo(request.name, participants);
}

function receiveVideo(sender, participants) {
  var participant = new Participant(sender);
  participants[sender] = participant;
  var video = participant.getVideoElement();

  var options = {
    remoteVideo: video,

    dataChannels: true,
    dataChannelConfig: {
      onmessage: onDataChanelMessage,
    },

    onicecandidate: participant.onIceCandidate.bind(participant),
  };

  participant.rtcPeer = new WebRtcPeer.WebRtcPeerRecvonly(options, function (
    error
  ) {
    if (error) {
      return console.error(error);
    }
    this.generateOffer(participant.offerToReceiveVideo.bind(participant));
  });
}

function onDataChanelMessage(event) {
  store.commit('addChat', event.data);
}

export function onParticipantLeft(request, participants) {
  console.log('Participant ' + request.name + ' left');
  var participant = participants[request.name];
  participant.dispose();
  delete participants[request.name];
}

export function receiveVideoResponse(result, participants) {
  participants[result.name].rtcPeer.processAnswer(
    result.sdpAnswer,
    function (error) {
      if (error) return console.error(error);
    }
  );
}
