package org.pcs.service;

import org.pcs.db.MySQLConnection;
import org.pcs.entities.Artist;
import org.pcs.entities.Genre;
import org.pcs.entities.Movie;
import org.pcs.entities.Song;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.pcs.service.ArtistDAO.getArtistById;
import static org.pcs.service.GenreDAO.getGenreById;
import static org.pcs.service.MovieDAO.getMovieById;

public class SongDAO {
    public static Map<String, Song> getAllSongs(){
        Map<String, Song> songs = new HashMap<>();
        String sql = "SELECT title, artist_id, genre_id, movie_id, timesPlayed " +
                "FROM songs";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            try(var stmt = conn.createStatement();
                var rs = stmt.executeQuery(sql)){

                while (rs.next()){
                    int artistId = rs.getInt("artist_id");
                    int genreId = rs.getInt("genre_id");
                    int movieId = rs.getInt("movie_id");

                    Artist artist = getArtistById(artistId);
                    Genre genre = getGenreById(genreId);
                    Movie movie = getMovieById(movieId);

                    Song song = new Song(
                            rs.getString("title"),
                            artist,
                            genre,
                            movie,
                            rs.getInt("timesPlayed")
                    );
                    songs.put(song.getTitle(), song);
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return songs;
    }

    public static void getSongId(int id){
        String sql = "SELECT title FROM songs WHERE id = ? ";

        try(var conn = MySQLConnection.connect()
        ){
            assert conn != null;
            try(var stmt = conn.prepareStatement(sql)){
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

    public static int addSong(Song song){
        int id = 0;
        String checkArtistSQL = "SELECT id FROM artists WHERE id = ?";
        String checkMovieSQL = "SELECT id FROM movies WHERE id = ?";
        String checkGenreSQL = "SELECT id FROM genres WHERE id = ?";
        String insertSongSQL = "INSERT INTO songs(title, artistId, genreId, movieId, timesPlayed) values(?, ?, ?, ?, ?)";

        try(var conn = MySQLConnection.connect()) {
            assert conn != null;
            conn.setAutoCommit(false);

            try(var checkArtistStmt = conn.prepareStatement(checkArtistSQL);
                var checkMovieStmt = conn.prepareStatement(checkMovieSQL);
                var checkGenreStmt = conn.prepareStatement(checkGenreSQL);
                var insertSongStmt = conn.prepareStatement(insertSongSQL)){

                checkArtistStmt.setInt(1, song.getArtist().getId());
                try (var rs = checkArtistStmt.executeQuery()){
                    if (!rs.next()) throw new SQLException("Artist Not Found");
                }

                checkMovieStmt.setInt(1, song.getMovie().getId());
                try (var rs = checkMovieStmt.executeQuery()){
                    if (!rs.next()) throw new SQLException("Movie Not Found");
                }

                checkGenreStmt.setInt(1, song.getGenre().getId());
                try (var rs = checkGenreStmt.executeQuery()){
                    if (!rs.next()) throw new SQLException("Genre Not Found");
                }

                insertSongStmt.setString(1, song.getTitle());
                insertSongStmt.setInt(2, song.getArtist().getId());
                insertSongStmt.setInt(3, song.getGenre().getId());
                insertSongStmt.setInt(4, song.getMovie().getId());
                insertSongStmt.setInt(5, song.getTimesPlayed());

                int rowsEffected = insertSongStmt.executeUpdate();
                if (rowsEffected == 1){
                    var rs = insertSongStmt.getGeneratedKeys();
                    if (rs.next()){
                        id = rs.getInt("id");
                    }
                }

            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return id;
    }

}
