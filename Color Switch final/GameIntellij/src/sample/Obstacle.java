package sample;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public abstract class Obstacle implements Colorable {
    Group root;

    protected abstract boolean detectCollision(Shape ball);

    protected boolean CollisionDup(Shape ball, ObservableList<Node> children) {
        for (Node node : children) {
            Rectangle child = (Rectangle) node;
            Shape intersect = Shape.intersect( child , ball);

//            Bounds ballBounds=ball.localToScene(ball.getBoundsInLocal());
//            Bounds startBounds=starView.localToScene(starView.getBoundsInLocal());
//            return startBounds.intersects(ballBounds);

            if ( intersect.getBoundsInLocal().getWidth() != -1 && child.getFill().equals(ball.getFill())==false )
                return true;
        }
        return false;
    }
}

class CircleObject extends Obstacle
{
    CircleObject(double x, double y)
    {
        Arc arc1=new Arc(x, y, 100, 100, 0, 90);
        arc1.setFill(colors[0]);
        arc1.setType(ArcType.OPEN);

        Arc arc2=new Arc(x, y, 100, 100, 90, 90);
        arc2.setFill(colors[1]);
        arc2.setType(ArcType.OPEN);

        Arc arc3=new Arc(x, y, 100, 100, 180, 90);
        arc3.setFill(colors[2]);
        arc3.setType(ArcType.OPEN);

        Arc arc4=new Arc(x, y, 100, 100, 270, 90);
        arc4.setFill(colors[3]);
        arc4.setType(ArcType.OPEN);

        root=new Group(arc1, arc2, arc3, arc4);

        RotateTransition rotate=new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(10));
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(root);
        rotate.play();
    }

    @Override
    public boolean detectCollision(Shape ball)
    {
        for (Node nodes_shapes: root.getChildren())
        {
            Shape arcs=(Shape) nodes_shapes;
            Shape intersect=Shape.intersect(arcs, ball);
            // && arcs.getFill().equals(ball.getFill())w
            if (intersect.getBoundsInLocal().getWidth() !=-1 && arcs.getFill().equals(ball.getFill())==false)
                return true;
        }
        return false;
    }

//    @Override
//    public boolean detectCollision(Shape ball) {
//        return CollisionDup(ball, root.getChildren());
//    }
}

/*
class doubleCircle extends Obstacle
{
    {
        root = new Group();
    }

    doubleCircle(double x, double y)
    {
        double leftRingX=x-150;
        Arc arc1=new Arc(leftRingX, y, 100, 100, 0, 90);
        arc1.setFill(colors[0]);
        arc1.setType(ArcType.ROUND);

        Arc arc2=new Arc(leftRingX, y, 100, 100, 90, 90);
        arc2.setFill(colors[1]);
        arc2.setType(ArcType.ROUND);

        Arc arc3=new Arc(leftRingX, y, 100, 100, 180, 90);
        arc3.setFill(colors[2]);
        arc3.setType(ArcType.ROUND);

        Arc arc4=new Arc(leftRingX, y, 100, 100, 270, 90);
        arc4.setFill(colors[3]);
        arc4.setType(ArcType.ROUND);

        root=new Group(arc1, arc2, arc3, arc4);

        RotateTransition rotate=new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(10));
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(root);
        rotate.play();

        double rightRingX=x+150;
        Arc arc5=new Arc(rightRingX, y, 100, 100, 0, 90);
        arc5.setFill(colors[0]);
        arc5.setType(ArcType.ROUND);

        Arc arc6=new Arc(rightRingX, y, 100, 100, 90, 90);
        arc6.setFill(colors[1]);
        arc6.setType(ArcType.ROUND);

        Arc arc7=new Arc(rightRingX, y, 100, 100, 180, 90);
        arc7.setFill(colors[2]);
        arc7.setType(ArcType.ROUND);

        Arc arc8=new Arc(rightRingX, y, 100, 100, 270, 90);
        arc8.setFill(colors[3]);
        arc8.setType(ArcType.ROUND);

        root=new Group(arc5, arc6, arc7, arc8);

        RotateTransition rotate2=new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(-360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setDuration(Duration.seconds(10));
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.setNode(root);
        rotate2.play();
    }

    @Override
    public boolean detectCollision(Shape ball) {
        return CollisionDup(ball, root.getChildren());
    }

}
*/

class Cross extends Obstacle implements Colorable
{
    Cross(double x, double y)
    {
        Rectangle[] rectangles=new Rectangle[4];
        Rotate[] rotates=new Rotate[4];
        for (int i=0;i<4;i++)
        {
            rectangles[i]=new Rectangle();
            rectangles[i].setWidth(10);
            rectangles[i].setHeight(80);
            rectangles[i].setX(x);
            rectangles[i].setY(y);
            rectangles[i].setFill(colors[i]);
            rotates[i]=new Rotate();
            rotates[i].setPivotX(x);
            rotates[i].setPivotY(y);
            rectangles[i].getTransforms().add(rotates[i]);
        }

        rotates[0].setAngle(0);
        rotates[1].setAngle(90);
        rotates[2].setAngle(180);
        rotates[3].setAngle(270);

        root=new Group(rectangles);

        RotateTransition rotate=new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(10));
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(root);
        rotate.play();
    }

    @Override
    public boolean detectCollision(Shape ball)
    {
        for (Node nodes_shapes: root.getChildren())
        {
            Shape arcs=(Shape) nodes_shapes;
            Shape intersect=Shape.intersect(arcs, ball);
            // && arcs.getFill().equals(ball.getFill())
            if (intersect.getBoundsInLocal().getWidth() !=-1 && arcs.getFill().equals(ball.getFill())==false)
                return true;
        }
        return false;
    }

//    @Override
//    public boolean detectCollision(Shape ball) {
//        return CollisionDup(ball, root.getChildren());
//    }

}

/*
class HorBars extends Obstacle implements Colorable
{
    //    Group group;
    int barsNumber=4;
    double width=420;
    double barsLength=100, barsHeight=10;
    double barsY=100;
    Rectangle[] bars=new Rectangle[barsNumber];

    HorBars(double x, double y) {

        for (int i=0;i<barsNumber;i++)
        {
            bars[i]=new Rectangle();
            bars[i].setHeight(barsHeight);
            bars[i].setWidth(barsLength);
            bars[i].setX(100*i);
            bars[i].setFill(colors[i]);
            bars[i].setLayoutY(barsY);
        }

        TranslateTransition[] translate = new TranslateTransition[4];
        for (int i = 0; i< barsNumber; i++)
            translate[i]=new TranslateTransition();

        for (int i = 0; i< barsNumber; i++)
        {
            translate[i].setDuration(Duration.seconds(3));
            translate[i].setCycleCount(Animation.INDEFINITE);
            translate[i].setInterpolator(Interpolator.LINEAR);
            translate[i].setAutoReverse(true);

            translate[i].setFromX(-200);
            translate[i].setToX(width - barsLength -150);
            translate[i].setNode(bars[i]);
        }

        for (int i = 0; i< barsNumber; i++)
            translate[i].play();

    }

    @Override
    public boolean detectCollision(Shape ball) {
        return CollisionDup(ball, root.getChildren());
    }
}
*/

class Square extends Obstacle implements Colorable
{

    RotateTransition rotateTransition;
    Square(double x, double y) {
        Rectangle rect1;
        Rectangle rect2;
        Rectangle rect3;
        Rectangle rect4;

        rect1 = new Rectangle();
        rect1.setWidth(10);
        rect1.setHeight(200);
        rect1.setFill(colors[0]);

        rect2 = new Rectangle();
        rect2.setWidth(10);
        rect2.setHeight(200);
        rect2.setFill(colors[1]);
        rect2.setRotate(90);

        rect3 = new Rectangle();
        rect3.setWidth(10);
        rect3.setHeight(200);
        rect3.setFill(colors[2]);
        rect3.setRotate(180);

        rect4 = new Rectangle();
        rect4.setWidth(10);
        rect4.setHeight(200);
        rect4.setFill(colors[3]);
        rect4.setRotate(270);

        rect1.relocate(x, y);
        rect2.relocate(x+100, y+100);
        rect3.relocate(x+200, y);
        rect4.relocate(x+100, y-100);

/*
        rect1.relocate(100, 200);
        rect2.relocate(200, 300);
        rect3.relocate(300, 200);
        rect4.relocate(200, 100);*/

        root = new Group(rect1, rect2, rect3, rect4);

        rotateTransition = new RotateTransition();
        rotateTransition.setAxis(Rotate.Z_AXIS);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setDuration(Duration.seconds(10));
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setNode(root);
        rotateTransition.play();
    }

    @Override
    public boolean detectCollision(Shape ball)
    {
        for (Node nodes_shapes: root.getChildren())
        {
            Shape arcs=(Shape) nodes_shapes;
            Shape intersect=Shape.intersect(arcs, ball);
            // && arcs.getFill().equals(ball.getFill())
            if (intersect.getBoundsInLocal().getWidth() !=-1 && arcs.getFill().equals(ball.getFill())==false)
                return true;

        }
        return false;
    }

//    @Override
//    protected boolean detectCollision(Shape ball) {
//        return CollisionDup(ball, root.getChildren());
//    }
}

class doublecircleObject extends Obstacle
{
    Group root2;
    Group root1;
    doublecircleObject(double x , double y)
    {

        Arc arc1=new Arc(x, y, 100, 100, 0, 90);
        arc1.setFill(colors[0]);
        arc1.setType(ArcType.OPEN);
        Arc arc2=new Arc(x, y, 100, 100, 90, 90);
        arc2.setFill(colors[1]);
        arc2.setType(ArcType.OPEN);
        Arc arc3=new Arc(x, y, 100, 100, 180, 90);
        arc3.setFill(colors[2]);
        arc3.setType(ArcType.OPEN);
        Arc arc4=new Arc(x, y, 100, 100, 270, 90);
        arc4.setFill(colors[3]);
        arc4.setType(ArcType.OPEN);
        Arc arc5 = new Arc(x,y,200 , 200 , 0-90 , 90 );
        arc5.setFill(colors[2]);
        arc5.setType(ArcType.OPEN);
        Arc arc6 = new Arc(x,y,200 , 200 , 90-90 , 90 );
        arc6.setFill(colors[1]);
        arc6.setType(ArcType.OPEN);
        Arc arc7 = new Arc(x,y,200 , 200 , 180-90 , 90 );
        arc7.setFill(colors[0]);
        arc7.setType(ArcType.OPEN);
        Arc arc8 = new Arc(x,y,200 , 200 , 270-90 , 90 );
        arc8.setFill(colors[3]);
        arc8.setType(ArcType.OPEN);

        root=new Group(arc1, arc2, arc3, arc4, arc5, arc6, arc7, arc8);

        root1=new Group(arc1, arc2, arc3, arc4);
        RotateTransition rotate=new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.seconds(10));
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(root1);
        rotate.play();

        root2 = new Group(arc5,arc6,arc7,arc8);
        RotateTransition rotate2=new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(-360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setDuration(Duration.seconds(10));
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.setNode(root2);
        rotate2.play();

//        root = new Group(root1,root2);
    }

    @Override
    public boolean detectCollision(Shape ball) {
        return false;
    }

//    @Override
//    protected boolean detectCollision(Shape ball) {
//        return false;
//    }
}