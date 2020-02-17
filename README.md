game
====

This project implements the game of Breakout.

Name: Michael Williams

### Timeline

Start Date: 1/29/2020

Finish Date:  2/16/2020

Hours Spent: 35

### Resources Used
Code and Algorithms:

https://stackoverflow.com/questions/27878421/intersect-of-imageviews-in-javafx
https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/progress.htm
https://stackoverflow.com/questions/11447780/convert-two-dimensional-array-to-list-in-java
https://www.java67.com/2018/12/how-to-remove-objects-or-elements-while-iterating-Arraylist-java.html
https://stackoverflow.com/questions/37018643/javafx-image-remove-from-imageview
https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
https://www.geeksforgeeks.org/file-getpath-method-in-java-with-examples/
https://www.w3schools.com/java/java_files_create.asp
https://streamable.com/7dyb
https://stackoverflow.com/questions/49216396/clearing-the-scene-in-javafx
https://www.tutorialspoint.com/javafx/javafx_images.htm
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Button.html
https://www.tutorialspoint.com/javafx/javafx_effects.htm
https://www.java67.com/2012/11/how-to-read-file-in-java-using-scanner-example.html
https://javaconceptoftheday.com/how-to-sort-a-text-file-in-java/
http://tutorials.jenkov.com/javafx/label.html

The next set is just resources that I used for every ImageView and Image

https://vignette.wikia.nocookie.net/idle-breakout/images/4/4b/Screen_Shot_2019-04-06_at_4.04.05_PM.png/revision/latest/top-crop/width/360/height/450?cb=20190406210459
"https://docs.microsoft.com/en-us/windows/uwp/get-started/images/monogame-tutorial-1.png
https://labs.phaser.io/assets/animations/lazer/lazer_01.png
https://st2.depositphotos.com/5205783/9032/v/950/depositphotos_90328212-stock-illustration-logo-abstract-circle-l-design.jpg";
 https://www.logolynx.com/images/logolynx/c3/c3fc5f02c40e5e729e646a2ca97871ca.jpeg";
 https://i.pinimg.com/originals/a2/f4/14/a2f414b603535c3726a8403de2ed99db.png
 https://steamuserimages-a.akamaihd.net/ugc/874122921369426925/E1E9341ECFFFDD61C985EB5B3AE35672F878B523/
 "https://image.shutterstock.com/image-vector/initial-company-circle-t-logo-260nw-752158675.jpg
 "https://media.merchantcircle.com/32160242/circle%20s1_full.jpeg
 https://i.ya-webdesign.com/images/d-transparent-circle-logo-1.png
 https://st2.depositphotos.com/5943796/8708/v/950/depositphotos_87080608-stock-illustration-h-initial-circle-company-or.jpg";
https://i.redd.it/rkfe2i3pdqqx.jpg


### Running the Program

Main class: src/breakout/Game.Java is the main class, tests/breakout/GameTest is the testing class

Data files needed: 
Files needed are the files in the Data/Images folder for textures, The data/levels folder for the levels, and the data/score.txt file for the   scores

Key/Mouse inputs:
In Normal/Hard Mode- You press the left,right,up,and down arrow keys to move the paddle in the respective directions

In Co-O-p, the F1, F2, F3, and F4 keys correspond to the arrow keys listed above for controlling the second paddle

Cheat keys:

L- Skips to the next Level

1-5- Skips to the desired level, only works on the first five files in the levels directory

D- Destroys a brick, starting at the brick in the top left corner, then going down from the first column

F- Turbo Mode- If you are feeling really impatient/want a challenge, this increases the rate of animation by a factor of 5. Esentially makes every aspect of the game go faster. Use at your own caution

S- Slow Mode, opposite of turbo mode, makes the game run at 10% speed, use if you want to annoy your Co- Op partner, makes overall game very grueling

N- Normal Mode- For undoing the effects of flow and turbo mode, resets game back to original rate

H- Fully restores your HP bar

B- Increases size of the ball

O- Increases speed of ball and paddle

M- Increase size of the Paddle



Known Bugs:

If the ball is going too fast, there will be a "piercing" effect observed sometimes where the ball will pass through a destorryed brick and hit another one, only happens with mutliple speedups

In Hard Mode, both users cannot press a movement or cheat key at the exact same time, because these events willbe registered as 1 concurrent button press and the action will consequently not be done. As explained in the rules section, Co-Op is a team based exercise that is centered around communication. You can't just make changes willy nilly when working with a team, you have to be considerate of how they move as well

### PowerUps:
Bigger Paddle- Indicated by Circle P, doubles the size of the Paddle

Heal-  Heals you be 25%

Laser- Once per game, creates vertical laser that does periodic damage to all bricks in a column, second best powerUp in the game

TurboMode- Increasesspeed of the game by a factor of 5, really good when comboed with Bigger Paddle

Faster Ball- Increases ball speed

Faster PAddle- Increases paddle speed, but useless with bigger paddle. Necessary for hard Mode

Skip Level- Skips this level. If at last level, wins you the Game

Multi Ball- Creates 5 balls that do not deal damage to the player when entering restricted zone. These balls will be effected by increase damage powerUps, but not by powerUps that increase ball speed or cheat codes that increase Ball Size

Slow Mode- Essentially a debuff, causes the game to run at 10% speed, useful if you want to talk out strats in Co-Op

Double Damage- Double the damage the ball deals to bricks




Extra credit:

Extra features added to this game were hard mode a Co-Op play

Co-Op- Play with 1 other player and  tactically clear the game. Currently Co-Op is played on Normal difficulty

Hard Mode- PAddle moves half as quickly and bricks have twice as much health, while you have half as much health to start with. Good luck. 

### Notes/Assumptions
Features Implemented by Michael Williams: The Ball, Paddle, all of theLevels/Bricks, all of the PowerUps, Score tracking, Health Bar tracking, restricted Zone, Co-Op, Hard Mode, General File I/O,all of Cheat Keys


### Impressions
Overall Project was pretty fun, I do not have much experience with JavaFX,k so using a new technology was very interesting.
