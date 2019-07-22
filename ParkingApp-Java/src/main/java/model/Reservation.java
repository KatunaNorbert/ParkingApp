package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
	@Column(name="Reservation_id")
	private int id;
	
	public Reservation() {
		this.id=0;
		this.start = "";
		this.end = "";
		this.pLot= new ParkingLot();
		this.user = new User();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Reservation(String start, String end, User u, ParkingLot p) {
		super();
		this.start = start;
		this.end = end;
		this.pLot = p;
		this.user = u;
	}
	
	public void setReservation(String start, String end, User u, ParkingLot p) {
		this.start = start;
		this.end = end;
		this.pLot = p;
		this.user = u;
	}

	@Column(name="Start")
	private String start;
	
	@Column(name="Ends")
	private String end;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="ParkingLocation")
	private ParkingLot pLot;
	
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="User_email")
	private User user;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public ParkingLot getpLot() {
		return pLot;
	}

	public void setParkingLotName(ParkingLot parkingLot) {
		pLot = parkingLot;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
