package fr.istic.jpa.tp1;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	private int id;
	private String title;
	private Collection<Card> fiches;
	
	public Tag() {
		
	}
	
	public Tag(String title) {
		this.title = title;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToMany(mappedBy="tags")
	public Collection<Card> getFiches() {
		return fiches;
	}

	public void setFiches(Collection<Card> fiches) {
		this.fiches = fiches;
	}
}
