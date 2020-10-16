package fr.istic.jpa.tp1;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import jpa.EntityManagerHelper;

public class CardDAO {
	private EntityManager manager;

	public CardDAO()
	{
		this(EntityManagerHelper.getEntityManager());
	}
	
	public CardDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	public Card addCard(Board board, String title)
	{
		Card card = new Card();
		card.setBoard(board);
		card.setLabel(title);
		manager.persist(card);
		return card;
	}
	
	public void removeCard(Card c)
	{
		manager.detach(c);
	}
	
	public void addTagToCard(Card c, Tag tag)
	{
		Collection<Tag> tags = c.getTags();
		tags.add(tag);
		c.setTags(tags);
		manager.persist(c);
	}
	
	public void removeTagFromCard(Card c, Tag tag)
	{
		Collection<Tag> tags = c.getTags();
		tags.remove(tag);
		c.setTags(tags);
		manager.persist(c);
	}
	
	
	public void moveCard(Card selected, Board b)
	{
		//Board prev = selected.getBoard();
		selected.setBoard(b);
		manager.persist(selected);
	}
}
