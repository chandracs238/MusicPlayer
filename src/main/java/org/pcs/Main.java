package org.pcs;

import org.pcs.entities.Song;
import org.pcs.service.SongDAO;
import org.pcs.ui.MainFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.go();
//        Map<String, Song> songs = SongDAO.getAllSongs();
//        songs.forEach((s, song) -> System.out.println(s));

    }

}