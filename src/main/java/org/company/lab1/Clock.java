package org.company.lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.util.*;
import java.text.*;

public class Clock extends JPanel implements Runnable {
    int width = 400;
    int height = 400;
    Thread t = null;
    boolean threadSuspended;
    int hours = 0, minutes = 0, seconds = 0;
    String timeString = "";
    SimpleDateFormat formatter;
    Font font;
    FontRenderContext frc;
    final int PAD;

    Clock() {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.black);
        formatter = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());
        font = new Font("lucida sans demibold", Font.PLAIN, 22);
        frc = new FontRenderContext(null, true, false);
        PAD = 10;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.setPriority(Thread.MIN_PRIORITY);
            threadSuspended = false;
            t.start();
        } else {
            if (threadSuspended) {
                threadSuspended = false;
                synchronized (this) {
                    notify();
                }
            }
        }
    }

    public void stop() {
        threadSuspended = true;
    }

    public void run() {
        try {
            while (true) {
                Calendar cal = Calendar.getInstance();
                hours = cal.get(Calendar.HOUR_OF_DAY);
                if (hours > 12) hours -= 12;
                minutes = cal.get(Calendar.MINUTE);
                seconds = cal.get(Calendar.SECOND);
                Date date = cal.getTime();
                timeString = formatter.format(date);
                if (threadSuspended) {
                    synchronized (this) {
                        while (threadSuspended) {
                            wait();
                        }
                    }
                }
                repaint();
                t.sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }

    private void drawFace(Graphics g)
    {
        g.drawOval(width / 16, height / 16, width * 7 / 8, height * 7 / 8);
        double theta = 0;
        int x0 = width / 2;
        int y0 = height / 2;
        int r = width * 7 / 16;
        for (int i = 0; i < 12; i++) {
            int x1 = x0 + (int) (r * Math.cos(theta));
            int y1 = y0 + (int) (r * Math.sin(theta));
            int x2 = x0 + (int) ((r - 4) * Math.cos(theta));
            int y2 = y0 + (int) ((r - 4) * Math.sin(theta));
            g.drawLine(x1, y1, x2, y2);
            theta += Math.PI / 6;
        }
        // numbers
        LineMetrics lm;
        for (int i = 0; i < 4; i++) {
            String s = String.valueOf((i + 1) * 3);
            float textWidth = (float) font.getStringBounds(s, frc).getWidth();
            lm = font.getLineMetrics(s, frc);
            float textHeight = lm.getAscent();
            double cos = Math.cos(theta);
            double sin = Math.sin(theta);
            double cx = x0 + (r - PAD) * cos - (textWidth * cos) / 2;
            double cy = y0 + (r - PAD) * sin - (textHeight * sin) / 2;
            int sx = (int) (cx - textWidth / 2);
            int sy = (int) (cy + textHeight / 2);
            g.drawString(s, sx, sy);
            theta += Math.PI / 2;
        }
    }

    void drawHand(double angle, int radius, Graphics g) {
        angle -= 0.5 * Math.PI;
        int x = (int) (radius * Math.cos(angle));
        int y = (int) (radius * Math.sin(angle));
        g.drawLine(width / 2, height / 2, width / 2 + x, height / 2 + y);
    }

    void drawWedge(double angle, int radius, Graphics g) {
        angle -= 0.5 * Math.PI;
        int x = (int) (radius * Math.cos(angle));
        int y = (int) (radius * Math.sin(angle));

        angle += 2 * Math.PI / 3;
        int x2 = (int) (5 * Math.cos(angle));
        int y2 = (int) (5 * Math.sin(angle));

        angle += 2 * Math.PI / 3;
        int x3 = (int) (5 * Math.cos(angle));
        int y3 = (int) (5 * Math.sin(angle));

        g.drawLine(width / 2 + x2, height / 2 + y2, width / 2 + x, height / 2 + y);
        g.drawLine(width / 2 + x3, height / 2 + y3, width / 2 + x, height / 2 + y);
        g.drawLine(width / 2 + x2, height / 2 + y2, width / 2 + x3, height / 2 + y3);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics.setFont(font);
        graphics.setColor(Color.white);

        drawFace(graphics);

        drawWedge(2 * Math.PI * hours / 12, width / 5, graphics);
        drawWedge(2 * Math.PI * minutes / 60, width / 3, graphics);

        drawHand(2 * Math.PI * seconds / 60, width * 3 / 8, graphics);

        graphics.setColor(Color.white);
        graphics.drawString(timeString, 10, height - 10);
    }

    public static void main(String[] args) {
        Clock clock = new Clock();
        clock.start();
        JFrame jf = new JFrame("Clock");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = jf.getContentPane();
        c.add(clock);
//        jf.setBounds (10, 10, 400,400);
        jf.pack();
        jf.setVisible(true);
    }
}