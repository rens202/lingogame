package webservices;

import domain.Word;
import domain.Wordlist;
import persistence.WordsDao;
import persistence.WordsDaoImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.util.ArrayList;

@Path("/words")
public class WordsResource {
	private WordsDao wordDao = new WordsDaoImpl();

	@GET
	@Produces("application/json")
	public Response getWordLists(@Context SecurityContext sec) {
			ArrayList<Wordlist> wordLists = wordDao.getWordLists();
			return Response.ok(wordLists).build();
		
	}

}
