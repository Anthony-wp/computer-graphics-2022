package org.company.lab2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;

@Getter
@Setter
@AllArgsConstructor
public class GraphHolder {
    private Graphics2D g2d;
    private Line2D.Float line;
}
