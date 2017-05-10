package hu.oe.nik.szfmv17t.environment.interfaces;

import hu.oe.nik.szfmv17t.environment.domain.WorldObjectState;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;

/**
 * Created by Bábel Gellért, Budai Krisztián, Molnár Attila on 2017. 03. 04..
 */
public interface IWorldObject {
    double getWidth();
    double getHeight();
    double getAxisAngle ();
    double getCenterX ();
    double getCenterY ();
    Vector2d getVisualRefCenter ();
    String getImageName ();
    int getZIndex ();
    WorldObjectState getState ();
}