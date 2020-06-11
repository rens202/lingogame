package persistence;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends PostgresBaseDao implements UserDao {
    
	
    @Override
    public User findByUsername(String username) {
        User result = null;
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Users WHERE username = ?");
            pstmt.setString(1, username);
            ResultSet dbResultSet = pstmt.executeQuery();
            if (dbResultSet.next()) {
            	result = toUser(dbResultSet);
            }
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public User find(int id) {
        User result = null;
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Users WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet dbResultSet = pstmt.executeQuery();
            if (dbResultSet.next()) {
            	result = toUser(dbResultSet);
            }
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }
    
    @Override
    public boolean saveNewUser(User user) {
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Users (username, password) values(?, ?)");
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            int res = pstmt.executeUpdate();
            if (res == 1) {
                return true;
            }
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }
    
    
    private User toUser(ResultSet rs) {
        User user = null;
        try {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");

            user = new User(id, username, password);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return user;
    }

	@Override
	public Boolean checkUser(String username, String password) {
		Boolean result = false;
		try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select exists(select 1 from users where username = ? and password = ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet dbResultSet = pstmt.executeQuery();
            if (dbResultSet.next()) {
            	result = dbResultSet.getBoolean("exists");
            }
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
		return result;
	}
    

 
}
