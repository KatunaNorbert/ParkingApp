package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import dbconfig.HibernateConfig;
import model.ParkingLot;
import model.User;

public class UserDAOImpl implements UserDAO{
	
	@Autowired
	HibernateConfig hibernateConfig;
	
	
	
	public void addUser(User u) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}
	
	public List<User> getUsersByEmail(String email) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where Email like concat('%',:email,'%')");
		query.setParameter("email", email);
		List<User> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mLis.isEmpty())
			return null;
		return mLis;
	}

	
	public void updatePasswordUser(User u) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update User set Password=:password" + " where Email=:email");
		query.setParameter("password", u.getPassword());
		query.setParameter("email", u.getEmail());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}
	
	public void updateUser(User u) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("update User set Password=:password,"+"Name=:name,"+"Surname=:surname,"+"Id=:id"+" where Email=:email");
		query.setParameter("password", u.getPassword());
		query.setParameter("email", u.getEmail());
		query.setParameter("name", u.getName());
		query.setParameter("surname", u.getSurname());
		query.setParameter("id", u.getId());
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	
	public List<User> getAllUsers() {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		List<User> mList=session.createQuery("from User").list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		return mList;
	}

	
	public User getUserByEmail(User u) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where Email=:email");
		query.setParameter("email", u.getEmail());
		List<User> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mLis.isEmpty())
			return null;
		return mLis.get(0);
	}
	
	public User getUserByEmailPassword(User u) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("from User where Email=:email and "+"Password=:password");
		query.setParameter("email", u.getEmail());
		query.setParameter("password", u.getPassword());
		List<User> mLis=query.list();
		session.getTransaction().commit();
		session.close();
		sf.close();
		if(mLis.isEmpty())
			return null;
		return mLis.get(0);
	}

	
	public void removeUserByEmail(String email) {
		SessionFactory sf=hibernateConfig.buildSessionFactory();
		Session session = sf.getCurrentSession();
		String uemail =""+email;
		session.beginTransaction();
		Query q = session.createQuery("delete from User where id=:Id");
		q.setParameter("Id", uemail);
		q.executeUpdate();
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

}
