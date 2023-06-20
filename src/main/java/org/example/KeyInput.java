package org.example;

import lombok.Data;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

@Data
public class KeyInput extends Thread {
    private String keyBoardKey="RIGHT";

    @Override
    public void run() {
        try(Terminal terminal= TerminalBuilder.terminal()){
            while (true){
                keyBoardKey = getCommand(terminal.reader().read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getCommand(int ch) {
        String command = "";
        switch (ch) {
            case 65:
                command = "UP";
                break;
            case 66:
                command = "DOWN";
                break;
            case 68:
                command = "LEFT";
                break;
            case 67:
                command = "RIGHT";
                break;
            default:
                command = "";
        };
        return command;
    }
}