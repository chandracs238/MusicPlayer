package org.pcs.service;

import org.pcs.db.MySQLConnection;
import org.pcs.entities.Movie;

import java.sql.*;

public class MovieDAO {
    public static int addMovie(Movie movie){
        int id = 0;
        String sql = "INSERT INTO movies(title, year) values(? , ?)";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                stmt.setString(1, movie.getTitle());
                stmt.setInt(2, movie.getYear());

                int rowsEffected = stmt.executeUpdate();
                if (rowsEffected == 1){
                    var rs = stmt.getGeneratedKeys();
                    if (rs.next()){
                        id = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public static Movie getMovieById(int id){
        Movie movie = new Movie();
        String sql = "SELECT title, year FROM movies WHERE id = ?";

        try(Connection connection = MySQLConnection.connect()) {
            assert connection != null;
            try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    movie.setId(id);
                    movie.setTitle(resultSet.getString("title"));
                    movie.setYear(resultSet.getInt("year"));
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return movie;
    }
}
