/*
 * Java2D Ellipse and Rectangle Demo - TCSS 305
 */

package edu.uw.tcss.view.demos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serial;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Demonstrates the painting of ellipses (and rectangles).
 * 
 * @author Alan Fowler
 * @author Charles Bryan
 * @version 1.2
 */
public final class EllipseAndRectanglePanel extends JPanel {
    
    /**  A generated serial version UID for object Serialization. */
    @Serial
    private static final long serialVersionUID = 8452917670991316606L;
    
    /** The width of the panel. */
    private static final int WIDTH = 400;

    /** The height of the panel. */
    private static final int HEIGHT = 400;

    /** The distance to move the shape. */
    private static final int MOVE_DISTANCE = 10;

    /** The width for the rectangle. */
    private static final int RECTANGLE_WIDTH = 50;

    /** The height for the rectangle. */
    private static final int RECTANGLE_HEIGHT = 50;

    /** A Shape to move around the panel. */
    private final RectangularShape myShape;


    /**
     * Constructs a new ellipse panel.
     */
    public EllipseAndRectanglePanel() {
        super();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        myShape = new Ellipse2D.Double(0, 0, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
        addButton();

    }

    private void addButton() {
        setLayout(new BorderLayout());
        final JButton move = new JButton("Move");

        move.addActionListener(theEvent -> {
            final Rectangle2D bounds = myShape.getBounds2D();
            myShape.setFrame(bounds.getX() + MOVE_DISTANCE,
                    bounds.getY() + MOVE_DISTANCE,
                    bounds.getWidth(),
                    bounds.getHeight());

            repaint();
        });


        add(move, BorderLayout.SOUTH);

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

        g2d.setPaint(Color.GREEN);
        g2d.fill(myShape);
    }

    /**
     * Creates and displays an EllipsePanel.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
        SwingUtilities.invokeLater(EllipseAndRectanglePanel::createAndShowGui);
    }

    public static void createAndShowGui() {
        final EllipseAndRectanglePanel panel = new EllipseAndRectanglePanel();
        final JFrame frame = new JFrame("Moving Ellipse Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }
}
