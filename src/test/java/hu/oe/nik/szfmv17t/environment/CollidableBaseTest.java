/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.nik.szfmv17t.environment;
import hu.oe.nik.szfmv17t.environment.domain.CollidableBase;
import hu.oe.nik.szfmv17t.environment.domain.World;
import hu.oe.nik.szfmv17t.environment.utils.Position;
import hu.oe.nik.szfmv17t.environment.utils.Resizer;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;
import java.util.ArrayList;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.*;
/**
 *
 * @author Igyártó Imre
 */
public class CollidableBaseTest {
 
    private CollidableBase cb;
    
    @org.junit.Before
    public void setUp() throws Exception {
		/* stuff written here runs before the tests */
		cb = new CollidableBase(480d,800d,108d,240d,0d,0,"car_1_white.png",200d,50d,0d);
             // positionX, positionY, width, height, axisAngle, zIndex, imageFilePath, mass, speed, directionAngle
	
    }
    
    @Test
    public void testGetPositionObj() {
        // positionX, positionY, width, height, axisAngle, directionAngle
        assertEquals(cb.getPositionObj().getAxisAngle(),0d);
        assertEquals(cb.getPositionObj().getDirectionAngle(),0d);
        assertEquals(cb.getPositionObj().getCenter().getX(),480d+108d/2);
        assertEquals(cb.getPositionObj().getCenter().getY(),800d+240d/2);
        assertEquals(cb.getPositionObj().getHeight(),240d);
        assertEquals(cb.getPositionObj().getMaximumX(),480d+108d);
        assertEquals(cb.getPositionObj().getMaximumY(),800d+240d);
        assertEquals(cb.getPositionObj().getMinimumX(),480d);
        assertEquals(cb.getPositionObj().getMinimumY(),800d);
        assertEquals(cb.getPositionObj().getWidth(),108d);
    }
    
    @Test
     public void testStep() {
        //cB = new CollidableBase(480d,800d,108d,240d,0d,0,"car_1_white.png",200d,50d,0d);
        //positionX, positionY, width, height, axisAngle, zIndex, imageFilePath, mass, speed, directionAngle
        cb.way.clear();
        cb.way.add(new Vector2d(490d,810d));
        cb.way.add(new Vector2d(500d,820d));
        cb.step();
     
        // Calculate
        Vector2d directionVector =(new Vector2d(490d-480d,810d-800d));
        double vectorLength =  Math.sqrt(Math.pow(490d-480d,2) + Math.pow(810d-800d,2));
        Vector2d oneStep = new Vector2d(directionVector.getX()/vectorLength, directionVector.getY()/vectorLength);
        Resizer meterToCors = Resizer.getResizer();
        
        double digree = (Math.atan2(directionVector.getY(), directionVector.getX()));
        double x = 480d + oneStep.getX() * meterToCors.meterToCoordinate(cb.getSpeed());
        double y = 800d + oneStep.getY() * meterToCors.meterToCoordinate(cb.getSpeed());
     
        //Equals
        assertEquals(cb.getPositionObj().getMinimumX(), x);
        assertEquals(cb.getPositionObj().getMinimumY(), y);
        assertEquals(cb.getAxisAngle(), digree);
        assertEquals(cb.getDirectionAngle(), digree);
     }
     
    @Test
     public void testInTarget()
    {
        assertEquals(cb.inTarget(new double[]{20,10,20,10}),true);// beért
        assertEquals(cb.inTarget(new double[]{21,10,20,10}),true);// elhagyta
        assertEquals(cb.inTarget(new double[]{20,100,20,10}),true);// elhagyta
        assertEquals(cb.inTarget(new double[]{20,9.99999,20,10}),false);// nem ért még be
        assertEquals(cb.inTarget(new double[]{19.9999999,9.99999,20,10}),false);// nem ért még be
        assertEquals(cb.inTarget(new double[]{19.999999999,10,20,10}),false);// nem ért még be
    }
     
     @Test
     public void testListRotate(){
     Vector2d start = cb.way.get(0);
     cb.listRotate(new double[]{20,10,20,10});
     int index =cb.way.indexOf(start);
        assertEquals(cb.way.get(index), start);//
        
     }
     
    @Test
    public void testGetMass() {assertEquals(cb.getMass(),200d);}

    @Test
    public void testGetSpeed() {assertEquals(cb.getSpeed(),50d);}

    @Test
    public void testSetSpeed() {
        cb.setSpeed(10d);
        assertEquals(cb.getSpeed(),10d);
    }

    @Test
    public void tetSetAxisAngle() {
        cb.setAxisAngle(100d);
        assertEquals(cb.getAxisAngle(), 100d);
    }

    @Test
    public void setDirectionAngle() {
        cb.setDirectionAngle(45d);
        assertEquals(cb.getDirectionAngle(), 45d);
    }
    
    @Test
    public void testRotate() {
         cb.rotate(90d);
         assertEquals(cb.getAxisAngle(), 90d);
         assertEquals(cb.getDirectionAngle(), 90d);
       
    }
}
