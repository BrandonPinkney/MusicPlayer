import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MusicPlayerGUI extends JFrame {

    // color configurations

    public static final Color FRAME_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    private MusicPlayer musicPlayer;

    // allows us to use file explorer in our app
    private JFileChooser jFileChooser;

    private JLabel songTitle, songArtist;
    private JPanel playbackBtns;

    public MusicPlayerGUI() {
        // calls JFrame constructor to configure out gui and set the title header to
        // music player
        super("Music Player");

        // set the width and height
        setSize(400, 600);

        // end process when app is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // launch the app at the center of the screen
        setLocationRelativeTo(null);

        // prevent the app from being resized
        setResizable(false);

        // set layout to null which allows us to control the (x,y) coordinaates of our
        // components
        // and also set the height and width
        setLayout(null);

        // change the frame color
        getContentPane().setBackground(FRAME_COLOR);

        musicPlayer = new MusicPlayer();

        jFileChooser = new JFileChooser();

        // set a default path for the file explorer
        jFileChooser.setCurrentDirectory(new File("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets"));

        addGuiComponents();
    }

    private void addGuiComponents() {
        // add toolbar
        addToolbar();

        // load record image
        JLabel songImage = new JLabel(loadImage("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets/record.png"));
        songImage.setBounds(0, 50, getWidth() - 20, 225);
        add(songImage);

        // song title
        songTitle = new JLabel("Song Title");
        songTitle.setBounds(0, 285, getWidth() - 10, 30);
        songTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        songTitle.setForeground(TEXT_COLOR);
        songTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(songTitle);

        // song artist
        songArtist = new JLabel("Artist");
        songArtist.setBounds(0, 315, getWidth() - 10, 30);
        songArtist.setFont(new Font("Dialog", Font.PLAIN, 24));
        songArtist.setForeground(TEXT_COLOR);
        songArtist.setHorizontalAlignment(SwingConstants.CENTER);
        add(songArtist);

        // playback slider
        JSlider playbackSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        playbackSlider.setBounds(getWidth() / 2 - 300 / 2, 365, 300, 40);
        playbackSlider.setBackground(null);
        add(playbackSlider);

        // playback buttons
        addPlaybackBtns();

    }

    private void addPlaybackBtns() {

        playbackBtns = new JPanel();
        playbackBtns.setBounds(0, 435, getWidth() - 10, 80);
        playbackBtns.setBackground(null);

        // previous button
        JButton prevButton = new JButton("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets/previous.png");
        prevButton.setBorderPainted(false);
        prevButton.setBackground(null);
        playbackBtns.add(prevButton);

        // play button
        JButton playButton = new JButton(loadImage("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets/play.png"));
        playButton.setBorderPainted(false);
        playButton.setBackground(null);
        playbackBtns.add(playButton);

        // pause button
        JButton pauseButton = new JButton(loadImage("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets/pause.png"));
        pauseButton.setBorderPainted(false);
        pauseButton.setBackground(null);
        pauseButton.setVisible(false);
        playbackBtns.add(pauseButton);

        // next button
        JButton nextButton = new JButton(loadImage("C:/Users/Pinkn/MusicPlayer/MusicPlayer/Assets/next.png"));
        nextButton.setBorderPainted(false);
        nextButton.setBackground(null);
        playbackBtns.add(nextButton);

        add(playbackBtns);

    }

    private void updateSongTitleAndArtist(Song song) {
        songTitle.setText(song.getSongTitle());
        songArtist.setText(song.getSongArtist());

    }

    private void addToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, getWidth(), 20);

        // prevent toolbar from being moved
        toolBar.setFloatable(false);

        // add drop down menu
        JMenuBar menuBar = new JMenuBar();
        toolBar.add(menuBar);

        // add song to menu where we will place the loading song option
        JMenu songMenu = new JMenu("Song");
        menuBar.add(songMenu);

        // add load song in the songMenu (creates a drop down under "Song"(songMenu))
        JMenuItem loadSong = new JMenuItem("Load Song");
        loadSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jFileChooser.showOpenDialog(MusicPlayerGUI.this);
                File selectedFile = jFileChooser.getSelectedFile();

                if (selectedFile != null) {
                    // create a song obj based on selected file
                    Song song = new Song(selectedFile.getPath());

                    // load song in music player
                    musicPlayer.loadSong(song);

                    // access song title and artist
                    updateSongTitleAndArtist(song);

                    // toggle on pause button and toggle off play button
                    // enablePauseButtonDisablePlayButton();
                }
            }
        });
        songMenu.add(loadSong);

        // now we will add the playlist menu
        JMenu playlistMenu = new JMenu("Playlist");
        menuBar.add(playlistMenu);

        // then add the items to the playlist menu
        JMenuItem createPlaylist = new JMenuItem("Create Playlist");
        playlistMenu.add(createPlaylist);

        // then load the playlist menu
        JMenuItem loadPlaylist = new JMenuItem("Load Playlist");
        playlistMenu.add(loadPlaylist);

        add(toolBar);

    }

    private ImageIcon loadImage(String imagePath) {
        try {
            // read the image file from the given path
            BufferedImage image = ImageIO.read(new File(imagePath));

            // returns an image icon so that our component can render the image
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}