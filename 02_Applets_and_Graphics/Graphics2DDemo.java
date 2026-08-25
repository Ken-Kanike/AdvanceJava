package applets_and_graphics;

import java.applet.Applet;
import java.awt.*;
import java.awt.geom.*;

/**
 * <h1>Applet Graphics2D Rendering Showcase</h1>
 * <p>
 * Demonstrates high-fidelity 2D geometric rendering within an Applet canvas:
 * <ul>
 *   <li>Anti-aliasing rendering hints</li>
 *   <li>Linear and Cyclic Gradient Paints</li>
 *   <li>Custom dashed strokes and joined paths</li>
 *   <li>Complex polygons, ellipses, arcs, and typography</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@SuppressWarnings("removal")
public class Graphics2DDemo extends Applet {

    @Override
    public void init() {
        setSize(700, 480);
        setBackground(new Color(250, 250, 252));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Enable Anti-Aliasing for smooth curves and shapes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 1. Gradient Rounded Card
        GradientPaint gp = new GradientPaint(30, 30, new Color(74, 105, 189), 230, 180, new Color(106, 137, 204));
        g2.setPaint(gp);
        g2.fill(new RoundRectangle2D.Double(30, 30, 200, 150, 20, 20));

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        g2.drawString("Java 2D Gradient", 65, 110);

        // 2. Dashed Circle with Radial Feel
        g2.setPaint(new Color(235, 94, 40));
        float[] dashPattern = {10, 8};
        g2.setStroke(new BasicStroke(3.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashPattern, 0));
        g2.draw(new Ellipse2D.Double(270, 30, 150, 150));

        g2.setPaint(new Color(254, 215, 0, 150));
        g2.fill(new Ellipse2D.Double(285, 45, 120, 120));

        // 3. Custom GeneralPath (Star Shape)
        GeneralPath star = new GeneralPath();
        double[] xPoints = {550, 570, 630, 580, 600, 550, 500, 520, 470, 530};
        double[] yPoints = {30, 80, 80, 120, 170, 140, 170, 120, 80, 80};

        star.moveTo(xPoints[0], yPoints[0]);
        for (int i = 1; i < xPoints.length; i++) {
            star.lineTo(xPoints[i], yPoints[i]);
        }
        star.closePath();

        g2.setPaint(new Color(38, 222, 129));
        g2.fill(star);
        g2.setColor(new Color(32, 191, 107));
        g2.setStroke(new BasicStroke(2));
        g2.draw(star);

        // 4. Arc and Sector Chart
        g2.setPaint(new Color(235, 77, 75));
        g2.fill(new Arc2D.Double(50, 230, 180, 180, 0, 135, Arc2D.PIE));

        g2.setPaint(new Color(104, 109, 224));
        g2.fill(new Arc2D.Double(50, 230, 180, 180, 135, 100, Arc2D.PIE));

        g2.setPaint(new Color(240, 147, 43));
        g2.fill(new Arc2D.Double(50, 230, 180, 180, 235, 125, Arc2D.PIE));

        // 5. Bézier Spline Curves
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        g2.setPaint(new Color(19, 15, 64));
        QuadCurve2D quadCurve = new QuadCurve2D.Double(300, 350, 450, 220, 600, 350);
        g2.draw(quadCurve);

        g2.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        g2.drawString("Quad Bézier Vector Spline", 390, 380);
    }
}
