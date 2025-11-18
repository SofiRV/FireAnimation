package fire.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Viewer extends Canvas implements Runnable{
    private final View view;
    private final FireView fireView;
    private BufferedImage fireplace;
    private BufferedImage buffer;
    private Thread viewerThread;
    private final int fireX=100;
    private final int fireY=300;
    private final int fireWidth=512;
    private final int fireHeight=512;

    public Viewer(View view){
        this.view=view;
        this.fireView=new FireView(fireWidth,fireHeight);
        viewerThread=new Thread(this);
        viewerThread.start();
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(fireplace,0,0,getWidth(),getHeight(),this);
    }

    @Override
    public void run(){
        while (getWidth() == 0 || getHeight() == 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
        buffer=new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);

        while (true){
            Graphics2D g2d=buffer.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,getWidth(),getHeight());

            if(fireplace!=null){
                g2d.drawImage(fireplace,0,0,getWidth(),getHeight(),null);
            }
            Graphics gFire = g2d.create(fireX, fireY, fireWidth, fireHeight);
            fireView.draw(gFire);
            gFire.dispose();

            g2d.dispose();

            Graphics g=getGraphics();
            if(g!=null){
                g.drawImage(buffer,0,0,null);
                g.dispose();
            }

            try {
                Thread.sleep(30); // aprox 33 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
