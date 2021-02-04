package setsail.klotski.entites;

import javax.swing.*;

public class Person extends JButton {

    int id;//编号
    String name;
    public Person(int id,String name){
        super("");
        this.name = name;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}