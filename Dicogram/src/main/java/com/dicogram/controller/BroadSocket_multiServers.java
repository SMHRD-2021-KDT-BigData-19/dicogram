package com.dicogram.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint; // WebSocket 호스트 설정


// 채팅방 코드 전달할 때 아래와 같은 @pathPram형식으로 작성해야 함**********
@ServerEndpoint("/BroadSocket_multiServers/{chatroomId}")
public class BroadSocket_multiServers{
	// 채팅방 ID를 key로 사용하고 해당 채팅방에 참여한 세션 리스트를 value로 사용하는 Map
	private static Map<String, List<Session>> chatRooms = Collections.synchronizedMap(new HashMap<>());

	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void handleOpen(Session userSession, @PathParam("chatroomId") String chatroomId){
		// 해당 채팅방 ID에 해당하는 세션 리스트를 가져온다.
		List<Session> chatroomSessions = chatRooms.getOrDefault(chatroomId, new ArrayList<>());
		
		// 클라이언트가 접속하면 WebSocket 세션을 리스트에 저장한다.
		chatroomSessions.add(userSession);
		
		// 해당 채팅방 ID에 해당하는 세션 리스트를 갱신한다.
        chatRooms.put(chatroomId, chatroomSessions);
        
        // 콘솔에 접속 로그를 출력한다.
        System.out.println("client is now connected to chatroom " + chatroomId);
        
        // test - 방에 존재하는 유저들
        System.out.println(chatroomId + ": " + chatroomSessions);
	}
	
	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession, @PathParam("chatroomId") String chatroomId) throws IOException {		
		// 해당 채팅방 ID에 해당하는 세션 리스트를 가져온다.
		List<Session> chatroomSessions = chatRooms.getOrDefault(chatroomId, new ArrayList<>());
		
		// 메시지 내용을 콘솔에 출력한다.
		System.out.println(message);
		
		// 해당 채팅방의 모든 세션에 메시지를 전송한다.
		chatroomSessions.forEach(session -> {
            if (session == userSession) {
                return;
            }
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }); 
	}
	
	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose 
	public void handleClose(Session userSession, @PathParam("chatroomId") String chatroomId) {
		// 해당 채팅방 ID에 해당하는 세션 리스트를 가져온다.
        List<Session> chatroomSessions = chatRooms.getOrDefault(chatroomId, new ArrayList<>());
        
        // 해당 채팅방에서 접속 끊은 세션을 제거한다.
        chatroomSessions.remove(userSession);
		
        // 해당 채팅방 ID에 해당하는 세션 리스트를 갱신한다.
        chatRooms.put(chatroomId, chatroomSessions);
        
        // test - 방에 존재하는 유저들
        System.out.println(chatroomId + ": " + chatroomSessions);
		
		// 콘솔에 접속 끊김 로그를 출력한다.
        System.out.println("client is now disconnected from chatroom " + chatroomId);
	}
	
	public void sendSignalToAllClients(List<Session> sessions, String signal) {
	    sessions.forEach(session -> {
	        try {
	            session.getBasicRemote().sendText(signal);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    });
	}
	
}

