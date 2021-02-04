package setsail.klotski.views;

import setsail.klotski.GameClient;
import setsail.klotski.entites.Node;
import setsail.klotski.entites.Person;
import setsail.klotski.utils.Constant;
import setsail.klotski.utils.Direction;
import setsail.klotski.utils.MapUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel
        implements ActionListener, MouseListener, KeyListener{

    private int level;

    public GamePanel(int level) {
        this.level = level;
        map = MapUtil.getMap(level);
        persons = new Person[10];
        icons = new Icon[15];
        pics = new Image[15];
        this.setLayout(null);
        this.setSize(400, 500);
        this.getIcons();
        initialize();
        HelpPanel.restart.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                yourStep = 0;
                for(int i = 0; i < 10; i++){
                    GameClient.gamePanel.remove(persons[i]);
                }
                repaint();
                map = MapUtil.getMap(level);
                GameClient.panel.remove(GameClient.helpPanel);
                GameClient.helpPanel = new HelpPanel(level);
                GameClient.panel.add(GameClient.helpPanel,BorderLayout.EAST);
                GameClient.helpPanel.validate();
                GameClient.gamePanel.validate();
                GameClient.panel.validate();
                initialize();
                setVisible(true);
                GameClient.gamePanel.requestFocus();
                repaint();
            }
        });
    }

    private Person[] persons;

    public void paint(Graphics graphics){
        super.paint(graphics);
        setLayout(null);
        graphics.clearRect(0,0, getWidth(), getHeight());
        for(int i = 0; i < 10; i++){
            persons[i].requestFocus();
            persons[i].paintComponents(graphics);
        }
    }

    private Image[] pics = new Image[15];
    private Icon[] icons;

    /**
     * 获取15张人物图片，并存至Icon[]中。
     */
    private void getIcons(){
        for(int i = 0; i < 15; i ++){//棋盘是4*5=20格。备选人物一共有15个。1个曹操，5个竖将，5个横将，4个小兵。
            if(i == 0){//曹操（横两个格，竖两个格）
                pics[i] = Toolkit.getDefaultToolkit().
                        getImage("src/img/pic" + i + ".png").
                        getScaledInstance(
                                2 * Constant.WIDTH_PER_GRID,
                                2 * Constant.HEIGHT_PER_GRID,
                                Image.SCALE_SMOOTH);
            }else if(i == 1 || i == 2 || i == 3 || i == 4 || i == 5){//5个横将（横两个格，竖一个格）
                pics[i] = Toolkit.getDefaultToolkit().
                        getImage("src/img/pic" + i + ".png").
                        getScaledInstance(
                                2 * Constant.WIDTH_PER_GRID,
                                Constant.HEIGHT_PER_GRID,
                                Image.SCALE_SMOOTH);
            }else if(i == 6 || i == 7 || i == 8 || i == 9) {//4个小兵（横一个格，竖一个格）
                pics[i] = Toolkit.getDefaultToolkit().
                        getImage("src/img/pic" + i + ".png").
                        getScaledInstance(
                                Constant.WIDTH_PER_GRID,
                                Constant.HEIGHT_PER_GRID,
                                Image.SCALE_SMOOTH);
            }else {//其余5个竖将（横一个格，竖两个格）
                pics[i] = Toolkit.getDefaultToolkit().
                        getImage("src/img/pic" + i + ".png").
                        getScaledInstance(
                                Constant.WIDTH_PER_GRID,
                                2 * Constant.HEIGHT_PER_GRID,
                                Image.SCALE_SMOOTH);
            }
            icons[i] = new ImageIcon(pics[i]);
        }
        repaint();
    }

    private Node[] map;

    /**
     * 对游戏区布局进行初始化。
     */
    private void initialize(){
        for(int i = 0; i < 10; i++){//每次出场的有10个人物。即1个曹操，4个小兵。横将或竖将（占两个格的将）有5个
            switch (map[i].getId()){
                case 0 :
                    persons[i] = new Person(i, "曹操");
                    persons[i].setIcon(icons[i]);
                    persons[i].setBounds(
                            Constant.LEFT_BLANK_X + map[i].getPixelX() * Constant.WIDTH_PER_GRID,
                            Constant.TOP_BLANK_Y + map[i].getPixelY() * Constant.HEIGHT_PER_GRID,
                            2 * Constant.WIDTH_PER_GRID,
                            2 * Constant.HEIGHT_PER_GRID);
                    break;
                case 1 :
                    persons[i] = new Person(i, "赵云");
                    doubleGridGeneral(i);
                    break;
                case 2 :
                    persons[i] = new Person(i, "张飞");
                    doubleGridGeneral(i);
                    break;
                case 3 :
                    persons[i] = new Person(i, "关羽");
                    doubleGridGeneral(i);
                    break;
                case 4 :
                    persons[i] = new Person(i, "马超");
                    doubleGridGeneral(i);
                    break;
                case 5 :
                    persons[i] = new Person(i, "黄忠");
                    doubleGridGeneral(i);
                    break;
                case 6 :
                case 7 :
                case 8 :
                case 9 :
                    persons[i] = new Person(i , "小兵");
                    persons[i].setIcon(icons[i]);
                    persons[i].setBounds(
                            Constant.LEFT_BLANK_X + map[i].getPixelX() * Constant.WIDTH_PER_GRID,
                            Constant.TOP_BLANK_Y + map[i].getPixelY() * Constant.HEIGHT_PER_GRID,
                            Constant.WIDTH_PER_GRID,
                            Constant.HEIGHT_PER_GRID);
                    break;
            }
            persons[i].addMouseListener(this);
            persons[i].addKeyListener(this);
            this.add(persons[i]);
        }
    }

    /**
     * 生成占两个格的将军
     */
    private void doubleGridGeneral(int i) {
        if(map[i].isHorizontal()){
            persons[i].setIcon(icons[i]);
            persons[i].setBounds(
                    Constant.LEFT_BLANK_X + map[i].getPixelX() * Constant.WIDTH_PER_GRID,
                    Constant.TOP_BLANK_Y + map[i].getPixelY() * Constant.HEIGHT_PER_GRID,
                    2 * Constant.WIDTH_PER_GRID,
                    Constant.HEIGHT_PER_GRID);
        }else {
            persons[i].setIcon(icons[i + 9]);
            persons[i].setBounds(
                    Constant.LEFT_BLANK_X + map[i].getPixelX() * Constant.WIDTH_PER_GRID,
                    Constant.TOP_BLANK_Y + map[i].getPixelY() * Constant.HEIGHT_PER_GRID,
                    Constant.WIDTH_PER_GRID,
                    2 * Constant.HEIGHT_PER_GRID);
        }
    }


    Point pointA = new Point(0,0);
    Point pointB = new Point(0,0);
    /**
     *  获取鼠标拖动方向
     */
    private Direction getDragDirection(){
        int distanceX = pointB.x - pointA.x;
        int distanceY = pointB.y - pointA.y;
        Direction dragDirection;
        if(distanceX == 0 && distanceY == 0){
            return Direction.NO_MOVE;
        }
        if(Math.abs(distanceX) > Math.abs(distanceY)){
            if(distanceX > 0){
                dragDirection = Direction.RIGHT;
            }else {
                dragDirection = Direction.LEFT;
            }
        }else {
            if(distanceY > 0){
                dragDirection = Direction.DOWN;
            }else {
                dragDirection = Direction.UP;
            }
        }
        return dragDirection;
    }

    /**
     * 用四个矩形隔离游戏区。
     */
    private Rectangle leftBoundary =
            new Rectangle(
                    Constant.LEFT_BLANK_X - Constant.ISOLATION_ZONE_WIDTH,
                    Constant.TOP_BLANK_Y,
                    Constant.ISOLATION_ZONE_WIDTH,
                    Constant.VERTICAL_NUMBER_OF_GAME_ZONE_GRID * Constant.HEIGHT_PER_GRID);
    private Rectangle rightBoundary =
            new Rectangle(
                    Constant.LEFT_BLANK_X + Constant.HORIZONTAL_NUMBER_OF_GAME_ZONE_GRID * Constant.WIDTH_PER_GRID,
                    Constant.TOP_BLANK_Y,
                    Constant.ISOLATION_ZONE_WIDTH,
                    Constant.VERTICAL_NUMBER_OF_GAME_ZONE_GRID * Constant.HEIGHT_PER_GRID);
    private Rectangle upBoundary =
            new Rectangle(Constant.LEFT_BLANK_X,
                    Constant.TOP_BLANK_Y - Constant.ISOLATION_ZONE_HEIGHT,
                    Constant.HORIZONTAL_NUMBER_OF_GAME_ZONE_GRID * Constant.WIDTH_PER_GRID,
                    Constant.ISOLATION_ZONE_HEIGHT);
    private Rectangle downBoundary =
            new Rectangle(Constant.LEFT_BLANK_X,
                    Constant.TOP_BLANK_Y + Constant.VERTICAL_NUMBER_OF_GAME_ZONE_GRID * Constant.HEIGHT_PER_GRID,
                    Constant.HORIZONTAL_NUMBER_OF_GAME_ZONE_GRID * Constant.WIDTH_PER_GRID,
                    Constant.ISOLATION_ZONE_HEIGHT);
    /**
     * 判断人物能不能移动
     */
    private boolean isMovable(Person person, Direction direction){

        boolean movable = true;

        Rectangle personRectangle = person.getBounds();//返回点击的人物按钮对应的矩形对象
        int x = personRectangle.x;
        int y = personRectangle.y;
        if(direction == Direction.UP){
            y -= Constant.HEIGHT_PER_GRID;
        }else if(direction == Direction.DOWN){
            y += Constant.HEIGHT_PER_GRID;
        }else if(direction == Direction.LEFT){
            x -= Constant.WIDTH_PER_GRID;
        }else if(direction == Direction.RIGHT){
            x += Constant.WIDTH_PER_GRID;
        }
        personRectangle.setLocation(x, y);
        for(int i = 0; i < 10; i++){//碰撞检测
            if(persons[i].getId() != person.getId()){
                Rectangle tmpPersonRect = persons[i].getBounds();
                if(tmpPersonRect.intersects(personRectangle)){
                    movable = false;
                }
            }
        }

        if(personRectangle.intersects(upBoundary) ||
                personRectangle.intersects(downBoundary) ||
                personRectangle.intersects(leftBoundary) ||
                personRectangle.intersects(rightBoundary)){
            movable = false;
        }
        return movable;
    }

    private int yourStep = 0;
    /**
     * 移动人物
     */
    private void move(Person person, Direction direction){
        switch (direction){
            case UP :
                person.setLocation(person.getX(),
                        person.getY() - Constant.HEIGHT_PER_GRID);
                break;
            case DOWN :
                person.setLocation(person.getX(),
                        person.getY() + Constant.HEIGHT_PER_GRID);
                break;
            case LEFT :
                person.setLocation(person.getX() - Constant.WIDTH_PER_GRID,
                        person.getY());
                break;
            case RIGHT :
                person.setLocation(person.getX() + Constant.WIDTH_PER_GRID,
                        person.getY());
                break;
        }
        yourStep++;
        HelpPanel.yourSteps.setText("" + yourStep);
        if(isWin(person)){
            if(level == 10){
                JOptionPane.showMessageDialog(
                        this,
                        "恭喜您通过最后一关");
            }else {
                String msg;
                if(yourStep < MapUtil.getRecord(level)){
                    msg = "恭喜您通过第" +
                            level +
                            "关！！！\n通关步数是" +
                            yourStep +
                            "\n刷新了历史记录" +
                            MapUtil.getRecord(level) +
                            "步\n是否要进入下一关？";
                    MapUtil.updateRecord(level, yourStep);
                }else {
                    msg = "恭喜你通过第" +
                            level +
                            "关！！！\n通关步数是" +
                            yourStep +
                            "\n"+"是否要进入下一关？";
                }
                int type = JOptionPane.YES_NO_OPTION;
                String title = "过关";
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        msg,
                        title,
                        type);
                if(choice == 1){
                    System.exit(0);
                }else if(choice == 0){
                    level++;
                    yourStep = 0;
                    for(int i = 0; i < 10; i++){
                        this.remove(persons[i]);
                    }
                    repaint();
                    map = MapUtil.getMap(level);
                    GameClient.panel.remove(GameClient.helpPanel);
                    GameClient.helpPanel = new HelpPanel(level);
                    GameClient.panel.add(GameClient.helpPanel,BorderLayout.EAST);
                    GameClient.helpPanel.validate();
                    GameClient.gamePanel.validate();
                    GameClient.panel.validate();
                    initialize();
                    setVisible(true);
                    this.requestFocus();
                }
            }
        }
    }

    /**
     * 胜利条件
     */
    private boolean isWin(Person person){
        if(person.getId() == 0 &&
                person.getX() == Constant.LEFT_BLANK_X + Constant.WIDTH_PER_GRID &&
                person.getY() == Constant.TOP_BLANK_Y + Constant.HEIGHT_PER_GRID * 3){
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource() == null){
            return;
        }
        pointA.x = e.getX();
        pointA.y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource() == null){
            return;
        }
        Person person = (Person) e.getSource();
        pointB.x = e.getX();
        pointB.y = e.getY();
        Direction dragDirection = getDragDirection();
        boolean movable;
        if(dragDirection.equals(Direction.UP)){
            movable = isMovable(person, Direction.UP);
            if(movable)
                move(person, Direction.UP);
        }else if(dragDirection.equals(Direction.DOWN)){
            movable = isMovable(person, Direction.DOWN);
            if(movable)
                move(person, Direction.DOWN);
        }else if(dragDirection.equals(Direction.LEFT)){
            movable = isMovable(person, Direction.LEFT);
            if(movable)
                move(person, Direction.LEFT);
        }else if(dragDirection.equals(Direction.RIGHT)){
            movable = isMovable(person,Direction.RIGHT);
            if(movable)
                move(person, Direction.RIGHT);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
