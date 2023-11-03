package com.test.secu1.common.config;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.secu1.common.vo.ChatVO;

import lombok.extern.slf4j.Slf4j;

@Component
@ServerEndpoint("/chat/{name}")
@Slf4j
public class WebsocketChat {
	private static Map<String, Session> sessionMap = // 데이터를 저장할 때 키(Key)와 밸류(Value)가 짝을 이루어 저장됩니다
			Collections.synchronizedMap(new HashMap<>()); // 데이터 컨테이너(Data Container)
	private static Set<String> names = Collections.synchronizedSet(new HashSet<>()); // HashSet은 순서 없이 요소를 저장하는 클래스.
																						// HashSet은 중복을 허용하지 않고 중복된 값을
																						// 추가할 경우 추가 작업은 실패하며 결과 값으로
																						// False를 반환.
	private ObjectMapper om = new ObjectMapper(); // 텍스트 형태의 JSON을 object로 변경해 주거나 object를 텍스트 형태의 JSON으로 변경해 주는 것을 의미한다

	@OnOpen
	public void open(Session session, EndpointConfig eConfig, @PathParam("name") String name) throws IOException {
		ChatVO chat = new ChatVO();
		if (!names.add(name)) {
			chat.setErrMsg(name + "은 중복된 이름입니다 ");
			sendMsg(session, chat);
			session.close();
			return;
		}
		chat.setMsg(name + "님이 입장하셨습니다");
		Iterator<String> it = sessionMap.keySet().iterator(); // 자바 컬렉션 인터페이스 (쉽게 List, Set 등등으로 이해) 에서는 iterator 인터페이스를
																// 구현한 클래스의 인스턴스를 반환하는
		// iterator() 메소드를 정의하여 각 요소에 접근하도록 합니다.
		while (it.hasNext()) {
			Session targetSession = sessionMap.get(it.next());
			sendMsg(targetSession, chat);
		}
		sessionMap.put(session.getId(), session);
		log.info("open sessionMap=>{}", sessionMap);
	}

	public void sendMsg(Session session, ChatVO chat) throws IOException {
		String json = om.writeValueAsString(chat);
		sendMsg(session, json);
	}

	private void sendMsg(Session session, String msg) throws IOException {
		session.getBasicRemote().sendText(msg); // getBasicRemote() 매서드는 WebSocket의 상대 쪽인 RemoteEndpoint
	}

	@OnMessage
	public void msg(String msg, Session session, @PathParam("name") String name) throws IOException {
		Iterator<String> it = sessionMap.keySet().iterator(); // 자바 컬렉션 인터페이스 (쉽게 List, Set 등등으로 이해) 에서는 iterator 인터페이스를
		ChatVO chat =new ChatVO();
		chat.setName(name);
		chat.setMsg(msg);												// 구현한 클래스의 인스턴스를 반환하는
		// iterator() 메소드를 정의하여 각 요소에 접근하도록 합니다.
		while (it.hasNext()) {
			String key = it.next();
			if (!key.equals(session.getId())) {
				Session targetSession = sessionMap.get(key);
				sendMsg(targetSession,chat);
			}
		}
	}

	@OnClose
	public void close(@PathParam("name") String name, Session session) {
		names.remove(name);
		sessionMap.remove(session.getId());
		log.info("close  sessionMap=>{}", sessionMap);
	}
}
