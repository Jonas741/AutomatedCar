package hu.oe.nik.szfmv17t.environment.domain;

import hu.oe.nik.szfmv17t.environment.utils.Vector2d;

import java.util.List;

/**
 * Created by Budai Krisztián, Molnár Attila on 2017. 05. 01..
 */
public class NpcCar extends Npc {
    public NpcCar (double positionX
            , double positionY
            , double width
            , double height
            , double axisAngle
            , int zIndex
            , String imageFilePath
            , double mass
            , double speed
            , double directionAngle
            , List<Vector2d> path) {
        super(positionX, positionY, width, height, axisAngle, zIndex, imageFilePath, mass, speed, directionAngle, path);
    }
}