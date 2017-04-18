package tancky;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class TankClient extends JFrame {
    NetClient netClient = new NetClient(this);

    private ConnectDialog connectDialog = new ConnectDialog();

    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    Tank tank = new Tank(0, 570, true, Direction.STOP, this);

    List<Missile> missiles = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();
    List<Tank> enemyTanks = new ArrayList<>();

    private Image offScreenImage = null;

    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.white);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        super.paint(gOffScreen);

        tank.draw(gOffScreen);

        gOffScreen.setColor(Color.black);
        gOffScreen.drawString("Kill: " + explodes.size(), 10, 50);
        gOffScreen.drawString("Enemies: " + enemyTanks.size(), 10, 70);
        gOffScreen.drawString("Missiles: " + missiles.size(), 10, 90);

        for (Tank tank : enemyTanks) {
            tank.draw(gOffScreen);
        }

        for (Missile missile : missiles) {
            if (missile.hitTank(tank)) {
                TankDeadMsg tdm = new TankDeadMsg(tank.id);
                netClient.send(tdm);
                MissileDeadMsg mdm = new MissileDeadMsg(missile.tankID, missile.id);
                netClient.send(mdm);
            }
            missile.draw(gOffScreen);
        }

        for (Explode e : explodes) {
            e.draw(gOffScreen);
        }

        gOffScreen.setColor(color);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    private void launchFrame() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((screenWidth - GAME_WIDTH) / 2, (screenHeight - GAME_HEIGHT) / 2);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("TankWar");
        this.setVisible(true);
        this.addKeyListener(new KeyMonitor());
        this.connectDialog.setVisible(true);
        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.launchFrame();
    }

    private class PaintThread implements Runnable {
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void enterIP(String IP) {
        int TCPPort = 46464;
        int UDPPort = (int) (Math.random() * 10000);
        netClient.setUdpPort(UDPPort);
        connectDialog.setVisible(false);
        connectDialog.setVisible(!netClient.connect(IP, TCPPort));
    }

    private class KeyMonitor extends KeyAdapter {
        public void keyReleased(KeyEvent keyEvent) {
            tank.keyReleased(keyEvent);
        }

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_C:
                    connectDialog.setVisible(true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    connectDialog.setVisible(false);
                case KeyEvent.VK_ENTER:
                    if (connectDialog.isVisible()) {
                        enterIP(connectDialog.textFieldIP.getText().trim());
                    }
                    break;
                default:
                    if (!connectDialog.isVisible()) {
                        tank.keyPressed(e);
                    }
                    break;
            }
        }
    }

    class ConnectDialog extends Dialog {
        Button button = new Button("Link");
        TextField textFieldIP = new TextField("127.0.0.1", 16);

        ConnectDialog() {
            super(TankClient.this, true);
            textFieldIP.addKeyListener(new KeyMonitor());
            this.setLayout(new FlowLayout());
            this.add(new Label("Host IP :"));
            this.add(textFieldIP);
            this.add(button);
            button.addActionListener(e -> enterIP(textFieldIP.getText().trim()));
            this.pack();
            this.setLocationRelativeTo(null);
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                }
            });
        }
    }
}
