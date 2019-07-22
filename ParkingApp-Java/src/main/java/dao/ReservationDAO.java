package dao;

import java.util.List;

import model.Reservation;

public interface ReservationDAO {
	public boolean addReservation(Reservation r);
	public boolean updateReservation(Reservation r);
	public boolean removeReservation(int idRes);
	public List<Reservation> userReservations(String uEmail);
	public Long getNumberOfReservedSpots(String start, String end);
	public void removeExpiredReservation(String date);
	public boolean updateEndDate(String end,int id);
	public boolean getReservation(String start,String end, String location, String email);
	public List<Reservation> getReservationsFromParkinLot(String location);
}
