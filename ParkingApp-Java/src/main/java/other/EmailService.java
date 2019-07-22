package other;

import org.springframework.stereotype.Service;

import model.ParkingLot;
import model.User;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


import java.util.Properties;

@Service
public class EmailService {


    public void sendEmail(String toEmail, String qrCodePath, User u, ParkingLot p,String start, String end) throws Exception {
        final String fromEmail = "katunanorbert@gmail.com"; //requires valid gmail id
        final String password = "Escapeplan11"; // correct password for gmail id

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        Email.sendEmail(session, toEmail,"Parking ticket",
        		  "<div><h1>Hello "+u.getName()+"</h2></div>"
        		+ "<div><h2>You have a reservation at <b>"+p.getName()+"</b> with location <b>"+p.getLocation()+"</b>.</h2></div>"
        		+ "<div><h2>Your reservation starts at <b>"+start+"</b> and ends at <b>"+end+"</b>.</h2></div>"
        		+ "<div><h2>Use the QRCode from bellow to acces your parking spot.</h2></div>"
        		+ "<div><img src=\"cid:image\"></div>");
    }
    
    public void sendEmailExtend(String toEmail, String qrCodePath, User u, ParkingLot p,String start, String end) throws Exception {
        final String fromEmail = "katunanorbert@gmail.com"; //requires valid gmail id
        final String password = "Escapeplan11"; // correct password for gmail id

        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        Email.sendEmail(session, toEmail,"Parking ticket",
        		  "<div><h1>Hello "+u.getName()+"</h2></div>"
        		+ "<div><h2>Your reservation at <b>"+p.getName()+"</b> with location <b>"+p.getLocation()+"</b> has been extended!</h2></div>"
        		+ "<div><h2>Your reservation starts at <b>"+start+"</b> and ends at <b>"+end+"</b>.</h2></div>"
        		+ "<div><h2>Use the QRCode from bellow to acces your parking spot.</h2></div>"
        		+ "<div><img src=\"cid:image\"></div>");
    }

}
