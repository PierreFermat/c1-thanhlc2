package touhou;

import tklibs.SpriteUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;
    private Graphics2D backBufferGraphics;
    private BufferedImage backBufferImage;
    private BufferedImage background;
    private BufferedImage player_image;
    private int background_x = 0;
    private int background_y = 0;
    private int player_x = 178;
    private int player_y = 600;

    private void movebackground (){
        background_y += 10;
    }

    public GameWindow() {
        background = SpriteUtils.loadImage("assets/images/background/0.png");
        player_image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);
        this.setTitle("TOUHOU - REMADE BY THANH LE CONG");
        this.backBufferImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.backBufferGraphics = (Graphics2D) this.backBufferImage.getGraphics();
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ( e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player_x += 10;
                }
                if ( e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player_x -= 10;
                }
                if ( e.getKeyCode() == KeyEvent.VK_UP) {
                    player_y -= 10;
                }
                if ( e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player_y += 10;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void loop() {
        while (true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {


    }
    @Override
    public void paint ( Graphics g) {
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.fillRect(0,0,1240,763);
        backBufferGraphics.drawImage(background,background_x, background_y,null );
        backBufferGraphics.drawImage(player_image, player_x,player_y,null);
        g.drawImage(backBufferImage,0,0,null);

    }

    private void render() {
        repaint();

    }
}
