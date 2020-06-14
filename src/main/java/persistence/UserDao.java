package persistence;

import domain.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserDao {

	User findByUsername(String username);

	boolean saveNewUser(User user);

	Boolean checkUser(String username, String password);

	User toUser(ResultSet res);
}
