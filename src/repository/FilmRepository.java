package repository;

import exception.*;

import model.Film;
import utils.*;

import java.sql.*;
import java.util.ArrayList;

public class FilmRepository {

    public Film create(Film film) throws SQLException {
        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO films VALUES (?,?,?,?)");
        ){
            ps.setInt(1, film.getId());
            ps.setString(2, film.getName());
            ps.setInt(3, film.getDuration());
            ps.setFloat(4, film.getRating());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DatabaseOperationException("Creating film failed, no rows affected.");
            }
            return film;

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while creating film: " + e.getMessage());
        }
    }

    public ArrayList<Film> getAll(){
        ArrayList<Film> films = new ArrayList<>();
        try(
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM films");
                ResultSet rs = ps.executeQuery();
        ){

            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int duration = rs.getInt(3);
                float rating = rs.getFloat(4);
                films.add(new Film(id, name, duration, rating));;
            }
            return films;

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting film: " + e.getMessage());
        }
    }

    public Film getById(int id){
        try(
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT * FROM films WHERE id = ?");
                ){
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    throw new ResourceNotFoundException("Film with id = " + id + " not found");
                }

                String name = rs.getString(2);
                int duration = rs.getInt(3);
                float rating = rs.getFloat(4);
                return new Film(id, name, duration, rating);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting film: " + e.getMessage());
        }
    }

    public Film update(int id, Film film) {

        try (
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE films SET name = ?, duration = ?, rating = ? WHERE id = ?");
        ){

            ps.setString(1, film.getName());
            ps.setInt(2, film.countDuration());
            ps.setFloat(3, film.getRating());
            ps.setInt(4, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new ResourceNotFoundException("Film with id = " + id + " not found (update failed)");
            }

            return getById(id);

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while updating film: " + e.getMessage());
        }
    }

    public void delete(int id){
        try(
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM films WHERE id = ?");
        ){

            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new ResourceNotFoundException("Film with id = " + id + " not found (delete failed)");
            }

        }catch (SQLException e){
            throw new DatabaseOperationException("DB error while deleting film: " + e.getMessage());
        }
    }
    public boolean existsByName(String name) throws SQLException {
        try (
                Connection con = DatabaseConnection.getConnection();
                PreparedStatement ps = con.prepareStatement("SELECT 1 FROM films WHERE name = ?");
        ){
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while checking film duplicate: " + e.getMessage());
        }
    }
}
