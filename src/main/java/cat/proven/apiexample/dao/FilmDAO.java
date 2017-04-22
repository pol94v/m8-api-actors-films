/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.apiexample.dao;

import cat.proven.apiexample.entities.Film;
import cat.proven.apiexample.entities.Actor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author pol94v
 */
public class FilmDAO {
    private Connection connection;

	public FilmDAO(Connection connection) {
		this.connection = connection;
	}
        
        public Connection getConnection() {
		return connection;
	}

	public Collection<Film> findAll() {
            String sql = "SELECT * FROM Film";
            ArrayList<Film> list = new ArrayList<Film>();

            try {
                    PreparedStatement statement = getConnection().prepareStatement(sql);
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                        Film film = new Film(rs.getInt("id"),rs.getInt("any"), rs.getString("name"));
                        list.add(film);
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            }
            return list;

	}
        
        public Collection<Film> findByIdActor(int idActor) {
            String sql = "SELECT id, any, name FROM ActorFilm sc INNER JOIN Film c ON sc.idFilm = c.id WHERE idActor= ?";

            ArrayList<Film> list = new ArrayList<Film>();

            try {
                    PreparedStatement statement = getConnection().prepareStatement(sql);
                    statement.setInt(1, idActor);
                    ResultSet rs = statement.executeQuery();

                    while (rs.next()) {
                            Film film = new Film(rs.getInt("id"),rs.getInt("any"), rs.getString("name"));
                            list.add(film);
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            }
            return list;

	}
        
        public Film findById(int id) {
            String sql = "SELECT * FROM Film WHERE id= ?";

            try {
                PreparedStatement statement = getConnection().prepareStatement(sql);

                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    Film film = new Film(rs.getInt("id"), rs.getInt("any"), rs.getString("name"));
                    return film;
                }

            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return null;

	}
        
        public Film add(Film film) {
            String sql = "INSERT INTO Film ('id', 'any' , 'name') VALUES (NULL,?)";

            try {
                PreparedStatement statement = getConnection().prepareStatement(sql);
                statement.setString(1, course.getAny());
                statement.setString(1, course.getName());
  
                int rs = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return film;
                
	}
        
        public Film update(FIlm film) {
            String sql = "UPDATE Film SET 'any, 'name' = ? WHERE `Film`.`id` = ?";

            try {
                PreparedStatement statement = getConnection().prepareStatement(sql);
                statement.setString(1, film.getAny());
                statement.setString(2, film.getName());
                statement.setInt(3, film.getId());
                int rs = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return film;

	}

	public int delete(Film film) {
            String sql = "DELETE FROM Film WHERE id = ?";
            int rs = 0;

            try {
                PreparedStatement statement = getConnection().prepareStatement(sql);
                statement.setInt(1, film.getId());
                rs = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return rs;

	}

}
