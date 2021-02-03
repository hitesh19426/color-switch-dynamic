package sample;
import java.io.File;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class GameplayPage implements Initializable, Colorable
{
    @FXML public Label pointLabel;
    @FXML private Circle Ball;
    @FXML private AnchorPane AnchorPaneGameplay;
    private final Random random=new Random();
    private TranslateTransition trans;
    Timeline timeline;
    private int kfInt=0;
    boolean flag=false;
    double height=640, width=420;
    ArrayList<Obstacle> obstacles=new ArrayList<>();
    ArrayList<Star> stars=new ArrayList<>();
    ArrayList<ColorChanger> colorChangers=new ArrayList<>();
    private int points=0;

    //GameDataSerialize gameDataSerialize = new GameDataSerialize();

    private void gravity()
    {
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame kv=new KeyFrame(Duration.millis(100), e-> {
            kfInt++;
            System.out.println("Print kf" + kfInt);

            AnchorPaneGameplay.setOnKeyReleased( k ->
            {
                if (k.getCode().equals(KeyCode.UP)){
                    MoveUp();
                    flag=true;
                    System.out.println("UP Arrow Key case");
                }
                else if (k.getCode().equals(KeyCode.W)){
                    MoveUp();
                    flag=true;
                    System.out.println("W key case");
                }
                else if (k.getCode().equals(KeyCode.SPACE)) {
                    MoveUp();
                    flag=true;
                    System.out.println("Space key used");
                }
                else if (k.getCode().equals(KeyCode.S)){
//                    trans.setToY(Ball.getCenterY()-50);
//                    Ball.setCenterY(Ball.getCenterY()-50);
//                    trans.play();
//                    System.out.println("Ball Moved Down");
                    trans.setToY(Ball.getCenterY()+50);
                    Ball.setCenterY(Ball.getCenterY()+50);
                    trans.play();
                    System.out.println("Ball Moved Down");
                    flag=true;
                    System.out.println("Down arrow case");
                }
                else if (k.getCode().equals(KeyCode.DOWN)) {
                    Ball.setTranslateY(Ball.getTranslateY()+50);
                    flag=true;
                    System.out.println("S case");
                }
            });

            if (checkGameOver())
            {
                timeline.pause();
                System.out.println("Game Over");
                gameOver();
            }

            if (Star.collide(Ball, stars.get(0).starView))
            {
                AnchorPaneGameplay.getChildren().remove(stars.get(0).starView);
                stars.remove(0);
                points++;
                pointLabel.setText(String.valueOf(points));
            }

            if (ColorChanger.collide(Ball, colorChangers.get(0).getColorChangerView()))
            {
                AnchorPaneGameplay.getChildren().remove(colorChangers.get(0).getColorChangerView());
                colorChangers.remove(0);
                colorChangers.get(0).changeColor(Ball);
            }

            if ( Ball.getCenterY()<=AnchorPaneGameplay.getMaxHeight() )
            {
                trans.setToY(Ball.getCenterY()+10);
                Ball.setCenterY(Ball.getCenterY()+10);
                trans.play();
                System.out.println("Ball Moved Down");
            }
            Bounds bounds=Ball.localToScreen(Ball.getBoundsInLocal());

            if (bounds.getCenterY() <= 600)
            {
                for (Obstacle obj: obstacles) {
                    obj.root.setTranslateY(obj.root.getTranslateY() + 5);
                }
                for (Star star: stars)
                    star.starView.setTranslateY(star.starView.getTranslateY()+5);

                for (ColorChanger colorChanger: colorChangers)
                    colorChanger.getColorChangerView().setTranslateY(colorChanger.getColorChangerView().getTranslateY()+5);
            }

//            if (kvInt%10==0) {
//                int randomnum= new Random().nextInt(colors.length);
//                TestingBall.setCenterX(TestingBall.getCenterX()+5);
//                TestingBall.setFill(colors[randomnum]);
//
//                if (barsPresent) {
//                    System.out.println("Already present");
//                }
//                else {
//                    HorBars bars = new HorBars(300, 300);
//                    for (int i=0;i< bars.barsNumber;i++)
//                        AnchorPaneGameplay.getChildren().add(bars.bars[i]);
//
//                    bars.play();
//                    barsPresent=true;
//                }
//            }

//            if (Ball.getCenterY()<=AnchorPaneGameplay.getMaxHeight())
//            {
//                trans.setToY(Ball.getCenterY()+5);
//                Ball.setCenterY(Ball.getCenterY()+5);
//                trans.play();
//                System.out.println("Ball Moved Down");
//            }
        });

        timeline.getKeyFrames().addAll(kv);
        timeline.play();

        // pause screen walla code

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        trans=new TranslateTransition();
        trans.setNode(Ball);
        trans.setDuration(Duration.millis(100));
        trans.setInterpolator(Interpolator.LINEAR);
        Ball.setFill(colors[0]);

        obstacles=new ArrayList<>();
        insertObstacles();

        System.out.println(obstacles.size());
        System.out.println(stars.size());
        System.out.println(colorChangers.size());

        gravity();
//        gravity2();
    }

    double y=350;
    private void insertObstacles()
    {
        for (int i=0;i<100;i++, y=y-500)
        {
            if (i==0) {
                Cross cross = new Cross(125, y);
                AnchorPaneGameplay.getChildren().add(cross.root);
                obstacles.add(cross);

                Star star= new Star(Ball.getLayoutX()-25, y+150);
                AnchorPaneGameplay.getChildren().add(star.starView);
                stars.add(star);

                ColorChanger colorChanger=new ColorChanger(Ball.getLayoutX()-25, y-150);
                AnchorPaneGameplay.getChildren().add(colorChanger.getColorChangerView());
                colorChangers.add(colorChanger);
                continue;
            }
            int randomNumber= random.nextInt(3)+1;
//            int randomNumber=0;
            switch (randomNumber){
                case 0->{
                    doublecircleObject double_circle=new doublecircleObject(200, y);
                    AnchorPaneGameplay.getChildren().add(double_circle.root);
                    obstacles.add(double_circle);
                    System.out.println("double circle case");
                    break;
                }
                case 1->{
                    CircleObject circle=new CircleObject(Ball.getCenterX()+200, y);
                    AnchorPaneGameplay.getChildren().add(circle.root);
                    obstacles.add(circle);

                    Star star= new Star(Ball.getLayoutX()-25, y+150);
                    AnchorPaneGameplay.getChildren().add(star.starView);
                    stars.add(star);

                    ColorChanger colorChanger=new ColorChanger(Ball.getLayoutX()-25, y-200);
                    AnchorPaneGameplay.getChildren().add(colorChanger.getColorChangerView());
                    colorChangers.add(colorChanger);
                }
                case 2->{
                    int randomPosition=new Random().nextInt(2);
                    Cross cross;
                    if (randomPosition==0)
                        cross = new Cross(125, y);
                    else
                        cross=new Cross(300, y);
                    AnchorPaneGameplay.getChildren().add(cross.root);
                    obstacles.add(cross);

                    Star star= new Star(Ball.getLayoutX()-25, y+170);
                    AnchorPaneGameplay.getChildren().add(star.starView);
                    stars.add(star);

                    ColorChanger colorChanger=new ColorChanger(Ball.getLayoutX()-25, y-170);
                    AnchorPaneGameplay.getChildren().add(colorChanger.getColorChangerView());
                    colorChangers.add(colorChanger);
                }
                case 3->{
                    Square square=new Square(Ball.getCenterX()+120, y);
                    AnchorPaneGameplay.getChildren().add(square.root);
                    obstacles.add(square);

                    Star star= new Star(Ball.getLayoutX()-25, y+180);
                    AnchorPaneGameplay.getChildren().add(star.starView);
                    stars.add(star);

                    ColorChanger colorChanger=new ColorChanger(Ball.getLayoutX()-25, y-180);
                    AnchorPaneGameplay.getChildren().add(colorChanger.getColorChangerView());
                    colorChangers.add(colorChanger);
                }

            }
        }
    }

/*
    private void gravity2() {
        AnimationTimer timer=new AnimationTimer() {
            double myTime=0;
            double velocityY=0;
            double previousVelocity=0;
            int counter=0;

            @Override
            public void handle(long l) {
                counter++;
                double currY=Ball.getCenterY();
//                velocityY = 0.5*Math.pow(myTime, 2);
                velocityY=5;
                double newY=currY+velocityY;

                moveObstacles();

//                Ball.relocate(Ball.getCenterX(), newY);
                if (Ball.getCenterY()<AnchorPaneGameplay.getMaxHeight()-Ball.getRadius())
                    Ball.setCenterY(newY);

                if (checkGameOver()) {
                    this.stop();
                    gameOver();
                }

//                previousVelocity=velocityY;
                myTime += .001;
            }
        };
        timer.start();
    }
*/


    private void gameOver() {

//        timeline.pause();
        Stage stage= (Stage) AnchorPaneGameplay.getScene().getWindow();
        Stage pauseStage = new Stage();
        pauseStage.initOwner(stage);
        pauseStage.setMaxWidth(width);
        pauseStage.setMaxHeight(height);
        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setTitle("COLLISION !!!!");



        Pane pane=new Pane();
        pane.setStyle("-fx-background-color: BLACK");

        //pane.getStyleClass().add("bg-black-style");
        pane.setPrefHeight(height);
        pane.setPrefWidth(width);

        //pane.setBackground(new Background());

        //pane.setStyle("-fx-background-color: #00000;");

        File color_swtich = new File("src/Images/cs.jpg");
        ImageView heading = new ImageView(new Image(color_swtich.toURI().toString()));
        heading.relocate(50, 30);

        Button Continue=new Button("Continue");
        Continue.setPrefSize(200 , 50 );
        Continue.setLayoutX(100);
        Continue.setLayoutY(330);

        Button home=new Button("Home");
        home.setPrefSize(200 , 50);
        home.setLayoutX(100);
        home.setLayoutY(400);

        Text score = new Text(75,250 , "Your Score is " + points);
        score.setFont(Font.font("Arial Bold", 40));
        score.setFill(Color.WHITE);

        if ( points >= 5){
            Text you_can_continue = new Text(105,280 , "You can continue\n using your stars");
            you_can_continue.setFont(Font.font("Arial", 25));
            you_can_continue.setFill(Color.WHITE);
            pane.getChildren().addAll(heading,Continue,home,score, you_can_continue);
        }
        else{
            pane.getChildren().addAll(heading,home,score);
        }




//
//        pane.getChildren().addAll(heading,Continue, home , score);
//          Scene scene = new Scene(Continue,width,height);
//          pauseStage.setScene(scene);

        pauseStage.setScene(new Scene(pane));
        pauseStage.setAlwaysOnTop(true);
        pauseStage.setResizable(true);

        pauseStage.show();

        Continue.setOnMouseClicked(e->{
            points -= 5;
            pointLabel.setText(String.valueOf(points));
            pauseStage.close();
            timeline.play();
        });

        home.setOnMouseClicked(e->{
            timeline.stop();
            pauseStage.close();
            System.out.println("home button clicked after collison");



//            BackToMainMenu();
//            System.out.println("Back to Main Menu Going");
            Parent root= null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//            timeline.stop();
            AnchorPaneGameplay.getChildren().setAll(root);

//            pauseStage.close();
//            timeline.play();
        });
    }

    private boolean checkGameOver() {
        //int i=points-5;
        for (int j=0;j<obstacles.size() ;j++)
        {
            if ( obstacles.get(j).detectCollision(Ball))
            {
                System.out.println("Collision happened ...");
                return true;
            }
        }
        return false;
    }


    public void BackToMainMenu() throws IOException {
        System.out.println("Back to Main Menu Going");
        Parent root= FXMLLoader.load(getClass().getResource("sample.fxml"));
        timeline.stop();
        AnchorPaneGameplay.getChildren().setAll(root);
    }

    public void PauseButtonClicked()  throws IOException {
        System.out.println("Pause button on Gameplay screen clicked");
        timeline.pause();


        // GameData(ArrayList<Obstacle> obstacleList , ArrayList<Star> starList , ArrayList<ColorChanger> colorChangerList ,
        //             int points , double ballPosition ,  Color ballColor)

        //System.out.println("serialization begins");
        //GameData game_ka_data = new GameData(obstacles , stars , colorChangers , points , Ball.getCenterY());
        //gameDataSerialize.serialize(game_ka_data , "dholchike");
        //System.out.println("serializations ends");



//        System.out.println("Save button on Gameplay screen clicked");
//        Parent root=FXMLLoader.load(getClass().getResource("SavedGamePage.fxml"));
//        AnchorPaneGameplay.getChildren().setAll(root);



        File file=new File("src/Images/pauseScreen.jpg");
        Image image=new Image(file.toURI().toString());
        ImageView view=new ImageView(image);
//        ImageView starView=new ImageView(image);
        view.setFitWidth(width);
        view.setFitHeight(height);
        view.setX(0);
        view.setY(0);

        Button resumeButton=new Button("Resume");
        resumeButton.setLayoutX(149);
        resumeButton.setLayoutY(249);
        resumeButton.setPrefHeight(102);
        resumeButton.setPrefWidth(117);
        resumeButton.setMnemonicParsing(false);
        resumeButton.setOpacity(0);


        Button homeButton=new Button("Home");
        homeButton.setLayoutX(21);
        homeButton.setLayoutY(32);
        homeButton.setPrefWidth(62);
        homeButton.setPrefHeight(49);
        homeButton.setMnemonicParsing(false);
        resumeButton.setOpacity(0);

        Pane pane=new Pane();
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);
        pane.getChildren().addAll(view,resumeButton, homeButton);

        Stage stage= (Stage) AnchorPaneGameplay.getScene().getWindow();
        Stage pauseStage = new Stage();
//        pauseStage.initOwner(stage);
        pauseStage.setMaxWidth(width);
        pauseStage.setMaxHeight(height);
//        pauseStage.initModality(Modality.APPLICATION_MODAL);
        pauseStage.setScene(new Scene(pane, Color.BLUE ));
        pauseStage.setAlwaysOnTop(true);
        pauseStage.setResizable(false);

        resumeButton.setOnAction(e->{
            timeline.play();
            pauseStage.close();
        });

        homeButton.setOnAction(e->{
            System.out.println("Home button on pause screen clicked");
            Parent root= null;
            try {
                root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            AnchorPaneGameplay.getChildren().setAll(root);
            pauseStage.close();
            stage.show();

//            Stage loaded_game_stage = new Stage();
//
//
//
//            loaded_game_stage.setMaxWidth(width);
//            loaded_game_stage.setMaxHeight(height);
////        pauseStage.initModality(Modality.APPLICATION_MODAL);
//            //pauseStage.setScene(new Scene(pane, Color.BLUE ));
//            loaded_game_stage.setAlwaysOnTop(true);
//            loaded_game_stage.setResizable(false);
//
//            try {
//                GameData extracted_data =  gameDataSerialize.deserialize();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//
//            loaded_game_stage.setScene(new Scene(new Pane() , width , height)) ;
//            loaded_game_stage.show();


        });

        pauseStage.show();
    }

    public void SaveButtonClicked() throws IOException {
        System.out.println("Save button on Gameplay screen clicked");
        Parent root=FXMLLoader.load(getClass().getResource("SavedGamePage.fxml"));
        AnchorPaneGameplay.getChildren().setAll(root);
    }

    public void MoveUp() {
        trans.setToY(Ball.getCenterY()-50);
        Ball.setCenterY(Ball.getCenterY()-50);
        trans.play();
        System.out.println("Ball Moved up");

        moveObstacles();
    }

    private void moveObstacles() {

    }
}