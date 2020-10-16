package fr.istic.jpa.tp1;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Tag")
public class Tag implements Serializable {
	private int id;
	private String title;
	private Collection<Card> fiches;
	
	public Tag() {
		
	}
	
	public Tag(String title)
	{
		this.title = title;
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
	@XmlElement(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToMany(mappedBy="tags")
	@XmlElementWrapper(name = "cards")
	@XmlElement(name = "card")
	public Collection<Card> getFiches() {
		return fiches;
	}

	public void setFiches(Collection<Card> fiches) {
		this.fiches = fiches;
	}
}
