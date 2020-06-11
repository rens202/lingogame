package webservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import domain.GameService;

@Path("/games")
public class GameResource {
	GameService gameService = new GameService();

	@POST
	@Path("/{wordListId}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response startGame(@Context SecurityContext sec, @PathParam("wordListId") int id) {
		if (sec.isSecure()) {
			String username = sec.getUserPrincipal().getName();
			Boolean response = gameService.startGame(id, username);
			if (response) {
				return Response.ok().build();
			} else {
				return Response.status(422, "Custom error").build();
			}
		} else {
			return Response.status(422, "Custom error").build();
		}
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response postTurn(String jsonString, @Context SecurityContext sec) {
		String username = sec.getUserPrincipal().getName();
		if (username != "Unknown" && sec.isSecure()) {
			System.out.println("Processing Turn!");
			String response = gameService.postTurn(jsonString, username);
			return Response.ok(response).build();
		} else {
			return Response.status(422).build();
		}
	}

	@GET
	@Produces("application/json")
	public Response getTurn(@Context SecurityContext sec) {
		String username = sec.getUserPrincipal().getName();
		if (username != "Unknown" && sec.isSecure()) {
			String response = gameService.getTurn(username);
			return Response.ok(response).build();
		} else {
			return Response.status(422).build();
		}

	}

}
