package dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dao.ReservationDAO;
import dbconfig.HibernateConfig;
import model.ParkingLot;
import model.Reservation;
public class ReservationDAOImpl implements ReservationDAO{

	@Autowired
	HibernateConfig hibernateConfig;
	
	public boolean addReservation(Reservation r) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(r);
		try {
		session.getTransaction().commit();
		session.close();
		sf.close();
		}catch(Error e) {
			return false;
		}
		return true;
		
	}

	public boolean updateReservation(Reservation r) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update Reservation set Start=:start,"+"End=:end,"+"ParkingLot_name=:name,"+"User_email:=email"+" where Email=:email");
		query.setParameter("start", r.getStart());
		query.setParameter("end", r.getEnd());
		query.setParameter("name", r.getpLot().getLocation());
		query.setParameter("email", r.getUser().getEmail());
		query.executeUpdate();
		try {
		session.getTransaction().commit();
		session.close();
		sf.close();
		}catch(Error e) {
			return false;
		}
		return true;
		
	}
	
	public boolean updateEndDate(String endDate,int idRes) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update Reservation set Ends=:endDate where Reservation_id=:idRes");
		query.setParameter("endDate", endDate);
		query.setParameter("idRes", idRes);
		query.executeUpdate();
		try {
		session.getTransaction().commit();
		session.close();
		sf.close();
		}catch(Error e) {
			return false;
		}
		return true;
		
	}

	public boolean removeReservation(int idRes) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		String id=""+idRes;
		Query q = session.createQuery("delete from Reservation where Reservation_id=:Id");
		q.setParameter("Id",id);
		q.executeUpdate();
		try {
		session.getTransaction().commit();
		session.close();
		sf.close();
		}catch(Error e) {
			return false;
		}
		return true;
	}

	public List<Reservation> userReservations(String uEmail) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Reservation where User_email=:email");
		query.setParameter("email", uEmail);
		List<Reservation> mList=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mList.isEmpty())
			return null;
		return mList;
	}
	
	public List<Reservation> getReservationsFromParkinLot(String location) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Reservation where ParkingLocation=:location");
		System.out.println(location);
		query.setParameter("location", location);
		List<Reservation> mList=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mList.isEmpty())
			return null;
		return mList;
	}
	
	
	public Reservation getReservationsById(int id) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Reservation where Reservation_id=:Id");
		query.setParameter("Id", id);
		List<Reservation> mList=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mList.isEmpty())
			return null;
		return mList.get(0);
	}
	

	public Long getNumberOfReservedSpots(String start, String end) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("select count(*) from Reservation r where (r.start<=:start and r.end<=:end and r.end>=:start)or(r.start>=:start and r.start<=:end and r.end>=:end)or(r.start<=:start and r.end>=:end)");
		query.setParameter("start", start);
		query.setParameter("end", end);
		Long count=(Long)query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return count;
	}

	public void removeExpiredReservation(String Date) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("delete from Reservation where Ends<:date");
		q.setParameter("date",Date);
		q.executeUpdate();
		try {
		session.getTransaction().commit();
		session.close();
		sf.close();
		}catch(Error e) {
		}
	}

	public boolean getReservation(String start, String end, String location, String email) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Reservation where Start=:start and Ends=:end and ParkingLocation=:location and User_email=:email");
		query.setParameter("start", start);
		query.setParameter("end", end);
		query.setParameter("location", location);
		query.setParameter("email", email);
		List<Reservation> mList=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mList.isEmpty())
			return false;
		return true;
	}

}
