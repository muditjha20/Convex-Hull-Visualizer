import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class SwingShell extends JFrame
        implements ActionListener, MouseListener {

    // The radius in pixels of the circles drawn in graph_panel

    final int NODE_RADIUS = 5;

    // GUI stuff
    CanvasPanel canvas = null;

    JPanel buttonPanel = null;
    JButton drawButton, clearButton;

    // Data Structures for the Points
    LinkedList<Point> vertices = new LinkedList<>();
    //LinkedList<Point> vertices = null;

    // Event handling stuff
    Dimension panelDim = null;

    public SwingShell() {
        super("Convex Hull creator");
        setSize(new Dimension(700, 575));

        // Initialize main data structures

        //The content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane,
                BoxLayout.Y_AXIS));

        //Create the drawing area
        canvas = new CanvasPanel(this);
        canvas.addMouseListener(this);

        Dimension canvasSize = new Dimension(700, 500);
        canvas.setMinimumSize(canvasSize);
        canvas.setPreferredSize(canvasSize);
        canvas.setMaximumSize(canvasSize);
        canvas.setBackground(Color.black);

        // Create buttonPanel and fill it
        buttonPanel = new JPanel();
        Dimension panelSize = new Dimension(700, 75);
        buttonPanel.setMinimumSize(panelSize);
        buttonPanel.setPreferredSize(panelSize);
        buttonPanel.setMaximumSize(panelSize);
        buttonPanel.setLayout(new BoxLayout(buttonPanel,
                BoxLayout.X_AXIS));
        buttonPanel.
                setBorder(BorderFactory.
                        createCompoundBorder(BorderFactory.
                                        createLineBorder(Color.red),
                                buttonPanel.getBorder()));

        Dimension buttonSize = new Dimension(175, 50);
        drawButton = new JButton("Convex Hull");
        drawButton.setMinimumSize(buttonSize);
        drawButton.setPreferredSize(buttonSize);
        drawButton.setMaximumSize(buttonSize);
        drawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        drawButton.setActionCommand("makehull");
        drawButton.addActionListener(this);
        drawButton.
                setBorder(BorderFactory.
                        createCompoundBorder(BorderFactory.
                                        createLineBorder(Color.green),
                                drawButton.getBorder()));

        clearButton = new JButton("Clear");
        clearButton.setMinimumSize(buttonSize);
        clearButton.setPreferredSize(buttonSize);
        clearButton.setMaximumSize(buttonSize);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setActionCommand("clearDiagram");
        clearButton.addActionListener(this);
        clearButton.
                setBorder(BorderFactory.
                        createCompoundBorder(BorderFactory.
                                        createLineBorder(Color.blue),
                                clearButton.getBorder()));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(drawButton);
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(clearButton);
        buttonPanel.add(Box.createHorizontalGlue());

        contentPane.add(canvas);
        contentPane.add(buttonPanel);
    }

    public void actionPerformed(ActionEvent e) {

        String buttonIdentifier = e.getActionCommand();

        if (buttonIdentifier.equals("clearDiagram")) {
            // toggle the color
            vertices.clear();
            canvas.clearHull();
            canvas.repaint();
        } else if (buttonIdentifier.equals("makehull")) {
            if (vertices.size() >= 2) {
                LinkedList<Point> hull = computeConvexHull(vertices);
                canvas.setHull(hull);
                canvas.repaint();
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        Point click_point = e.getPoint();
        vertices.add(click_point);
        canvas.repaint();
    }

    // using gift wrap
    public LinkedList<Point> computeConvexHull(LinkedList <Point> points) {
        if (points.size() <= 2)
            return new LinkedList<>(points);

        LinkedList<Point> hull = new LinkedList<>();

        // Find leftmost and lowest point
        Point start = Collections.min(points, Comparator.comparingInt(p -> p.x)); // built-in function

        Point current = start;

        // Keep finding next point
        do {
            hull.add(current);
            Point nextTarget = points.get(0);

            for (Point p : points) {
                if (p == current)
                    continue;

                int direction = crossProduct(current, nextTarget, p);
                if (direction > 0 || (direction == 0 && distance(current, p) > distance(current, nextTarget))) {
                    nextTarget = p;
                }
            }

            current = nextTarget;
        } while (current != start); // stop when we return to start

        return hull;
    }

    // calculate cross product
    private int crossProduct(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    // calculate distance squared
    private int distance(Point a, Point b) {
        return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}


