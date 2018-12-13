package model;


import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<MaskPoint>  points;

    public Line(MaskPoint point1, MaskPoint point2) {
        points = new ArrayList<MaskPoint>();
        points.add(point1);
        points.add(point2);
    }

    public List<MaskPoint> getPoints() { return points; }

    public void removePoint(MaskPoint point) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i) == point) {
                points.remove(i);
                point.removeLine(this);
            }
        }
    }
}
