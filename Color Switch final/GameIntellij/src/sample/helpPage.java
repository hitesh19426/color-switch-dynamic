package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class helpPage {
    @FXML private AnchorPane AnchorPaneMotionPage;

    public void BackToMainMenu() throws IOException {
        System.out.println("Going back to main menu from help Page");
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        AnchorPaneMotionPage.getChildren().setAll(root);
    }

    public void youtubeTutorial() throws IOException {
        System.out.println("opening web browser and www.youtube.com for the tutorial ");

        try {
            Desktop.getDesktop().browse(new URI("https://youtu.be/0uF28HnpqVw"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }


}
