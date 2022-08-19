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
	
	public void FilterOn(UserSession sender, Room room) {
	    MediaPipeline pipeline = sender.getPipeline();
		// Media logic
	    FaceOverlayFilter faceOverlayFilter = new FaceOverlayFilter.Builder(pipeline).build();

	    faceOverlayFilter.setOverlayedImage("https://raw.githubusercontent.com/turtlebooster/WebRtc-test/main/src/main/resources/fox.png", -0.35F, -0.6F, 2.0F, 2.0F);
	    
	    sender.getOutgoingWebRtcPeer().connect(faceOverlayFilter);
	    ConcurrentMap<String, WebRtcEndpoint> incomingMedia;
	    
	    String userName = sender.getName();
	    for (UserSession session : room.getParticipants()) {
	    	if (session.getName().equals(userName)) continue;
	    	incomingMedia = session.getIncomingMedia();
	    	faceOverlayFilter.connect(incomingMedia.get(userName));
	    }
	    sender.setFilter(true);
	}
	
	public void FilterOff(UserSession sender, Room room) {
		ConcurrentMap<String, WebRtcEndpoint> incomingMedia;
		
		String userName = sender.getName();
		for (UserSession session : room.getParticipants()) {
			if (session.getName().equals(userName)) continue;
			incomingMedia = session.getIncomingMedia();
			sender.getOutgoingWebRtcPeer().connect(incomingMedia.get(userName));
		}
		sender.setFilter(false);
	}
	
}
