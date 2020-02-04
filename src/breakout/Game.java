package breakout;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


    /**
     * A simple frogger-like game.
     *
     * @author Robert C. Duvall
     */
    public class Game extends Application {
        public static final String TITLE = "Super Breakout";
        public static final String WINNING_MESSAGE = "WINNER!";
        public static final int SIZE = 400;
        public static final int FRAMES_PER_SECOND = 60;
        public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
        public static final Paint BACKGROUND = Color.AZURE;
        public static final int MOVER_SPEED = 5;
        public static final int BLOCK_SIZE = 30;
        public static final int BLOCK_MIN_SPEED = 10;
        public static final int BLOCK_MAX_SPEED = 100;
        public static final int BLOCK_SPEEDUP_FACTOR = 2;
        public static final int BALL_SPEED = 5;
        public static final Image BALL_PICTURE = new Image("https://vignette.wikia.nocookie.net/idle-breakout/images/4/4b/Screen_Shot_2019-04-06_at_4.04.05_PM.png/revision/latest/top-crop/width/360/height/450?cb=20190406210459"); //TODO: Insert the initial image of the ball here, for now I am using the link provided just for testing purposes


        // some things we need to remember during our game
        private Scene myScene;
        private Timeline myAnimation;
        private Paddle myPaddle;
        // TODO: add many blocks
        private Ball myBall;
        private int myBlockSpeed;


        /**
         * Initialize what will be displayed and how it will be updated.
         */
        @Override
        public void start (Stage stage) {
            // attach scene to the stage and display it
            myScene = setupScene(SIZE, SIZE, BACKGROUND,BALL_PICTURE);
            stage.setScene(myScene);
            stage.setTitle(TITLE);
            stage.show();
            // attach "game loop" to timeline to play it
            KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
            myAnimation = new Timeline();
            myAnimation.setCycleCount(Timeline.INDEFINITE);
            myAnimation.getKeyFrames().add(frame);
            myAnimation.play();
        }


        // Create the game's "scene": what shapes will be in the game and their starting properties
        Scene setupScene (int width, int height, Paint background, Image ballImage) {
            // create one top level collection to organize the things in the scene
            Group root = new Group();
            // make some shapes, set their properties, and add them to the scene
            myBall = new Ball(ballImage,0,height/2);
            myBall.setSpeed(BALL_SPEED);
            root.getChildren().add(myBall);
            myPaddle = new Paddle(BLOCK_SIZE/2, BLOCK_SIZE,width/2,height/2);
            root.getChildren().add(myPaddle);
            // create a place to see the shapes
            myScene = new Scene(root, width, height, background);
            // respond to
            myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
            return myScene;
        }

        // Change properties of shapes to animate them
        void step (double elapsedTime) {
            // get internal values of other classes
//            Rectangle blockShape = myBall.getShape();
//            Rectangle moverShape = myPaddle.getShape();

            // update attributes
            myBall.setX(myBall.getX() + myBlockSpeed * elapsedTime);
            if (myBall.getX() > myScene.getWidth()) {
                myBall.setX(0);
                if (myBlockSpeed < BLOCK_MAX_SPEED) {
                    myBlockSpeed += BLOCK_SPEEDUP_FACTOR;
                }
            }
            // check for collisions TODO: Make  a physics class that handles this, for now I will figure out how to do this later
//            if (Shape.intersect(myPaddle, myBall).getBoundsInLocal().getWidth() != -1) {
//                // reset player's position to the start
//                myPaddle.setX(myScene.getWidth() / 2 - myPaddle.getWidth() / 2);
//                myPaddle.setY(myScene.getHeight() - myPaddle.getHeight());
//            }
            // TODO: check for win and, if true, pause the animation
        }

        // What to do each time a key is pressed
        private void handleKeyInput (KeyCode code) {
            // move player
            Rectangle moverShape = myPaddle;
            if (code == KeyCode.RIGHT) {
                moverShape.setX(moverShape.getX() + MOVER_SPEED);
            }
            else if (code == KeyCode.LEFT) {
                moverShape.setX(moverShape.getX() - MOVER_SPEED);
            }
            else if (code == KeyCode.UP) {
                moverShape.setY(moverShape.getY() - MOVER_SPEED);
            }
            else if (code == KeyCode.DOWN) {
                moverShape.setY(moverShape.getY() + MOVER_SPEED);
            }
            // pause/restart animation
            if (code == KeyCode.SPACE) {
                if (myAnimation.getStatus() == Animation.Status.RUNNING) {
                    myAnimation.pause();
                }
                else {
                    myAnimation.play();
                }
            }
        }
        public static void main (String[] args) {
            launch(args);
        }



    }
