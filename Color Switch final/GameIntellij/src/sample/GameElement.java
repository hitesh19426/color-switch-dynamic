package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GameElement
{
    private final SimpleStringProperty GameID;
    private final SimpleIntegerProperty HighScore;
    private final SimpleStringProperty LoadButton;


    public GameElement(String gameID, Integer highScore, String loadButton) {
        this.GameID = new SimpleStringProperty(gameID);
        this.HighScore = new SimpleIntegerProperty(highScore);
        this.LoadButton = new SimpleStringProperty(loadButton);
    }

}
