package hu.oe.nik.szfmv17t.visualisation.mock;

import hu.oe.nik.szfmv17t.environment.domain.WorldObjectState;
import hu.oe.nik.szfmv17t.environment.interfaces.IWorldObject;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;

public class WorldObjectMock implements IWorldObject {
    private double x, y, width, height;

    public WorldObjectMock(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getAxisAngle() {
        return 0;
    }

    @Override
    public double getCenterX() {
        return x + width / 2;
    }

    @Override
    public double getCenterY() {
        return y + height / 2;
    }

    @Override
    public String getImageName() {
        return null;
    }

    @Override
    public int getZIndex() {
        return 0;
    }

    @Override
    public WorldObjectState getState() {
        return null;
    }

    @Override
    public Vector2d getVisualRefCenter() {
        return null;
    }
}
