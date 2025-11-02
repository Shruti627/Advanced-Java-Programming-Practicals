import java.awt.*;
import java.awt.event.*;

public class MouseEventDemo extends Frame implements MouseListener, MouseMotionListener {
    private TextArea eventLog;
    private Label statusLabel;
    private Panel triggerPanel;
    private boolean mainPanelVisible = false;

    public MouseEventDemo() {
        super("AWT Mouse Events Demo");
        setupGUI();
        setupEvents();
    }

    private void setupGUI() {
        setSize(800, 600);
        setLocation(100, 100);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Add window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Status label
        statusLabel = new Label("Move mouse to GREEN area below to show main content", Label.CENTER);
        statusLabel.setBackground(Color.LIGHT_GRAY);
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(statusLabel, BorderLayout.NORTH);

        // Event log
        eventLog = new TextArea();
        eventLog.setEditable(false);
        eventLog.setBackground(Color.BLACK);
        eventLog.setForeground(Color.GREEN);
        eventLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Trigger panel
        triggerPanel = new Panel();
        triggerPanel.setBackground(new Color(0, 200, 0)); // Bright green
        triggerPanel.setPreferredSize(new Dimension(800, 100));
        triggerPanel.setLayout(new BorderLayout());

        Label triggerLabel = new Label("MOVE MOUSE HERE →", Label.CENTER);
        triggerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        triggerLabel.setForeground(Color.WHITE);
        triggerPanel.add(triggerLabel);

        add(triggerPanel, BorderLayout.SOUTH);
    }

    private void setupEvents() {
        // Add listeners to trigger panel
        triggerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                logEvent("TRIGGER: Mouse entered green area");
                showMainContent();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logEvent("TRIGGER: Mouse exited green area");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                logEvent("TRIGGER: Clicked at (" + e.getX() + "," + e.getY() + ")");
            }
        });

        // Add mouse listeners to the entire frame
        addMouseListener(this);
        addMouseMotionListener(this);

        // Also add to content area components
        eventLog.addMouseListener(this);
        eventLog.addMouseMotionListener(this);
    }

    private void showMainContent() {
        if (!mainPanelVisible) {
            add(eventLog, BorderLayout.CENTER);
            mainPanelVisible = true;
            statusLabel.setText("Main content visible! Move and click anywhere in the window");
            statusLabel.setBackground(Color.GREEN);
            validate(); // Refresh layout
            logEvent("=== MAIN CONTENT SHOWN ===");
            logEvent("Now move mouse and click to see events");
        }
    }

    private void logEvent(String message) {
        eventLog.append(message + "\n");
        eventLog.setCaretPosition(eventLog.getText().length());
        System.out.println(message);
    }

    // MouseListener methods
    public void mouseClicked(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        String button = getButtonName(e.getButton());
        String message = source + ": " + button + " click at (" + e.getX() + "," + e.getY() + ")";
        statusLabel.setText(message);
        statusLabel.setBackground(Color.YELLOW);
        logEvent(message);
    }

    public void mousePressed(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        logEvent(source + ": Mouse pressed at (" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseReleased(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        logEvent(source + ": Mouse released at (" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseEntered(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        if (source.equals("FRAME")) {
            statusLabel.setText("Mouse entered main window");
            statusLabel.setBackground(Color.CYAN);
        }
        logEvent(source + ": Mouse entered");
    }

    public void mouseExited(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        if (source.equals("FRAME")) {
            statusLabel.setText("Mouse exited main window");
            statusLabel.setBackground(Color.PINK);
        }
        logEvent(source + ": Mouse exited");
    }

    // MouseMotionListener methods
    public void mouseMoved(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        logEvent(source + ": Mouse moved to (" + e.getX() + "," + e.getY() + ")");
    }

    public void mouseDragged(MouseEvent e) {
        String source = getSourceName(e.getComponent());
        logEvent(source + ": Mouse dragged at (" + e.getX() + "," + e.getY() + ")");
        statusLabel.setText("Dragging mouse...");
        statusLabel.setBackground(Color.RED);
    }

    private String getSourceName(Component comp) {
        if (comp == this) return "FRAME";
        if (comp == eventLog) return "TEXTAREA";
        if (comp == triggerPanel) return "TRIGGER";
        return "UNKNOWN";
    }

    private String getButtonName(int button) {
        switch (button) {
            case MouseEvent.BUTTON1: return "Left";
            case MouseEvent.BUTTON2: return "Middle";
            case MouseEvent.BUTTON3: return "Right";
            default: return "Unknown";
        }
    }

    public static void main(String[] args) {
        // Make sure to run on EDT
        EventQueue.invokeLater(() -> {
            MouseEventDemo demo = new MouseEventDemo();
            demo.setVisible(true);

            // Force focus to the frame
            demo.requestFocus();

            System.out.println("=== AWT Mouse Events Demo Started ===");
            System.out.println("1. Move mouse into the GREEN area at the bottom");
            System.out.println("2. The main content will appear");
            System.out.println("3. Then move mouse around and click anywhere");
            System.out.println("4. Watch events appear in both the black area and console");
            System.out.println("=====================================");
        });
    }
}