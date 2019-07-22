package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;

import dao.ParkingLotDAOImpl;
import dao.ReservationDAOImpl;
import dao.UserDAOImpl;
import model.ParkingLot;
import model.Reservation;
import model.User;
import other.EmailService;
import other.QRCodeGenerator;


@Path("/reservation")
public class ReservationController {
	
	@Autowired
	ParkingLotDAOImpl pl= new ParkingLotDAOImpl();
	
	@Autowired
	ReservationDAOImpl res= new ReservationDAOImpl();
	
	@Autowired
	UserDAOImpl ud= new UserDAOImpl();
	
	@GET
	@Path("getNumberOfReservedSpots/{start}/{end}")
	@Produces(MediaType.TEXT_HTML)
	public String getNumberOfReservedSpots(@PathParam("start") String start,@PathParam("end") String end) {
		Long n=res.getNumberOfReservedSpots(start, end);
		System.out.println(n);
		return Long.toString(n);
	}
	
	
	@DELETE
	@Path("deleteExpired/{date}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteExpiredReservation(@PathParam("date") String date) {
		res.removeExpiredReservation(date);
		return "done";
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteReservation(@PathParam("id") int id) {
		res.removeReservation(id);
		return "done";
	}
	
	@GET
	@Path("getUserReservations/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String getUserResevations(@PathParam("email") String email) {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(res.userReservations(ud.getUsersByEmail(email).get(0).getEmail()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("getReservationsFromParkingLot/{location}")
	@Produces(MediaType.TEXT_HTML)
	public String getResevationsFromParkingLot(@PathParam("location") String location) {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(res.getReservationsFromParkinLot(location));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/*@POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
	@Path("/add/{start}/{end}")
	public String addUser(Reservation res) {
		res.setParkingLotName(this.p.getName());
		res.setUserEmail(this.user.getEmail());
		ReservationDAOImpl rez= new ReservationDAOImpl();
		if(rez.addReservation(res)==false) {
			return "exist";
		}else {
			return "done";
		}
	}*/
	
	@GET
	@Produces({MediaType.TEXT_HTML})
	@Path("/add/{start}/{end}/{email}/{location}")
	public String addUser(@PathParam("start") String start,@PathParam("end") String end,@PathParam("email") String email,@PathParam("location") String location) {
		Reservation res=new Reservation(start,end,ud.getUsersByEmail(email).get(0),pl.getParkingLotByLocation(location));
		ReservationDAOImpl rez= new ReservationDAOImpl();
		if(rez.addReservation(res)==false) {
			return "exist";
		}else {
			return "done";
		}
	}
	
	@GET
	@Path("/update/{start}/{end}/{location}/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String updateUserReservation(@PathParam("start") String start,@PathParam("end") String end,@PathParam("location") String location,@PathParam("email") String email) {
		User user=new User();
		user.setEmail(email);
		Reservation r=new Reservation(start,end,ud.getUserByEmail(user),pl.getParkingLotByLocation(location));
		ReservationDAOImpl rez= new ReservationDAOImpl();
		if(rez.updateReservation(r)==false) {
			return "exist";
		}else {
			return "done";
		}
	}
	
	@GET
	@Path("/verify/{start}/{end}/{location}/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String verifyReservation(@PathParam("start") String start,@PathParam("end") String end,@PathParam("location") String location,@PathParam("email") String email) {
		if(res.getReservation(start, end, location, email)) {
			return "<html>"
					+"<body style=\"margin:0; padding:0;\">"
						+"<div style=\"height:100%; width:100%; display:flex; align-items:center; justify-content:center; background-color:rgba(51, 110, 123, 1)\">"
						+ "<h1 style=\"font-size:74px;\">"+"The ticket is valid"+"</h1>"
						+ "</div>"
					+"<body>"
					+"</html>";
		}else {
			return "<html>"
					+"<body style=\"margin:0; padding:0;\">"
					+"<div style=\"height:100%; width:100%; display:flex; align-items:center; justify-content:center; background-color:rgba(51, 110, 123, 1)\">"
					+ "<h1 style=\"font-size:74px;\">"+"The ticket is invalid"+"</h1>"
					+ "</div>"
				+"<body>"
				+"</html>";
		}
	}
	
	@GET
	@Path("/extendReservation/{end}/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String extendReservation(@PathParam("end") String end,@PathParam("id") int id) {
		String url="http://192.168.1.4:8080/ParkingApp/reservation/verify/";
		if(res.updateEndDate(end,id)==false) {
			return "false";
		}
		EmailService es=new EmailService();
		Reservation r=res.getReservationsById(id);
		url+=r.getStart()+"/"+r.getEnd()+"/"+r.getpLot().getLocation()+"/"+r.getUser().getEmail();
		String userPath="/Users/Norbert/eclipse-workspace-ee/ParkingApp/src/main/resources/"+r.getUser().getEmail()+"/QR.png";
		File code=new File(userPath);
		code.delete();
		try {
			QRCodeGenerator.generateQRCodeImage(url, 350, 350, code.getPath());
			
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			es.sendEmailExtend(r.getUser().getEmail(),userPath,r.getUser(),r.getpLot(),r.getStart(),r.getEnd());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "done";
	}
	
	@GET
	@Path("/sendEmail/{email}/{qrCode}/{location}/{start}/{end}")
	@Produces(MediaType.TEXT_HTML)
	public String sendMail(@PathParam("email") String email,@PathParam("qrCode") String qrCode,@PathParam("location") String location,@PathParam("start") String start,@PathParam("end") String end) {
		//QRCodeGenerator qr=new QRCodeGenerator();
		String[] tokens=qrCode.split("111");
		String url="http://172.20.10.2:8080/ParkingApp/reservation/verify/";
		for(int i=0; i<tokens.length-1; i++) {
			url=url+tokens[i]+"/";
		}
		url+=tokens[tokens.length-1];
		User u=ud.getUsersByEmail(email).get(0);
		ParkingLot p=pl.getParkingLotByLocation(location);
		String defaultPath="/Users/Norbert/eclipse-workspace-ee/ParkingApp/src/main/resources/";
		String userPath=defaultPath+email;
		File newFolder = new File(userPath);
		if(!newFolder.exists()) {
			newFolder.mkdir();
		}
		userPath+="/QR.png";
		File code=new File(userPath);
		if(code.exists()) {
			code.delete();
		}
		try {
			QRCodeGenerator.generateQRCodeImage(url, 350, 350, code.getPath());
			
		} catch (WriterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EmailService es=new EmailService();
		try {
			es.sendEmail(email,userPath,u,p,start,end);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "done";
		
	}

}
