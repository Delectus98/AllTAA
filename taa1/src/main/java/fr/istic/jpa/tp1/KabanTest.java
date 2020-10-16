package fr.istic.jpa.tp1;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class KabanTest {
	private EntityManager manager;
	private BoardDAO bd;
	private CardDAO cd;
	private TagDAO td;

	public KabanTest(EntityManager manager) {
		this.manager = manager;
		bd = new BoardDAO(manager);
		cd = new CardDAO(manager);
		td = new TagDAO(manager);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = factory.createEntityManager();
		KabanTest test = new KabanTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createTableauxWithDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listTableaux();
		test.listFiches();
		test.listTags();
		
		List<Card> InProgressCards = manager.createNamedQuery("Card.AllInProgress").getResultList();
		for (Card c : InProgressCards)
		{
			System.out.println("In progress:" + c.getLabel());
		}

		manager.close();
		System.out.println(".. done");
	}

	private void createTableaux() {
		int numOfEmployees = manager.createQuery("Select a From Board a", Board.class).getResultList().size();
		if (numOfEmployees == 0) {
			// on créé des tags de base
			Tag research = new Tag("research");
			Tag dev = new Tag("dev");
			manager.persist(research);
			manager.persist(dev);
			// pas de tableau donc on les ajoute
			Board todo = new Board("TODO");
			Board progress = new Board("IN PROGRESS");
			Board done = new Board("DONE");
			
			Board deleted = new Board("DELETED");
			manager.persist(todo);
			manager.persist(progress);
			manager.persist(done);
			manager.persist(deleted);
			// on créé des fiches de base dummy
			Card f1 = new Card("Buy something", todo, research);
			Card f2 = new Card("Search something", done, research);
			Card f3 = new Card("Code something", progress, dev);
			
			Card f3d = new Card("Will be deleted", deleted);

			manager.persist(f1);
			manager.persist(f2);
			manager.persist(f3);
			manager.persist(f3d);
		}
	}
	
	private void createTableauxWithDAO() {
		int numOfEmployees = manager.createQuery("Select a From Board a", Board.class).getResultList().size();
		if (numOfEmployees == 0) {
			// on créé des tags de base
			Map<String, Board> boards = bd.createDefaultKabanBoards();			
			Card c1 = cd.addCard(boards.get("TODO"), "Eat candy!");
			Card c2 = cd.addCard(boards.get("TODO"), "Give candy!");
			cd.addCard(boards.get("IN PROGRESS"), "Buy candy!");
			cd.addCard(boards.get("DONE"), "Go for candy!");
			
			Tag t1 = td.addTag("UNHEALTHY");
			Tag t2 = td.addTag("GOOD BEHAVIOUR");

			cd.addTagToCard(c1, t1);
			cd.addTagToCard(c2, t1);
			cd.addTagToCard(c2, t2);
		}
	}

	private void listFiches() {
		List<Card> resultList = manager.createQuery("Select a From Card a", Card.class).getResultList();
		System.out.println("num of cards:" + resultList.size());
		for (Card next : resultList) {
			System.out.println("next card: " + next.getLabel());
		}
	}

	private void listTableaux() {
		List<Board> resultList = manager.createQuery("Select a From Board a", Board.class).getResultList();
		System.out.println("num of boards:" + resultList.size());
		for (Board next : resultList) {
			System.out.println("next board: " + next.getName());
		}
	}

	private void listTags() {
		List<Tag> resultList = manager.createQuery("Select a From Tag a", Tag.class).getResultList();
		System.out.println("num of tags:" + resultList.size());
		for (Tag next : resultList) {
			System.out.println("next tag: " + next.getTitle());
		}
	}

	// private List<Fiche> get

}
