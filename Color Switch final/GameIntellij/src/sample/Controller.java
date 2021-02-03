package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
//import com.restfb.De

public class Controller
{
    @FXML private AnchorPane AnchorPaneMain;

    public void PlayButtonCLicked() throws IOException
    {
        System.out.println("Play button clicked");
        Parent root= FXMLLoader.load(getClass().getResource("GameplayPage.fxml"));

//        Stage stageMain = (Stage) AnchorPaneMain.getScene().getWindow();
        AnchorPaneMain.getChildren().setAll(root);
    }

    public  void SavedGamesButtonClicked() throws IOException{
        System.out.println("Saved Games button on main page clicked");
        Parent root=FXMLLoader.load(getClass().getResource("SavedGamePage.fxml"));
        AnchorPaneMain.getChildren().setAll(root);
    }

    public void SettingsButtonClicked() throws IOException
    {
        System.out.println("Settings Button clicked");
        // yada yada
        Parent root = FXMLLoader.load(getClass().getResource("settingsPage.fxml"));
        AnchorPaneMain.getChildren().setAll(root);




    }

    public void HelpButtonClicked() throws IOException{
        System.out.println("Help Button CLicked");
        //yada yada
        Parent root = FXMLLoader.load(getClass().getResource("helpPage.fxml"));
        AnchorPaneMain.getChildren().setAll(root);

    }

    public void ExitButtonClicked()
    {
        System.out.println("Exit Button CLicked");
        Stage stageMain = (Stage) AnchorPaneMain.getScene().getWindow();
        stageMain.close();
    }

    public  void TranslationTry() throws IOException {
        System.out.println("Translational page open kar rha hu");
        Parent root=FXMLLoader.load(getClass().getResource("TranslationalTry.fxml"));
        AnchorPaneMain.getChildren().setAll(root);
    }

    public  void fbShare() throws IOException{
        System.out.println("opening web browser and www.fb.com");

        try {
            Desktop.getDesktop().browse(new URI("http://www.facebook.com"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }


    }

}

