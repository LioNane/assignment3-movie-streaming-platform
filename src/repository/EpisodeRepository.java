package repository;

import exception.*;

import model.Episode;
import utils.*;

import java.sql.*;
import java.util.ArrayList;

public class EpisodeRepository {
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public Episode create(Episode episode) {

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("INSERT INTO episodes VALUES (?,?,?,?)");

            ps.setInt(1, episode.getId());
            ps.setString(2, episode.getName());
            ps.setInt(3, episode.getDuration());
            ps.setInt(4, episode.getSeries_id());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DatabaseOperationException("Creating episode failed, no rows affected.");
            }

            return episode;
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while creating episode: " + e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }

    public ArrayList<Episode> getAll(){
        ArrayList<Episode> episodes = new ArrayList<>();
        try{
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM episodes");
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int duration = rs.getInt(3);
                int series_id = rs.getInt(4);
                episodes.add(new Episode(id, name, duration, series_id));;
            }
            return episodes;

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting episode: " + e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }

    public Episode getById(int id){
        try{
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT * FROM episodes WHERE id = ?");
            ps.setInt(1, id);

            try {
                ResultSet rs = ps.executeQuery();
                String name = rs.getString(2);
                int duration = rs.getInt(3);
                int series_id = rs.getInt(4);
                return new Episode(id, name, duration, series_id);
            } catch (SQLException e) {
                throw new ResourceNotFoundException("Film with id = " + id + " not found");
            }


        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting film: " + e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
                rs.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }

    public Episode update(int id, Episode episode) {

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("UPDATE episodes SET name = ?, duration = ?, series_id = ? WHERE id = ?");

            ps.setString(1, episode.getName());
            ps.setInt(2, episode.getDuration());
            ps.setInt(3, episode.getSeries_id());
            ps.setInt(4, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new ResourceNotFoundException("Episode with id=" + id + " not found (update failed)");
            }
            return getById(id);


        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while updating film: " + e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }

    public void delete(int id){
        try{
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("DELETE FROM episodes WHERE id = ?");

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new ResourceNotFoundException("Film with id=" + id + " not found (delete failed)");
            }

        }catch (SQLException e){
            throw new DatabaseOperationException("DB error while deleting film: " + e.getMessage());
        }  finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }

    public boolean existsBySeriesIdAndEpisodeName(int series_id, String name) {

        try {
            con = DatabaseConnection.getConnection();
            ps = con.prepareStatement("SELECT 1 FROM episodes WHERE series_id = ? AND name = ?");
            ps.setInt(1, series_id);
            ps.setString(2, name);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while checking episode duplicate: " + e.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }
}
