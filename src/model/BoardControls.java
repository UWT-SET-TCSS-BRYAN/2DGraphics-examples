package model;

import java.awt.*;

public interface BoardControls {
    Dimension getBoardDimensions();
    Point getPieceLocation();
    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();
}
