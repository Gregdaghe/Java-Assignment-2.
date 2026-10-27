import java.awt.*;
import java.io.File;
import javax.swing.*;

public class RadioButtonDemo extends JFrame {

    private JLabel imageLabel;

    public RadioButtonDemo() {
        setTitle("Radio Button Demo - Pet Selection");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel for radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(5, 1, 5, 5));
        radioPanel.setBorder(
            BorderFactory.createTitledBorder("Choose a Pet")
        );

        ButtonGroup group = new ButtonGroup();
        String[] pets = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};

        for (String pet : pets) {
            JRadioButton radioButton = new JRadioButton(pet);
            group.add(radioButton);
            radioPanel.add(radioButton);

            radioButton.addActionListener(e -> {
                displayPet(pet);

                JOptionPane.showMessageDialog(
                    this,
                    "You selected: " + pet,
                    "Pet Selection",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
        }

        // Label for displaying images
        imageLabel = new JLabel(
            "Select a pet to display its image",
            SwingConstants.CENTER
        );

        add(radioPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void displayPet(String pet) {
        String imagePath =
            "Images/" + pet.toLowerCase() + ".png";

        File file = new File(imagePath);

        // Try lowercase folder if needed
        if (!file.exists()) {
            file = new File(
                "images/" + pet.toLowerCase() + ".png"
            );
        }

        System.out.println(
            "Image location: " + file.getAbsolutePath()
        );
        System.out.println(
            "Image exists: " + file.exists()
        );

        if (file.exists()) {
            ImageIcon icon = new ImageIcon(
                file.getAbsolutePath()
            );

            Image image = icon.getImage().getScaledInstance(
                300, 280, Image.SCALE_SMOOTH
            );

            imageLabel.setIcon(new ImageIcon(image));
            imageLabel.setText("");
        } else {
            imageLabel.setIcon(null);
            imageLabel.setText(
                "Image not found: " + pet
                + " (check the Images folder)"
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RadioButtonDemo();
        });
    }
}