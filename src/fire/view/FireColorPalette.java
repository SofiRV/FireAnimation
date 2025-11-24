package fire.view;

import fire.dto.DtoColorTarget;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FireColorPalette {
    private final Color[] colors;
    private ArrayList<DtoColorTarget> targets;

    public FireColorPalette(ArrayList<DtoColorTarget> targets){
        colors=new Color[1024];
        this.targets=targets;

        /*
        targets.add(new DtoColorTarget(0,    new Color(0,0,0,0)));
        targets.add(new DtoColorTarget(64,    new Color(160,0,0,100)));
        targets.add(new DtoColorTarget(128,    new Color(196,72,0,140)));
        targets.add(new DtoColorTarget(192,    new Color(232,143,0,180)));
        targets.add(new DtoColorTarget(255,    new Color(255,220,71,220)));

        targets.add(new DtoColorTarget(0,    new Color(0,0,0,0)));
        targets.add(new DtoColorTarget(15,   new Color(100,0,50,100)));
        targets.add(new DtoColorTarget(64,    new Color(145, 7, 76,140)));
        targets.add(new DtoColorTarget(255,    new Color(191, 29, 110,180)));
        targets.add(new DtoColorTarget(1023, new Color(252, 136, 194,220)));

        targets.add(new DtoColorTarget(0,    new Color(0, 0, 0, 0)));
        targets.add(new DtoColorTarget(64,   new Color(220, 80, 140, 100)));
        targets.add(new DtoColorTarget(128,  new Color(240, 120, 170, 140)));
        targets.add(new DtoColorTarget(192,  new Color(255, 160, 200, 180)));
        targets.add(new DtoColorTarget(255,  new Color(255, 200, 230, 220)));
        */


        targets.sort(Comparator.comparingInt(t -> t.temperature));

        if (targets.get(0).temperature!=0) {
            targets.add(0, new DtoColorTarget(0, new Color(0,0,0,0)));
        }

        if (targets.get(targets.size()-1).temperature!=1023) {
            targets.add(new DtoColorTarget(1023, new Color(255,255,255,255)));
        }

        for (int i=0; i<targets.size()-1; i++) {
            DtoColorTarget lower=targets.get(i);
            DtoColorTarget upper=targets.get(i+1);

            int delta=upper.temperature-lower.temperature;

            float incR=(upper.color.getRed()-lower.color.getRed())/(float) delta;
            float incG=(upper.color.getGreen()-lower.color.getGreen())/(float) delta;
            float incB=(upper.color.getBlue()-lower.color.getBlue())/(float) delta;
            float incA=(upper.color.getAlpha()-lower.color.getAlpha())/(float) delta;

            for (int t=0; t<=delta; t++) {
                int index=lower.temperature+t;

                colors[index]=new Color(
                        Math.round(lower.color.getRed()+incR * t),
                        Math.round(lower.color.getGreen()+incG * t),
                        Math.round(lower.color.getBlue()+incB * t),
                        Math.round(lower.color.getAlpha()+incA * t)
                );
            }
        }
    }

    public Color[] getColors(){
        return colors;
    }
}

