import javax.swing.*;
import java.awt.*;

public class MENU {

    public static void start() {
        JFrame frame = new JFrame("Centered Text");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel WELCOME_TEXT = new JLabel("Play My Piano!", SwingConstants.CENTER);
        JLabel INFO_TEXT = new JLabel("You are Playing:", SwingConstants.CENTER);
        JLabel PIANO_INPUT = new JLabel(NOTES.show_note(), SwingConstants.CENTER);

        WELCOME_TEXT.setFont(new Font("Arial", Font.BOLD, 60));
        INFO_TEXT.setFont(new Font("Arial", Font.ITALIC, 40));
        PIANO_INPUT.setFont(new Font("Arial", Font.PLAIN, 50));

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        INFO_TEXT.setAlignmentX(Component.CENTER_ALIGNMENT);
        PIANO_INPUT.setAlignmentX(Component.CENTER_ALIGNMENT);
        WELCOME_TEXT.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelPanel.add(WELCOME_TEXT);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        labelPanel.add(INFO_TEXT);
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        labelPanel.add(PIANO_INPUT);

        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.add(labelPanel);
        frame.add(outerPanel);
        frame.setVisible(true);

        // Timer to update the PIANO_INPUT label every 100ms
        Timer timer = new Timer(100, e -> {
            PIANO_INPUT.setText(NOTES.show_note());
        });
        timer.start();
    }
}
