package fire.controller;

import fire.model.Model;
import fire.view.View;

public class Controller {
    private final View view;
    private final Model model;

    public Controller() {
        this.view =new View(this);
        this.model =new Model(this);
    }
}
