package sample;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;

public class GameData implements Serializable {

    ArrayList<Obstacle> obstacleList;
    ArrayList<Star> starList;
    ArrayList<ColorChanger> colorChangerList;
    int points;
    double ballPosition;
    Color ballColor;


    GameData(ArrayList<Obstacle> obstacleList , ArrayList<Star> starList , ArrayList<ColorChanger> colorChangerList ,
             int points , double ballPosition ){

        this.obstacleList = obstacleList;
        this.starList = starList;
        this.colorChangerList = colorChangerList;
        this.points = points;
        this.ballPosition = ballPosition;
        //this.ballColor = ballColor;

    }


}

class GameDataSerialize {

    public void serialize(GameData gameplayPage, String name) throws IOException {
        ObjectOutputStream out = null;
        try {
            FileOutputStream fileOutObject = new FileOutputStream("D:/AP/savedgames.txt");
            out = new ObjectOutputStream(fileOutObject);
            out.writeObject(gameplayPage);
        } finally {
            out.close();
        }
    }

    public GameData deserialize() throws IOException {
        GameData a = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("D:/AP/savedgames.txt"));
            a = (GameData) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        assert a != null;

//        Main.stage.setScene(a.setup());
        return a;
    }
}

//    public void serialize(TestClass gameplayPage, String name) throws IOException {
//        ObjectOutputStream out = null;
//        try {
//            FileOutputStream fileOutObject = new FileOutputStream("/home/hitesh/savedgames.txt");
//            out = new ObjectOutputStream(fileOutObject);
//            out.writeObject(gameplayPage);
//        } finally {
//            out.close();
//        }


//    public TestClass deserialize() throws IOException
//    {
//        //TestClass a=null;
//        ObjectInputStream in=null;
//        try{
//            in=new ObjectInputStream(new FileInputStream("/home/hitesh/savedgames.txt"));
//            a=(TestClass) in.readObject();
//        }
//        catch (IOException | ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        finally {
//            in.close();
//        }
//        assert a!=null;
//
////        Main.stage.setScene(a.setup());
//
//        return a;
//    }
//
//
//}
