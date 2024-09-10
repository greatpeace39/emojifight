package emoji_fight;
import javax.swing.*;
import java.awt.*;

public  class LayeredImageExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Layered Image Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // 创建一个 JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        // 第一层图片
        JLabel label1 = new JLabel(new ImageIcon("src/image_game/1.png"));
        label1.setBounds(50, 50, 200, 200);  // 设置图片的位置和大小
        layeredPane.add(label1, JLayeredPane.DEFAULT_LAYER);  // 加入默认层

        // 第二层图片
        JLabel label2 = new JLabel(new ImageIcon("src/image_game/2.png"));
        label2.setBounds(100, 100, 200, 200);  // 设置图片的位置和大小
        layeredPane.add(label2, JLayeredPane.PALETTE_LAYER);  // 加入调色板层

        // 第三层图片
        JLabel label3 = new JLabel(new ImageIcon("src/image_game/3.png"));
        label3.setBounds(150, 150, 200, 200);  // 设置图片的位置和大小
        layeredPane.add(label3, JLayeredPane.MODAL_LAYER);  // 加入模态层

        // 将 JLayeredPane 添加到 JFrame 中
        frame.add(layeredPane);
        frame.pack();
        frame.setVisible(true);
    }
}

