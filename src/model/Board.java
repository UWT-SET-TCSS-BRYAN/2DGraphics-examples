package model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Board implements BoardControls {

    public static final String PROPERTY_PIECE_LOCATION = "Now the string value doesnt reallu mattear ata all!!!*RE*W(REW*kR()";

    private PropertyChangeSupport myPcs;

    private Dimension myBoardDimension;

    private Point myPieceLocation;

    public Board() {
        this(0, 0);
    }

    public Board(final int theWidth, final int theHeight) {
        this(new Dimension(theWidth, theHeight));
    }

    public Board(final Dimension theDimension) {
        myBoardDimension = new Dimension(theDimension);
        myPieceLocation = new Point(0,0);
        myPcs = new PropertyChangeSupport(this);
    }


    @Override
    public Dimension getBoardDimensions() {
        return new Dimension(myBoardDimension);
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
