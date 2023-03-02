/*
 * Java2D Ellipse and Rectangle Demo - TCSS 305
 */

package view;

import model.Board;
import model.BoardControls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Demonstrates the painting of ellipses (and rectangles).
 * 
 * @author Alan Fowler
 * @author Charles Bryan
 * @version 1.2
 */
public class MyBoardPanel extends JPanel implements PropertyChangeListener {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L;

    /** The width of the panel. */
    private static final int WIDTH = 400;

    /** The height of the panel. */
    private static final int HEIGHT = 400;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 10;

    /** The width for the rectangle. */
    private static final int RECTANGLE_WIDTH = 50;

    /** The height for the rectangle. */
    private static final int RECTANGLE_HEIGHT = 50;

    private final RectangularShape myPiece;

    private final BoardControls myBoard;


    /**
     * Constructs a new ellipse panel.
     */
    public MyBoardPanel(final BoardControls theBoard) {
        super();

        myBoard = theBoard;

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(myBoard.getBoardDimensions().width * RECTANGLE_WIDTH,
                myBoard.getBoardDimensions().height * RECTANGLE_HEIGHT));

        final Point loc = myBoard.getPieceLocation();

        myPiece = new Ellipse2D.Double(loc.getX() * RECTANGLE_WIDTH, loc.getY() * RECTANGLE_HEIGHT,
                RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
//        addButton();

        addKeyListener(new ControlKeyListener());
        setFocusable(true);
        requestFocus();


    }

    private void addButton() {
        setLayout(new BorderLayout());
        final JButton move = new JButton("Move");

        move.addActionListener(theEvent -> {
            final Rectangle2D bounds = myPiece.getBounds2D();
            myPiece.setFrame(bounds.getX() + 25,
                    bounds.getY() + 5,
                    bounds.getWidth(),
                    bounds.getHeight());

            repaint();
            move.transferFocus();
        });


        add(move, BorderLayout.SOUTH);
        move.transferFocus();
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

//        System.out.println("Paint me in paintComponent!: W:" + getWidth() + ", H:" + getHeight());

        g2d.setPaint(Color.BLACK);
        for(int row = 0; row < myBoard.getBoardDimensions().height; row++) {
            for(int col = 0; col < myBoard.getBoardDimensions().width; col++) {
                g2d.draw(new Rectangle2D.Double(col * RECTANGLE_WIDTH, row * RECTANGLE_HEIGHT,
                        RECTANGLE_WIDTH, RECTANGLE_HEIGHT));
            }
        }

        g2d.setPaint(Color.GREEN);
        g2d.fill(myPiece);


//        final Shape ellipse = new Ellipse2D.Double(10, 10, 50, 100);
//        g2d.setPaint(Color.GREEN);
//        g2d.setStroke(new BasicStroke(1));
//        g2d.fill(ellipse);
//
//        final Shape rectangle =
//                        new Rectangle2D.Double((getWidth() - RECTANGLE_WIDTH) / 2,
//                                               (getHeight() - RECTANGLE_HEIGHT) / 2,
//                                               RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
//        g2d.setPaint(Color.RED);
//        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
//        g2d.setStroke(new BasicStroke(STROKE_WIDTH, BasicStroke.CAP_BUTT,
//                                      BasicStroke.JOIN_MITER, 1.5f));
//        g2d.draw(rectangle);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (Board.PROPERTY_PIECE_LOCATION.equals(evt.getPropertyName())) {

            final Point location = (Point) evt.getNewValue();

            myPiece.setFrame(location.x * RECTANGLE_WIDTH,
                    location.getY() * RECTANGLE_HEIGHT,
                    RECTANGLE_WIDTH,
                    RECTANGLE_HEIGHT);

            repaint();
        }
    }


    class ControlKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_W) {
                myBoard.moveUp();
                System.out.println("up");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S){
                myBoard.moveDown();
                System.out.println("down");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_A){
                myBoard.moveLeft();
                System.out.println("left");
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D){
                myBoard.moveRight();
                System.out.println("right");
            }

        }
    }

    /**
     * Creates and displays an EllipsePanel.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
        SwingUtilities.invokeLater(MyBoardPanel::createAndShowGui);
    }

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
}
