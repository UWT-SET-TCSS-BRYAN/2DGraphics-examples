package model;

import java.awt.*;

public interface BoardControls {
    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();
    Point getPieceLocation();
}
