package cat.proven.apiexample.dao;

import cat.proven.apiexample.entities.Film;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import cat.proven.apiexample.entities.Actor;
import java.util.List;

public class ActorDAO {

    private Connection connection;

    public ActorDAO(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public Collection<Actor> findAll() {
        String sql = "SELECT * FROM Actor";
        ArrayList<Actor> list = new ArrayList<Actor>();

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Actor actor = new Actor(rs.getInt("id"), rs.getString("name"), rs.getString("lloc_naixement"), rs.getString("biografia"));
                FilmDAO filmDAO = new FilmDAO(connection);
                actor.setFilms((List<Film>) filmDAO.findByIdActor(actor.getId()));
                
                list.add(actor);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return list;

    }
    
     public Collection<Actor> findByIdFilm(int idFilm) {
            String sql = "SELECT id, name, lloc_naixement, biografia FROM ActorFilm sc INNER JOIN Actor s ON sc.idActor = s.id WHERE idFilm= ?";

            ArrayList<Actor> list = new ArrayList<Actor>();

            try {
                    PreparedStatement statement = getConnection().prepareStatement(sql);
                    statement.setInt(1, idFilm);
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        Actor Actor = new Actor(rs.getInt("id"),rs.getString("name"), rs.getString("lloc_naixement"), rs.getString("biografia"));
                        list.add(actor);
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            }
            return list;

	}

    public Actor findById(int id) {
        String sql = "SELECT * FROM Actor WHERE id= ?";
        Connection conn = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Actor Actor = new Actor(rs.getInt("id"),rs.getString("name"), rs.getString("lloc_naixement"), rs.getString("biografia"));
                FilmDAO filmDAO = new FilmDAO(connection);
                actor.setFilms((List<Film>) filmDAO.findByIdActor(actor.getId()));
                
                return actor;
            }

        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;

    }

    public Actor add(Actor actor) {
        String sql = "INSERT INTO Actor ('id', 'name', 'lloc_naixement', 'biografia') VALUES (NULL,?,?)";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, actor.getName());
            statement.setString(2, actor.getLloc_naixement());
            statement.setString(3, actor.getBiografia());
            int rs = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;

    }

    public Actor update(Actor actor) {
        String sql = "UPDATE Actor SET `name` = ?, `lloc_naixement` = ?,  `biografia` = ? WHERE `Actor`.`id` = ?";

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getLloc_naixement());
            statement.setString(3, student.getBiografia());
            statement.setInt(4, actor.getId());
            int rs = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;

    }

    public int delete(Actor actor) {
        String sql = "DELETE FROM Actor WHERE id = ?";
        int rs = 0;

        try {
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, actor.getId());
            rs = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;

    }


}
