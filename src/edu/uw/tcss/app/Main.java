package edu.uw.tcss.app;

import edu.uw.tcss.view.MyBoardPanel;
import javax.swing.SwingUtilities;

/**
 * Driver class for the GUI.
 * @author Charles Bryan
 * @version 1.0
 */
public final class Main {

    private Main() {
        super();
    }

    /**
     * The main method.
     * @param theArgs the command line arguments.
     */
    public static void main(final String[] theArgs) {

        SwingUtilities.invokeLater(MyBoardPanel::createAndShowGui);
    }
}
