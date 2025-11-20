package fire.view;

import fire.dto.DtoColorTarget;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class FireColorPalette {
    private final Color[] colors;
    private ArrayList<DtoColorTarget> targets;

    public FireColorPalette(){
        colors=new Color[1024];
        targets=new ArrayList<>();

        targets.add(new DtoColorTarget(0,    new Color(0,0,0,0)));
        targets.add(new DtoColorTarget(20,   new Color(100,0,50,100)));
        targets.add(new DtoColorTarget(1023, new Color(255,150,200,255)));

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

