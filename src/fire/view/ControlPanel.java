package fire.view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final View view;

    public ControlPanel(View view){
        this.view=view;
        setBackground(Color.LIGHT_GRAY);
    }
}
