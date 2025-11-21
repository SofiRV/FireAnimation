package fire.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Viewer extends Canvas implements Runnable{
    private final View view;
    private final FireView fireView;
    private BufferedImage fireplace;
    private Thread viewerThread;
    private final int fireX= 300;
    private final int fireY=245;
    private final int fireWidth=200;
    private final int fireHeight=200;
    private int[][] latestTemps;

    public Viewer(View view){
        this.view=view;
        this.fireView=new FireView(fireWidth,fireHeight);
        try {
            fireplace=ImageIO.read(new File("src/images/fireplace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(fireplace,0,0,getWidth(),getHeight(),this);
    }

    @Override
    public void run() {
        createBufferStrategy(2);
        BufferStrategy bs=getBufferStrategy();

        while (true) {
            latestTemps=view.updateFire();
            Graphics g=bs.getDrawGraphics();
            g.drawImage(fireplace, 0, 0, getWidth(), getHeight(), null);
            if (latestTemps!=null) {
                fireView.setTemperatures(latestTemps);
                fireView.draw(g, fireX, fireY);
            }
            g.dispose();
            bs.show();
            try {
                Thread.sleep(16);
            } catch (InterruptedException ignored) {}
        }
    }

    public void updateFire(int[][] temperatures){
        this.latestTemps=temperatures;
    }
    public void startViewer(){
        viewerThread=new Thread(this);
        viewerThread.start();
    }
}
