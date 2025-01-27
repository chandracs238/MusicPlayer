package org.pcs.service;

import org.pcs.db.MySQLConnection;
import org.pcs.entities.Artist;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    public static int addArtist(Artist artist){
        int id = 0;
        try(var conn = MySQLConnection.connect()){
            String sql = "INSERT INTO artists(name) values(?)";
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, artist.getName());
                int rowsEffected = stmt.executeUpdate();

                if (rowsEffected == 1){
                    var rs = stmt.getGeneratedKeys();
                    if (rs.next()){
                        id = rs.getInt(1);
                    }
                }
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public static List<Artist> getArtists(){
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT name FROM artists";
        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.createStatement();
                var rs = stmt.executeQuery(sql)){
                while (rs.next()){
                    artists.add(new Artist(rs.getInt(1), rs.getString("name")));
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return artists;
    }

    public static Artist getArtistById(int id){
        Artist artist = new Artist();
        String sql = "SELECT id, name FROM artists WHERE id = ?";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                try(var rs = stmt.executeQuery()){
                    if (rs.next()){
                        artist.setId(rs.getInt("id"));
                        artist.setName(rs.getString("name"));
                    }
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return artist;
    }

    public static void deleteArtistById(int id){
        String sql = "DELETE FROM artists WHERE id = ?";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                int rowsEffected = stmt.executeUpdate();
                if (rowsEffected == 1){
                    System.out.println("Row Deleted Successfully");
                }else {
                    System.out.println("Id not found: " + id);
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}

