import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;

public class MusicPlayer {
    // we will need a way to store songs details

    private Song currentSong;

    // use JLayer Library to create an AdvancedPlayer obj which will handle playing
    // the music
    private AdvancedPlayer advancedPlayer;

    // constructor

    public MusicPlayer() {

    }

    public void loadSong(Song song) {
        currentSong = song;

        // play the current song if not null

        if (currentSong != null) {
            playCurrentSong();
        }
    }

    public void playCurrentSong() {
        try {
            // read mp3 audio data
            FileInputStream fileInputStream = new FileInputStream(currentSong.getFilePath());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            // create a new advanced player
            advancedPlayer = new AdvancedPlayer(bufferedInputStream);

            // start music
            startMusicThread();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startMusicThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // play music
                    advancedPlayer.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}