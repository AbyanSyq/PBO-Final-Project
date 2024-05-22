package com.abyan;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        // Memuat gambar dari path yang diberikan
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Menghitung skala gambar berdasarkan tinggi panel
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        int imageWidth = backgroundImage.getWidth(null);
        int imageHeight = backgroundImage.getHeight(null);

        // Menyesuaikan lebar gambar agar mempertahankan rasio aspek
        int newHeight = panelHeight;
        int newWidth = (int) ((double) newHeight * imageWidth / imageHeight);

        // Menghitung posisi gambar agar berada di tengah panel secara horizontal
        int x = (panelWidth - newWidth) / 2;
        int y = 0; // gambar akan mulai dari atas

        // Menggambar gambar di tengah panel dengan ukuran yang diubah sesuai tinggi panel
        g.drawImage(backgroundImage, x, y, newWidth, newHeight, this);
    }
}
