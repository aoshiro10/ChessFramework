package framework;

import framework.core.Chess;
import framework.core.Side;
import framework.gui.GUI;
import plugin.AlphaBeta;

import javax.swing.*;

public class Program {

    public Program() {
    }

    private static void showGui() {
        new GUI(new Chess(new AlphaBeta(Side.White, 3), null));
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Program::showGui);

    }

}
