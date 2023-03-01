package model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Board implements BoardControls {

    public final String PIECE_MOVED_PROPERTY = "THIS PRT doesnt matter!!#@!#@#@@#$@#$";

    private final Dimension myDimension;

    private final Point myPiece;

    private final PropertyChangeSupport myPcs;

    public Board(final Dimension theDimension) {
        myDimension = theDimension;
        myPiece = new Point(0,0);
        myPcs = new PropertyChangeSupport(this);
    }

    public Board(final int theWidth, final int theHeight) {
        this(new Dimension(theWidth, theHeight));
    }

    public Board() {
        this(new Dimension(10, 10));
    }

    @Override
    public void moveUp() {
        myPiece.translate(0, -1);
        notifyOfChange();
    }

    @Override
    public void moveDown() {
        myPiece.translate(0, 1);
        notifyOfChange();
    }

    @Override
    public void moveRight() {
        myPiece.translate(-1, 0);
        notifyOfChange();
    }

    @Override
    public void moveLeft() {
        myPiece.translate(1, 0);
        notifyOfChange();
    }

    private void notifyOfChange() {
        myPcs.firePropertyChange(PIECE_MOVED_PROPERTY, null, new Point(myPiece));
    }

    @Override
    public Point getPieceLocation() {
        return new Point(myPiece);
    }

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
                myPcs.removePropertyChangeListener(theListener);
    }

    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }
}
