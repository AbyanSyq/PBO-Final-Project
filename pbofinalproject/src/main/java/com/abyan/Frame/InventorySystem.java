package com.abyan.Frame;

import com.abyan.Manager.*;
import com.abyan.Object.*;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InventorySystem extends JFrame {
    private HomeBaseFrame homeBaseFrame;

    private JPanel gridPanel;
    private JPanel infoPanel;
    private JLabel itemDescription;
    private JLabel itemImage; 
    private JButton[] itemSlots;
    private JButton buyButton;
    private BufferedImage backgroundImage;

    public InventorySystem(HomeBaseFrame homeBaseFrame) {
        this.homeBaseFrame = homeBaseFrame;
        setTitle("Game Inventory System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        loadBackgroundImage();

        BackgroundPanel mainPanel = new BackgroundPanel(backgroundImage);
        mainPanel.setLayout(new BorderLayout());

        gridPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        gridPanel.setOpaque(false); 

        infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false); 

        itemDescription = new JLabel("Select an item to see its description.");
        itemDescription.setOpaque(false); 
        itemImage = new JLabel();
        itemImage.setOpaque(false); 
        itemSlots = new JButton[Player.items.size()];

        Dimension buttonSize = new Dimension(100, 50); 
        for (int i = 0; i < Player.items.size(); i++) {
            itemSlots[i] = new JButton(Player.items.get(i).getName());
            itemSlots[i].setFocusable(false);
            itemSlots[i].setPreferredSize(buttonSize);
            itemSlots[i].setBackground(Color.BLACK); 
            itemSlots[i].setForeground(Color.WHITE); 
            itemSlots[i].setOpaque(true); 
            itemSlots[i].setBorderPainted(false); 
            itemSlots[i].addActionListener(new ItemButtonListener(i)); 
            gridPanel.add(itemSlots[i]);
        }

        infoPanel.add(itemDescription, BorderLayout.NORTH);
        infoPanel.add(itemImage, BorderLayout.CENTER); 

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("Asset/InventoryBackground.jpg")); 
            backgroundImage = resizeImage(backgroundImage, 800, 600); 
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading background image: " + e.getMessage());
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    private class ItemButtonListener implements ActionListener {
        private int index; 

        public ItemButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Item item = Player.items.get(index);
            itemDescription.setText(item.getName());

            ImageIcon itemIcon = new ImageIcon(item.getImagePath());
            itemImage.setIcon(itemIcon);

            if (buyButton != null) {
                infoPanel.remove(buyButton);
            }

            buyButton = new JButton("Beli (" + item.getHarga() + ")");
            buyButton.setBackground(Color.BLACK); 
            buyButton.setForeground(Color.WHITE); 
            buyButton.setOpaque(true); 
            buyButton.setBorderPainted(false); 
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (Player.ep >= item.getHarga()) {
                        JOptionPane.showMessageDialog(InventorySystem.this, "You bought " + item.getName() + " for " + item.getHarga());
                        if (item.getName().equalsIgnoreCase("Heal Potion")) {
                            Player.healPotion++;
                        } else if (item.getName().equalsIgnoreCase("Damage Potion")) {
                            Player.damagePotion++;
                        }
                        Player.ep -= item.getHarga();
                        homeBaseFrame.updateMonsterInfo();
                    } else {
                        JOptionPane.showMessageDialog(InventorySystem.this, "Ep tidak cukup");
                    }
                }
            });
            infoPanel.add(buyButton, BorderLayout.SOUTH);
            infoPanel.revalidate();
            infoPanel.repaint();
        }
    }

    class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventorySystem(new HomeBaseFrame()).setVisible(true);
            }
        });
    }
}
