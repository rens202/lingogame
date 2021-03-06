package webservices;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import domain.Score;
import persistence.ScoreDao;
import persistence.ScoreDaoImpl;

@Path("/scores")
public class ScoreResource {
	ScoreDao scoreDao = new ScoreDaoImpl();
	
	@GET
	@Produces("application/json")
	@Path("/{wordListId}")
	public Response getScores(@PathParam("wordListId") int id){
		ArrayList<Score> result = scoreDao.getScores(id);
		return Response.ok(result).build();
	}
	
	@GET
	@Produces("application/json")
	public Response getAllScores(){
		ArrayList<Score> result = scoreDao.getScores();
		return Response.ok(result).build();
	}
	

}
