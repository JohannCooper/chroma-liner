package test;

import model.MaskPoint;
import model.MaskPointLime;
import model.NoNeighborException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MeshPointTest {
    private MaskPoint testMaskPoint;

    @BeforeEach
    public void runBefore() {
        testMaskPoint = new MaskPointLime(100.0, 100.0);
        System.out.println("dogShoe");
    }

    @Test
    public void testGetXCoord() {
        assertTrue(testMaskPoint.getXCoord() == 100.0);
    }

    @Test
    public void testGetYCoord() {
        assertTrue(testMaskPoint.getYCoord() == 100.0);
    }

    @Test
    public void testMovePoint() {
        testMaskPoint.movePoint(250.0, 150.0);
        assertTrue(testMaskPoint.getXCoord() == 250.0);
        assertTrue(testMaskPoint.getYCoord() == 150.0);
    }

    @Test
    public void testNearestNeighborEmpty() {
        try {
            testMaskPoint.findNearestNeighbor(new ArrayList<MaskPoint>());
            fail("Should have found a NoNeighborException");
        } catch (NoNeighborException e) {
            System.out.println("Good");
        }
    }

    @Test
    public void testNearestNeighborSmall() {
        MaskPoint neighbor = new MaskPointLime(101.0, 101.0);
        ArrayList<MaskPoint> neighbors = new ArrayList<MaskPoint>();
        neighbors.add(neighbor);
        try {
            testMaskPoint.findNearestNeighbor(neighbors);
            fail("Should have found a NoNeighborException");
        } catch (NoNeighborException e) {
            System.out.println("Good");
        }
    }

    @Test
    public void testNearestNeighborLarge() {
        MaskPoint neighbor1 = new MaskPointLime(101.0, 101.0);
        MaskPoint neighbor2 = new MaskPointLime(150.0, 100.0);
        MaskPoint neighbor3 = new MaskPointLime(250.0, 100.0);
        ArrayList<MaskPoint> neighbors = new ArrayList<MaskPoint>();
        neighbors.add(neighbor1);
        neighbors.add(neighbor2);
        neighbors.add(neighbor3);
        try {
            MaskPoint nearestNeighbor = testMaskPoint.findNearestNeighbor(neighbors);
            assertEquals(nearestNeighbor, neighbor1);
        } catch (NoNeighborException e) {
            fail("Should not have found a NoNeighborException");
        }
    }
}
