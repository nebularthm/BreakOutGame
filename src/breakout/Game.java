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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Random;


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


        public static final int BALL_SPEED = 50;

    public static final String BALL_PICTURE = "https://vignette.wikia.nocookie.net/idle-breakout/images/4/4b/Screen_Shot_2019-04-06_at_4.04.05_PM.png/revision/latest/top-crop/width/360/height/450?cb=20190406210459";

    public static final String PADDLE_PICTURE = "https://docs.microsoft.com/en-us/windows/uwp/get-started/images/monogame-tutorial-1.png";
    // some things we need to remember during our game
    public static final String BIGGERPADDLEIMAGE = "https://i.pinimg.com/originals/b4/92/d5/b492d594465b29bcbe7ff840fa18c896.png";
        private static final double BALL_PENALTY = 0.25 ;

    //TODO: Find better Brick textures, all you have to do here is just add themm to the sources, and then read the file like how i showed you
    //These URLS correspond to the brick images

    //These URL's correspond to paddle Images
    private static final String  LASERIMAGE = "https://labs.phaser.io/assets/animations/lazer/lazer_01.png";
    private static final String MULTIBALLIMAGE = "https://www.logolynx.com/images/logolynx/c3/c3fc5f02c40e5e729e646a2ca97871ca.jpeg";
    private static final String LEVELSKIPIMAGE = "https://lh3.googleusercontent.com/proxy/YHqYDsLvTKimMM9CdmN7NP4foZtIrQDY-S_9aOSM2c4HFkJu4icxQz7XX8AM8r32ExbbkVKLTIGfD40C_vX5DoTpfJaWNXb1Ky5kXfvCcy_1LcsjEgF1";
    private static final String FASTBALLIMAGE = "https://i.pinimg.com/originals/a2/f4/14/a2f414b603535c3726a8403de2ed99db.png";
    private static final String FASTPADDLEIMAGE = "https://steamuserimages-a.akamaihd.net/ugc/874122921369426925/E1E9341ECFFFDD61C985EB5B3AE35672F878B523/";
    private static final String TURBOIMAGE = "data:image/jpeg;base64,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";
    private static final String SLOWIMAGE = "https://media.merchantcircle.com/32160242/circle%20s1_full.jpeg";
    private static final String DMGIMAGE = "data:image/jpeg;base64,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";

    //this next part is just for the powerUP types
    private static final String BIG_PADDLE = "bigpad";
    private static final String LEVELSKIP = "levskip";
    private static final String FASTER_BALL = "fastball";
    private static final String FASTPADDLE = "fastpad";
    private static final String MULTIBALL = "moreball";
    private static final String TURBOMODE = "turb";
    private static final String SLOWMO = "slow";
    private static final String DOUBLEDMG = "doub";



    // some things we need to remember during our game
        private Scene myScene;
        private Timeline myAnimation;
        private Paddle myPaddle;
        private Ball myBall;
        private ArrayList<Ball> multiBalls;
        private ImageView boundary;
        private int myBlockSpeedX, myBlockSpeedY;
       //this is a 2D array of our bricks
        private ProgressBar healthBar;
        private HBox healthBarLabel;
        private Label hLabel;
        private Label scoreTrack;
        private int myScore;
        private Text scoreText;

        private boolean winCon = false;
        private PowerUp powerUp;
        private Group root;
        private boolean isPowerUP = false;
        private Menu myMenu;
        private int dmgPenalty;
        private ArrayList<PowerUp> possiblePowerUps;
        private LevelBuilder level;
        private boolean areManyBalls = false;

    /**
     * this method returns data structure containing all possible powerUps, and a powerUP will be randomly selected
     * @return
     */
    private ArrayList<PowerUp> allPowerUps(){
            ArrayList<PowerUp> retList = new ArrayList<>();
            PowerUp bigpad = new PowerUp(new Image(BIGGERPADDLEIMAGE,30,30,false,false));
            bigpad.setType(BIG_PADDLE);
            retList.add(bigpad);
        PowerUp manyball = new PowerUp(new Image(MULTIBALLIMAGE,30,30,false,false));
        manyball.setType(MULTIBALL);
        retList.add(manyball);
//        PowerUp bigpad = new PowerUp(new Image(BIGGERPADDLEIMAGE,30,30,false,false));
//        retList.add(bigpad);
//        PowerUp bigpad = new PowerUp(new Image(BIGGERPADDLEIMAGE,30,30,false,false));
//        retList.add(bigpad);
//        PowerUp bigpad = new PowerUp(new Image(BIGGERPADDLEIMAGE,30,30,false,false));
        //retList.add(bigpad);
            return retList;
        }


        private void powerUPEffect(String type){
            if(type.equals(BIG_PADDLE)){
                bigify();
            }
            if(type.equals(MULTIBALL)){
                multiply();
            }
        }

    /**
     * this method adds 5 balls to the screen, biracial
     */
    private void multiply() {
    if(areManyBalls == true){
        return;
    }
        multiBalls = makeMoreBalls();
    for(Ball ball:multiBalls){
        ball.setSpeedX(myBlockSpeedX);
        ball.setSpeedY(myBlockSpeedY);
        root.getChildren().add(ball);
    }
    }

    /**
     * This method returns a data structure data
     * @return
     */
    private ArrayList<Ball> makeMoreBalls() {
        ArrayList<Ball> retList = new ArrayList<>();
        Image ballImage = new Image(BALL_PICTURE,30,30,false,false);
        retList.add(new Ball(ballImage,0,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,myScene.getWidth()/4,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,myScene.getWidth()/2 - 15,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,3 * myScene.getWidth()/4,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,7 * myScene.getWidth()/8,myScene.getHeight()/2 +60));
        return retList;
    }

    /**
     * this method doubles the size of the paddle
     */
    private void bigify() {
        myPaddle.setFitWidth(myPaddle.getImage().getWidth() * 2);
    }

    /**
     * this code is from stack, essentially converts
     * @param twoDArray 2d array of bricks
     * @return
     */

    /**
     * this method constructs the grid of bricks by reading the config file for a level
     * @param levelSource string representing filename
     * @return
     */


    /**
     * based on the type of brick, we set the HP of this brick
     * @param brickType type of brick in the file
     * @return
     */

        /**
         * Initialize what will be displayed and how it will be updated.
         */
        @Override
        public void start (Stage stage) {
            // attach scene to the stage and display it
            myScene = setupScene(SIZE, SIZE, BACKGROUND);
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
        Scene setupScene (int width, int height, Paint background) {
            // create one top level collection to organize the things in the scene
            root = new Group();
            // make some shapes, set their properties, and add them to the scene
            Image ballImage = new Image(BALL_PICTURE,30,30,false,false);
            myBall = new Ball(ballImage,width/2 - 15,height/2 +60);
            dmgPenalty = 1;
            myBlockSpeedX = BALL_SPEED;
            myBlockSpeedY = BALL_SPEED;
            myBall.setSpeedX(myBlockSpeedX);
            myBall.setSpeedY(myBlockSpeedY);
            root.getChildren().add(myBall);
            myPaddle = new Paddle(new Image(PADDLE_PICTURE, BLOCK_SIZE + 100,BLOCK_SIZE,false,false),width/2 , height - 100);

            root.getChildren().add(myPaddle);

            // create a place to see the shapes
            myScene = new Scene(root, width, height, background);

            level = new LevelBuilder("data/lev1.txt",width,height);
            level.setLevelAsList();

            for(ArrayList<Bricks> brickies:level.getLevelAsList()){
                for(Bricks brick: brickies){
                    root.getChildren().add(brick);
                }
            }
            //create boundary that ball cannot pass over
            boundary = new ImageView();
            boundary.setImage(new Image("https://i.redd.it/rkfe2i3pdqqx.jpg",myScene.getWidth(),BLOCK_SIZE,false,false));
            boundary.setY(4 * height/5);
            boundary.setId("boundary");
            root.getChildren().add(boundary);
            healthBar = new ProgressBar(1);
            healthBarLabel = new HBox();//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/progress.htm I am gonna use a ProgressBar to represent the health we have

            hLabel = new Label("Health",healthBar);
            hLabel.setLayoutY(7 * height/8);
            hLabel.setId("hLabel");
            root.getChildren().add(hLabel);
            myScore = 0;
            scoreText = new Text(myScore + "0");
            scoreTrack = new Label("Score: ", scoreText);
            scoreTrack.setLayoutY(7*height/8);
            scoreTrack.setLayoutX(width * 4/5);
            scoreTrack.setId("scoreTrack");
            root.getChildren().add(scoreTrack);
            FileInputStream imgFile = null;
            try {
                imgFile = new FileInputStream("data/Images/gameover.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image img = new Image(imgFile);

            myMenu = new Menu(img,SIZE/2,SIZE/2);
            myMenu.setFitWidth(100);
            myMenu.setFitHeight(100);


            possiblePowerUps = allPowerUps();

            // respond to
            myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
            return myScene;
        }

        // Change properties of shapes to animate them

    /**
     * this method handles the animation of the program
     * @param elapsedTime
     */
    void step (double elapsedTime) {




            // update attributes
            dropPowerUp(elapsedTime);

            updateBall(elapsedTime);
            if(multiBalls != null){
                updateManyBalls(elapsedTime);
                areManyBalls = true;
            }
            if(multiBalls != null && multiBalls.size() == 0){
                areManyBalls = false;
        }

            // check for collisions TODO: Make  a physics class that handles this, for now I will figure out how to do this later
             if(myBall.getBoundsInParent().intersects(boundary.getBoundsInParent())){
                myBall.setX(0);
                myBall.setY(myScene.getHeight()/2);
                healthBar.setProgress(healthBar.getProgress() - BALL_PENALTY);
                hLabel = new Label("Health", healthBar);
            }
            if(healthBar.getProgress() == 0){//this is if you run out of health
                System.out.println("You lost the game and you suck");
                root.getChildren().clear();
                root.getChildren().add(myMenu);
                // TODO: Figure out how to get the button on the menu
//                Button ret = myMenu.getReset();
//                root.getChildren().add(ret);
                //myAnimation.stop();
            }
            //if you hit the paddle, bounce as if you hit the wall
            if(myBall.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
            myBall.setSpeedY(myBall.getBallSpeedY() * -1);
            myBall.setSpeedX(myBall.getBallSpeedX() * -1);

        }
            //check for case when you hit a brick
            updateBricks(myBall);
            destroyBricks();
            // TODO: check for win and, if true, pause the animation
        }

    /**
     * this method is responsible for updating many balls in the case of the multiball powerup
     * @param elapsedTime
     */
    private void updateManyBalls(double elapsedTime) {
        Iterator<Ball> itr = multiBalls.iterator();
        while(itr.hasNext()){
            Ball ball = itr.next();
            ball.setX(ball.getX() + ball.getBallSpeedX() * elapsedTime);
            if (ball.getX() + ball.getWidth()>= myScene.getWidth() || ball.getX() <= 0 ) {
                ball.setSpeedX(ball.getBallSpeedX() * -1);
            }
            ball.setY(ball.getY() + ball.getBallSpeedY() * elapsedTime);
            if (ball.getY()  >= myScene.getHeight() || ball.getY() <= 0 ) {
                ball.setSpeedY(ball.getBallSpeedY() * -1);
            }
            if(ball.getBoundsInParent().intersects(boundary.getBoundsInParent())){
               ball.setImage(null);
               itr.remove();
            }
            if(ball.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
                ball.setSpeedY(ball.getBallSpeedY() * -1);
                ball.setSpeedX(ball.getBallSpeedX() * -1);

            }
            updateBricks(ball);
        }
    }

    /**
     * this method handles updating the health of bricks as they are collided into
     */
    private void updateBricks(Ball ball) {
        for(ArrayList<Bricks> brickies: level.getLevelAsList()){
            for(Bricks brick: brickies){
                if(ball.getBoundsInParent().intersects(brick.getBoundsInParent())){
                    ball.setSpeedY(ball.getBallSpeedY() * -1);
                    ball.setSpeedX(ball.getBallSpeedX() * -1);
                    brick.updateHealth(dmgPenalty);
                    brick.updateDestroyed();
                    myScore += 10;
                    scoreText.setText(myScore + "0");
                    scoreTrack = new Label("Score: ",scoreText);

                }
            }
        }
    }

    /**
     * This method handles the destruction of bricks
     */
    private void destroyBricks() {
        for(ArrayList<Bricks> brickies:level.getLevelAsList()){
            Iterator<Bricks> itr = brickies.iterator();
            while(itr.hasNext()){
                Bricks brick = itr.next();
                if(brick.isDestroyed()){
                    brick.setImage(null);
                    itr.remove();
                    root.getChildren().remove(brick);
                    if(isPowerUP == false) {
                        isPowerUP = true;

                        Random rand = new Random();
                        powerUp = possiblePowerUps.get(rand.nextInt(possiblePowerUps.size()));
                        powerUp.setX(brick.getX());
                        powerUp.setY(brick.getY());
                        root.getChildren().add(powerUp);
                    }
                }
            }
        }
    }

    /**
     * This method updates the positioning of the ball
     * @param elapsedTime
     */
    private void updateBall(double elapsedTime) {
        myBall.setX(myBall.getX() + myBall.getBallSpeedX() * elapsedTime);
        if (myBall.getX() + myBall.getWidth()>= myScene.getWidth() || myBall.getX() <= 0 ) {
           myBall.setSpeedX(myBall.getBallSpeedX() * -1);
        }
        myBall.setY(myBall.getY() + myBall.getBallSpeedY() * elapsedTime);
        if (myBall.getY()  >= myScene.getHeight() || myBall.getY() <= 0 ) {
            myBall.setSpeedY(myBall.getBallSpeedY() * -1);
        }
    }

    /**
     * This method drops a powerup from a brick that was destroyed on the PREVIOUS frame
     * @param elapsedTime how much time has elapsed
     */
    private void dropPowerUp(double elapsedTime) {
        if(isPowerUP) {
            powerUp.setY(powerUp.getY() + 50 * elapsedTime);
            if (powerUp.getBoundsInParent().intersects(myPaddle.getBoundsInParent())) {
                powerUPEffect(powerUp.getType());
                isPowerUP = false;
                root.getChildren().remove(powerUp);
            }
            if(powerUp.getBoundsInParent().intersects(boundary.getBoundsInParent())){
                isPowerUP = false;
                root.getChildren().remove(powerUp);
            }
        }
    }


    /**
     * counts the bricks remaining in the grid
     * @return the int with bricks
     */
    private int brickCount(){
        int brickAmount = 0;
        for(ArrayList<Bricks> brickies:level.getLevelAsList()){
            Iterator<Bricks> itr = brickies.iterator();
            while(itr.hasNext()){
                brickAmount++;
            }
        }
        return brickAmount;
        }

        // What to do each time a key is pressed

    /**
     * handles what ahppens when the player presses a certain key
     * @param code the key the player pressed
     */
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
            if(code == KeyCode.P){
                isPowerUP = true;
                Random rand = new Random();
                powerUp = possiblePowerUps.get(rand.nextInt(possiblePowerUps.size()));
                root.getChildren().add(powerUp);
            }
            if(code == KeyCode.B){
                myBall.setFitWidth(myBall.getWidth() * 2);
            }
            if(code == KeyCode.D){// we are going to break the first undamaged brick
                for(ArrayList<Bricks> brickies:level.getLevelAsList()){
                    Iterator<Bricks> itr = brickies.iterator();
                    while(itr.hasNext()){
                        Bricks brick = itr.next();
                        if(!brick.isDestroyed()){
                            brick.setImage(null);
                            itr.remove();
                            root.getChildren().remove(brick);
                            if(isPowerUP == false) {
                                isPowerUP = true;
                                Random rand = new Random();
                                powerUp = possiblePowerUps.get(rand.nextInt(possiblePowerUps.size()));
                                root.getChildren().add(powerUp);
                            }
                            return;
                        }
                    }
                }
            }
            //setRate - increases the rate
            if(code == KeyCode.F){//this engages fast mode
                myAnimation.setRate(5);
            }
            if(code == KeyCode.S){//this engages fast mode
                myAnimation.setRate(.1);
            }
            if(code == KeyCode.N){//makes stuff normal
                myAnimation.setRate(1);
            }
        }
        public static void main (String[] args) {
            launch(args);
        }



    }
