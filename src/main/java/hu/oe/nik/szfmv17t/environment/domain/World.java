package hu.oe.nik.szfmv17t.environment.domain;

import hu.oe.nik.szfmv17t.environment.interfaces.IWorldObject;
import hu.oe.nik.szfmv17t.environment.interfaces.IWorldVisualisation;
import hu.oe.nik.szfmv17t.environment.utils.CollisionDetector;
import hu.oe.nik.szfmv17t.environment.utils.XmlParser;
import java.util.ArrayList;
import java.util.List;

public class World implements IWorldVisualisation {

    private int width = 0;
    private int height = 0;
    private List<IWorldObject> worldObjects = new ArrayList<>();
    private XmlParser xmlParser;

    public void updateWorld() {
        for (IWorldObject object : worldObjects) {
            if (object instanceof CollidableBase) {
                ((CollidableBase) object).updateWorldObject();
                checkIfCollided(object);
            }
        }
    }

    private void checkIfCollided(IWorldObject object1) {
        for (IWorldObject object2 : worldObjects) {
            if (object2 instanceof CollidableBase && object2.equals(object1) == false) {
                boolean isCollided = CollisionDetector.collide((CollidableBase)object1, (CollidableBase)object2);
                if (isCollided) {
                    // TODO nincs fizikus csapattól interfész az ütközés jelzésére
                }
            }
        }
    }

    public World(String pathToXml) {
        xmlParser = new XmlParser(pathToXml);
        width = xmlParser.getMapWidth();
        height = xmlParser.getMapHeight();
        worldObjects = xmlParser.getWorldObjects();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<IWorldObject> getWorld() {
        return worldObjects;
    }

    public void addObjectToWorld(IWorldObject o) {
        worldObjects.add(o);
    }
}
