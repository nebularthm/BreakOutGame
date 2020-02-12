Michael Williams mw376 


It has become clear that our longest method is the Step method, which essentially handles the animations/game logic of our game. In refactoring, I am trying to compress this method into several different methods that handle different partsof the gameLogic. That way this method is a hub where these things happen, versus doing all of thjese things itself. However this process should not just be about splitting this method up, this process should also involve eliminating duplicity in this code