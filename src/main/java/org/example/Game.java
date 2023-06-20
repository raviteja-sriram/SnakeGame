package org.example;

import java.util.Objects;

public class Game {
    private final int width;
    private final int height;
    private SnakeCell[] snake;
    private String direction;
    private int length;
    private boolean status = true;
    private final int[] food = new int[2];
    private final KeyInput keyInput = new KeyInput();
    private StringBuffer gameString;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        keyInput.start();
    }

    void startGame() throws Exception {
        init();
        while (true) {
            setGameString();
            if(status) {
                moveSnake();
            } else {
                gameString.append("Game Over \n");
                System.out.println(gameString);
                System.exit(-1);
            }
            Thread.sleep(200);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            gameString = new StringBuffer();
        }
    }

    private void setGameString() {
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j <= width; j++) {
                if (j == food[0] && i == food[1]) {
                    gameString.append("@");
                } else if (isSnakeCell(i, j)) {
                    gameString.append("#");
                } else {
                    gameString.append(".");
                }
            }
            gameString.append("\n");
        }
    }

    private void init() {
        snake = new SnakeCell[width * height];
        gameString = new StringBuffer();
        length = 4;
        direction = "RIGHT";

        for(int i=0;i<length;i++) {
            snake[i] = new SnakeCell(width / 2, height / 2);
        }

        food[0] = (int) (Math.random() * (width - 2) + 1);
        food[1] = (int) (Math.random() * (height - 2) + 1);
    }

    public void moveSnake() {
        if (Objects.equals(keyInput.getKeyBoardKey(), "UP") && !Objects.equals(direction, "DOWN"))
            direction = "UP";
        if (Objects.equals(keyInput.getKeyBoardKey(), "DOWN") && !Objects.equals(direction, "UP"))
            direction = "DOWN";
        if (Objects.equals(keyInput.getKeyBoardKey(), "LEFT") && !Objects.equals(direction, "RIGHT"))
            direction = "LEFT";
        if (Objects.equals(keyInput.getKeyBoardKey(), "RIGHT") && !Objects.equals(direction, "LEFT"))
            direction = "RIGHT";
        for (int i = length - 1; i >= 1; i--) {
            snake[i].x = snake[i - 1].x;
            snake[i].y = snake[i - 1].y;
        }
        switch (direction) {
            case "UP" -> {
                snake[0].y--;
                if (snake[0].y == 0)
                    snake[0].y = height - 1;
            }
            case "DOWN" -> {
                snake[0].y++;
                if (snake[0].y == height)
                    snake[0].y = 1;
            }
            case "LEFT" -> {
                snake[0].x--;
                if (snake[0].x == 0)
                    snake[0].x = width - 1;
            }
            case "RIGHT" -> {
                snake[0].x++;
                if (snake[0].x == width)
                    snake[0].x = 1;
            }
        }
        if (snake[0].x == food[0] && snake[0].y == food[1]) {
            snake[length] = new SnakeCell(0, 0);
            food[0] = (int) (Math.random() * (width - 2) + 1);
            food[1] = (int) (Math.random() * (height - 2) + 1);
            length++;
        }
        if (doesHeadTouchBody()) {
            status = false;
        }
        System.out.println(gameString);
    }

    private boolean doesHeadTouchBody() {
        int bound = length;
        for (int i = 1; i < bound; i++) {
            if (snake[0].x == snake[i].x && snake[0].y == snake[i].y) {
                return true;
            }
        }
        return false;
    }

    private boolean isSnakeCell(int i, int j) {
        for (int k = 0; k < length; k++) {
            SnakeCell snakeCell = snake[k];
            if (j == snakeCell.x && i == snakeCell.y)
                return true;
        }
        return false;
    }

    static class SnakeCell {
        int x,y;
        public SnakeCell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}