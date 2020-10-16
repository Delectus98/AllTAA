package fr.istic.jpa.tp1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jpa.EntityManagerHelper;


public class BoardDAO {
	private EntityManager manager;
	
	public BoardDAO()
	{
		this(EntityManagerHelper.getEntityManager());
	}
	
	public BoardDAO(EntityManager manager)
	{
		this.manager = manager;
	}
	
	public Board addBoard(String title)
	{
		Board b = new Board(title);
		manager.persist(b);
		return b;
	}
	
	/*public Board getBoard(String title)
	{
		return manager.createQuery("Select a From Board a Where a.title == :title", Board.class).getSingleResult();
	}*/
	// transations
	public void removeBoard(int id)
	{
		Board board = manager.createQuery("Select a From Board a Where a.id == :id", Board.class).setParameter("id",  id).getSingleResult();
		manager.detach(board);
	}
	
	public Map<String, Board> createDefaultKabanBoards() {	
		int numOfBoard = manager.createQuery("Select a From Board a", Board.class).getResultList().size();
		if (numOfBoard == 0) {
			Map<String, Board> map = new HashMap<String, Board>();
			// pas de tableau donc on les ajoute
			map.put("TODO", addBoard("TODO"));
			map.put("IN PROGRESS", addBoard("IN PROGRESS"));
			map.put("DONE", addBoard("DONE"));
			return map;
		}
		
		return null;
	}
	// request
	public Collection<Board> getBoards()
	{
		return manager.createNamedQuery("Board.All").getResultList();
	}
	
	public Map<String, Board> getBoardsMap()
	{
		Map<String, Board> boards = new HashMap<>();
		for (Board b : getBoards())
		{
			boards.put(b.getName(), b);
		}
		return boards;
	}
}
