package org.kurento.tutorial.groupcall.service;

import java.util.concurrent.ConcurrentMap;

import org.kurento.client.FaceOverlayFilter;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;
import org.kurento.tutorial.groupcall.Room;
import org.kurento.tutorial.groupcall.UserSession;
import org.springframework.stereotype.Service;

@Service
public class FilterService {
	
	public void useFilter(UserSession sender, Room room) {
	    MediaPipeline pipeline = sender.getPipeline();
		// Media logic
	    FaceOverlayFilter faceOverlayFilter = new FaceOverlayFilter.Builder(pipeline).build();

	    //String appServerUrl = System.getProperty("app.server.url",
	    //    MagicMirrorApp.DEFAULT_APP_SERVER_URL);
	    String appServerUrl = "http://files.openvidu.io";
	    faceOverlayFilter.setOverlayedImage(appServerUrl + "/img/mario-wings.png", -0.35F, -1.2F,
	        1.6F, 1.6F);
	    
	    sender.getOutgoingWebRtcPeer().connect(faceOverlayFilter);
//	    faceOverlayFilter.connect(sender.getOutgoingWebRtcPeer());
	    ConcurrentMap<String, WebRtcEndpoint> incomingMedia = sender.getIncomingMedia();
	    
	    String userName = sender.getName();
	    for (UserSession session : room.getParticipants()) {
	    	if (session.getName().equals(userName)) continue;
	    	incomingMedia = session.getIncomingMedia();
	    	faceOverlayFilter.connect(incomingMedia.get(userName));
	    }
	}
}
