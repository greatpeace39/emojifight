package emoji_fight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.awt.Color.GREEN;
import static java.awt.Color.green;
//启动界面，显示游戏介绍，开始游戏，查看排行榜
public class Start_Fram extends Main_fram {
    public Start_Fram() {
        initbasic();
        initstartpage();
        this.setVisible(true);
    }

    private void initstartpage() {
        this.getContentPane().removeAll();
        JLabel label1 = new JLabel(new ImageIcon("image_game/startpage.png"));
        //label1.add
        JMenuBar menuBar = new JMenuBar();//开始游戏
        JMenuBar menuBar1 = new JMenuBar();//查看排行榜
        menuBar.setOpaque(false);  // 设置透明
        menuBar.setBackground(new Color(0, 0, 0, 100));  // 半透明背景 (黑色, 100 透明度)
        menuBar1.setOpaque(false);  // 设置透明
        menuBar1.setBackground(new Color(0, 0, 0, 100));  // 半透明背景 (黑色, 100 透明度)
        menuBar.setBounds(280, 500, 250, 50);
        menuBar1.setBounds(280, 400, 250, 50);
        //menuBar1.setBackground(new Color(0,255,0));
        JMenu menu1 = new JMenu("查看排行榜");
        JMenuItem jm1 = new JMenuItem("GAME1");
        JMenuItem jm2 = new JMenuItem("GAME2");
        menu1.add(jm1);
        menu1.add(jm2);
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"GAME1");
                top t = new top("game1_record");
            }
        });
        jm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null,"GAME2");
                top t = new top("game2_record");
            }
        });
        menu1.setOpaque(true);
        menu1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        //添加menu2表示进行下一关
        JMenu menu2 = new JMenu("开始游戏");
        menu2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Main_fram fram = new Main_fram();
                fram.game_start();
            }
        });
        menuBar1.add(menu2);
        menuBar.add(menu1);
        label1.add(menuBar);
        label1.add(menuBar1);;
        label1.setBounds(0, 0, 800,600 );
        this.add(label1);
        this.repaint();
    }

    private void initbasic() {
        this.setTitle("emoji_fight");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
