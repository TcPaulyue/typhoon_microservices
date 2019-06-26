package com.nju.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class ListenerApplicationTests {
        private JFrame jFrame;
        private JTextArea jTextArea;
    @Test
    public void DisplayListener(){
        jFrame= new JFrame();
        jTextArea=new JTextArea("Welcome to javatpoint");
        jTextArea.insert("雨情：",100);
        jTextArea.setBounds(10,30, 500,500);
        jFrame.add(jTextArea);
        jFrame.setSize(500,500);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }
}
