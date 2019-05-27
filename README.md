# Chess Framework

Chess framework built using Java and Swing. 

Allows plugins to extend the game by implementing different AI's to play chess. 

## Prerequisites

[Gradle](https://gradle.org/install/)

## Getting Started

Use 'gradle run' to initiliaze the game. By default the game will run with no plugins (human vs human). 

To create a new plugin, create a new class under the package `plugin` and implement the [`Player`](https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/core/Player.java) interface under the package `core`.

The [`Player`](https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/core/Player.java) interface has only two required methods: 
1. `Side getSide();`
2. `Move chooseMove(Board board);`

#### `getSide()`:

Return: side (white or black) of the player the plugin is controlling : [`Side`](https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/core/Side.java)

#### `chooseMove()`:

Parameter: board represting the current state of the game : [`Board`](https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/core/Board.java)

Return: from the list of available moves from the current chess state, return a move : [`Move`](https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/core/Move.java)

Observation: 'Board' has a method 'getValidMoves(final Side side)' that takes a 'Side' and returns a list of valid moves for the current side.

## Installing new plugin

Make sure you implement the [`Player`](https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/core/Player.java).

After creating the plugin class, under 'Program'(https://github.com/aoshiro10/chess-framework/blob/master/src/main/java/framework/Program.java) initialize the plugin for a specific player (i.e. 'whitePlayer').

## Running the tests

Use 'gradle build' to test framework test

Use 'gradle run' to play chess with the plugin. 
