package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int width = 20, height = 20;
        try {
            Game game = new Game(width, height);
            game.startGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}