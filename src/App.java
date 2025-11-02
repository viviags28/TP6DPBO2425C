import javax.swing.*;

// Saya Vivi Agustina Suryana dengan NIM 2400456 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek
// Untuk keberkahan-Nya, maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan.

public class App {
    public static void main(String[] args){
        // Mulai dari menu utama
        SwingUtilities.invokeLater(() -> {
            new MainMenu(); // tampilkan menu utama
        });

        // Tunggu sampai tombol Play ditekan di MainMenu
        while (!MainMenu.startGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        // frame.setVisible(true);

        Logic logika = new Logic();   // instansiasi logic
        // instasiasi sehingga view bisa berkomunikasi dengan logic
        View tampilan = new View(logika);
        // begitu pula kebalikannya
        logika.setView(tampilan);


        // Insasiasi objek
        frame.add(tampilan);
        frame.pack();
        frame.setVisible(true);
        tampilan.requestFocus();

    }
}
