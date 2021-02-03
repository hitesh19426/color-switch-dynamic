package sample;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import java.io.File;
import java.io.Serializable;
import java.util.Random;

public class ColorChanger implements Serializable ,  Colorable
{
    private  ImageView colorChangerView;



    ColorChanger(double centerX, double centerY)
    {
        File file=new File("src/Images/ColorSwitcher.jpeg");
        Image colorImage=new Image(file.toURI().toString());
        ImageView colorView=new ImageView(colorImage);

//        File file2=new File("src/Images/star.css");
//        Image image2=new Image(file2.toURI().toString());
//        ImageView view2=new ImageView().setStyle();
        colorView.setFitWidth(40);
        colorView.setFitHeight(40);
        colorView.setX(centerX);
        colorView.setY(centerY);
        colorChangerView=colorView;
    }

    public void changeColor(Circle ball)
    {
        Random random=new Random();
        int randomInt=random.nextInt(colors.length);
        ball.setFill(colors[randomInt]);
    }
    //credits stackoverflow
    public static boolean collide(Circle ball, ImageView colorChangerView)
    {
        Bounds ballBounds=ball.localToScene(ball.getBoundsInLocal());
        Bounds colorChangerBounds=colorChangerView.localToScene(colorChangerView.getBoundsInLocal());
        return colorChangerBounds.intersects(ballBounds);
    }

    public ImageView getColorChangerView() {
        return colorChangerView;
    }

    public void setColorChangerView(ImageView colorChangerView) {
        this.colorChangerView = colorChangerView;
    }
}
