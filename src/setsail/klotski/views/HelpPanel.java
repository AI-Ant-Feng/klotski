package setsail.klotski.views;

import setsail.klotski.entites.MapFactory;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {

    private String[] names = new String[]{
            "七步成诗",
            "横刀立马",
            "屯兵东路",
            "插翅难飞",
            "巧过五关",
            "层层设防",
            "近在咫尺",
            "兵临曹营",
            "众志成城",
            "佳人梳妆"
    };
    private int level;
    private JPanel panelNorth = new JPanel(new GridLayout(4,2,10,30));
    public static JLabel yourSteps = new JLabel("0");
    private JLabel currentLevel = new JLabel("");
    private JLabel record = new JLabel("9999");
    public static JButton restart = new JButton("重置");

    public HelpPanel(int level){
        this.level = level;
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        panelNorth.add(new JLabel("关卡名字："));
        panelNorth.add(currentLevel);
        panelNorth.add(new JLabel("当前步数："));
        panelNorth.add(yourSteps);
        panelNorth.add(new JLabel("历史记录："));
        panelNorth.add(record);
        panelNorth.add(restart);
        this.add(panelNorth, BorderLayout.NORTH);
        initialize();
    }

    public void initialize(){
        currentLevel.setText(names[level - 1] + "");
        yourSteps.setText("0");
        record.setText("" + MapFactory.getRecord(level));
    }
}
