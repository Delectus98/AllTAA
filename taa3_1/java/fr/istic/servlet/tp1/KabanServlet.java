package fr.istic.servlet.tp1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.istic.jpa.tp1.Board;
import fr.istic.jpa.tp1.BoardDAO;
import fr.istic.jpa.tp1.Card;
import fr.istic.jpa.tp1.CardDAO;
import jpa.EntityManagerHelper;

@WebServlet(name="kabanservlet", urlPatterns={"/kaban"})
public class KabanServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		EntityManager man = EntityManagerHelper.getEntityManager();
		
		BoardDAO bd = new BoardDAO(man);
		
		out.println(
				"<HTML>\n" +
				"<style>\n" +
				"body {background-color: powderblue;}" +
				"h1   {color: blue;}" +
				"div  {border:1px solid #ff0000;}" +
				"</style>" + 
				"<BODY>\n");
		Collection<Board> boards = bd.getBoards();
		
		out.println(
				"<p>Add card to board:</p>" +
				"<FORM Method=\"POST\" Action=\"/kaban\">\n" + 
				"Label :        <INPUT type='text' size='20' name='label'><BR>\n" + 
				"Board :        <INPUT type='text' size='2' name='board'><BR>\n" + 
				"				<INPUT type=submit value=Send>\n" +
				"</FORM>\n"
			);
		for (Board b : boards)
		{
			out.println("<div width=150px padding=1em outline-style=dotted outline-color=black>");
			out.println("<h1 width=150px padding=1em>" + b.getName() + "</h1>");
			
			for (Card c : b.getCards())
			{
				out.println("<div width=120px padding=2em>");
				out.println("<h2>" + c.getLabel() + "</h2>");
				out.println("<p>" + c.getId() + "</p>");
				out.println("</div>");
			}
			out.println("</div>");
		}
		
		out.println("</BODY></HTML>");
		
		EntityManagerHelper.closeEntityManager();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		EntityManager man = EntityManagerHelper.getEntityManager();
		BoardDAO bd = new BoardDAO(man);
		CardDAO cd = new CardDAO(man);
		PrintWriter out = response.getWriter();

		Map<String, Board> boards = bd.getBoardsMap();
		if (boards.containsKey(request.getParameter("board")))
		{
			out.println("<h3 font-color=green>Transaction done!</h3>");
			EntityTransaction et = man.getTransaction();
			et.begin();
			cd.addCard(boards.get(request.getParameter("board")), request.getParameter("label"));
			et.commit();
		}
		else
		{
			out.println("<h3 font-color=red>Transaction failed (no match)!</h3>");
		}
		EntityManagerHelper.closeEntityManager();

		doGet(request, response);
	}
	
}
