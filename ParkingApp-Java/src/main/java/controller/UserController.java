package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;

import dao.UserDAOImpl;
import model.User;
import other.EmailService;
import other.QRCodeGenerator;

@Path("/user")
public class UserController {
	
	
	@GET
	@Path("")
	@Produces(MediaType.TEXT_HTML)
	public String getUsers() {
		ObjectMapper mapper= new ObjectMapper();
		UserDAOImpl ud= new UserDAOImpl();
		String json="";
		try {
			json=mapper.writeValueAsString(ud.getAllUsers());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	/*
	@GET
	@Path("/logStatus")
	@Produces(MediaType.TEXT_HTML)
	public String checkLogStatus() {
		if(this.user.getName()=="") {
			return "false";
		}else {
			return "true";
		}
		
	}*/
	
	@GET
	@Path("/logIn/{email}/{password}")
	@Produces(MediaType.TEXT_HTML)
	public String login(@PathParam("email") String email,@PathParam("password") String password) {
		User u=new User("","",email,password,"","");
		UserDAOImpl ud= new UserDAOImpl();
		if(ud.getUserByEmailPassword(u)==null) {
			return "register";
		}
		u.setUser(ud.getUserByEmail(u));
		if(u.getType().equals("user")) {
			return "user";
		}else {
		return "admin";	
		}
	}
	
	
	/*@GET
	@Path("/logOut")
	@Produces(MediaType.TEXT_HTML)
	public String logOut() {
		User u=new User();
		user.setUser(u);
		return "done";
	}
	*/
	
	@GET
	@Path("getUser/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserByEmail(@PathParam("email") String email) {
		ObjectMapper mapper= new ObjectMapper();
		UserDAOImpl ud= new UserDAOImpl();
		String json="";
		try {
			User u = new User();
			u.setEmail(email);
			json = mapper.writeValueAsString(ud.getUserByEmail(u));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAll() {
		ObjectMapper mapper= new ObjectMapper(); 
		UserDAOImpl ud= new UserDAOImpl();
		String json="";
		try {
			json = mapper.writeValueAsString(ud.getAllUsers());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("getUsersByEmail/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsersByEmail(@PathParam("email") String email) {
		ObjectMapper mapper= new ObjectMapper();
		UserDAOImpl ud= new UserDAOImpl();
		String json="";
		try {
			json = mapper.writeValueAsString(ud.getUsersByEmail(email));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	/*
	@GET
	@Path("/getLoggedUser")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoggedUser() {
		ObjectMapper mapper= new ObjectMapper();
		UserDAOImpl ud= new UserDAOImpl();
		String json="";
		try {
			json = mapper.writeValueAsString(ud.getUserByEmail(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	*/
	
	@DELETE
	@Path("/delete/{email}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteUserByEmail(@PathParam("email") String email) {
		UserDAOImpl ud= new UserDAOImpl();
		try {
		ud.removeUserByEmail(email);
		}catch(Exception e) {
			return "can't find user";
		}
		return "done";
	}
	
	@GET
	@Path("/add/{name}/{surname}/{email}/{password}/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String addUser(@PathParam("name") String name,@PathParam("surname") String surname,@PathParam("email") String email,@PathParam("password") String password,@PathParam("id") String id) {
		User u=new User(name,surname,email,password,"user",id);
		UserDAOImpl ud= new UserDAOImpl();
		if(ud.getUserByEmail(u)!=null) {
			return "exist";
		}
		ud.addUser(u);
		return "done";
	}
	
	@GET
	@Path("admin/add/{name}/{surname}/{email}/{password}/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String adminAddUser(@PathParam("name") String name,@PathParam("surname") String surname,@PathParam("email") String email,@PathParam("password") String password,@PathParam("id") String id) {
		User u=new User(name,surname,email,password,"admin",id);
		UserDAOImpl ud= new UserDAOImpl();
		if(ud.getUserByEmail(u)!=null) {
			return "exist";
		}
		ud.addUser(u);
		return "done";
	}
	
	@GET
	@Path("/update/{email}/{password}")
	@Produces(MediaType.TEXT_HTML)
	public String updateUserPassword(@PathParam("email") String email,@PathParam("password") String password) {
		User u=new User("","",email,password,"user","");
		UserDAOImpl ud= new UserDAOImpl();
		if(ud.getUserByEmail(u)==null) {
			return "Couldn't find user";
		}
		ud.updatePasswordUser(u);
		return "done";
	}
	
	@GET
	@Path("/update/{name}/{surname}/{email}/{password}/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String updateUser(@PathParam("name") String name,@PathParam("surname") String surname,@PathParam("email") String email,@PathParam("password") String password,@PathParam("id") String id) {
		User u=new User(name,surname,email,password,"user",id);
		UserDAOImpl ud= new UserDAOImpl();
		if(ud.getUserByEmail(u)==null) {
				return "can't find user";
			}
		ud.updateUser(u);
		return "done";
	}
}
