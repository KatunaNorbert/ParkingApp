package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.enterprise.context.Dependent;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;

import dao.ReservationDAOImpl;
import other.Message;

@Dependent
@ServerEndpoint(value = "/atpendpoint")
public class ATPWSEndpoint extends TimerTask{
	private static final Logger LOG = Logger.getLogger(ATPWSEndpoint.class.getName());
    private static Set<Session> session = Collections.synchronizedSet(new HashSet<Session>());
    private static Session s;
    
    @Autowired
	ReservationDAOImpl res= new ReservationDAOImpl();
    
    @OnMessage
    public void onMessage(String message,Session session) {
    	this.s=session;
        try {
        	for(Session s:this.session) {
        		s.getBasicRemote().sendText(message);
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnOpen
    public void onOpen(Session session) {
    	this.session.add(session);
        LOG.info("Connection opened ...");
        Timer timer=new Timer();
        timer.schedule(this,0,600000);
        
        Message message = new Message();
        message.setMessage("Welcome " + session.getId());
        System.out.println("Welcome " + session.getId());
        session.getAsyncRemote().sendObject(message);
      
    }
    @OnClose
    public void onClose(Session peer) {
        LOG.info("Connection closed ...");
        session.remove(peer);
    }
	@Override
	public void run() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm:ss");
		Date date = new Date();
		res.removeExpiredReservation(dateFormat.format(date));
		this.onMessage("timer", s);
	}
    
    



}
