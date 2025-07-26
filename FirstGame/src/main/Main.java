package main;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();

        window.setSize(450,220);    // genislik yukseklik
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //x butonu
        window.setResizable(false); //yeniden olceklendirmesi
        window.setTitle("GAME");    //titlei

        windowSettings wnd = new windowSettings();
        window.add(wnd);    //bir bilesene baska bir bilesen ekleme

        window.pack();  //bilesenlere gore boyutlandirma tarzi birsey

        window.setLocationRelativeTo(null); //konumu ile ilgili
        window.setVisible(true);    //gorunurlugu

        wnd.startGameThread();
    }
}