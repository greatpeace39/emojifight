package emoji_fight;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class top extends JFrame {
    private String filename;
// 构造函数，传入文件名，读取文件，将数据转化为排行榜
    top(String filename) {
        this.filename = filename;
        List<String> ls = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                ls.add(line);
            }
            datamake(ls);
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(ls);
    }
// 将数据转化为排行榜
    private void datamake(List<String> ls) {
        ArrayList<Integer> toprecord = new ArrayList<>();
        for(String line : ls){
            toprecord.add(Integer.parseInt(line));
        }
        Collections.sort(toprecord);
        initfram(toprecord);
    }
// 初始化窗口，显示排行榜
    private void initfram(ArrayList<Integer> toprecord) {
        this.setTitle("emoji_fight_top");
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GREEN);

        // 添加排行榜标题
        JLabel titleLabel = new JLabel("排行榜");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        int rank = 1;
        for (int score : toprecord) {
            JLabel scoreLabel = new JLabel(rank + ". 分数: " + score);
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(scoreLabel);
            if(rank == 10) break;
            rank++;
        }
        this.add(panel);
        this.setVisible(true);
    }
}
