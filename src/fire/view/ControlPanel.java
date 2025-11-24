package fire.view;

import fire.dto.BackgroundOptionsDto;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private final View view;
    private final JButton CONFIRM_SELECTION;
    private final JComboBox<BackgroundOptionsDto> backgroundComboBox;


    public ControlPanel(View view){
        this.view=view;
        setBackground(new Color(10, 10, 25));
        this.CONFIRM_SELECTION=new JButton("Confirm selection");
        this.backgroundComboBox=new JComboBox<>(BackgroundOptionsDto.values());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.insets=new Insets(6,6,6,6);
        gbc.gridwidth=3;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.CENTER;
        add(backgroundComboBox,gbc);

        gbc.gridy++;
        add(CONFIRM_SELECTION,gbc);
    }

    public JButton getCONFIRM_SELECTION() {
        return CONFIRM_SELECTION;
    }

    public JComboBox<BackgroundOptionsDto> getBackgroundComboBox() {
        return backgroundComboBox;
    }

    public View getView() {
        return view;
    }
}
