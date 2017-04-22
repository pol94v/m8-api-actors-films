package cat.proven.apiexample.resources;

import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import cat.proven.apiexample.dao.DAOFactory;
import cat.proven.apiexample.dao.ActorDAO;
import cat.proven.apiexample.entities.Film;
import cat.proven.apiexample.entities.Actor;
import cat.proven.apiexample.exception.ApplicationException;
import cat.proven.apiexample.service.FilmService;
import cat.proven.apiexample.service.ActorService;


@Path("films")
@Produces("application/json")
public class FilmsResource {

    ActorService actorService;
    FilmService filmService;

    public FilmsResource(@Context ServletContext context) {

        if (context.getAttribute("actorService") != null)
                actorService = (ActorService) context.getAttribute("actorService");
        else {
                actorService = new ActorService();
                context.setAttribute("actorService", actorService);
        }

        if (context.getAttribute("filmService") != null)
                filmService = (FilmService) context.getAttribute("filmService");
        else {
                filmService = new FilmService();
                context.setAttribute("filmService", filmService);
        }
    }

    @GET
    public Response films() {
        Collection<Film> films = filmService.getFilms();
        GenericEntity<Collection<Film>> result = new GenericEntity<Collection<Film>>(films) {
        };
        return Response.ok().entity(result).build();
    }

    @Path("{id}")
    @GET
    public Response getFilmById(@PathParam("id") int id) {
        Film film = filmService.getFilmById(id);
        if (film == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(course).build();
    }

    @POST
    public Response addFilm(@FormParam("any") String any, @FormParam("name") String name) {

        if (any == null || name.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter any is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }
        if (name == null || name.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter name is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }

        Film film = filmService.add>Film(new Film(0, any, name));
        return Response.ok(film).build();
    }

    @Path("{id}")
    @POST
    public Response updateFilm(@FormParam("any") String any, @FormParam("name") String name, @PathParam("id") int id) {

        if (any == null || name.equals("")) {
                ApplicationException ex = new ApplicationException("Parameter any is mandatory");
                return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }
        if (name == null || name.equals("")) {
                ApplicationException ex = new ApplicationException("Parameter name is mandatory");
                return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }

        Film film = filmService.getFilmById(id);

        if (film == null)
                return Response.status(Response.Status.NOT_FOUND).build();

        film.setName(name);
        film = filmService.updateFilm(film);

        return Response.ok(film).build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteFilm(@PathParam("id") int id) {
        Film film = filmService.getFilmById(id);
        if (film == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else {
            filmService.deleteFilm(film);
            return Response.ok(film).build();
        }
    }

    @Path("{id}/actors")
    @GET
    public Response getFilmActors(@PathParam("id") int id) {
        List<Actor> filmActors = (List<Actor>) filmService.getFilmActors(id);

        if (filmActors == null)
                return Response.status(Response.Status.NOT_FOUND).build();
        else {

            GenericEntity<List<Actor>> entity = new GenericEntity<List<Actor>>(filmActors) {
            };

            return Response.ok().entity(entity).build();
        }
    }


    @Path("{id_actor}/films/{id_film}")
    @POST
    public Response addActorToFilm(@PathParam("id_actor") int idActor, @PathParam("id_film") int idFilm) {

        Actor actor = actorService.getActorById(idActor);
        if (actor == null)
                return Response.status(Response.Status.NOT_FOUND).build();

        Film film = filmService.getFilmById(idFilm);
        if (film == null)
                return Response.status(Response.Status.NOT_FOUND).build();

        actor.getfilms().add(film);
        return Response.ok().build();
    }

}
