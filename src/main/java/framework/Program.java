package framework;

import framework.core.Chess;
import framework.core.Player;
import framework.core.Side;
import framework.gui.GUI;
import plugin.MinMax;

import javax.swing.*;

public class Program {

    public Program() {
    }

    private static void showGui() {

        Player whitePlayer = new MinMax(Side.White, 2);
        Player blackPlayer = new MinMax(Side.Black, 2);

        new GUI(new Chess(whitePlayer, blackPlayer));

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Program::showGui);

    }

}
