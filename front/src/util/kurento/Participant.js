import { useStore } from "vuex";

export function Participant(name) {
  this.name = name;
  // for socket access
  var store = useStore();

  // var rtcPeer
  Object.defineProperty(this, "rtcPeer", {
    writable: true,
  });

  // create video
  var video = document.createElement("video");
  video.id = "video-" + name;
  video.autoplay = true;
  video.controls = false;

  var rtcPeer;
  console.log(rtcPeer);

  this.getVideoElement = function () {
    return video;
  };

  this.offerToReceiveVideo = function (error, offerSdp) {
    if (error) return console.error("sdp offer error");
    console.log("Invoking SDP offer callback function");
    var msg = {
      id: "receiveVideoFrom",
      sender: name,
      sdpOffer: offerSdp,
    };
    store.dispatch("sendMessage", msg);
  };

  this.onIceCandidate = function (candidate) {
    console.log(
      "Local candidate" + JSON.stringify(candidate)
    );

    var message = {
      id: "onIceCandidate",
      candidate: candidate,
      name: name,
    };
    store.dispatch("sendMessage", message);
  };

  this.dispose = function () {
    console.log("Disposing participant " + this.name);
    this.rtcPeer.dispose();
  };
}
