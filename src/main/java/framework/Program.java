package framework;

import framework.core.Chess;
import framework.core.Player;
import framework.core.Side;
import framework.gui.GUI;
import plugin.AlphaBeta;
import plugin.MinMax;

import javax.swing.*;

public class Program {

    private static void showGui() {

        Player whitePlayer = new AlphaBeta(Side.White, 2);
        Player blackPlayer = new AlphaBeta(Side.Black, 2);

        Chess chess = new Chess(whitePlayer, blackPlayer);

        new GUI(chess);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Program::showGui);
    }

}
