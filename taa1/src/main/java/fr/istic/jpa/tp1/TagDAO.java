package fr.istic.jpa.tp1;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hsqldb.rights.User;

import jpa.EntityManagerHelper;

public class TagDAO {
	private EntityManager manager;

	public TagDAO()
	{
		this(EntityManagerHelper.getEntityManager());
	}
	
	public TagDAO(EntityManager manager) {
		this.manager = manager;
	}

	public Tag addTag(String label) {
		Tag tag = new Tag(label);
		manager.persist(tag);
		return tag;
	}
	
	public void deleteTag(int id)
	{
		//TODO: Add parameter id
		Tag selected = manager.createQuery("Select a From Tag a Where a.id == :id", Tag.class).setParameter("id", id).getSingleResult();
		deleteTag(selected);
	}
	
	public void deleteTag(Tag selected)
	{
		Collection<Card> cards = manager.createQuery("Select a From Board a", Card.class).getResultList();
		manager.remove(selected);
		for (Card c : cards) {
		     Collection<Tag> tags = c.getTags();
		     tags.remove(selected);
		     c.setTags(tags);
		}
	}

}
