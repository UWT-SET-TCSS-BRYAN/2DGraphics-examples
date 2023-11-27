package edu.uw.tcss.model;

import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Board implements BoardControls {

    /** The manager of this object's PropertyChangeListeners. */
    private final PropertyChangeSupport myPcs;

    /** The abstract size of this board. Not, not in pixels. */
    private final Dimension myBoardDimension;

    /** The abstract location of the piece on the board. Note, not in pixels. */
    private final Point myPieceLocation;

    public Board() {
        this(0, 0);
    }

    public Board(final int theWidth, final int theHeight) {
        this(new Dimension(theWidth, theHeight));
    }

    public Board(final Dimension theDimension) {
        super();
        myBoardDimension = new Dimension(theDimension);
        myPieceLocation = new Point(0, 0);
        myPcs = new PropertyChangeSupport(this);
    }

    @Override
    public int getBoardWidth() {
        return myBoardDimension.width;
    }

    @Override
    public int getBoardHeight() {
        return myBoardDimension.height;
    }

    @Override
    public Point getPieceLocation() {
        return new Point(myPieceLocation);
    }

    @Override
    public void moveUp() {
        myPieceLocation.translate(0, -1);
        notifyObserversOfLocationChange();
    }

    @Override
    public void moveDown() {
        myPieceLocation.translate(0, 1);
        notifyObserversOfLocationChange();
    }

    @Override
    public void moveRight() {
        myPieceLocation.translate(1, 0);
        notifyObserversOfLocationChange();
    }

    @Override
    public void moveLeft() {
        myPieceLocation.translate(-1, 0);
        notifyObserversOfLocationChange();
    }

    private void notifyObserversOfLocationChange() {
        myPcs.firePropertyChange(PROPERTY_PIECE_LOCATION, null, new Point(myPieceLocation));
    }

    private void notifyObserversOfDimensionChange() {
        myPcs.firePropertyChange(PROPERTY_PIECE_LOCATION, null, new Point(myPieceLocation));
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }
}
