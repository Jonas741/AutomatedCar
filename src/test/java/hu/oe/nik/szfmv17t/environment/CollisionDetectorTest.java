/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.nik.szfmv17t.environment;

import hu.oe.nik.szfmv17t.environment.domain.Car;
import hu.oe.nik.szfmv17t.environment.domain.CollidableBase;
import hu.oe.nik.szfmv17t.environment.utils.CollisionDetector;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Gellert Babel <OE-NIK>
 */
public class CollisionDetectorTest {

    private CollidableBase first;
    private CollidableBase second;

    @org.junit.Before
    public void setUp() throws Exception {
        /* stuff written here runs before the tests */

        first = new Car(0d, 0d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 0d, 0d);
        second = new Car(50d, 50d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 0d, 0d);
    }

    @Test
    public void noNullPointerException() throws Exception {
        assertEquals(CollisionDetector.collide(null, null), false);
    }

    @Test
    public void sameObject() throws Exception {
        assertEquals(CollisionDetector.collide(first, first), true);
    }

    @Test
    public void differentObjectFarAway() throws Exception {
        assertEquals(CollisionDetector.collide(first, second), false);
    }
    
    @Test
    public void Collide_Should_ReturnFalse_When_Called_With_NoObjects() throws Exception {
        // Act.
        boolean result = CollisionDetector.collide(null, null);
        
        // Assert.
        assertEquals(false, result);
    }
    
    @Test
    public void Collide_Should_ReturnFalse_When_Called_With_OneObject() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, null);
        
        // Assert.
        assertEquals(false, result);
    }
    
    @Test
    public void Collide_Should_ReturnTRue_When_Called_With_TheSameObject() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, firstObject);
        
        // Assert.
        assertEquals(true, result);
    }

    @Test
    public void Collide_Should_ReturnFalse_When_Called_With_TwoNonTouchingObjectsAtSamePositionXAndSameDirection() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(100d, 112d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(false, result);
    }
    
    @Test
    public void Collide_Should_ReturnTrue_When_Called_With_TwoNonTouchingObjectsAtSamePositionXAnd45DegreeDirection() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(100d, 112d, 10d, 10d, 45d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(false, result);
    }
    
    @Test
    public void Collide_Should_ReturnTrue_When_Called_With_TwoTouchingObjectsAtSamePositionXAndSameDirection() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(100d, 95d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(true, result);
    }

    @Test
    public void Collide_Should_ReturnFalse_When_Called_With_TwoNonTouchingObjectsAtSamePositionYAndSameDirection() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(112d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(false, result);
    }
    
    @Test
    public void Collide_Should_ReturnTrue_When_Called_With_TwoNonTouchingObjectsAtSamePositionYAnd45DegreeDirection() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(112d, 100d, 10d, 10d, 45d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(false, result);
    }

    @Test
    public void Collide_Should_ReturnTrue_When_Called_With_TwoTouchingObjectsAtSamePositionYAndSameDirection() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(95d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(true, result);
    }
    
    @Test
    public void Collide_Should_ReturnTrue_When_Called_With_TwoTouchingObjectsAtSamePosition() throws Exception {
        // Arrange.
        CollidableBase firstObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        CollidableBase secondObject = new Car(100d, 100d, 10d, 10d, 0d, 0, "test.jpg", 1000d, 10d, 0d);
        
        // Act.
        boolean result = CollisionDetector.collide(firstObject, secondObject);
        
        // Assert.
        assertEquals(true, result);
    }
}
