package dao;

import java.util.List;

import model.ParkingLot;

public interface ParkingLotDAO {
	public void addParkingLot(ParkingLot p);
	public void updateReservedSpots(int nr_spots, String location);
	public void updateParkingLot(ParkingLot p);
	public List<ParkingLot> getAllParkingLotsFromCity(String town);
	public List<ParkingLot> getAllParkingLots();
	public List<ParkingLot> getParkingLotsByLocation(String location);
	public ParkingLot getParkingLotByLocation(String location);
	public void removeParkingLotByLocation(String location);
}
