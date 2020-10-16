package fr.istic.jpa.tp1;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import fr.istic.taa.jaxrs.dao.generic.EntityManagerHelper;

public class KabanTest {
	private EntityManager manager;

	public KabanTest(EntityManager manager) {
		this.manager = manager;
	}

	public static void main(String[] args) {
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
		EntityManager manager = EntityManagerHelper.getEntityManager();
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

		manager.close();
		System.out.println(".. done");
	}
	
	private void createTableauxWithDAO() {
		BoardDAO bd = new BoardDAO();
		CardDAO cd = new CardDAO();
		TagDAO td = new TagDAO();
		int numOfEmployees = manager.createQuery("Select a From Board a", Board.class).getResultList().size();
		if (numOfEmployees == 0) {
			// on créé des tags de base
			Map<String, Board> boards = bd.createDefaultKabanBoards();
			Card c1;
			cd.save(c1 = new Card("Eat candy!", boards.get("TODO")));
			Card c2;
			cd.save(c2 = new Card("Eat candy!", boards.get("TODO")));
			cd.save(new Card("Buy candy!", boards.get("IN PROGRESS")));
			cd.save(new Card("Go for candy!", boards.get("DONE")));
			
			Tag t1;
			td.save(t1 = new Tag("UNHEALTHY"));
			Tag t2; 
			td.save(t2 = new Tag("GOOD BEHAVIOUR"));

			cd.addTagToCard(c1, t1);
			cd.addTagToCard(c2, t1);
			cd.addTagToCard(c2, t2);
		}
	}

	private void criteriaQuery() {
		CriteriaBuilder cb = manager.getCriteriaBuilder();

		CriteriaQuery<Card> q = cb.createQuery(Card.class);
		Root<Card> c = q.from(Card.class);
		ParameterExpression<Integer> p = cb.parameter(Integer.class);
		q.select(c).where(cb.gt(c.get("population"), p));
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
