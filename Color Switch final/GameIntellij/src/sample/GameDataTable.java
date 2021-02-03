package sample;

import java.io.Serializable;
import java.util.ArrayList;



public class GameDataTable implements Serializable
{
    int score;
    int ballColor;
    private static GameDataTable gameDataTable=null;

    public static GameDataTable getInstance()
    {
        if (gameDataTable==null)
            gameDataTable=new GameDataTable();
        return gameDataTable;
    }

    public GameDataTable()
    {
        score=0;
        ballColor=1;
    }

    public GameDataTable(int score, int ballColor)
    {
        this.score=score;
        this.ballColor=ballColor;
    }

}


//
//public class GameDataTable implements Serializable {
//    int score;
//    int ballcolor;
//
//    // save our obstacle arraylist somehow
//
//    ArrayList<Double> obstacleLocation;
//
//
//    //
//
//    //
//
//    public GameDataTable(){
//
//    }
//
//
//
//}
