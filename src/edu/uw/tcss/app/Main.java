package edu.uw.tcss.app;

import javax.swing.SwingUtilities;

import edu.uw.tcss.view.MyBoardPanel;

/**
 * Driver class for the GUI.
 * @author Charles Bryan
 * @version 1.0
 */
public final class Main {

    private Main() {

    }

    /**
     * The main method.
     * @param theArgs the command line arguments.
     */
    public static void main(final String[] theArgs) {

        SwingUtilities.invokeLater(MyBoardPanel::createAndShowGui);
    }
}
