/*
 * Java2D Ellipse and Rectangle Demo - TCSS 305
 */

package edu.uw.tcss.view;


import edu.uw.tcss.model.Board;
import edu.uw.tcss.model.BoardControls;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Demonstrates the painting of ellipses (and rectangles).
 * 
 * @author Alan Fowler
 * @author Charles Bryan
 * @version 1.2
 */
public final class MyBoardPanel extends JPanel implements PropertyChangeListener {

    /**  A generated serial version UID for object Serialization. */
    @Serial
    private static final long serialVersionUID = 8452917670991316606L;

    /** The width for the rectangle. */
    private static final int RECTANGLE_WIDTH = 50;

    /** The height for the rectangle. */
    private static final int RECTANGLE_HEIGHT = 50;

    /** The Shape to move around the panel. */
    private final RectangularShape myPiece;

    /** The edu.uw.tcss305.app.model that controls/holds the piece state. */
    private final BoardControls myBoard;

    /**
     * Constructs a new ellipse panel.
     */
    public MyBoardPanel(final BoardControls theBoard) {
        super();

        myBoard = theBoard;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(
                myBoard.getBoardWidth() * RECTANGLE_WIDTH,
                myBoard.getBoardHeight() * RECTANGLE_HEIGHT));

        final Point loc = myBoard.getPieceLocation();

        myPiece = new Ellipse2D.Double(
                loc.getX() * RECTANGLE_WIDTH,
                loc.getY() * RECTANGLE_HEIGHT,
                RECTANGLE_WIDTH,
                RECTANGLE_HEIGHT);
//        addButton();

        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();


    }

    /**
     * Paints some ellipses.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        // for better graphics display
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.BLACK);
        for (int row = 0; row < myBoard.getBoardHeight(); row++) {
            for (int col = 0; col < myBoard.getBoardWidth(); col++) {
                g2d.draw(new Rectangle2D.Double(col * RECTANGLE_WIDTH, row * RECTANGLE_HEIGHT,
                        RECTANGLE_WIDTH, RECTANGLE_HEIGHT));
            }
        }

        g2d.setPaint(Color.GREEN);
        g2d.fill(myPiece);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {

        if (Board.PROPERTY_PIECE_LOCATION.equals(theEvent.getPropertyName())) {

            final Point location = (Point) theEvent.getNewValue();

            myPiece.setFrame(location.x * RECTANGLE_WIDTH,
                    location.getY() * RECTANGLE_HEIGHT,
                    RECTANGLE_WIDTH,
                    RECTANGLE_HEIGHT);
            repaint();
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGui() {
        final Board board = new Board(5, 10);

        final MyBoardPanel panel = new MyBoardPanel(board);
        board.addPropertyChangeListener(panel);
        final JFrame frame = new JFrame("EllipseAndRectanglePanel Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }


    class ControlKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_W) {
                myBoard.moveUp();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S) {
                myBoard.moveDown();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_A) {
                myBoard.moveLeft();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D) {
                myBoard.moveRight();
            }

        }
    }


}
