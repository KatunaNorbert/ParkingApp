package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import model.Reservation;

@Entity
@Table(name="User")
public class User {
	
	
	@Column(name="Name")
	private String name;

	@Column(name="Surname")
	private String surname;
	
	@Id
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Type")
	private String type;
	
	@Column(name="ID")
	private String id;
	
	@JsonBackReference
	@OneToMany(targetEntity=Reservation.class, mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Reservation> reservations;


	public User() {
		this.name ="";
		this.surname = "";
		this.email = "";
		this.password = "";
		this.type ="";
		this.id ="";
		this.reservations=new ArrayList<Reservation>();
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setUser(User u) {
		this.name = u.name;
		this.surname = u.surname;
		this.email = u.email;
		this.password = u.password;
		this.type = u.type;
		this.id = u.id;
	}
	
	public User(String name, String surname, String email, String password, String type, String id) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.type = type;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
