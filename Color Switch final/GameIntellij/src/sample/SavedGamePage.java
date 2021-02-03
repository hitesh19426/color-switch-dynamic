package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SavedGamePage implements Initializable
{
    @FXML private AnchorPane AnchorPaneMain;
    @FXML private MenuItem Exit;
    @FXML private MenuItem BackToMainMenu;
    @FXML private TableView<GameElement> Table;
    @FXML private TableColumn Column1;
    @FXML private TableColumn Column2;
    @FXML private TableColumn Column3;

    public void ExitTheGame()
    {
        System.out.println("Exiting the game from saved Games Menu");
        Stage stagemain = (Stage) AnchorPaneMain.getScene().getWindow();
        stagemain.close();
    }

    public void BackToMainMenu() throws IOException {
        System.out.println("Going to main menu from saved games menu");
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        AnchorPaneMain.getChildren().setAll(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        final ObservableList<GameElement> data= FXCollections.observableArrayList(
//                new GameElement("Game1",10 , "load"),
//                new GameElement("Game2", 15, "load")
//        );
//
//        Column1.setCellValueFactory(new PropertyValueFactory<GameElement, String>("GameID"));
//        Column2.setCellValueFactory(new PropertyValueFactory<GameElement, Integer>("HighScore"));
//        Column3.setCellValueFactory(new PropertyValueFactory<GameElement, String>("LoadButton"));
//
//        Table.setItems(data);
    }
}
