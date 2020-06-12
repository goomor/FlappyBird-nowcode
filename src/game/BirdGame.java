package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * 游戏界面
 */
public class BirdGame extends JPanel{

        //背景图片
        BufferedImage background;

        //开始图片
        BufferedImage startImage;
        //结束图片
        BufferedImage gameOverImage;
        //地面
        Ground ground;
        //柱子
        Column column1, column2;
        //小鸟
        Bird bird;

        //游戏分数
        int score;
        //游戏状态
        int state;



    //状态常量
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int GAME_OVER = 2;


    /**
     * 初始化游戏
     */
    public BirdGame() throws Exception {
        // 初始化背景图片
        background = ImageIO.read(getClass().getResource("/resources/bg.png"));

        // 初始化开始、结束图片
        startImage = ImageIO.read(getClass().getResource("/resources/start.png"));
        gameOverImage = ImageIO.read(getClass().getResource("/resources/gameover.png"));

        // 初始化地面、柱子、小鸟
        ground = new Ground();
        column1 = new Column(1);
        column2 = new Column(2);
        bird = new Bird();

        // 初始化分数
        score = 0;

        // 初始化状态
        state = START;
    }


    /**
     * 绘制图像
     */
    public void paint(Graphics g){
        //绘制背景
        g.drawImage(background, 0, 0, null);

        //绘制地面
        g.drawImage(ground.image, ground.x, ground.y, null);

        //绘制柱子
        g.drawImage(column1.image, column1.x - column1.width / 2, column1.y -
                column1.height / 2, null);
        g.drawImage(column2.image, column2.x - column2.width / 2, column2.y -
                column2.height / 2,null);

        //绘制小鸟（旋转坐标系）
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(-bird.alpha, bird.x, bird.y);
        g.drawImage(bird.image, bird.x - bird.width/2, bird.y -
                bird.height / 2, null);
        g2.rotate(bird.alpha, bird.x, bird.y);

        //绘制分数
        Font f = new Font(Font.SANS_SERIF, Font.BOLD, 40);
        g.setFont(f);
        g.drawString(""+score, 40, 60);
        g.setColor(Color.WHITE);
        g.drawString(""+score, 40-3, 60-3);

        //绘制开始与结束界面
        switch(state){
            case START:
                g.drawImage(startImage,0,0,null);
                break;
            case GAME_OVER:
                g.drawImage(gameOverImage,0,0,null);
                break;
        }
    }




    /**
         * 启动方法
         */


    public static void main(String[] args) throws Exception{
        JFrame frame = new JFrame();
        BirdGame game= new BirdGame();
        frame.add(game);
        frame.setSize(440,670);
        frame.setLocationRelativeTo(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }




}