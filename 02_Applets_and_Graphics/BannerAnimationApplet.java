package applets_and_graphics;

import java.applet.Applet;
import java.awt.*;

/**
 * <h1>Banner Scrolling & Animation Applet</h1>
 * <p>
 * Demonstrates thread-driven multithreaded graphics animation within Java Applets:
 * <ul>
 *   <li>Implements <code>Runnable</code> with dedicated animation thread.</li>
 *   <li>Horizontal scrolling ticker banner with wrap-around physics.</li>
 *   <li>Bouncing orb simulation with elastic boundary collision detection.</li>
 *   <li>Double-buffered style smooth rendering via Graphics2D.</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@SuppressWarnings("removal")
public class BannerAnimationApplet extends Applet implements Runnable {
    private Thread animationThread = null;
    private volatile boolean isRunning = false;

    // Scrolling Banner state
    private String bannerText = "🚀 Welcome to Advance Java Curriculum & Architecture! • Master Swing, Applets, JDBC, Sockets & Servlets • ";
    private int bannerX = 700;
    private final int bannerY = 80;

    // Bouncing Ball state
    private int ballX = 50;
    private int ballY = 150;
    private int ballDX = 4;
    private int ballDY = 3;
    private final int ballRadius = 24;

    @Override
    public void init() {
        setSize(750, 380);
        setBackground(new Color(24, 32, 54)); // Dark futuristic theme
    }

    @Override
    public void start() {
        if (animationThread == null) {
            isRunning = true;
            animationThread = new Thread(this, "BannerAnimationThread");
            animationThread.start();
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            // Update Banner Position
            bannerX -= 3;
            if (bannerX < -1200) {
                bannerX = getWidth();
            }

            // Update Ball Physics
            ballX += ballDX;
            ballY += ballDY;

            int width = getWidth();
            int height = getHeight();

            if (ballX - ballRadius < 0 || ballX + ballRadius > width) {
                ballDX = -ballDX;
            }
            if (ballY - ballRadius < 110 || ballY + ballRadius > height - 20) {
                ballDY = -ballDY;
            }

            repaint();

            try {
                Thread.sleep(30); // ~33 FPS
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // 1. Ticker Header Background
        GradientPaint headerGradient = new GradientPaint(0, 0, new Color(30, 39, 46), 0, 110, new Color(11, 14, 17));
        g2.setPaint(headerGradient);
        g2.fillRect(0, 0, width, 110);

        // 2. Glowing Ticker Text
        g2.setFont(new Font("Segoe UI", Font.BOLD, 20));
        g2.setColor(new Color(0, 210, 211));
        g2.drawString(bannerText, bannerX, bannerY);

        // 3. Animation Arena Boundary
        g2.setColor(new Color(44, 58, 71));
        g2.drawRect(15, 120, width - 30, height - 140);

        // 4. Bouncing Orb with Radial Shadow
        GradientPaint orbPaint = new GradientPaint(
            ballX - ballRadius / 2f, ballY - ballRadius / 2f, new Color(255, 107, 107),
            ballX + ballRadius, ballY + ballRadius, new Color(238, 82, 83)
        );
        g2.setPaint(orbPaint);
        g2.fillOval(ballX - ballRadius, ballY - ballRadius, ballRadius * 2, ballRadius * 2);

        // Orb highlight
        g2.setColor(new Color(255, 255, 255, 180));
        g2.fillOval(ballX - ballRadius / 2, ballY - ballRadius / 2, ballRadius / 2, ballRadius / 2);

        // Arena Status Info
        g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g2.setColor(new Color(178, 190, 195));
        g2.drawString(String.format("Ball Vector: [%d, %d] | Position: (%d, %d)", ballDX, ballDY, ballX, ballY), 30, height - 35);
    }

    @Override
    public void stop() {
        isRunning = false;
        if (animationThread != null) {
            animationThread.interrupt();
            animationThread = null;
        }
    }

    @Override
    public void destroy() {
        stop();
    }
}
