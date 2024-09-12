package emoji_fight;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main_fram extends JFrame {
    private static final String path = "image_game/";// 图片路径
    private static int counttime;//游戏时长
    private static int TIME = 0;//游戏时间
    private static int FLAG = 0;//胜利判断标准
    private Timer timer;
    private static  int VIC;
    private records R;// 倒计时
    private static String[]  images = new String[] {"1","2","3","4","5","6"};//种类
    private static List<String> temp_image = new ArrayList<>();//状态栏图片
    Rectangle removearea = new Rectangle(0, 460, 800, 80);//状态栏区域
    private static List<String> layer1 = new ArrayList<>();  // 图层1（最高层）有8张图片
    private static List<String> layer2 = new ArrayList<>();  // 图层2（中间层）有20张图片
    private static List<String> layer3 = new ArrayList<>();  // 图层3（最低层）有26张图片
    //Layers layers = new Layers();
    public void game_start() {
        counttime = 60;
        TIME = counttime;//游戏时长
        FLAG = 0;
        temp_image.clear();
        VIC = images.length*9;
        layer1.clear();
        layer2.clear();
        layer3.clear();
        initfram();
        initimagelaters();
        initimage();
        this.setVisible(true);
    }

    private void initimagelaters() {
        Map<String,Integer> imagecounts = new HashMap<>();//记录图片数量
        //初始化图片数量
        for(String image:images){
            imagecounts.put(image,9);
        }
        //随机选择3种图片放置于图层一中
        List<String> l1_images =  new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));
        Collections.shuffle(l1_images);
        String image1 = l1_images.get(0);
        String image2 = l1_images.get(1);
        String image3 = l1_images.get(2);
        for(int i = 0; i < 3;i++){
            layer1.add(image1);
            layer1.add(image2);
        }
        for(int i = 0; i < 2;i++){
            layer1.add(image3);
        }

        //更新剩余图片数量
        imagecounts.put(image1,imagecounts.get(image1)-3);
        imagecounts.put(image2,imagecounts.get(image2)-3);
        imagecounts.put(image3,imagecounts.get(image3)-2);

        //随机选择4种图片放置于图层二中
        List<String> l2_images =  new ArrayList<>(List.of("1", "2", "3", "4", "5", "6"));
        Collections.shuffle(l2_images);
        for(int i = 0; i < 4;i++){
            for(int j = 0; j < 5;j++){
                layer2.add(l2_images.get(i));
            }
            imagecounts.put(l2_images.get(i),imagecounts.get(l2_images.get(i))-5); //更新剩余图片数
        }


        //将剩下图片安放在最低层
        for(String image:images){
            for(int i = 0; i < imagecounts.get(image);i++){
                layer3.add(image);
            }
        }
        Collections.shuffle(layer1);
        Collections.shuffle(layer2);
        Collections.shuffle(layer3);

    }

    private void initimage() {
        this.getContentPane().removeAll();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));//
        //背景及状态栏设置
        JLabel label1 = new JLabel(new ImageIcon(path + "background.png"));
        JLabel label2 = new JLabel(new ImageIcon(path + "background2.png"));
        label1.setBounds(0, 0, 800, 600);  // 设置图片的位置和大小
        label2.setBounds(0, 460, 800, 80);
        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);// 加入默认层
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);

//
        //初始化最底层图层
        for(int i = 0; i < layer3.size();i++){
            JLabel label = new JLabel(new ImageIcon(path + layer3.get(i) + ".png"));
                label.setBounds(i % 7 * 100+60, i/7 * 100, 80, 80);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        {
                            FLAG++;
                            String str = label.getIcon().toString();
                            temp_image.add(str);
                            layeredPane.remove(label);
                            is_clear(Collections.frequency(temp_image,str));
                            inittemp_fram(layeredPane);
                            revalidate();
                            repaint();
                        }
                    }
                });
                layeredPane.add(label, JLayeredPane.MODAL_LAYER);
        }
        //初始化第二层
        for(int i = 0; i < layer2.size();i++){
            JLabel label = new JLabel(new ImageIcon(path + layer2.get(i) + ".png"));
                label.setBounds(i % 5 * 100+60, i/5 * 100, 80, 80);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        {
                            FLAG++;
                            String str = label.getIcon().toString();
                            temp_image.add(str);
                            layeredPane.remove(label);
                            is_clear(Collections.frequency(temp_image,str));
                            inittemp_fram(layeredPane);
                            revalidate();
                            repaint();
                        }
                    }});
                layeredPane.add(label, JLayeredPane.POPUP_LAYER);
        }
        //初始化第一层
        for(int i = 0; i < layer1.size();i++){
            JLabel label = new JLabel(new ImageIcon(path + layer1.get(i) + ".png"));
                MouseListener mouseListener = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        {
                            FLAG++;
                            String str = label.getIcon().toString();
                            temp_image.add(str);
                            layeredPane.remove(label);
                            is_clear(Collections.frequency(temp_image,str));
                            inittemp_fram(layeredPane);
                            revalidate();
                            repaint();
                        }

                    }
                };
                label.addMouseListener(mouseListener);
                label.setBounds(i % 2 * 240 +220, i/2 * 100, 80, 80);
                layeredPane.add(label, JLayeredPane.DRAG_LAYER);
        }
        //初始化一个60秒的倒计时
        JLabel Time_t = new JLabel("时间还剩下"+counttime + "s");
        Time_t.setBounds(300,400, 300, 50);
        Time_t.setFont(new Font("微软雅黑", Font.BOLD, 30));
        Time_t.setForeground(Color.RED);
        //final int[] temptime = {counttime};
        layeredPane.add(Time_t, JLayeredPane.MODAL_LAYER);
        if(timer != null){
            timer.stop();
        }

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counttime--;
                Time_t.setText("时间还剩下"+ counttime + "s");
                if( counttime < 0){
                    timer.stop();
                    initendpage();
                }
            }
        });
        timer.start();
        getContentPane().add(layeredPane);
    }

    private void initendpage() {
        //System.out.println(counttime);
        this.getContentPane().removeAll();
        JLabel label1 = new JLabel(new ImageIcon(path + "failpage.png"));
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(300, 500, 200, 50);
        menuBar.setBackground(new Color(255, 192, 203));
        JMenu menu1 = new JMenu("退出游戏");
        menu1.setFont(new Font("微软雅黑", Font.BOLD, 20));
        //给menu1添加鼠标监听事件，当监听到事件时关闭窗口退出游戏
        menu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        //给menu1添加鼠标监听事件，当监听到事件时创建一个新游戏
        JMenu menu2 = new JMenu("重新开始");
        menu2.setFont(new Font("微软雅黑", Font.BOLD, 20));
        menu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    game_start();
            }
        });
        menuBar.add(menu1);
        menuBar.add(menu2);
        label1.add(menuBar);
        label1.setBounds(0, 0, 800,600 );
        this.add(label1);
        this.repaint();
    }

    private boolean checkliveisfull() {
        if(temp_image.size() == 8){
            return true;
        }else{
            return false;
        }
    }

    private void is_clear(int frequency) {
        if(frequency == 3){
            List<String> toremove = Arrays.asList(temp_image.get(temp_image.size() - 1));
            temp_image.removeAll(toremove);
        }
    }

    private void inittemp_fram(JLayeredPane layeredPane) {
            if(temp_image.isEmpty()){
                checkisvictory();
            }
                flash_live(layeredPane);
            for (int i = 0; i < temp_image.size(); i++) {
                JLabel label = new JLabel(new ImageIcon(temp_image.get(i).toString()));
                label.setBounds(i * 100, 460, 80, 80);
                layeredPane.add(label, JLayeredPane.DRAG_LAYER);
                if(checkliveisfull()) initendpage();
        }
    }

    private void checkisvictory() {
                    if(FLAG == VIC){
                        this.getContentPane().removeAll();
                        if(timer != null){
                            timer.stop();
                        }
                        TIME = TIME - counttime;
                        R = new records(TIME,"game1_record");
                        System.out.println(TIME);
                        JLabel label1 = new JLabel(new ImageIcon(path + "victory.png"));
                        JMenuBar menuBar = new JMenuBar();
                        menuBar.setBounds(300, 500, 200, 50);
                        menuBar.setBackground(new Color(255, 192, 203));
                        JMenu menu1 = new JMenu("退出游戏");
                        menu1.setFont(new Font("微软雅黑", Font.BOLD, 20));
                        //给menu1添加鼠标监听事件，当监听到事件时关闭窗口退出游戏
                        menu1.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                System.exit(0);
                            }
                        });
                        //添加menu2表示进行下一关
                        JMenu menu2 = new JMenu("下一关");
                        menu2.setFont(new Font("微软雅黑", Font.BOLD, 20));
                        menu2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                Final_Stage fs = new Final_Stage();
                                fs.game_start();
                            }
                        });
                        menuBar.add(menu2);
                        menuBar.add(menu1);
                        label1.add(menuBar);
                        label1.setBounds(0, 0, 800,600 );
                        this.add(label1);
                        this.repaint();
                    }
    }

    private void flash_live(JLayeredPane layeredPane) {
            for(Component comp:layeredPane.getComponentsInLayer(layeredPane.DRAG_LAYER)){
                if(removearea.intersects(comp.getBounds())){
                    layeredPane.remove(comp);
                }
            }
            layeredPane.repaint();
    }

    private void initfram() {
        this.setTitle("emoji_fight");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
