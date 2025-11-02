import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    public static boolean startGame = false;

    public MainMenu() {
        setTitle("Flappy Bird Menu");
        setSize(360, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel() {
            Image bg = new ImageIcon(getClass().getResource("assets/menu.png")).getImage();


            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null);

        // Judul besar di atas tombol
        JLabel title = new JLabel("FLAPPY GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(255, 215, 0)); // kuning tua
        title.setBounds(30, 200, 300, 60);
        panel.add(title);

        // Tombol Play
        JButton playButton = new JButton("Play");
        playButton.setBounds(110, 320, 140, 50);
        playButton.setFont(new Font("Arial", Font.BOLD, 22));
        playButton.setBackground(new Color(50, 205, 50)); // hijau
        playButton.setForeground(Color.WHITE);
        playButton.setFocusPainted(false);
        panel.add(playButton);

        // Tombol Exit
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(110, 390, 140, 50);
        exitButton.setFont(new Font("Arial", Font.BOLD, 22));
        exitButton.setBackground(new Color(220, 20, 60)); // merah
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        panel.add(exitButton);

        // Aksi tombol
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame = true;
                dispose();
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        add(panel);
        setVisible(true);
    }
}
