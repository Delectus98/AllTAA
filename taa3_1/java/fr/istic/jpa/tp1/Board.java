package fr.istic.jpa.tp1;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name="Board.All", query="SELECT c FROM Board c"),
})
public class Board {
	private int id;
	@Column(unique=true)
	private String name;
	private Collection<Card> fiches;
	
	public Board() {
		
	}
	
	public Board(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// en supprimant une table on supprime automatiquement les cartes
	@OneToMany(mappedBy = "board", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval=true)
	public Collection<Card> getCards() {
		return fiches;
	}
	public void setCards(Collection<Card> fiches) {
		this.fiches = fiches;
	}
	
	
}
