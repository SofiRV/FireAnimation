package fire.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class FireView {
    private final FireColorPalette colors;
    private final BufferedImage fireBuffer;
    private final int width;
    private final int height;

    public FireView(int width, int height){
        this.width=width;
        this.height=height;
        this.colors=new FireColorPalette();
        this.fireBuffer=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
    }

    public void createFire(int[][] temperatures){
        ArrayList<Color> colorsList = colors.getColors();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int temp=temperatures[i][j];
                if(temp<0){
                    temp=0;
                }
                if(temp>1023){
                    temp=1023;
                }
                fireBuffer.setRGB(i,j,colorsList.get(temp).getRGB());
            }
        }
    }

    public void draw(Graphics g){
        g.drawImage(fireBuffer,0,0,width,height,null);
    }
}
