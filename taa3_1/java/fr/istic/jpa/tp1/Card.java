package fr.istic.jpa.tp1;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(name="Card.AllInProgress", query="SELECT c FROM Card c WHERE c.board.name = 'IN PROGRESS'"),
	@NamedQuery(name="Card.AllWithTag", query="SELECT c FROM Card c WHERE :tag MEMBER OF c.tags") 
})
public class Card {
	
	private int id;
	private String label;
	private Date date;
	private String estimation;
	private String user;
	private Collection<Tag> tags;
	private String location;
	private String url;
	private Board board;
	/*
	Un libellé
	La date butoire
	un utilisateur à qui cette tâche est affectée
	Le temps dont vous estimez avoir besoin en minutes
	un ensemble de tags
	un lieu
	une url
	il est également possible d'ajouter une note explicative.
	*/
	public Card() {
		this.tags = new LinkedList<Tag>();
	}
	
	public Card(String label, Board board) {
		this.tags = new LinkedList<Tag>();
		this.label = label;
		this.board = board;
	}

	public Card(String label, Board tab, Tag defaultTag) {
		this.tags = new LinkedList<Tag>();
		this.tags.add(defaultTag);
		this.label = label;
		this.board = board;
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEstimation() {
		return estimation;
	}
	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@ManyToMany
	public Collection<Tag> getTags() {
		return tags;
	}
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@ManyToOne
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}

}
