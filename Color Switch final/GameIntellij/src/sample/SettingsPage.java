package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;

public class SettingsPage {

    @FXML
    private AnchorPane AnchorPaneMotionPage;
    @FXML
    private CheckBox mera_checkbox;

    public void BackToMainMenu() throws IOException {
        System.out.println("Going back to main menu from settings Page");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        AnchorPaneMotionPage.getChildren().setAll(root);
    }

    public void checkBoxClicked() throws IOException {
        if (mera_checkbox.isSelected()) {
            System.out.println("checkBox pe tick aa gaya ");
            // play sound now
            // write code for ^^
        } else {
            System.out.println("checkbox unticked boss aka tick gayab ho gaya ");
            // stop music now
            // write code for ^^
        }
    }
}
