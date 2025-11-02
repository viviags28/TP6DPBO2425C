import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener{
    int frameWidth = 360;
    int frameHiegth = 640;

    int playerStarPosX = frameWidth / 2;
    int playerStarPosY = frameHiegth / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    // tamahkan atribut posisi dan ukuran pipa
    int pipeStarPosX = frameWidth;
    int pipestarPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    View view;
    Image birdImage;
    Player player;

    // tambahkan list ppia dan gambarnya
    Image lowePipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesColdown;
    int gravity = 1;

    int pipeVelocityX = -2;
    // tambah untuk konfirmasi gameOver
    boolean gameOver = false;
    boolean gameStarted = false; // nunggu game mulai
    int score = 0;

    public Logic(){
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStarPosX, playerStarPosY, playerWidth, playerHeight, birdImage);

        lowePipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesColdown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!gameOver) placePipes(); // berhenti buat pipa kalau game over
            }
        });
        pipesColdown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void setView(View view) {
        this.view = view;
    }

    public Player getPlayer(){
        return player;
    }

    public ArrayList<Pipe> getPipes(){
        return  pipes;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void placePipes(){
    int randomPosY = (int) (pipestarPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
    int openingSpace = frameHiegth / 4;

    Pipe upperPipe = new Pipe(pipeStarPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
    pipes.add(upperPipe);

    Pipe lowerPipe = new Pipe(pipeStarPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowePipeImage);
    pipes.add(lowerPipe);
    }

    public void move() {
        if (!gameStarted || gameOver) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        checkCollisionAndScore();
        updateScore();
    }

    // Mengecek tabrakan dan kondisi kalah
    public void checkCollisionAndScore() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());

            // Jika burung menyentuh pipa → Game Over
            if (playerRect.intersects(pipeRect)) {
                gameOver = true;
            }
        }

        // Jika burung jatuh di bawah layar → Game Over
        if (player.getPosY() > frameHiegth - player.getHeight()) {
            gameOver = true;
        }
    }


    // Reset ke awalgame
    public void resetGame() {
        gameOver = false;
        player = new Player(playerStarPosX, playerStarPosY, playerWidth, playerHeight, birdImage);
        pipes.clear();
        score = 0;

        if (view != null) {
            view.updateScore(0);
        }

    }

    // Update skor saat player melewati pipa (hitung hanya pipa bawah)
    public void updateScore() {
        for (Pipe pipe : pipes) {
            // hanya pipa bawah yang memicu skor
            if (pipe.getPosY() > 0) continue;

            if (!pipe.isPassed() && pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
                if (view != null) {
                    view.updateScore(score);
                }
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e){
        // Saat belum Game Over, jalankan pergerakan
        if (!gameOver) {
            move();
            checkCollisionAndScore();
        }
        if (view != null){
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e) {
        // Tekan SPACE untuk mulai dan loncat
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true;
                pipesColdown.start(); // baru mulai generate pipa
            }
            if (!gameOver) player.setVelocityY(-10);
        }

        // Tekan R untuk restart
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) resetGame();
    }

    public void keyReleased(KeyEvent e){}
}
