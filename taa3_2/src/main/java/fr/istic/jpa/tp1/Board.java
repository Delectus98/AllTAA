package fr.istic.jpa.tp1;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Board")
public class Board implements Serializable {
	private int id;
	private String name;
	private Collection<Card> fiches;
	
	public Board() {
		
	}
	
	public Board(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	@XmlElement(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "board", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	@XmlElementWrapper(name = "cards")
	@XmlElement(name = "card")
	public Collection<Card> getCards() {
		return fiches;
	}
	
	public void setCards(Collection<Card> fiches) {
		this.fiches = fiches;
	}
	
	
}
