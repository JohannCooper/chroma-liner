package model;

import java.util.ArrayList;

public interface SelectNode {
    MaskPoint findNearestNeighbor(ArrayList<MaskPoint> listOfPoints) throws NoNeighborException;
}
