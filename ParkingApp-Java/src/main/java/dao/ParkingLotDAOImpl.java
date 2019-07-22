package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dbconfig.HibernateConfig;
import model.ParkingLot;
import model.User;

public class ParkingLotDAOImpl implements ParkingLotDAO{
	
	@Autowired
	HibernateConfig hibernateConfig;

	public void addParkingLot(ParkingLot p) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public void updateReservedSpots(int nr_spots, String location) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update User set Spot_number=:nr_spots" + "where Location=:location");
		query.setParameter("nr_spots", nr_spots);
		query.setParameter("location", location);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public void updateParkingLot(ParkingLot p) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update ParkingLot set Name=:name,"+"Town=:town,"+"Latitude=:latitude,"+"Longitude=:longitude,"+"Spot_number=:spot_nr,"+"Location=:location,"+"Price=:price"+" where Location=:location");
		query.setParameter("name", p.getName());
		query.setParameter("town", p.getTown());
		query.setParameter("latitude", p.getLatitude());
		query.setParameter("longitude", p.getLongitude());
		query.setParameter("spot_nr", p.getSpot_number());
		query.setParameter("location", p.getLocation());
		query.setParameter("price", p.getPrice());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	public List<ParkingLot> getAllParkingLotsFromCity(String town) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from ParkingLot where Town like concat('%',:town,'%')");
		query.setParameter("town", town);
		List<ParkingLot> mList=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mList.isEmpty())
			return null;
		return mList;
	}
	
	public List<ParkingLot> getAllParkingLots() {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from ParkingLot");
		List<ParkingLot> mList=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mList.isEmpty())
			return null;
		return mList;
	}

	public List<ParkingLot> getParkingLotsByLocation(String location) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from ParkingLot where Location like concat('%',:location,'%')");
		query.setParameter("location", location);
		List<ParkingLot> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mLis.isEmpty())
			return null;
		return mLis;
	}
	
	public ParkingLot getParkingLotByLocation(String location) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from ParkingLot where Location=:location");
		query.setParameter("location", location);
		List<ParkingLot> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mLis.isEmpty())
			return null;
		return mLis.get(0);
	}

	public void removeParkingLotByLocation(String location) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("delete from ParkingLot where Location=:location");
		q.setParameter("location", location);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

}
