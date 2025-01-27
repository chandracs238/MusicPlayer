package org.pcs.service;

import org.pcs.db.MySQLConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class SongDAO {
    public static void getAllSongs(){

        String sql = "SELECT id, title, artist, genre, movie, year, timesPlayed " +
                "FROM songs";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.createStatement();
                var rs = stmt.executeQuery(sql)){

                while (rs.next()){
                    System.out.println(
                            rs.getInt("id") + "\t" +
                            rs.getString("title") + "\t" +
                            rs.getString("artist") + "\t" +
                            rs.getString("genre") + "\t" +
                            rs.getString("movie") + "\t" +
                            rs.getInt("year") + "\t" +
                                    rs.getString("timesPlayed")
                    );
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void getSongId(int id){
        String sql = "SELECT title FROM songs WHERE id = ? ";

        try(var conn = MySQLConnection.connect()
        ){
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setInt(1, id);
                var rs = stmt.executeQuery();
                while (rs.next()){
                    System.out.println(rs.getString("title"));
                }
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void addSong(String title, )
}
