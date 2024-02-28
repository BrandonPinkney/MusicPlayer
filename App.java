
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // make sure GUI is executed on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Song song = new Song("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets/Yeat - narcoticz (Slowed).mp3");
                System.out.println(song.getSongTitle());
                System.out.println(song.getSongArtist());

                new MusicPlayerGUI().setVisible(true);

            }

        });

    }

}
