package org.pcs;

import org.pcs.entities.Song;
import org.pcs.localDb.MockSongs;

import java.util.*;

public class Jukebox {
    public static void main(String[] args) {
        new Jukebox().go();
    }

    public void go(){
        List<Song> songList = MockSongs.getSongs();
//        Collections.sort(songList); // This only works for String objects
//        TitleCompare titleCompare = new TitleCompare();
//        ArtistCompare artistCompare = new ArtistCompare();
//        songList.sort(titleCompare);
//        songList.sort(artistCompare);
//        songList.sort(new Comparator<Song>() {
//            @Override
//            public int compare(Song o1, Song o2) {
//                return o1.getTitle().compareTo(o2.getTitle());
//            }
//        });
        // Then lastly using lamda expression
//        songList.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));
        List<Song> songs2 = new ArrayList<>(songList);
        songs2.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));
        System.out.println("Using ArrayList");
        System.out.println(songs2);

        Set<Song> songSet = new HashSet<>(songList);
        System.out.println("Using HashSet");
        System.out.println(songSet);

//        Set<Song> songSet1 = new TreeSet<>(songList);
        Set<Song> songSet1 = new TreeSet<>((one, two) -> two.getBpm() - one.getBpm());
        songSet1.addAll(songList);
        System.out.println("Using TreeSet");
        System.out.println(songSet1);

        songList.forEach(song -> System.out.println(song.getTitle()));





    }
}

class ArtistCompare implements Comparator<Song>{
    public int compare(Song one, Song two){
        return one.getArtist().compareTo(two.getArtist());
    }
}

class TitleCompare implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}