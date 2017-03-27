/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.nik.szfmv17t.environment;

import hu.oe.nik.szfmv17t.environment.utils.Position;
import hu.oe.nik.szfmv17t.environment.utils.Vector2d;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Igyártó Imre
 */
public class PositionTest {
    private Position pt;
    
    @org.junit.Before
    public void setUp() throws Exception {
		/* stuff written here runs before the tests */
		pt = new Position(480d,800d,108d,240d,10d,100d);
             // positionX, positionY, width, height, axisAngle, directionAngle
	
    }
    
    @Test
    public void testGetMinimumX ()
    {
        assertEquals(pt.getMinimumX(), 480d);
        
    }

    @Test
    public void testGetMinimumY ()
    {
        assertEquals(pt.getMinimumY(), 800d);
    }

    @Test
    public void testGetMaximumX ()
    {
        assertEquals(pt.getMaximumX(), 480d+108d);
    }

    @Test
    public void testGetMaximumY ()
    {
        assertEquals(pt.getMaximumY(), 800d+240d);
    }

    @Test
    public void testGetAxisAngle ()
    {
        assertEquals(pt.getAxisAngle(),10d );
    }

    @Test
    public void testGetDirectionAngle ()
    {
        assertEquals(pt.getDirectionAngle(), 100d);
    }

    @Test
    public void testGetWidth ()
    {
        assertEquals(pt.getWidth(), 108d);
    }

    @Test
    public void testGetHeight ()
    {
        assertEquals(pt.getHeight(), 240d);
    }

    @Test
    public void testGetCenter ()
    {
        double centerX = 480d + (108d/2);//534d
        double centerY = 800d + (240d/2);//920d
        
        assertEquals(pt.getCenter().getX(),centerX);
        assertEquals(pt.getCenter().getY(),centerY);
       //assertEquals(pt.getCenter(),new Vector2d(centerX, centerY));// ez igy nem jo :/
    }

    @Test
    public void testSetPositionX ()
    {
        pt.setPositionX(10d);
        assertEquals(pt.getMinimumX(),10d);
        assertEquals(pt.getMaximumX(),10d + pt.getWidth());
        assertEquals(pt.getMaximumX(),10d + 108d);
        assertEquals(pt.getCenter().getX(),10d + (pt.getWidth()/2));
        assertEquals(pt.getCenter().getY(),800d + (pt.getHeight()/2));
    }

    @Test
    public void testSetPositionY ()
    {
        pt.setPositionY(100d);
        assertEquals(pt.getMinimumY(), 100d);
        assertEquals(pt.getMaximumY(),100d + pt.getHeight());
        assertEquals(pt.getMaximumY(),100d + 240d);
        assertEquals(pt.getCenter().getY(),100d + (pt.getHeight()/2));
        assertEquals(pt.getCenter().getX(),480d + (pt.getWidth()/2));
    }

    @Test
    public void testSetAxisAngle ()
    {
        pt.setAxisAngle(45d);
        assertEquals(pt.getAxisAngle(),45d);
    }

    @Test
    public void testSetDirectionAngle ()
    {
        pt.setDirectionAngle(160d);
        assertEquals(pt.getDirectionAngle(), 160d);
    }

    @Test
    public void testRotate ()
    {
        pt.rotate(175d);
        assertEquals(pt.getAxisAngle(),10d+175d);
        assertEquals(pt.getDirectionAngle(),100d+175d);
    }
}
