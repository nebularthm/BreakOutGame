package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.*;



class GameTest extends DukeApplicationTest {
    // create an instance of our game to be able to call in tests (like step())
    private final Game myGame = new Game();
    // keep created scene to allow mouse and keyboard events
    private Scene myScene;
    // keep any useful elements whose values you want to test directly in multiple tests
    private Paddle myPaddle;
    // TODO: add many blocks
    private Ball myBall;
    private ImageView boundary;
    private int myBlockSpeedX, myBlockSpeedY;
    private Bricks [][] level;//this is a 2D array of our bricks
    private ProgressBar healthBar;
    private Label hLabel;
    private Label scoreTrack;
    private int myScore;
    private Text scoreText;
    private ArrayList<ArrayList<Bricks>> levelAsList;
    private boolean winCon = false;
    private PowerUp bigpaddie;

    @Override
    public void start (Stage stage) {
        myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
        stage.setScene(myScene);
        stage.show();
        stage.setTitle(myGame.TITLE);
        myPaddle = lookup("#paddle").query();
        myBall = lookup("#ball_is_life").query();
        hLabel = lookup("#hLabel").query();
        scoreTrack = lookup("#scoreTrack").query();

    }

    @Test
    public void testPaddleInitialPosition () {
        assertEquals(Game.SIZE/2 - Game.BLOCK_SIZE/2, myPaddle.getX());
        assertEquals(4 * Game.SIZE/5, myPaddle.getY());

    }
    @Test
    public void testBallInitialPosition () {
        assertEquals(Game.SIZE/2 - 15, myBall.getX());
        assertEquals(Game.SIZE/2 + 60, myBall.getY());

        assertEquals(50,myBall.getBallSpeed());
    }/*
    @Test
    public void testBallInitialPosition () {
        assertEquals(Game.SIZE/2 - 15, myBall.getX());
        assertEquals(Game.SIZE/2 - 15, myBall.getY());
        assertEquals(30, myBall.getWidth());
        assertEquals(30, myBall.getHeight());
    }
    */

    @Test
    public void testPaddleMove () {
        // given mover is in middle of the scene
        myPaddle.setX(myScene.getWidth() / 2);
        myPaddle.setY(myScene.getHeight() / 2);
        // move it up one step by "pressing" the up arrow
        // PAUSE: not typically recommended in tests
        press(myScene, KeyCode.LEFT);
        // PAUSE: but useful when debugging to verify what is happening
        // then check its position has changed properly
        assertEquals(190, myPaddle.getX());
        assertEquals(200, myPaddle.getY());
    }
    @Test
    public void testHealthAndScore(){
        assertEquals(7 * Game.SIZE/8, hLabel.getLayoutY());
        assertEquals(0,hLabel.getLayoutX());
        assertEquals(Game.SIZE * 4/5,scoreTrack.getLayoutX());
        assertEquals(Game.SIZE* 7/8, scoreTrack.getLayoutY());
    }

}
