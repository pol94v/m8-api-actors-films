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


@Path("actors")
@Produces("application/json")
public class ActorsResource {

    ActorService actorService;
    FilmService filmService;

    public ActorsResource(@Context ServletContext context) {

        if (context.getAttribute("actorService") != null)
                actorService = (ActorService) context.getAttribute("actorService");
        else {
                actorService = new ActorService();
                context.setAttribute("actorService", actorService);
        }

        if (context.getAttribute("filmService") != null)
                courseService = (FilmService) context.getAttribute("filmService");
        else {
                filmService = new FilmService();
                context.setAttribute("filmService", filmService);
        }

    }

    @GET
    public Response actors() {
        Collection<Actor> actors = actorService.getActors();
        GenericEntity<Collection<Actor>> result = new GenericEntity<Collection<Actor>>(actors) {
        };
        return Response.ok().entity(result).build();
    }

    @Path("{id}")
    @GET
    public Response getActorById(@PathParam("id") int id) {
        Actor actor = actorService.getActorById(id);
        if (actor == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.ok(actor).build();
    }

    @POST
    public Response addActor(@FormParam("name") String name, @FormParam("lloc_naixement") String lloc_naixement, @FormParam("biografia") String biografia) {

        if (name == null || name.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter name is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }

        if (lloc_naixement == null || lloc_naixement.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter lloc_naixement is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }
        
        if (biografia == null || biografia.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter biografia is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }

        Actor actor = actorService.addActor(new Actor(0, name, lloc_naixement, biografia));
        return Response.ok(actor).build();
    }

    @Path("{id}")
    @POST
    public Response updateActor(@FormParam("name") String name, @FormParam("lloc_naixement") String lloc_naixement, @FormParam("biografia") String biografia) {

        if (name == null || name.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter name is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }

        if (lloc_naixement == null || lloc_naixement.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter lloc_naixement is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }
        
        if (biografia == null || biografia.equals("")) {
            ApplicationException ex = new ApplicationException("Parameter biografia is mandatory");
            return Response.status(Response.Status.BAD_REQUEST).entity(ex).build();
        }

        Actor actor = actorService.getActorById(id);

        if (actor == null)
                return Response.status(Response.Status.NOT_FOUND).build();

        actor.setName(name);
        actor.setLloc_naixement(lloc_naixement);
        actor.setBiografia(biografia);
        actor = actorService.updateActor(actor);

        return Response.ok(actor).build();

    }

    @DELETE
    @Path("{id}")
    public Response deleteActor(@PathParam("id") int id) {
        Actor actor = actorService.getActorById(id);
        if (actor == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        else {
            actorService.deleteActor(actor);
            return Response.ok(actor).build();
        }
    }

    @Path("{id}/films")
    @GET
    public Response getActorFilms(@PathParam("id") int id) {
        List<Film> actorFilms = (List<Film>) actorService.getActorFilms(id);

        if (actorFilms == null)
                return Response.status(Response.Status.NOT_FOUND).build();
        else {

            GenericEntity<List<Film>> entity = new GenericEntity<List<Film>>(actorFilms) {
            };

            return Response.ok().entity(entity).build();
        }
    }


    @Path("{id_actor}/films/{id_film}")
    @POST
    public Response addActorToFilm(@PathParam("id_actor") int idActor, @PathParam("id_film") int idFilms) {

        Actor actor = actorService.getActorById(idActor);
        if (actor == null)
                return Response.status(Response.Status.NOT_FOUND).build();

        Film film = filmService.getFilmById(idFilm);
        if (film == null)
                return Response.status(Response.Status.NOT_FOUND).build();

        student.getFilms().add(film);
        return Response.ok().build();
    }

}
