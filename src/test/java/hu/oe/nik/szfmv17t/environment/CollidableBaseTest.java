/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.nik.szfmv17t.environment;
import hu.oe.nik.szfmv17t.environment.domain.CollidableBase;
import hu.oe.nik.szfmv17t.environment.domain.World;
import hu.oe.nik.szfmv17t.environment.utils.Position;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.*;
/**
 *
 * @author Igyártó Imre
 */
public class CollidableBaseTest {
 
    private CollidableBase cB;
    
    @org.junit.Before
    public void setUp() throws Exception {
		/* stuff written here runs before the tests */
		cB = new CollidableBase(480,800,108,240,0d,0,"car_1_white.png",200d,50d,0d);
             // positionX, positionY, width, height, axisAngle, zIndex, imageFilePath, mass, speed, directionAngle
	}
    
    @Test
     public void testStep() {}
     
    @Test
     public void testInTarget()
    {
        assertEquals(cB.inTarget(new double[]{20,10,20,10}),true);// beért
        assertEquals(cB.inTarget(new double[]{21,10,20,10}),true);// elhagyta
        assertEquals(cB.inTarget(new double[]{20,100,20,10}),true);// elhagyta
        assertEquals(cB.inTarget(new double[]{20,9.99999,20,10}),false);// nem ért még be
        assertEquals(cB.inTarget(new double[]{19.9999999,9.99999,20,10}),false);// nem ért még be
        assertEquals(cB.inTarget(new double[]{19.999999999,10,20,10}),false);// nem ért még be
    }
     
     @Test
     public void testListRotate(){
     Vector2d start = cB.way.get(0);
     cB.listRotate(new double[]{20,10,20,10});
     int index =cB.way.indexOf(start);
        assertEquals(cB.way.get(index), start);//
        
     }
     
    @Test
    public void testGetMass() {assertEquals(cB.getMass(),200d);}

    @Test
    public void testGetSpeed() {assertEquals(cB.getMass(),50d);}

    @Test
    public void testGetPositionObj() {
        assertEquals(cB.getPositionObj(), this);
    }

    @Test
    public void testSetSpeed() {
        
    }

    @Test
    public void testRotate() {
       
    }

    @Test
    public void tetSetAxisAngle() {
        
    }

    @Test
    public void setDirectionAngle() {
        
    }
    
}
