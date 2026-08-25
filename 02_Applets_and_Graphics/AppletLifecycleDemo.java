package applets_and_graphics;

import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Java Applet Lifecycle Demonstration</h1>
 * <p>
 * Illustrates the complete Applet lifecycle methods:
 * <ol>
 *   <li><code>init()</code> - Invoked once when the applet is loaded to initialize resources.</li>
 *   <li><code>start()</code> - Invoked when the browser/appletviewer starts or resumes execution.</li>
 *   <li><code>paint(Graphics g)</code> - Invoked whenever the applet window needs redisplay.</li>
 *   <li><code>stop()</code> - Invoked when the user navigates away or browser is minimized.</li>
 *   <li><code>destroy()</code> - Invoked once prior to unloading the applet from memory.</li>
 * </ol>
 * </p>
 *
 * <p><b>Execution Command:</b> <code>appletviewer AppletLifecycleDemo.html</code></p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
@SuppressWarnings("removal")
public class AppletLifecycleDemo extends Applet {
    private final List<String> lifecycleLog = new ArrayList<>();
    private String statusMessage = "Applet Loaded";

    @Override
    public void init() {
        setBackground(new Color(245, 248, 250));
        recordEvent("1. init() called - Memory allocated & components initialized.");
        System.out.println("[Applet Lifecycle] init() invoked.");
    }

    @Override
    public void start() {
        statusMessage = "Applet Running Active";
        recordEvent("2. start() called - Applet execution started / resumed.");
        System.out.println("[Applet Lifecycle] start() invoked.");
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Header Title Card
        g2d.setColor(new Color(41, 128, 185));
        g2d.fillRoundRect(20, 20, 540, 50, 15, 15);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
        g2d.drawString("Java Applet Lifecycle Monitor", 40, 52);

        // Status
        g2d.setColor(new Color(52, 73, 94));
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        g2d.drawString("Current Status: " + statusMessage, 30, 95);

        // Event History Box
        g2d.setColor(Color.WHITE);
        g2d.fillRect(20, 110, 540, 240);
        g2d.setColor(new Color(189, 195, 199));
        g2d.drawRect(20, 110, 540, 240);

        g2d.setColor(new Color(44, 62, 80));
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 13));

        int yPos = 135;
        synchronized (lifecycleLog) {
            for (String event : lifecycleLog) {
                g2d.drawString("▶ " + event, 35, yPos);
                yPos += 25;
            }
        }
    }

    @Override
    public void stop() {
        statusMessage = "Applet Suspended";
        recordEvent("4. stop() called - Applet execution stopped / paused.");
        System.out.println("[Applet Lifecycle] stop() invoked.");
    }

    @Override
    public void destroy() {
        recordEvent("5. destroy() called - System resources released.");
        System.out.println("[Applet Lifecycle] destroy() invoked.");
    }

    private void recordEvent(String msg) {
        synchronized (lifecycleLog) {
            if (lifecycleLog.size() > 8) {
                lifecycleLog.remove(0);
            }
            lifecycleLog.add(msg);
        }
    }
}
