package repository;

import exception.*;

import model.Episode;
import utils.*;

import java.sql.*;
import java.util.ArrayList;

public class EpisodeRepository {
    public void create(Episode episode) {

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO episodes VALUES (?,?,?,?)");

            ps.setInt(1, episode.getId());
            ps.setString(2, episode.getName());
            ps.setInt(3, episode.getDuration());
            ps.setInt(4, episode.getSeries_id());

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while creating episode: " + e.getMessage());
        }
    }

    public ArrayList<Episode> getAll(){
        ArrayList<Episode> episodes = new ArrayList<>();
        try{
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM episodes");
            ResultSet rs = ps.executeQuery();
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
        }
    }

    public Episode getById(int id){
        try{
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM episodes WHERE id = ?");
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
        }
    }

    public void update(int id, Episode episode) {

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE episodes SET name = ?, duration = ?, series_id = ? WHERE id = ?");

            ps.setString(1, episode.getName());
            ps.setInt(2, episode.getDuration());
            ps.setInt(3, episode.getSeries_id());
            ps.setInt(4, id);
            

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while updating film: " + e.getMessage());
        }
    }

    public void delete(int id){
        try{
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM episodes WHERE id = ?");

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new ResourceNotFoundException("Film with id=" + id + " not found (delete failed)");
            }

        }catch (SQLException e){
            throw new DatabaseOperationException("DB error while deleting film: " + e.getMessage());
        }
    }
}
