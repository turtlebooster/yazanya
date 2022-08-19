import store from '@/store';

export function Participant(name) {
  this.name = name;

  Object.defineProperty(this, 'rtcPeer', {
    writable: true,
  });

  // create video
  var video = document.createElement('video');
  video.id = 'video-' + name;
  video.autoplay = true;
  video.controls = false;

  var rtcPeer;
  console.log('avoid error ' + rtcPeer);

  this.getVideoElement = function () {
    return video;
  };

  // managing peer audio and video
  this.handleAudio = function (flag) {
    if (this.rtcPeer) {
      this.rtcPeer.audioEnabled = flag;
    }
  };
  this.handleVideo = function (flag) {
    if (this.rtcPeer) {
      console.log('videoEnagled');
      this.rtcPeer.videoEnabled = flag;
    }
  };

  this.offerToReceiveVideo = function (error, offerSdp) {
    if (error) return console.error('sdp offer error');
    console.log('Invoking SDP offer callback function');
    var msg = {
      id: 'receiveVideoFrom',
      sender: name,
      sdpOffer: offerSdp,
    };
    store.commit('sendMessage', msg);
  };

  this.onIceCandidate = function (candidate) {
    console.log('Local candidate' + JSON.stringify(candidate));

    var message = {
      id: 'onIceCandidate',
      candidate: candidate,
      name: name,
    };
    store.commit('sendMessage', message);
  };

  this.dispose = function () {
    console.log('Disposing participant ' + this.name);
    this.rtcPeer.dispose();
  };
}
