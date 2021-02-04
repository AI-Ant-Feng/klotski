package setsail.klotski.entites;

import java.io.*;

public class MapFactory {

    static String[] array;
    private static Node[][] map = new Node[][]{
            {//七步成诗布局
                    new Node(0,2,2,true),new Node(1,2,0,false),new Node(2,0,1,false),
                    new Node(3,0,3,false),new Node(4,0,0,false),new Node(5,2,1,false),
                    new Node(6,4,0,true),new Node(7,4,1,true),new Node(8,4,2,true),new Node(9,4,3,true)
            },

            {//横刀立马布局
                    new Node(0,0,1,true),new Node(1,0,3,false),new Node(2,2,3,false),
                    new Node(3,2,1,true),new Node(4,0,0,false),new Node(5,2,0,false),
                    new Node(6,4,0,true),new Node(7,3,1,true),new Node(8,3,2,true),new Node(9,4,3,true)
            },

            {//屯兵东路布局
                    new Node(0,0,0,true),new Node(1,0,3,false),new Node(2,0,2,false),
                    new Node(3,2,0,true),new Node(4,3,0,false),new Node(5,3,1,false),
                    new Node(6,2,2,true),new Node(7,2,3,true),new Node(8,3,2,true),new Node(9,3,3,true)
            },
            {//插翅难飞布局
                    new Node(0,0,1,true),new Node(1,0,0,false),new Node(2,4,2,true),
                    new Node(3,4,0,true),new Node(4,2,1,false),new Node(5,0,3,false),
                    new Node(6,2,0,true),new Node(7,3,0,true),new Node(8,2,3,true),new Node(9,3,3,true)
            },
            {//巧过五关布局
                    new Node(0,0,1,true),new Node(1,2,2,true),new Node(2,3,2,true),
                    new Node(3,4,1,true),new Node(4,3,0,true),new Node(5,2,0,true),
                    new Node(6,0,0,true),new Node(7,1,0,true),new Node(8,0,3,true),new Node(9,1,3,true)
            },
            {//层层设防布局
                    new Node(0,0,1,true),new Node(1,1,3,false),new Node(2,1,0,false),
                    new Node(3,4,1,true),new Node(4,2,1,true),new Node(5,3,1,true),
                    new Node(6,0,0,true),new Node(7,3,0,true),new Node(8,0,3,true),new Node(9,3,3,true)
            },
            {//近在咫尺布局
                    new Node(0,3,2,true),new Node(1,0,1,false),new Node(2,3,0,true),
                    new Node(3,2,0,true),new Node(4,0,3,false),new Node(5,0,2,false),
                    new Node(6,0,0,true),new Node(7,1,0,true),new Node(8,2,2,true),new Node(9,2,3,true)
            },
            {//兵临曹营布局
                    new Node(0,0,1,true),new Node(1,3,1,false),new Node(2,3,2,false),
                    new Node(3,2,1,true),new Node(4,2,0,false),new Node(5,2,3,false),
                    new Node(6,0,0,true),new Node(7,1,0,true),new Node(8,0,3,true),new Node(9,1,3,true)
            },
            {//众志成城布局
                    new Node(0,1,1,true),new Node(1,3,1,false),new Node(2,3,3,false),
                    new Node(3,0,2,true),new Node(4,0,0,false),new Node(5,2,0,false),
                    new Node(6,0,1,true),new Node(7,1,3,true),new Node(8,2,3,true),new Node(9,4,2,true)
            },
            {//佳人梳妆布局
                    new Node(0,1,0,true),new Node(1,3,1,false),new Node(2,3,2,false),
                    new Node(3,0,2,true),new Node(4,1,2,false),new Node(5,2,3,false),
                    new Node(6,3,0,true),new Node(7,4,0,true),new Node(8,1,3,true),new Node(9,4,3,true)
            }
    };

    public static Node[] getMap(int level) {
        Node[] temp = new Node[10];
        for(int i = 0; i < 10; i++){
            temp[i] = map[level - 1][i];
        }
        return temp;
    }

    public static int getRecord(int level) {
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        FileInputStream fileInputStream = null;
        DataInputStream dataInputStream = null;
        File file = new File("game_record");
        if(!file.exists()){
            file.mkdirs();
        }
        File record = new File("game_record/record.txt");
        try{
            if(!record.exists()){
                record.createNewFile();
                fileOutputStream = new FileOutputStream(record);
                dataOutputStream = new DataOutputStream(fileOutputStream);
                String string = "9999,9999,9999,9999,9999,9999,9999,9999,9999,9999";
                dataOutputStream.writeBytes(string);
                System.out.println(record.isFile());
            }
            fileInputStream = new FileInputStream(record);
            dataInputStream = new DataInputStream(fileInputStream);
            String string = dataInputStream.readLine();
            array = string.split(",");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(dataInputStream != null){
                    dataInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
                if(dataOutputStream != null){
                    dataOutputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return Integer.parseInt(array[level - 1]);
    }

    public static void writeRecord(int level, int step) {
        FileOutputStream fileOutputStream = null;
        DataOutputStream dataOutputStream = null;
        File record = new File("game_record/record.txt");
        try{
            FileWriter fileWriter = new FileWriter(record);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
            fileOutputStream = new FileOutputStream(record);
            dataOutputStream = new DataOutputStream(fileOutputStream);
            array[level -1] = step + "";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(array[0]);
            for(int i = 1; i < array.length; i++){
                stringBuilder.append("," + array[i]);
            }
            System.out.println(stringBuilder.toString());
            dataOutputStream.writeBytes(stringBuilder.toString());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
                if(dataOutputStream != null){
                    dataOutputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
