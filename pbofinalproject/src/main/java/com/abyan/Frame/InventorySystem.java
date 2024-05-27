package com.abyan.Frame;

import javax.swing.*;
import com.abyan.Manager.Player;
import com.abyan.Object.Item;

import java.awt.*;
import java.awt.event.*;

public class InventorySystem extends JFrame {
    private HomeBaseFrame homeBaseFrame;

    // Components
    private JPanel gridPanel;
    private JPanel infoPanel;
    private JTabbedPane categoryTabs;
    private JLabel itemDescription;
    private JLabel itemImage; // Label to display item image
    private JButton[] itemSlots;
    private JButton buyButton; // Button to buy item

    public InventorySystem(HomeBaseFrame homeBaseFrame) {
        this.homeBaseFrame = homeBaseFrame;
        setTitle("Game Inventory System");
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Initialize components
        gridPanel = new JPanel(new GridLayout(4, 5, 10, 10));
        infoPanel = new JPanel(new BorderLayout());
        categoryTabs = new JTabbedPane();
        itemDescription = new JLabel("Select an item to see its description.");
        itemImage = new JLabel(); // Initialize the label for item image
        itemSlots = new JButton[Player.items.size()];

        // Set up grid panel with item slots
        for (int i = 0; i < Player.items.size(); i++) {
            itemSlots[i] = new JButton(Player.items.get(i).getName());
            itemSlots[i].setFocusable(false);
            itemSlots[i].addActionListener(new ItemButtonListener(i)); // Pass the index to the listener
            gridPanel.add(itemSlots[i]);
        }

        // Set up info panel
        infoPanel.add(itemDescription, BorderLayout.NORTH);
        infoPanel.add(itemImage, BorderLayout.CENTER); // Add the label for item image to the info panel

        // Set up category tabs
        categoryTabs.addTab("Potion", gridPanel);
        add(categoryTabs, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
    }

    private class ItemButtonListener implements ActionListener {
        private int index; // Store the index of the item

        public ItemButtonListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Item item = Player.items.get(index);
            itemDescription.setText(item.getName());

            // Update item image
            ImageIcon itemIcon = new ImageIcon(item.getImagePath());
            itemImage.setIcon(itemIcon);

            // If the buy button exists, remove it before creating a new one
            if (buyButton != null) {
                infoPanel.remove(buyButton);
            }

            // Create and add the buy button
            buyButton = new JButton("Beli (" + item.getHarga() + ")");
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
                    }else{
                        JOptionPane.showMessageDialog(InventorySystem.this, "Ep tidak cukup");
                    }
                    

                }
            });
            infoPanel.add(buyButton, BorderLayout.SOUTH);
            infoPanel.revalidate();
            infoPanel.repaint();
        }
    }
}
