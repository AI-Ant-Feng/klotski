package setsail.klotski;

import setsail.klotski.views.GamePanel;
import setsail.klotski.views.HelpPanel;

import javax.swing.*;
import java.awt.*;

public class GameClient extends JFrame{
    public static GamePanel gamePanel;
    public static HelpPanel helpPanel;
    public static Container panel;

    public GameClient(){

        helpPanel = new HelpPanel(1);
        gamePanel = new GamePanel(1);//设置关卡
        gamePanel.setLayout(new BorderLayout());
        panel = this.getContentPane();
        panel.setLayout(new BorderLayout());
        panel.add(helpPanel,BorderLayout.EAST);
        panel.add(gamePanel,BorderLayout.CENTER);
        this.setSize(650,650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("华容道");
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args) {
        new GameClient();
    }
}
