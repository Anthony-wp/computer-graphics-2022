package org.company.lab2;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class lab2 {
    private static Rectangle2D clipWindow;
    private static CohenSutherlandClipping algorithm;
    private static final double DELTA = 0.001;

    public static void main(String[] args) {
        clipWindow = new Rectangle(10, 10, 50, 50);
        algorithm = new CohenSutherlandClipping(clipWindow);
        Line2D.Float line = new Line2D.Float(5, 25, 25, 5);
        System.out.println(algorithm.clip(line));
    }
}
