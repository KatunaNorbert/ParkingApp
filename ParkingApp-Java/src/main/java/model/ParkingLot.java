package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ParkingLot")
public class ParkingLot {

	@Column(name="Name")
	private String name;
	
	@Column(name="Town")
	private String town;
	
	@Column(name="Latitude")
	private String latitude;
	
	@Column(name="Longitude")
	private String longitude;
	
	@Column(name="Spot_number")
	private int spot_number;
	
	@Id
	@Column(name="Location")
	private String location;
	
	@Column(name="Price")
	private int price;
	
	@JsonBackReference
	@OneToMany(targetEntity=Reservation.class, mappedBy="pLot", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Reservation> reservations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ParkingLot() {
		this.reservations=new ArrayList<Reservation>();
		
	}
	
	public ParkingLot(String name, String town, String latitude, String longitude, int spot_number, String location,int price) {
		super();
		this.name = name;
		this.town = town;
		this.latitude = latitude;
		this.longitude = longitude;
		this.spot_number = spot_number;
		this.location = location;
		this.price= price;
	}
	
	public void setParkingLot(ParkingLot p) {
		this.name = p.name;
		this.town = p.town;
		this.latitude = p.latitude;
		this.longitude = p.longitude;
		this.spot_number = p.spot_number;
		this.location = p.location;
		this.price= p.price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getSpot_number() {
		return spot_number;
	}

	public void setSpot_number(int spot_number) {
		this.spot_number = spot_number;
	}

}
