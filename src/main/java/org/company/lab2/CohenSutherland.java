package org.company.lab2;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class CohenSutherland extends JFrame {
    private static Rectangle2D clipWindow;
    private static CohenSutherlandClipping algorithm;
    private static final double DELTA = 0.001;
    private static Stroke STROKE = new BasicStroke(3f);
    private static Stroke BASIC_STROKE = new BasicStroke(1f);

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

        g2d.draw(line);

        g2d.setColor(Color.RED);
        g2d.setStroke(STROKE);
        g2d.draw(algorithm.clip(line));

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
