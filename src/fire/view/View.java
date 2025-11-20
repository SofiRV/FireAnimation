package fire.view;

import fire.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private final Controller controller;
    private final ControlPanel controlPanel;
    private final Viewer viewer;

    public View(Controller controller){
        this.controller=controller;
        this.controlPanel=new ControlPanel(this);
        this.viewer=new Viewer(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);

        GridBagConstraints gbc=new GridBagConstraints();
        JPanel content=new JPanel(new GridBagLayout());

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0;
        gbc.weighty=1;
        content.add(controlPanel,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.weightx=3.0;
        content.add(viewer,gbc);

        add(content);
        setLocationRelativeTo(null);
        setVisible(true);
        viewer.startViewer();
    }

    public int[][] updateFire(){
        return controller.updateFire();
    }

    public Controller getController() {
        return controller;
    }
}
