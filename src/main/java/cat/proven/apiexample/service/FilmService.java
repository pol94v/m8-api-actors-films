/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.apiexample.service;

import cat.proven.apiexample.dao.FilmDAO;
import cat.proven.apiexample.dao.DAOFactory;

import cat.proven.apiexample.entities.Film;
import cat.proven.apiexample.entities.Actor;

import java.util.Collection;

public class FilmService {
    
    public FilmDAO filmDAO;
    
    public FilmService () {
        filmDAO = DAOFactory.getInstance().createFilmDAO();
    }
    
    public Film getFilmById(int idFilm) {
        Film f = filmDAO.findById(idFilm);
        return f;
    }
   
    public Film addFilm(Film film) {
        Film f = filmDAO.add(film);
        return f;
    }
    
    public Film updateFilm(Film film) {
        Film f = filmDAO.update(film);
        return f;
    }
    
    public int deleteFilm(Film film) {
        int result = filmDAO.film(film);
        return result;
    }
    
    public Collection<Film> getFilms() { 
        Collection<Film> films = filmDAO.findAll();
        return films;
    }

    public Collection<Film> getFilmsByIdActor(int idActor) {
        Collection<Film> films = filmDAO.findByIdActor(idActor);
        return films;
    }
    
    public Collection<Actor> getFilmActors(int idFilm) {
        ActorService actorService = new ActorService();
        Collection<Actor> actors = actorService.getActorsByIdFilm(idFilm);
        return actors;
    }
    
}
