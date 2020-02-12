Michael Williams mw376 


It has become clear that our longest method is the Step method in the Game class, which essentially handles the animations/game logic of our game. In refactoring, I am trying to compress this method into several different methods that handle different partsof the gameLogic. That way this method is a hub where these things happen, versus doing all of thjese things itself. However this process should not just be about splitting this method up, this process should also involve eliminating duplicity in this code

The second  thing we will tackle is the intra-file duplicated code, as seen with   hLabel = new Label("Health",healthBar), in constantly reconstructing our health bar label in the Game class.