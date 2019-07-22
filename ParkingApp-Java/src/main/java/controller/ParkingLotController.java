package controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ParkingLotDAOImpl;
import dao.UserDAOImpl;
import model.ParkingLot;
import model.User;

@Path("/parkinglot")
public class ParkingLotController {
	
	ParkingLotDAOImpl pl= new ParkingLotDAOImpl();
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(pl.getAllParkingLots());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getbytown/{town}")
	@Produces(MediaType.TEXT_HTML)
	public String getByTown(@PathParam("town") String town) throws Exception {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(pl.getAllParkingLotsFromCity(town));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getbyLocation/{location}")
	@Produces(MediaType.TEXT_HTML)
	public String getByLocation(@PathParam("location") String location) throws Exception {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(pl.getParkingLotsByLocation(location));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Path("/getPLotbyLocation/{location}")
	@Produces(MediaType.TEXT_HTML)
	public String getPlotByLocation(@PathParam("location") String location) throws Exception {
		ObjectMapper mapper= new ObjectMapper();
		String json="";
		try {
			json=mapper.writeValueAsString(pl.getParkingLotByLocation(location));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	
	@GET
	@Path("/add/{name}/{town}/{latitude}/{longitude}/{nr_spots}/{location}/{price}")
	@Produces(MediaType.TEXT_HTML)
	public String addParkingLot(@PathParam("name") String name,@PathParam("town") String town,@PathParam("latitude") String latitude,@PathParam("longitude") String longitude,@PathParam("nr_spots") int nr_spots,@PathParam("location") String location,@PathParam("price") int price) {
		ParkingLot p=new ParkingLot(name,town,latitude,longitude,nr_spots,location,price);
		if(pl.getParkingLotByLocation(location)!=null) {
			return "ParkingLot already exist";
		}
		pl.addParkingLot(p);;
		return "done";
	}
	
	@GET
	@Path("/delete/{location}")
	@Produces(MediaType.TEXT_HTML)
	public String deleteParkingLotByLocation(@PathParam("location") String location) {
		try {
		pl.removeParkingLotByLocation(location);
		}catch(Exception e) {
			return "can't find ParkingLot";
		}
		return "done";
	}
	
	@GET
	@Path("/update/{name}/{town}/{latitude}/{longitude}/{nr_spots}/{location}/{price}")
	@Produces(MediaType.TEXT_HTML)
	public String updateParkingLot(@PathParam("name") String name,@PathParam("town") String town,@PathParam("latitude") String latitude,@PathParam("longitude") String longitude,@PathParam("nr_spots") int nr_spots,@PathParam("location") String location,@PathParam("price") int price) {
		ParkingLot p=new ParkingLot(name,town,latitude,longitude,nr_spots,location,price);
		if(pl.getParkingLotByLocation(location)==null) {
			return "ParkingLot already exist";
		}
		pl.updateParkingLot(p);
		return "done";
	}
	
	@GET
	@Path("/update/{nr_spots}/{location}")
	@Produces(MediaType.TEXT_HTML)
	public String updateReservedSpots(@PathParam("nr_spots") int nr_spots,@PathParam("location") String location) {
		if(pl.getParkingLotByLocation(location)!=null) {
			return "ParkingLot already exist";
		}
		pl.updateReservedSpots(nr_spots, location);;
		return "done";
	}
}