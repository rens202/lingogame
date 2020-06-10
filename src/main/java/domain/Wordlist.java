package domain;

public class Wordlist {

    private int id;
    private String name;
    private Language language;

    public Wordlist(int id, String name, Language language) {
        this.id = id;
        this.name = name;
        this.language = language;
    }

    public Wordlist(int wordlistId) {
		this.id = wordlistId;
	}

	public int getId() {
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
}
