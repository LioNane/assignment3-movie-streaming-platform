package repository;

import exception.*;

import model.Episode;
import utils.*;

import java.sql.*;
import java.util.ArrayList;

public class EpisodeRepository {

    public Episode create(Episode episode) {

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO episodes VALUES (?,?,?,?)");
        ){
            ps.setInt(1, episode.getId());
            ps.setString(2, episode.getName());
            ps.setInt(3, episode.getDuration());
            ps.setInt(4, episode.getSeriesId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new DatabaseOperationException("Creating episode failed, no rows affected.");
            }

            return episode;
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while creating episode: " + e.getMessage());
        }
    }

    public ArrayList<Episode> getAll(){
        ArrayList<Episode> episodes = new ArrayList<>();
        try(
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM episodes");
            ResultSet rs = ps.executeQuery();
        ){
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int duration = rs.getInt(3);
                int seriesId = rs.getInt(4);
                episodes.add(new Episode(id, name, duration, seriesId));;
            }
            return episodes;

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting episode: " + e.getMessage());
        }
    }

    public Episode getById(int id){
        try(
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM episodes WHERE id = ?");
            ){
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if(!rs.next()){
                    throw new ResourceNotFoundException("Episode with id = " + id + " not found");

                }
                String name = rs.getString(2);
                int duration = rs.getInt(3);
                int seriesId = rs.getInt(4);
                return new Episode(id, name, duration, seriesId);
            }


        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting film: " + e.getMessage());
        }
    }

    public Episode update(int id, Episode episode) {

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE episodes SET name = ?, duration = ?, series_id = ? WHERE id = ?");
        ){
            ps.setString(1, episode.getName());
            ps.setInt(2, episode.getDuration());
            ps.setInt(3, episode.getSeriesId());
            ps.setInt(4, id);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new ResourceNotFoundException("Episode with id = " + id + " not found (update failed)");
            }
            return getById(id);


        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while updating film: " + e.getMessage());
        }
    }

    public void delete(int id){
        try(
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM episodes WHERE id = ?");
        ){
            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new ResourceNotFoundException("Film with id=" + id + " not found (delete failed)");
            }

        }catch (SQLException e){
            throw new DatabaseOperationException("DB error while deleting film: " + e.getMessage());
        }
    }

    public boolean existsBySeriesIdAndEpisodeName(int seriesId, String name) {

        try (
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM episodes WHERE series_id = ? AND name = ?");
        ){
            ps.setInt(1, seriesId);
            ps.setString(2, name);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while checking episode duplicate: " + e.getMessage());
        }
    }
}