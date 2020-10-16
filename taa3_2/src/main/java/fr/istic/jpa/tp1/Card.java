package fr.istic.jpa.tp1;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Card")
public class Card implements Serializable {
	
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
	@XmlElement(name ="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement(name = "label")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	@XmlElement(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@XmlElement(name = "estimation")
	public String getEstimation() {
		return estimation;
	}
	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}
	@XmlElement(name = "user")
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@ManyToMany
	@XmlElementWrapper(name = "tags")
	@XmlElement(name = "tag")
	public Collection<Tag> getTags() {
		return tags;
	}
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	@XmlElement(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@XmlElement(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@ManyToOne
	@XmlElement(name = "board")
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}

}
