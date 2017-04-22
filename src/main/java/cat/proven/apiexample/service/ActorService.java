/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.apiexample.service;

import cat.proven.apiexample.dao.DAOFactory;
import cat.proven.apiexample.dao.ActorDAO;
import cat.proven.apiexample.entities.Film;
import cat.proven.apiexample.entities.Actor;

import java.util.Collection;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

public class ActorService {
    
    public ActorDAO actorDAO;
    
    public ActorService () {
        actorDAO = DAOFactory.getInstance().createActorDAO();
    }
    
    public Actor getActorById(int idActor) {
        Actor a = actorDAO.findById(idActor);
        return a;
    }
    
    public Actor addActor(Actor actor) {
        Actor a = actorDAO.add(actor);
        return a;
    }
    
    public Actor updateActor(Actor actor) {
        Actor a = actorDAO.update(actor);
        return a;
    }
    
    public int deleteActor(Actor actor) {
        int result = actorDAO.delete(actor);
        return result;
    }
    
    public Collection<Actor> getActor() { 
        Collection<Actor> actors = actorDAO.findAll();
        return actors;
    }
    
    public Collection<Actor> getActorsByIdFilm(int idFilm) {
        Collection<Actor> actors = actorDAO.findByIdFilm(idFilm);
        return actors;
    }
    
    public Collection<Film> getActorFilms(int idActor) {
        FilmService filmService = new FilmService();
        Collection<Film> films = filmService.getFilmByIdActor(idActor);
        return films;
    }
    
}
