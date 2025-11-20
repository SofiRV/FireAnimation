package fire.view;

import java.awt.*;

public class FireView {
    private final FireColorPalette colors;
    private final int width;
    private final int height;
    private int[][] temperatures;

    public FireView(int width, int height){
        this.width=width;
        this.height=height;
        this.colors=new FireColorPalette();
        this.temperatures=new int[height][width];
    }

    public void setTemperatures(int[][] temperatures){
        this.temperatures=temperatures;
    }

    public void draw(Graphics g, int offsetX, int offsetY){
        Color[] colorsList=colors.getColors();
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                int temp=temperatures[y][x];
                if (temp<0) {
                    temp=0;
                }
                if (temp>1023){
                    temp=1023;
                }
                g.setColor(colorsList[temp]);
                g.fillRect(offsetX + x, offsetY + y, 1, 1);
            }
        }
    }
}

