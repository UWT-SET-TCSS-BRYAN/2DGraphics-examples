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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Demonstrates the painting of ellipses (and rectangles).
 * 
 * @author Alan Fowler
 * @author Charles Bryan
 * @version 1.2
 */
public class GuiBoard extends JPanel implements PropertyChangeListener {

    /**  A generated serial version UID for object Serialization. */
    private static final long serialVersionUID = 8452917670991316606L;

    /** The width of the panel. */
    private static final int WIDTH = 400;

    /** The height of the panel. */
    private static final int HEIGHT = 400;

    /** The stroke width in pixels. */
    private static final int STROKE_WIDTH = 10;

    /** The width for the rectangle. */
    private static final int PIECE_SIZE = 50;

    /** The height for the rectangle. */
    private static final int RECTANGLE_HEIGHT = 50;

    private final Ellipse2D myPiece;

    private final BoardControls myBoard;


    /**
     * Constructs a new ellipse panel.
     */
    public GuiBoard(final BoardControls theBoard) {
        super();

        myBoard = theBoard;
        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(myBoard.getBoardDimension().width * PIECE_SIZE + 1,
                myBoard.getBoardDimension().height * PIECE_SIZE + 1));


        myPiece = new Ellipse2D.Double(myBoard.getPieceLocation().x * PIECE_SIZE,
                myBoard.getPieceLocation().y * PIECE_SIZE,
                PIECE_SIZE,
                PIECE_SIZE);
//        addButton();
        addKeyListener(new BoardKeyListener());
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
        });


        add(move, BorderLayout.SOUTH);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Board.PIECE_MOVED_PROPERTY)) {
            final Point temp = (Point) evt.getNewValue();
            myPiece.setFrame(new Point(temp.x * PIECE_SIZE, temp.y * PIECE_SIZE),
                    new Dimension(PIECE_SIZE, PIECE_SIZE));
            repaint();
        }
    }

    class BoardKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_A) {
                myBoard.moveLeft();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_D) {
                myBoard.moveRight();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_W) {
                myBoard.moveUp();
            } else if (theEvent.getKeyCode() == KeyEvent.VK_S) {
                myBoard.moveDown();
            }
        }
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

        g2d.setPaint(Color.MAGENTA);

        for(int row = 0; row < myBoard.getBoardDimension().height; row++) {
            for(int col = 0; col < myBoard.getBoardDimension().width; col++) {
                g2d.draw(new Rectangle2D.Double(col * PIECE_SIZE,
                        row * PIECE_SIZE,
                        PIECE_SIZE,
                        PIECE_SIZE));
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

    /**
     * Creates and displays an EllipsePanel.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
        SwingUtilities.invokeLater(GuiBoard::createAndShowGui);
    }

    public static void createAndShowGui() {
        final Board board = new Board(5, 10);

        final GuiBoard panel = new GuiBoard(board);
        board.addPropertyChangeListener(panel);

        final JFrame frame = new JFrame("EllipseAndRectanglePanel Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }
}
