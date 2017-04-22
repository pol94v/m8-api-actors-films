package cat.proven.apiexample.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pol94v
 */
@XmlRootElement
public class Film {
    private int id;
    private String any;
    private String name;
    private List<Actor> actors;

    public Film() {
        this.actors = new ArrayList<Actor>();
    }

    public Film(int id, String any, String name) {
        this();
        this.id = id;
        this.any = any;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAny() {
        return any;
    }

    public void setAny(String any) {
        this.any = any;
    }
    
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Actor> getActors() {
            return actors;
    }

    public void setFilms(List<Actor> actors) {
            this.actors = actors;
    }
    
}
