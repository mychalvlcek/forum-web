/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.aos.airline.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Websocket endopoint for counting of active (online) users.
 *
 * @author Jakub Kopřiva <kopriva.jakub@gmail.com>
 */
@ServerEndpoint("/counter")
public class ClientCounterServerEndpoint {
	static Set<Session> endpoints = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Method invocated when a new web socket session is open.
     * @param session Session
     */
    @OnOpen
	public void onOpen(Session session) {
		endpoints.add(session);
		System.out.println("Client connected:\t\t" + session.getId());
		this.sendNewValues();
	}

    /**
     * Method invocated when an existing socket session sends message.
     * @param message String message
     * @param session Session
     * @return String
     */
	@OnMessage
	public String onMessage(String message, Session session) {
        return message;
	}

    /**
     * Method invocated when an existing socket session is closed.
     * @param session Session
     * @param closeReason reason of close
     */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		endpoints.remove(session);
		System.out.println("Client disconnected:\t" + session.getId());
		this.sendNewValues();
	}

	private void sendNewValues() {
		String size = ""+endpoints.size();
		for (Session endpoint : endpoints) {
			endpoint.getAsyncRemote().sendText(size);
		}
	}

}
