package fire.view;

import fire.controller.Controller;
import fire.dto.BackgroundOptionsDto;
import fire.dto.DtoColorTarget;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JFrame {
    private final Controller controller;
    private final ControlPanel controlPanel;
    private final Viewer viewer;

    public View(Controller controller){
        this.controller=controller;
        this.controlPanel=new ControlPanel(this);
        this.viewer=new Viewer(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,600);

        GridBagConstraints gbc=new GridBagConstraints();
        JPanel content=new JPanel(new GridBagLayout());

        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.weightx=0;
        gbc.weighty=1;
        content.add(controlPanel,gbc);

        addConfirmSelectionButtonListener();

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

    public void addConfirmSelectionButtonListener(){
        controlPanel.getCONFIRM_SELECTION().addActionListener(e->{
            handleSelection();
        });
    }

    public void handleSelection(){
        BackgroundOptionsDto option=(BackgroundOptionsDto) controlPanel.getBackgroundComboBox().getSelectedItem();
        FireColorPalette colorPalette;
        switch (option){
            case BACKGROUND_1:
                viewer.updateBackground("src/images/fireplace.png");
                colorPalette=new FireColorPalette(targetsBG1());
                viewer.updateFire(310,205,250,250,colorPalette);
                break;
            case BACKGROUND_2:
                viewer.updateBackground("src/images/modern_fireplace.png");
                colorPalette=new FireColorPalette(targetsBG2());
                viewer.updateFire(290,110,250,250,colorPalette);
                break;
            case BACKGROUND_3:
                viewer.updateBackground("src/images/medieval_fireplace_3.png");
                colorPalette=new FireColorPalette(targetsBG4());
                viewer.updateFire(307,257,250,250,colorPalette);
                break;
            case BACKGROUND_4:
                viewer.updateBackground("src/images/ice_fireplace_2.png");
                colorPalette=new FireColorPalette(targetsBG3());
                viewer.updateFire(294,213,250,250,colorPalette);
                break;
        }
    }

    public ArrayList<DtoColorTarget> targetsBG1(){
        ArrayList<DtoColorTarget> targets=new ArrayList<>();
        targets.add(new DtoColorTarget(0,    new Color(0, 0, 0, 0)));
        targets.add(new DtoColorTarget(64,   new Color(230, 0, 115, 80)));
        targets.add(new DtoColorTarget(128,  new Color(245, 40, 150, 120)));
        targets.add(new DtoColorTarget(192,  new Color(255, 90, 185, 160)));
        targets.add(new DtoColorTarget(255,  new Color(255, 150, 220, 200)));

        return targets;
    }

    public ArrayList<DtoColorTarget> targetsBG2(){
        ArrayList<DtoColorTarget> targets=new ArrayList<>();
        targets.add(new DtoColorTarget(0,    new Color(0,0,0,0)));
        targets.add(new DtoColorTarget(64,    new Color(160,0,0,100)));
        targets.add(new DtoColorTarget(128,    new Color(196,72,0,140)));
        targets.add(new DtoColorTarget(192,    new Color(232,143,0,180)));
        targets.add(new DtoColorTarget(255,    new Color(255,220,71,220)));

        return targets;
    }

    public ArrayList<DtoColorTarget> targetsBG3(){
        ArrayList<DtoColorTarget> targets=new ArrayList<>();
        targets.add(new DtoColorTarget(0,    new Color(0, 0, 0, 0)));
        targets.add(new DtoColorTarget(64,   new Color(0, 60, 120, 80)));
        targets.add(new DtoColorTarget(128,  new Color(0, 110, 190, 120)));
        targets.add(new DtoColorTarget(192,  new Color(50, 160, 230, 160)));
        targets.add(new DtoColorTarget(255,  new Color(130, 200, 255, 200)));

        return targets;
    }

    public ArrayList<DtoColorTarget> targetsBG4(){
        ArrayList<DtoColorTarget> targets=new ArrayList<>();
        targets.add(new DtoColorTarget(0,    new Color(0, 0, 0, 0)));
        targets.add(new DtoColorTarget(64,   new Color(30, 120, 90, 80)));
        targets.add(new DtoColorTarget(128,  new Color(60, 180, 130, 120)));
        targets.add(new DtoColorTarget(192,  new Color(100, 220, 170, 160)));
        targets.add(new DtoColorTarget(255,  new Color(150, 255, 210, 200)));

        return targets;
    }
}
