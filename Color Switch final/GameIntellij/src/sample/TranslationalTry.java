package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TranslationalTry implements Initializable
{
    public AnchorPane ColorSwitch;
    public AnchorPane Star;
    TranslateTransition translate;
    RotateTransition rotate;
    RotateTransition rotate2;
    RotateTransition rotate3;

    @FXML private AnchorPane AnchorPaneMotionPage;
    @FXML private Group HorBars;
    @FXML private Group CrossObstacle;
    @FXML private Group SquareObstacle;
    @FXML private Group arcObstacle;

    public void TranslateBars()
    {
        System.out.println("Translating the Bars");

        translate=new TranslateTransition();
        translate.setFromX(10);
        translate.setByX(420);
//        translate.setFromY(10);
//        translate.setByY(500);
        translate.setDuration(Duration.seconds(1));
        translate.setCycleCount(Animation.INDEFINITE);
        translate.setInterpolator(Interpolator.LINEAR);
        translate.setAutoReverse(true);

        translate.setNode(HorBars);
        translate.play();
    }

    public void StartMotion()
    {
        System.out.println("Moving the obstacles");

        translate=new TranslateTransition();
        translate.setFromX(0);
        translate.setByX(420);
        translate.setDuration(Duration.seconds(1));
        translate.setCycleCount(100);
        translate.setInterpolator(Interpolator.LINEAR);
        translate.setAutoReverse(true);
        translate.setNode(HorBars);
        translate.play();

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(500);
        rotate.setDuration(Duration.seconds(4));
        rotate.setInterpolator(Interpolator.LINEAR);

        rotate2 = new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(500);
        rotate2.setDuration(Duration.seconds(4));
        rotate2.setInterpolator(Interpolator.LINEAR);

        rotate3 = new RotateTransition();
        rotate3.setAxis(Rotate.Z_AXIS);
        rotate3.setByAngle(360);
        rotate3.setCycleCount(500);
        rotate3.setDuration(Duration.seconds(4));
        rotate3.setInterpolator(Interpolator.LINEAR);

        rotate.setNode(CrossObstacle);
        rotate2.setNode(SquareObstacle);
        rotate3.setNode(arcObstacle);
        rotate.play();
        rotate2.playFromStart();
        rotate3.play();
    }

    public void BackToMainMenu() throws IOException {
        System.out.println("Going back to main menu from Motion Page");
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        AnchorPaneMotionPage.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StartMotion();
    }
}
