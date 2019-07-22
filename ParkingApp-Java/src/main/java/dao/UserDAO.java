package dao;

import java.util.List;

import model.User;

public interface UserDAO {
	
	public void addUser(User u);
	public void updatePasswordUser(User u);
	public void updateUser(User u);
	public List<User> getAllUsers();
	public User getUserByEmail(User u);
	public User getUserByEmailPassword(User u);
	public void removeUserByEmail(String email);
	public List<User> getUsersByEmail(String email);
}
