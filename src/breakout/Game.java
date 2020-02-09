package breakout;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


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
        public static final int PADDLE_SPEED = 10;
        public static final int BLOCK_SIZE = 30;
        public static final int BLOCK_MIN_SPEED = 10;
        public static final int BLOCK_MAX_SPEED = 100;
        public static final int BLOCK_SPEEDUP_FACTOR = 2;
        public static final int BRICK_AMOUNT = 5;
        public static final int BALL_SPEED = 50;
        public static final Image BALL_PICTURE = new Image("https://vignette.wikia.nocookie.net/idle-breakout/images/4/4b/Screen_Shot_2019-04-06_at_4.04.05_PM.png/revision/latest/top-crop/width/360/height/450?cb=20190406210459",30,30,false,false); //TODO: Insert the initial image of the ball here, for now I am using the link provided just for testing purposes
        public static final Image PADDLE_PICTURE = new Image("https://www.paddleballgalaxy.com/mm5/graphics/00000001/z5yellowcomp.jpg",BLOCK_SIZE,BLOCK_SIZE,false,false);
    private static final double BALL_PENALTY = 0.25 ;
    // some things we need to remember during our game
        private Scene myScene;
        private Timeline myAnimation;
        private Paddle myPaddle;
        // TODO: add many blocks
        private Ball myBall;
        private ImageView boundary;
        private int myBlockSpeedX, myBlockSpeedY;
        private Bricks [][] level;//this is a 2D array of our bricks
        private ProgressBar healthBar;
        private HBox healthBarLabel;
        private Label hLabel;
        private Label scoreTrack;
        private int myScore;

    /**
     * this method constructs the grid of bricks by reading the config file for a level
     * @param levelSource string representing filename
     * @return
     */
    private Bricks [][]  levelReader(String levelSource, int width, int height){
            Bricks [][] brickconfig = new Bricks[BRICK_AMOUNT-1][BRICK_AMOUNT];
            File lev  = new File(levelSource);
            Scanner levelScanner;
            try{
                levelScanner = new Scanner(lev);
            }
            catch(FileNotFoundException e){
                return new Bricks [0][0];
            }
            int i = 0;
            while(levelScanner.hasNextLine()){
                int j = 0;
                String levelLine = levelScanner.nextLine();
                String [] brickTypes = levelLine.split(" ");
                for(String type:brickTypes) {

                        Image defaultBrick = new Image("https://images-na.ssl-images-amazon.com/images/I/410eZ0DDF2L._SL500_AC_SS350_.jpg", 30, 30, false, false);
                        brickconfig[i][j] = new Bricks(defaultBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT));//this is so bricks are added ight next to the next brick
                        j++;
                }
                i++;
            }
            return brickconfig;
        }
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
            myPaddle = new Paddle(PADDLE_PICTURE,width/2,height/2);
            myBlockSpeedX = BALL_SPEED;
            myBlockSpeedY = BALL_SPEED;
            root.getChildren().add(myPaddle);

            // create a place to see the shapes
            myScene = new Scene(root, width, height, background);
            level = levelReader("data/tutorial.txt",width,height);
            for(Bricks [] brickies:level){
                for(Bricks brick: brickies){
                    root.getChildren().add(brick);
                }
            }
            //create boundary that ball cannot pass over
            boundary = new ImageView();
            boundary.setImage(new Image("https://i.redd.it/rkfe2i3pdqqx.jpg",myScene.getWidth(),BLOCK_SIZE,false,false));
            boundary.setY(4 * height/5);
            root.getChildren().add(boundary);
            healthBar = new ProgressBar(1);
            healthBarLabel = new HBox();//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/progress.htm I am gonna use a ProgressBar to represent the health we have

            hLabel = new Label("Health",healthBar);
            hLabel.setLayoutY(7 * height/8);
            root.getChildren().add(hLabel);
            myScore = 0;
            scoreTrack = new Label("Score: " + myScore + "0");
            scoreTrack.setLayoutY(7*height/8);
            scoreTrack.setLayoutX(width * 4/5);
            root.getChildren().add(scoreTrack);
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
            myBall.setX(myBall.getX() + myBlockSpeedX * elapsedTime);
            if (myBall.getX() >= myScene.getWidth() || myBall.getX() <= 0 ) {
               myBlockSpeedX *= -1;
            }
            myBall.setY(myBall.getY() + myBlockSpeedY * elapsedTime);
            if (myBall.getY() >= myScene.getHeight() || myBall.getY() <= 0 ) {
                myBlockSpeedY *= -1;
            }

            // check for collisions TODO: Make  a physics class that handles this, for now I will figure out how to do this later
//            if (Shape.intersect(myPaddle, myBall).getBoundsInLocal().getWidth() != -1) {
//                // reset player's position to the start
//                myPaddle.setX(myScene.getWidth() / 2 - myPaddle.getWidth() / 2);
//                myPaddle.setY(myScene.getHeight() - myPaddle.getHeight());
//            }
            //this is for when the ball hits the boundary
            if(myBall.getBoundsInParent().intersects(boundary.getBoundsInParent())){
                myBall.setX(0);
                myBall.setY(myScene.getHeight()/2);
                healthBar.setProgress(healthBar.getProgress() - BALL_PENALTY);
                hLabel = new Label("Health", healthBar);
            }
            if(healthBar.getProgress() == 0){
                System.out.println("You lost the game and you suck");
                myAnimation.stop();
            }
            //if you hit the paddle, bounce as if you hit the wall
            if(myBall.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
                myBlockSpeedY *= -1;
                myBlockSpeedX *= -1;
            }
            //check for case when you hit a brick
            for(Bricks [] brickies:level){
                for(Bricks brick: brickies){
                    if(myBall.getBoundsInParent().intersects(brick.getBoundsInParent())){
                        myBlockSpeedY *= -1;
                        myBlockSpeedX *= -1;
                        brick.updateDamage();
                    }
                }
            }
            for(Bricks [] brickies:level){
                for(Bricks brick: brickies){
                    if(brick.getDamge() == true){
                       brick = null;
                    }
                }
            }
            // TODO: check for win and, if true, pause the animation
        }

        // What to do each time a key is pressed
        private void handleKeyInput (KeyCode code) {
            // move player
            ImageView moverShape = myPaddle;
            if (code == KeyCode.RIGHT) {
                moverShape.setX(moverShape.getX() + PADDLE_SPEED);
            }
            else if (code == KeyCode.LEFT) {
                moverShape.setX(moverShape.getX() - PADDLE_SPEED);
            }
            else if (code == KeyCode.UP) {
                moverShape.setY(moverShape.getY() - PADDLE_SPEED);
            }
            else if (code == KeyCode.DOWN) {
                moverShape.setY(moverShape.getY() + PADDLE_SPEED);
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
            //when you press r this completely resets ball and paddle
            if(code == KeyCode.R){
                myBall.setX(0);
                myBall.setY(myScene.getHeight()/2);
                myPaddle.setX(myScene.getWidth()/2);
                myPaddle.setY(myScene.getHeight()/2);
            }
            if(code == KeyCode.L){//This block of code gives the player full health on the presing of the L key
                healthBar.setProgress(1);
                hLabel = new Label("Health", healthBar);
            }
        }
        public static void main (String[] args) {
            launch(args);
        }



    }
