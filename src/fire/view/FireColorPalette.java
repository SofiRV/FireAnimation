package fire.view;

import fire.dto.DtoColorTarget;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FireColorPalette {
    private final ArrayList<Color> colors;

    public FireColorPalette(){
        colors=new ArrayList<>(1024);

        ArrayList<DtoColorTarget> targets=new ArrayList<>();
        targets.add(new DtoColorTarget(0,new Color(0,0,0,0)));
        targets.add(new DtoColorTarget(200,new Color(100,0,50,150)));
        targets.add(new DtoColorTarget(400,new Color(200,50,150,180)));
        targets.add(new DtoColorTarget(700,new Color(255,150,200,220)));
        targets.add(new DtoColorTarget(1023,new Color(255,255,255,255)));

        Collections.sort(targets, Comparator.comparingInt(t->t.temperature));

        for (int i = 0; i < targets.size()-1; i++) {
            DtoColorTarget lower=targets.get(i);
            DtoColorTarget upper=targets.get(i+1);

            int deltaTemperatures= upper.temperature- lower.temperature;
            float incrementR=(upper.color.getRed()-lower.color.getRed())/(float) deltaTemperatures;
            float incrementG=(upper.color.getGreen()-lower.color.getGreen())/(float) deltaTemperatures;
            float incrementB=(upper.color.getBlue()-lower.color.getBlue())/(float) deltaTemperatures;
            float incrementA=(upper.color.getAlpha()-lower.color.getAlpha())/(float) deltaTemperatures;

            float r=lower.color.getRed();
            float b=lower.color.getBlue();
            float g=lower.color.getGreen();
            float a=lower.color.getAlpha();

            for (int t = lower.temperature; t <= upper.temperature ; t++) {
                colors.add(new Color(Math.round(r),Math.round(g),Math.round(b),Math.round(a)));
                r+=incrementR;
                g+=incrementG;
                b+=incrementB;
                a+=incrementA;
            }
        }
        while (colors.size()<1024){
            colors.add(new Color(255,255,255,255));
        }
    }
    public ArrayList<Color> getColors(){
        return colors;
    }
}
