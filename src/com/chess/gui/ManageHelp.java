package com.chess.gui;

import javax.swing.*;

public class ManageHelp {
    public JPanel MainContainer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ManageHelp");
        frame.setContentPane(new ManageHelp().MainContainer);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.pack();
        frame.setVisible(true);
    }
}