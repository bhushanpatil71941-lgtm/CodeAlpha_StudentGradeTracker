package components;

import java.awt.*;
import javax.swing.*;

public class Card extends JPanel {
    private JLabel lblValue;

    public Card(String title, String initialValue, Color bgColor, Color textColor) {
        setBackground(bgColor);
        setLayout(new GridLayout(2, 1, 5, 5));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitle = new JLabel(title.toUpperCase());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTitle.setForeground(new Color(128, 128, 128));

        lblValue = new JLabel(initialValue);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblValue.setForeground(textColor);

        add(lblTitle);
        add(lblValue);
    }

    public void setValue(String value) {
        lblValue.setText(value);
    }
}
