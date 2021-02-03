package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


import java.io.File;
import java.nio.file.Paths;
import javafx.util.Duration;

public class Main extends Application
{
    private static final double length = 640 ;
    private static final double width = 420;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        playMusic bajao = new playMusic();
        //addmusic();
        Thread t1 = new Thread(bajao);
        t1.start();
        t1.join();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Color Switch");

        primaryStage.setScene(new Scene(root, width , length));
        primaryStage.show();
    }

    MediaPlayer mediaPlayer;

    public void addmusic(){

        //Media sound = new Media(getClass().getResource("/fashion_ka_jalwa.mp3").toString());
        Media sound = new Media(new File("bin/mera_fashion.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(50));
        mediaPlayer.play();
//
//        String s = "fashion_ka_jalwa.mp3";
//        Media h = new Media(Paths.get(s).toUri().toString());
//        mediaPlayer = new MediaPlayer(h);
//        mediaPlayer.play();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}

class playMusic implements  java.lang.Runnable{
    @Override
    public void run() {
        String s = "D:/AP/latest COLOR SWITCH AP GAME/GameIntellij/src/attackontitan.wav";
        Media h = new Media(Paths.get(s).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }
}
