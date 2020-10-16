package fr.istic.jpa.tp1;

import java.util.Collection;

import org.springframework.stereotype.Component;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;

@Component//@Repository
public class CardDAO extends AbstractJpaDao<Long, Card>{
	public CardDAO()
	{
	}
	
	public void addTagToCard(Card c, Tag tag)
	{
		Collection<Tag> tags = c.getTags();
		tags.add(tag);
		c.setTags(tags);
		entityManager.persist(c);
	}
	
	public void removeTagFromCard(Card c, Tag tag)
	{
		Collection<Tag> tags = c.getTags();
		tags.remove(tag);
		c.setTags(tags);
		entityManager.persist(c);
	}
	
	
	public void moveCard(Card selected, Board b)
	{
		//Board prev = selected.getBoard();
		selected.setBoard(b);
		entityManager.persist(selected);
	}
}
