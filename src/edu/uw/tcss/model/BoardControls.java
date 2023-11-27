package edu.uw.tcss.model;

import java.awt.Point;
import java.beans.PropertyChangeListener;

/**
 * Defines the actions a Board object may make.
 * @author Charles Bryan
 * @version 1.0
 */
public interface BoardControls {

    /** A property lable for the Piece location. */
    String PROPERTY_PIECE_LOCATION = "Prop for Piece llocation";

    /**
     * Returns the board's abstract width, not pixel dimention.
     * @return the board's abstract width
     */
    int getBoardWidth();

    /**
     * Returns the board's abstract height, not pixel dimention.
     * @return the board's abstract height
     */
    int getBoardHeight();

    /**
     * Returns the piece's abstract location on the board, not pixel.
     * @return the piece's abstract location on the board
     */
    Point getPieceLocation();

    /**
     * Moves the piece up one location.
     */
    void moveUp();

    /**
     * Moves the piece down one location.
     */
    void moveDown();

    /**
     * Moves the piece right one location.
     */
    void moveRight();

    /**
     * Moves the piece left one location.
     */
    void moveLeft();

    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for
     * all properties. The same listener object may be added more than once, and will be
     * called as many times as it is added. If listener is null, no exception is thrown and
     * no action is taken.
     *
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);


    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number
     * of times it was added for that property. If propertyName or listener is null, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a
     * PropertyChangeListener that was registered for all properties. If listener was added
     * more than once to the same event source, it will be notified one less time after being
     * removed. If listener is null, or was never added, no exception is thrown and no action
     * is taken.
     *
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less
     * time after being removed. If propertyName is null, no exception is thrown and no action
     * is taken. If listener is null, or was never added for the specified property, no
     * exception is thrown and no action is taken.
     *
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName,
                                      PropertyChangeListener theListener);
}
