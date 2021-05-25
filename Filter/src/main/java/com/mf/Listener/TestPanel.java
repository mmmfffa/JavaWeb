package com.mf.Listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestPanel {
    public static void main(String[] args) {
        Frame frame = new Frame("监听器GUI");
        Panel panel = new Panel();
        frame.setLayout(null);
        frame.setBounds(300,300,600,400);
        frame.setBackground(Color.red);
        panel.setBounds(50,50,200,300);
        panel.setBackground(Color.gray);
        frame.add(panel);
        frame.setVisible(true);

       //关闭事件，加监听事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
