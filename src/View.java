import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic; // tambah atribut ini
    private JLabel labelScore; // label untuk menampilkan skor
    private Image bgImage;

    public View(Logic logic){
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));

        // Load gambar background
        bgImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // tambahkan fokus dan key listener
        setFocusable(true);
        addKeyListener(logic);

        // label skor
        labelScore = new JLabel("Score: 0");
        labelScore.setFont(new Font("Arial", Font.BOLD, 20));
        labelScore.setForeground(Color.BLACK);
        labelScore.setBounds(10, 10, 150, 30);
        setLayout(null);
        add(labelScore);
    }



    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Gambar background
        g.drawImage(bgImage, 0, 0, width, height, null);

        // lanjut gambar burung dan pipa seperti biasa
        draw(g);
    }

    public void draw(Graphics g){
        Player player = logic.getPlayer();
        if (player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null){
            for (int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // Teks sebelum mulai
        // Jika belum mulai dan belum game over
        if (!logic.isGameStarted() && !logic.isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.setColor(Color.BLACK);
            String startText = "Tekan SPACE untuk mulai!";
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(startText);
            g.drawString(startText, (width - textWidth) / 2, height / 2);
            return; // berhenti gambar, biar belum mulai game nggak langsung jalan
        }

        // Tampilan jika kalah
        if (logic.isGameOver()) {
            Graphics2D g2 = (Graphics2D) g;

            // Menampilkan GAME OVER
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.setColor(Color.RED);
            String text1 = "GAME OVER";
            FontMetrics fm1 = g2.getFontMetrics();
            int text1Width = fm1.stringWidth(text1);
            int centerY = height / 2;
            g2.drawString(text1, (width - text1Width) / 2, centerY - 30);

            // Menampilkan skor akhir
            g2.setFont(new Font("Arial", Font.BOLD, 25));
            g2.setColor(Color.BLACK);
            String text3 = "Skor Akhir: " + logic.score;
            FontMetrics fm3 = g2.getFontMetrics();
            int text3Width = fm3.stringWidth(text3);
            g2.drawString(text3, (width - text3Width) / 2, centerY + 10);

            // Pemberitahuan restart
            g2.setFont(new Font("Arial", Font.PLAIN, 18));
            g2.setColor(Color.BLACK);
            String text2 = "Tekan R untuk mengulang (>__< )";
            FontMetrics fm2 = g2.getFontMetrics();
            int text2Width = fm2.stringWidth(text2);
            g2.drawString(text2, (width - text2Width) / 2, centerY + 50);
        }
    }

    // untuk update skor di layar
    public void updateScore(int score) {
        labelScore.setText("Score: " + score);
    }


}
