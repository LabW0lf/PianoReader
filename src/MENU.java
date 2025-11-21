import javax.swing.*;
import java.awt.*;

public class MENU {
    public static void start() {
        JFrame frame = new JFrame("Centered Text");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Labels aka. Text that will be outputted
        JLabel WELCOME_TEXT = new JLabel("Play My Piano!", SwingConstants.CENTER);
        JLabel INFO_TEXT = new JLabel("You are Playing:", SwingConstants.CENTER);
        JLabel PIANO_INPUT = new JLabel("Cmaj7", SwingConstants.CENTER);

        // choose font family, weight and size
        WELCOME_TEXT.setFont(new Font("Arial", Font.BOLD, 60));
        INFO_TEXT.setFont(new Font("Arial", Font.ITALIC, 40));
        PIANO_INPUT.setFont(new Font("Arial", Font.PLAIN, 50));

        // Panel to stack labels vertically
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS)); // responsible for vertical stacking
        INFO_TEXT.setAlignmentX(Component.CENTER_ALIGNMENT); // ensures element is centered horizontally in the stack
        PIANO_INPUT.setAlignmentX(Component.CENTER_ALIGNMENT);
        WELCOME_TEXT.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPanel.add(WELCOME_TEXT); // add the label
        labelPanel.add(Box.createRigidArea(new Dimension(0, 50))); // space between labels
        labelPanel.add(INFO_TEXT); // add the label
        labelPanel.add(Box.createRigidArea(new Dimension(0, 10))); // space between labels
        labelPanel.add(PIANO_INPUT);

        // Outer panel to center everything
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.add(labelPanel);

        frame.add(outerPanel);
        frame.setVisible(true);
    }
}
