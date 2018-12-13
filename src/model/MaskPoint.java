package model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

abstract public class MaskPoint implements MoveablePoint, SelectNode {
    private Double xCoord;
    private Double yCoord;
    private List<Line> lines;

    public MaskPoint(Double xCoord, Double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        lines = new ArrayList<>();
    }

    public Double getXCoord() { return xCoord; }
    public Double getYCoord() { return yCoord; }

    public void movePoint(Double newX, Double newY) {
        xCoord = newX;
        yCoord = newY;
    }

    public MaskPoint findNearestNeighbor(ArrayList<MaskPoint> listOfImagePoints) throws NoNeighborException {
        if (listOfImagePoints.size() < 2) {
            throw new NoNeighborException();
        }

        double smallestDistance = Double.POSITIVE_INFINITY;
        int index = -1;
        int smallestDistanceIndex = 0;

        if (listOfImagePoints.size() == 0) { return null; }

        for (MaskPoint point : listOfImagePoints) {
            index++;
            if (distanceBetweenPoints(this, point) < smallestDistance) {
                smallestDistance = distanceBetweenPoints(this, point);
                smallestDistanceIndex = index;
            }
        }

        MaskPoint nearestNeighbor = listOfImagePoints.get(smallestDistanceIndex);
        Line neighborConnection = new Line(this, nearestNeighbor);
        lines.add(neighborConnection);

        if (nearestNeighbor.getLines().size() > 2 && distanceBetweenPoints(this, nearestNeighbor) < 50) {
            //build new shape
        }

        return nearestNeighbor;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void removeLine(Line line) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i) == line) {
                lines.remove(i);
                line.removePoint(this);
            }
        }
    }

    private double distanceBetweenPoints(MaskPoint point1, MaskPoint point2) {
        return Math.sqrt(Math.pow((double) point1.getXCoord() - (double) point2.getXCoord(), 2) + Math.pow((double) point1.getYCoord() - (double) point2.getYCoord(), 2));
    }

    abstract public void paint(Pane layout);
}
