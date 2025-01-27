package org.pcs.service;

import org.pcs.db.MySQLConnection;
import org.pcs.entities.Genre;

import java.sql.SQLException;
import java.sql.Statement;

public class GenreDAO {

    public static Genre getGenreById(int id){
        Genre genre = new Genre();
        String sql = "SELECT name FROM genres WHERE id = ?";

        try (var conn = MySQLConnection.connect()) {
            assert conn != null;
            try (var stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                var rs = stmt.executeQuery();
                while (rs.next()){
                    genre.setId(id);
                    genre.setName(rs.getString("name"));
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return genre;
    }

    public static int addGenre(Genre genre){
        int id = 0;
        String sql = "INSERT INTO genre(name) values(?)";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, genre.getName());

                int rowsEffected = stmt.executeUpdate();
                if (rowsEffected == 1){
                    var rs = stmt.getGeneratedKeys();
                    if (rs.next()){
                        id = rs.getInt("id");
                    }
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return id;

    }
}
