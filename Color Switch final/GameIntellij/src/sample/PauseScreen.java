package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class PauseScreen
{
    @FXML private AnchorPane MainAnchorPane;

    public void HomeButton() throws IOException
    {
        System.out.println("Home Button on pause screen clicked");
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        MainAnchorPane.getChildren().setAll(root);
    }

    public void ResumeButton() throws IOException
    {
        System.out.println("Resume Button on pause screen clicked");
        Parent root=FXMLLoader.load(getClass().getResource("GameplayPage.fxml"));
        MainAnchorPane.getChildren().setAll(root);
    }

}
