package fr.istic.jpa.tp1;

import java.util.HashMap;
import java.util.Map;

import fr.istic.taa.jaxrs.dao.generic.*;

public class BoardDAO extends AbstractJpaDao<Long, Board>{	
	
	/*public void createDefaultKabanBoards() {	
		Board todo = new Board();
		todo.setName("TODO");
		Board progress = new Board();
		progress.setName("IN PROGRESS");
		Board done = new Board();
		done.setName("DONE");
		this.save(todo);
		this.save(progress);
		this.save(done);
	}*/
	
	public Map<String, Board> createDefaultKabanBoards() {	
		int numOfBoard = this.entityManager.createQuery("Select a From Board a", Board.class).getResultList().size();
		if (numOfBoard == 0) {
			Board todo = new Board();
			todo.setName("TODO");
			Board progress = new Board();
			progress.setName("IN PROGRESS");
			Board done = new Board();
			done.setName("DONE");
			this.save(todo);
			this.save(progress);
			this.save(done);
			Map<String, Board> map = new HashMap<String, Board>();
			// pas de tableau donc on les ajoute
			map.put("TODO", todo);
			map.put("IN PROGRESS", progress);
			map.put("DONE", done);
			return map;
		}
		
		return null;
	}
}
