package fr.istic.rest.tp2;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.istic.jpa.tp1.Board;
import fr.istic.jpa.tp1.BoardDAO;
import fr.istic.jpa.tp1.Card;
import fr.istic.jpa.tp1.CardDAO;
import fr.istic.jpa.tp1.Tag;
import fr.istic.jpa.tp1.TagDAO;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Component
@Path("/kabanproject")
@Produces({MediaType.TEXT_HTML})
public class KabanRestService {
	@Autowired
	private AbstractJpaDao<Long, Board> bd;
	@Autowired
	private AbstractJpaDao<Long, Card> cd;
	@Autowired
	private AbstractJpaDao<Long, Tag> td;

	@GET
	@Path("/kaban")
	public List<Board> getKabanBoards()
	{
		List<Board> boards = bd.findAll();
		return boards;
	}
	
	@POST
	@Path("/kaban")
	public Response addCardToBoard(
		@Parameter(description="Card that will be added to board", required=true) Card card,
		@PathParam("board") String board) {

		Optional<Board> foundBoard = bd.findAll().stream().filter(b -> b.getName().equals(board)).findAny();

		if (foundBoard.isPresent())
		{
			cd.save(card);
			return Response.status(Status.NOT_FOUND).entity("FAILED TO INSERT CARD TO BOARD").build();
		}
		
		return Response.ok().entity("SUCCESS").build();
	}
	
	@DELETE
	@Path("/kaban/{cardId}")
	public Response removeFromBoard(@PathParam("cardId") Long cardId)
	{
		cd.deleteById(cardId);
		return Response.ok().entity("SUCCESS").build();
	}
}
