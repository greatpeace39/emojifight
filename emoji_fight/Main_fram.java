package emoji_fight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main_fram extends JFrame {
    private static final String path = "src/image_game/";
    private static String[]  images = new String[] {"1","2","3","4","5","6"};
    //private ArrayList<MouseListener> mouseListeners = new ArrayList<>(); // 存储监听器
    private static List<String> layer1 = new ArrayList<>();  // 图层1（最高层）有8张图片
    private static List<String> layer2 = new ArrayList<>();  // 图层2（中间层）有16张图片
    private static List<String> layer3 = new ArrayList<>();  // 图层3（最低层）有24张图片
    //Layers layers = new Layers();
    public void game_start() {
        initfram();
        initimagelaters();
        initimage();
        this.setVisible(true);
    }

    private void initimagelaters() {
        Map<String,Integer> imagecounts = new HashMap<>();//记录图片数量
        //初始化图片数量
        for(String image:images){
            imagecounts.put(image,8);
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
            for(int j = 0; j < 4;j++){
                layer2.add(l2_images.get(i));
            }
            imagecounts.put(l2_images.get(i),imagecounts.get(l2_images.get(i))-4); //更新剩余图片数
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
        //int [][] map3 = getmap();
        for(int i = 0; i < layer3.size();i++){
            JLabel label = new JLabel(new ImageIcon(path + layer3.get(i) + ".png"));
                label.setBounds(i % 6 * 120, i/6 * 100, 80, 80);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        layeredPane.remove(label);
                        repaint();
                    }
                });
                layeredPane.add(label, JLayeredPane.MODAL_LAYER);
        }
        //初始化第二层
        //int [][] map2 = getmap();
        for(int i = 0; i < layer2.size();i++){
            JLabel label = new JLabel(new ImageIcon(path + layer2.get(i) + ".png"));
                label.setBounds(i % 4 * 120, i/4 * 100, 80, 80);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        layeredPane.remove(label);
                        repaint();
                    }});
                layeredPane.add(label, JLayeredPane.MODAL_LAYER);
        }
        //初始化第一层
        for(int i = 0; i < layer1.size();i++){
            JLabel label = new JLabel(new ImageIcon(path + layer1.get(i) + ".png"));
                MouseListener mouseListener = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        layeredPane.remove(label);
                        repaint();
                    }
                };
                label.addMouseListener(mouseListener);
                label.setBounds(i % 4 * 120, i/4 * 100, 80, 80);
                layeredPane.add(label, JLayeredPane.MODAL_LAYER);
        }

        getContentPane().add(layeredPane);
    }

    private void initfram() {
        this.setTitle("emoji_fight");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
