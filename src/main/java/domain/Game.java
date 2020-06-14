package domain;

import java.sql.Timestamp;

public class Game {
	private int id;
	private Word word;
	private int status; //0 = new, 1 = in progress, 2 = done
	private int turn;
	private User user;
	
	
	public Game(Word word, int status,  int turn, User user) {
		this.word = word;
		this.status = status;
		this.turn = turn;
		this.user = user;
	}
	public Game(Word word, int status, int turn, User user, int id) {
		this.word = word;
		this.status = status;
		this.turn = turn;
		this.user = user;
		this.id = id;
	}


	public void setId(int int1) {
		this.id = int1;
		
	}


	public int getId() {
		return this.id;
	}


	public Word getWord() {
		return this.word;
	}


	public int getTurn() {
		return this.turn;
	}


	public int getStatus() {
		return this.status;
	}


	public User getUser() {
		return this.user;
	}
	public void addTurn() {
		this.turn = this.turn + 1;
		
	}
	public void setStatus(int i) {
		this.status = i;
		
	}

	
	

}
