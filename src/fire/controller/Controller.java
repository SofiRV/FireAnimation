package fire.controller;

import fire.model.Model;
import fire.view.View;

public class Controller {
    private final View view;
    private final Model model;

    public Controller() {
        this.model=new Model(this);
        this.view=new View(this);

    }
    public int[][] updateFire(){

        return model.getTemperatures();
    }
}
