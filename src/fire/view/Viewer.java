package fire.view;

import fire.dto.DtoColorTarget;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Viewer extends Canvas implements Runnable{
    private final View view;
    private FireView fireView;
    private BufferedImage fireplace;
    private Thread viewerThread;
    private int fireX;
    private int fireY;
    private int fireWidth;
    private int fireHeight;
    private int[][] latestTemps;
    private FireColorPalette colorPalette;

    public Viewer(View view){
        this.view=view;
        this.colorPalette=new FireColorPalette(view.targetsBG1());;
        this.fireView=new FireView(fireWidth,fireHeight,colorPalette);
        try {
            fireplace=ImageIO.read(new File("src/images/fireplace.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBackground(String path){
        try {
            fireplace=ImageIO.read(new File(path));
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

    public void updateFire(int fireX, int fireY, int fireWidth, int fireHeight, FireColorPalette colorPalette){
        this.fireX=fireX;
        this.fireY=fireY;
        this.fireWidth=fireWidth;
        this.fireHeight=fireHeight;
        this.fireView=new FireView(fireWidth,fireHeight, colorPalette);
    }

    public void startViewer(){
        viewerThread=new Thread(this);
        viewerThread.start();
    }


}
