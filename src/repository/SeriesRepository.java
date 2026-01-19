package repository;

import exception.*;
import model.Series;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class SeriesRepository {
    public void create(Series series) {

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO series VALUES (?,?,?)");

            ps.setInt(1, series.getId());
            ps.setString(2, series.getName());
            ps.setFloat(3, series.getRating());

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while creating series: " + e.getMessage());
        }
    }

    public ArrayList<Series> getAll(){
        ArrayList<Series> series = new ArrayList<>();
        try{
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM series");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float rating = rs.getFloat(3);
                series.add(new Series(id, name, rating));;
            }
            return series;

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting series: " + e.getMessage());
        }
    }

    public Series getById(int id){
        try{
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM series WHERE id = ?");
            ps.setInt(1, id);

            try {
                ResultSet rs = ps.executeQuery();
                String name = rs.getString(2);
                float rating = rs.getFloat(3);
                return new Series(id, name, rating);
            } catch (RuntimeException e) {
                throw new ResourceNotFoundException("Series with id = " + id + " not found");
            }


        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while getting series: " + e.getMessage());
        }
    }

    public void update(int id, Series series) {

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE series SET name = ?, duration = ?, rating = ? WHERE id = ?");

            ps.setString(1, series.getName());
            ps.setInt(2, series.countDuration());
            ps.setFloat(3, series.getRating());
            ps.setInt(4, id);


        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while updating series: " + e.getMessage());
        }
    }

    public void delete(int id){
        try{
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM series WHERE id = ?");

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new ResourceNotFoundException("Series with id=" + id + " not found (delete failed)");
            }

        }catch (SQLException e){
            throw new DatabaseOperationException("DB error while deleting series: " + e.getMessage());
        }
    }
}
