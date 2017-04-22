package cat.proven.apiexample.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Actor {

	private Integer id;
	private String name;
	private String lloc_naixement;
	private String biografia;
	private List<Film> films;

	public Actor() {
		this.films = new ArrayList<Film>();
	}

	public Actor(int id, String name, String lloc_naixement, String biografia) {
		// Call to default constructor, in this case to fill the films list
		this();
		this.id = id;
		this.name = name;
		this.lloc_naixement = lloc_naixement;
		this.biografia = biografia;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLloc_naixement() {
		return lloc_naixement;
	}

	public void setLloc_naixement(String lloc_naixement) {
		this.lloc_naixement = lloc_naixement;
	}
	
	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

}
