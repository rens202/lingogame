package domain;

public class User {
	public User(int id2, String username2, String password2) {
		this.id = id2;
		this.username = username2;
		this.password = password2;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(int userId, String username2) {
		this.id = userId;
		this.username = username2;
	}
	private int id;
	private String username;
	private String password;
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
     public void setPassword(String password) {
        this.password = password;
    }
     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }
     
     public String toString() {
    	 return "username: " + username;
     }


}
