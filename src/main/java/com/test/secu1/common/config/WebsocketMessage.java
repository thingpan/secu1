package com.test.secu1.common.config;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.test.secu1.common.vo.ChatVO;

import lombok.extern.slf4j.Slf4j;

@Component
@ServerEndpoint("/msg")
@Slf4j
public class WebsocketMessage {
	private static Map<String ,Session> sessionMap =
			Collections.synchronizedMap(new HashMap<>());
	@OnOpen 
	public void open(Session session) {
		sessionMap.put(session.getId(), session);
		log.info("open sessionMap=>{}",sessionMap);
	}
	@OnMessage
	public void msg(String msg, Session session) throws IOException {
		Iterator<String> it =sessionMap.keySet().iterator();
	
		while(it.hasNext()){
		String key= it.next();
		if(!key.equals(session.getId())) {
			Session targetSession =sessionMap.get(key);
			targetSession.getBasicRemote().sendText(msg);
		}
		}
	}
	
	@OnClose
	public void close(Session session) {
		sessionMap.remove(session.getId());
		log.info("close  sessionMap=>{}",sessionMap);
	}
}
