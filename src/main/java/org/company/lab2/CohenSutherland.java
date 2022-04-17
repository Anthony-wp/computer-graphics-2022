package org.company.lab2;

import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class CohenSutherland extends JFrame {
    private static Rectangle2D clipWindow;
    private static CohenSutherlandClipping algorithm;
    private static final double DELTA = 0.001;
//    private static Stroke STROKE = new BasicStroke(3f);
//    private static Stroke BASIC_STROKE = new BasicStroke(1f);

    public CohenSutherland() {
        super("Cohen-Sutherland");

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    void drawLines(Graphics g) {
        Line2D.Float line = new Line2D.Float(130, 50, 350, 170);

        Graphics2D g2d = (Graphics2D) g;
        drawRectangle(g2d);
        GraphHolder graphHolder;

        float x1 = RandomUtils.nextFloat(10, 350);
        float y1 = RandomUtils.nextFloat(10, 350);
        float x2 = RandomUtils.nextFloat(10, 350);
        float y2 = RandomUtils.nextFloat(10, 350);

        line.setLine(x1, y1, x2, y2);
        for (int i = 0; i < 30; i++) {
            x2 = (float) line.getX2();
            y2 = (float) line.getY2();

            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(1f));
            g2d.draw(line);

            graphHolder = algorithm.clip(line);
            g2d.setStroke(graphHolder.getStroke());
            g2d.setColor(graphHolder.getColor());
            g2d.draw(graphHolder.getLine());

            x1 = RandomUtils.nextFloat(50, 350);
            y1 = RandomUtils.nextFloat(50, 350);

            line.setLine(x2, y2, x1, y1);
        }

    }

    private void drawRectangle(Graphics2D g2d) {
        g2d.draw(clipWindow);
    }

    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }

    public static void main(String[] args) {
        clipWindow = new Rectangle(100, 100, 200, 200);
        algorithm = new CohenSutherlandClipping(clipWindow);

        JFrame jFrame = new JFrame();
        jFrame.setSize(600, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CohenSutherland().setVisible(true);
            }
        });
    }
}
