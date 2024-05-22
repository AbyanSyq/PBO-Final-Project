package com.abyan.Template;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonsterPanel extends JFrame {
    private JLabel monsterLabel;
    private String[] monsterImages = {
        "air1.png",  // Ganti dengan path gambar monster pertama
        "air2.png"   // Ganti dengan path gambar monster kedua
    };
    private int currentMonsterIndex = 0;

    public MonsterPanel() {
        setTitle("Monster Selector");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        JButton prevButton = new JButton("Previous Monster");
        JButton nextButton = new JButton("Next Monster");

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMonster(-1);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeMonster(1);
            }
        });

        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        // Label untuk menampilkan gambar monster
        monsterLabel = new JLabel();
        monsterLabel.setHorizontalAlignment(JLabel.CENTER);
        updateMonsterImage();

        // Tambahkan komponen ke frame
        add(monsterLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void changeMonster(int direction) {
        currentMonsterIndex += direction;

        // Pastikan indeks tetap berada dalam rentang array
        if (currentMonsterIndex < 0) {
            currentMonsterIndex = monsterImages.length - 1;
        } else if (currentMonsterIndex >= monsterImages.length) {
            currentMonsterIndex = 0;
        }

        updateMonsterImage();
    }

    private void updateMonsterImage() {
        ImageIcon icon = new ImageIcon(monsterImages[currentMonsterIndex]);
        monsterLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MonsterPanel();
            }
        });
    }
}
