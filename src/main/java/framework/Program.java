package framework;

import framework.core.Chess;
import framework.gui.GUI;

import javax.swing.*;

public class Program {

    public Program() {
    }

    private static void showGui() {
        new GUI(new Chess());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(Program::showGui);

    }

}
