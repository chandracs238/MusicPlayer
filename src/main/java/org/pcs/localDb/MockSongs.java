package org.pcs.localDb;

import org.pcs.entities.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockSongs {

    public static List<String> getSongString(){
        List<String> songs = new ArrayList<>();
        songs.add("Naa Sammi");
        songs.add("Sooreede");
        songs.add("Odiyamma");
        songs.add("Chitti");
        return songs;
    }

    public static List<Song> getSongs(){
        return List.of(
                new Song("Sooreede", "Ravi Basrur", 201),
                new Song("Monna Kanipinchavu", "Harris Jayaraj", 332),
                new Song("Gundellonaa", "Leon James", 200),
                new Song("Monna Kanipinchavu", "Harris Jayaraj", 332),
                new Song("Gundellonaa", "Leon James", 200));
    }

}
