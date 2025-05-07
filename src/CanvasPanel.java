import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.util.*;

public class CanvasPanel extends JPanel {

    SwingShell parent = null;
    LinkedList<Point> vertices = null;
    Color currentColor = Color.red;
    LinkedList<Point> hullVertices = new LinkedList<>();

    public CanvasPanel(SwingShell _parent) {
        super();
        parent = _parent;
        vertices = parent.vertices;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(currentColor);

        ListIterator iterator = vertices.listIterator(0);

        Point currentVertex = null;

        for (int i = 0; i < vertices.size(); ++i) {
            currentVertex = (Point) iterator.next();
            g.fillOval(currentVertex.x - parent.NODE_RADIUS,
                    currentVertex.y - parent.NODE_RADIUS,
                    2 * parent.NODE_RADIUS, 2 * parent.NODE_RADIUS);
        }
        g.setColor(Color.green);
        for (int i = 0; i < hullVertices.size(); i++) {
            Point p1 = hullVertices.get(i);
            Point p2 = hullVertices.get((i + 1) % hullVertices.size());
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void setHull(LinkedList<Point> hull) {
        this.hullVertices = hull;
    }

    public void clearHull() {
        this.hullVertices.clear();
    }
}







