# Game Design Final
### Names
Michael Williams
## Team Roles and Responsibilities

 * Team Member #1
Michael Willams- Design and implement every aspect of gameplay such as ball, paddle, powwer ups, and levels. For this project, took role of lead developer and implementer.
 * Team Member #2


## Design goals
Take advantage of JavaFX's inherent parent-node class hierarcy, and extend the functionality of those parent/node classes, rather than invent new classes/hierarchies that way the design itself is efficient

In terms of gameplay design, the goal was to produce an implementation of super breakout that supported both hard and co-op modes.

#### What Features are Easy to Add
The easiest to add features related to core aspects of gameplay, such as balls bouncing off the paddle/walls/bricks to power ups dropping. This is because in our design, by taking advantage of built in class hierarchies, features are based around node objects interacting with each other. In addition, certain features are "activated", such as Co-op mode, hard mode, or certain powerups baed on the current state one is in. Because of this, it was fairly easy for me to sdd oarticular gameplay features based on the documentation for JavaFX, rather than reinvent the wheeel for each and every feature.

## High-level Design

#### Core Classes
Core classes are the Game, Ball, Paddle, PowerUp, Bricks, Menu, and LevelBuilder classes.

Ball- represents all balls that are in this game, can be modified to give balls different effects

Paddle- Represents all Paddles in this game

PowerUp- All possible powerUps in this game, when they intersect a paddle they provide an effect

Bricks- Represents indivual bricks in this game, each instance has a specific HP and position

Menu- All menus and splash screens

LevelBuilder- For building the level based on input text file configuration.

## Assumptions that Affect the Design

#### Features Affected by Assumptions
One assumption I made is that there would only ever be 1 powerUp on the screen no matter what, which meant that in every method that could generate a powerUp, there was always a check to see if a powerUp was already on the screen

Another Assumption I made was that the ball hitting the top/bottom or side of the brick would not matter in terms of how the ball bounces

Another assumption I made was that the restricted zone would  be the bottom 20% of the screen.



## New Features HowTo
While we had all of the specified complete features for this project, there are ways to expand the functionality/add features to each aspect of gameplay. There were also particular features within the specifcaton, like certain powerups, that were not added but would make gameplay much better.




#### Easy to Add Features
To add more levels, simply add a valid 5x5 brick configuration to the data/levels folder as a text file. The valid bricks are lowercase colors: green,blue,orange,red,purple,black,white

To add more power ups, this is a bit more complicated but simple from a coding perspective. First, find an image resource(either online or from a file) that represents what the power up looks like. 
Then, create a power up object and add it to the retList variable in the allPowerUps method. This puts the powerUp in the pool of available powerUps. Then all you have to do is specify the affct of that powerUp, which can be anything
To add more cheatkeys, go tpo to the handleKeyInput method and just describe what hapens wshen a certain key is pressed. Just make sure that they key of interest is not already being used somehwere else. You can see all currently used keys in the README.md file.

#### Other Features not yet Done
For adding achievements to this game, an achievment class could be made that scans that scans through the score.txt file, and depending on the score creates a Node(either an imageView or a label) that tells the user they have gotten a specific milestone

In terms of vanity skins, there could be a vanityClass that scans through the score.txt file, much like the achievment class, and changes the image/texture on the paddle or the ball based on your scores.

For powerUps and bricks, one effect that we really wanted to add was an explosive/splsash effect that would hit multiple bricks at once, but figuring that feature out during development was too difficult. One way to do this would be to create several imageview objects that originate from the point of contact and go in all radial directions, then delete those Imageviews from the scene. Using the updateBricksImageView method, it would be possible to track these collisions, and the normal damage penalty could be applied.

For a signifigant feature, probably a Co-Op version of hardMode, which can be added by adding the Co-Op flag to the isHArdMode checker. 
