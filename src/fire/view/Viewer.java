package fire.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Viewer extends Canvas {
    private final View view;
    private Image fireplace;

    public Viewer(View view){
        this.view=view;
        //todo esto habria que moverlo al run y hacer el thread de viewer
        try {
            fireplace= ImageIO.read(new File("src/images/fireplace.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    //todo creo que esto tambien va en el run
    @Override
    public void paint(Graphics g){
        g.drawImage(fireplace,0,0,getWidth(),getHeight(),this);
    }
}
