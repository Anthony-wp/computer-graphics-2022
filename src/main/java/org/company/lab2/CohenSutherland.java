package org.company.lab2;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

public class CohenSutherland extends JFrame {
    private static Rectangle2D clipWindow;
    private static CohenSutherlandClipping algorithm;
    private static final double DELTA = 0.001;

    public CohenSutherland() {
        super("Cohen-Sutherland");

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.draw(clipWindow);
        Line2D.Float line = new Line2D.Float(50, 150, 350, 70);

        g2d.draw(line);

        Path2D path2D = new Path2D.Float();
        path2D.moveTo(40, 140);
        path2D.lineTo(340, 60);

        Stroke stroke1 = new BasicStroke(3f);

        g2d.draw(path2D);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(stroke1);
        g2d.setColor(Color.RED);
        g2d.draw(algorithm.clip(line));

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
