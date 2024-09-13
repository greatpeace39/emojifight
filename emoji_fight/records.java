package emoji_fight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//包装类用于读取游戏记录
public class records{
    int record;
    records(int record,String txt)  {
        try {
            //System.out.println(record + " " + txt);
            FileWriter fw = new FileWriter(txt,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Integer.toString(record));
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
