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
    private Stroke stroke;
    private Color color;
    private Line2D.Float line;
}
