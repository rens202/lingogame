package persistence;

import domain.User;

import java.util.List;

public interface UserDao {

	User findByUsername(String username);

	User find(int id);

	boolean saveNewUser(User user);

	Boolean checkUser(String username, String password);
}
