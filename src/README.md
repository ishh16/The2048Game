Installation

Make sure you have Java 17 or higher installed.

Check with:

java -version


Clone the repository:

git clone git@github.com:ishh16/The2048Game.git


Open the project in IntelliJ IDEA (or any Java IDE).

Build the project:

Go to Build → Build Artifacts → Build

The JAR file will be generated in out/artifacts/Game2048_jar/Game2048.jar

Running the Game
Option 1: Using Terminal
cd path\to\Game2048.jar
java -jar Game2048.jar

Option 2: Double-click JAR

Navigate to the JAR file and double-click to launch the game (requires Java installed).

Game Instructions

The goal is to combine tiles with the same number to reach 2048.

Use the arrow keys to move tiles:

Up – move tiles up

Down – move tiles down

Left – move tiles left

Right – move tiles right

After each move, a new tile (2 or 4) will appear at a random empty spot.

Score increases whenever two tiles merge.

The game ends when there are no possible moves.

Implementation Details

Language & Framework: Java 17, Swing GUI

Board: 4x4 grid stored as a 2D array (int[4][4])

Tile Logic:

Slide and merge tiles in the direction of the move

Merge only once per move

Spawn new tile (2 or 4) randomly after each valid move

Score Tracking: Maintains total score, updated when tiles merge

GUI Features:

Color-coded tiles

Dynamic grid with numbers displayed

Score label updating in real time