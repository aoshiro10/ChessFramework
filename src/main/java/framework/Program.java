package framework;

import framework.core.Chess;
import framework.core.Player;
import framework.core.Side;
import framework.gui.GUI;
import plugin.AlphaBeta;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Program {

    public Program() {
    }

    private static void showGui() {

        Player whitePlayer = null;
        Player blackPlayer = new AlphaBeta(Side.Black, 3);

        new GUI(new Chess(whitePlayer, blackPlayer));

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Program::showGui);

    }

}
