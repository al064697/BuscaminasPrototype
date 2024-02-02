import javax.swing.*;
import java.awt.*;

public class Frameset extends JFrame {
    int numRows = 10, numCols = 10;
    JButton[][] boardButtons;

    public Frameset(String title) {
        super(title);

        setSize(400,400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        panel.add(new JLabel("Usuario: "), BorderLayout.WEST);
        panel.add(new JTextField(10), BorderLayout.CENTER);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel.add(panel1, BorderLayout.SOUTH);
        panel1.add(new JLabel("Número de minas"), BorderLayout.WEST);
        panel1.add(new JTextField(10), BorderLayout.CENTER);
        panel1.add(new Button("Generar minas"), BorderLayout.EAST);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(numRows, numCols));
        add(panel2, BorderLayout.CENTER);
        boardButtons = new JButton[numRows][numCols];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardButtons[i][j] = new JButton();
                panel2.add(boardButtons[i][j]);
            }
        }

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        add(panel3, BorderLayout.SOUTH);
        panel3.add(new JLabel("Score: "), BorderLayout.WEST);
        panel3.add(new JTextField(10), BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Frameset("Buscaminas");
    }
}