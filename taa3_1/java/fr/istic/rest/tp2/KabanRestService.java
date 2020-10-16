package fr.istic.rest.tp2;

import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.istic.jpa.tp1.Board;
import fr.istic.jpa.tp1.BoardDAO;
import fr.istic.jpa.tp1.Card;
import fr.istic.jpa.tp1.CardDAO;
import jpa.EntityManagerHelper;

@Path("/kabanproject")
@Produces({MediaType.TEXT_HTML})
public class KabanRestService {

	@GET
	@Path("/kaban")
	public Response getKabanBoard() {
		String msg = "";
		
		EntityManager man = EntityManagerHelper.getEntityManager();
		
		BoardDAO bd = new BoardDAO(man);
		
		msg += (
				"<HTML>\n" +
				"<style>\n" +
				"body {background-color: powderblue;}" +
				"h1   {color: blue;}" +
				"div  {border:1px solid #ff0000;}" +
				"</style>" + 
				"<BODY>\n");
		Collection<Board> boards = bd.getBoards();
		
		msg += (
				"<p>Add card to board:</p>" +
				"<FORM Method=\"POST\" Action=\"/kabanproject/kaban\">\n" + 
				"Label :        <INPUT type='text' size='20' name='label'/><BR/>\n" + 
				"Board :        <INPUT type='text' size='2' name='board'/><BR/>\n" + 
				"				<INPUT type='submit' value='Send'/><BR/>\n" +
				"</FORM>\n"
			);
		for (Board b : boards)
		{
			msg += ("<div width='150px' padding='1em' outline-style='dotted' outline-color='black'>");
			msg += ("<h1 width='150px' padding='1em'>" + b.getName() + "</h1>");
			
			for (Card c : b.getCards())
			{
				msg += ("<div width='120px' padding='2em'>");
				msg += ("<h2>" + c.getLabel() + "</h2>");
				msg += ("<p>" + c.getId() + "</p>");
				msg += ("</div>");
			}
			msg += ("</div>");
		}
		
		msg += ("</BODY></HTML>");
		
		EntityManagerHelper.closeEntityManager();
		
		return Response.status(200).entity(msg).build();
	}
	
	@POST
	@Path("/kaban")
	public Response addCardToBoard(
		@FormParam("label") String label,
		@FormParam("board") String board) {
		EntityManager man = EntityManagerHelper.getEntityManager();
		BoardDAO bd = new BoardDAO(man);
		CardDAO cd = new CardDAO(man);

		Map<String, Board> boards = bd.getBoardsMap();
		if (boards.containsKey(board))
		{
			EntityTransaction et = man.getTransaction();
			et.begin();
			cd.addCard(boards.get(board), label);
			et.commit();
		}
		
		EntityManagerHelper.closeEntityManager();

		return getKabanBoard();
	}
}
