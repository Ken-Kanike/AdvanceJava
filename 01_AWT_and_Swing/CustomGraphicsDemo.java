package awt_and_swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * <h1>Custom 2D Graphics and PaintComponent Demo</h1>
 * <p>
 * Demonstrates advanced custom rendering in Java Swing:
 * <ul>
 *   <li>Overriding <code>paintComponent(Graphics g)</code> with <code>Graphics2D</code></li>
 *   <li>Rendering Hints (Anti-aliasing, Text Quality)</li>
 *   <li>Linear Gradient & Radial Gradient Fills</li>
 *   <li>Geometric Primitives: RoundRect, Ellipse, Polygons, Cubic Curves</li>
 *   <li>Interactive Freehand Drawing Canvas with dynamic stroke and color</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class CustomGraphicsDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java 2D Custom Graphics & Canvas Studio");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 650);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = new JTabbedPane();
            tabs.addTab("2D Shapes & Gradients", new ShapesAndGradientsPanel());
            tabs.addTab("Interactive Paint Canvas", new InteractiveDrawingCanvas());

            frame.add(tabs);
            frame.setVisible(true);
        });
    }
}

/**
 * Panel showcasing Graphics2D anti-aliased shapes, gradients, and custom typography.
 */
class ShapesAndGradientsPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable high-quality anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // 1. Linear Gradient Background Box
        GradientPaint gradient = new GradientPaint(20, 20, new Color(52, 152, 219), 260, 160, new Color(142, 68, 173));
        g2d.setPaint(gradient);
        g2d.fillRoundRect(30, 30, 240, 140, 25, 25);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g2d.drawString("Linear Gradient Card", 55, 105);

        // 2. Dashed Circle / Ellipse
        g2d.setPaint(new Color(230, 126, 34));
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawOval(320, 30, 140, 140);
        g2d.setColor(new Color(243, 156, 18, 120)); // Semi-transparent fill
        g2d.fillOval(320, 30, 140, 140);

        // 3. Custom Polygon (Star / Pentagon)
        int[] xPoints = {550, 590, 640, 600, 620, 550, 480, 500, 460, 510};
        int[] yPoints = {30, 75, 75, 110, 160, 130, 160, 110, 75, 75};
        g2d.setPaint(new Color(231, 76, 60));
        g2d.fillPolygon(xPoints, yPoints, 10);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(192, 57, 43));
        g2d.drawPolygon(xPoints, yPoints, 10);

        // 4. Bezier Curve / Sine Wave Simulation
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setPaint(new Color(46, 204, 113));
        CubicCurve2D curve = new CubicCurve2D.Float(50, 300, 200, 200, 350, 400, 500, 300);
        g2d.draw(curve);

        g2d.setColor(new Color(44, 62, 80));
        g2d.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        g2d.drawString("Cubic Bezier Spline Curve (Graphics2D)", 150, 350);

        // 5. Pie Chart Segment
        g2d.setPaint(new Color(26, 188, 156));
        g2d.fillArc(600, 230, 150, 150, 0, 120);
        g2d.setPaint(new Color(52, 152, 219));
        g2d.fillArc(600, 230, 150, 150, 120, 150);
        g2d.setPaint(new Color(241, 196, 15));
        g2d.fillArc(600, 230, 150, 150, 270, 90);
    }
}

/**
 * Interactive canvas enabling real-time drawing.
 */
class InteractiveDrawingCanvas extends JPanel {
    private final List<Point> points = new ArrayList<>();
    private Color currentColor = new Color(41, 128, 185);

    public InteractiveDrawingCanvas() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnClear = new JButton("Clear Canvas");
        JButton btnBlue = new JButton("Blue");
        JButton btnRed = new JButton("Red");
        JButton btnGreen = new JButton("Green");

        btnBlue.addActionListener(e -> currentColor = new Color(41, 128, 185));
        btnRed.addActionListener(e -> currentColor = new Color(231, 76, 60));
        btnGreen.addActionListener(e -> currentColor = new Color(39, 174, 96));
        btnClear.addActionListener(e -> {
            points.clear();
            repaint();
        });

        toolbar.add(new JLabel("Brush Color:"));
        toolbar.add(btnBlue);
        toolbar.add(btnRed);
        toolbar.add(btnGreen);
        toolbar.add(btnClear);
        add(toolbar, BorderLayout.NORTH);

        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                points.add(null); // Delimiter for continuous strokes
            }
        };

        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            if (p1 != null && p2 != null) {
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}
