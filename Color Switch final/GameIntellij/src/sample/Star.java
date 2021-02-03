package sample;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import java.io.File;

public class Star
{
    ImageView starView;

    Star(double x, double y)
    {
        File file=new File("src/Images/Star.jpeg");

        Image starImage=new Image(file.toURI().toString());
        ImageView starView=new ImageView(starImage);
        starView.setFitWidth(40);
        starView.setFitHeight(40);
        starView.setX(x);
        starView.setY(y);

        this.starView=starView;
    }

    public static boolean collide(Circle ball, ImageView starView)
    {
        Bounds ballBounds=ball.localToScene(ball.getBoundsInLocal());
        Bounds startBounds=starView.localToScene(starView.getBoundsInLocal());
        return startBounds.intersects(ballBounds);
    }
}
