package fire.model;

import fire.controller.Controller;

public class Model {
    private final Controller controller;
    private final Fire fire;

    public Model(Controller controller){
        this.controller=controller;
        this.fire=new Fire();
        this.fire.startFire();
    }
    public int[][] getTemperatures(){
        return fire.getTemperatures();
    }
}
