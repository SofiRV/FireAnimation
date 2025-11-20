package fire.model;

import java.util.Random;

public class Fire implements Runnable{
    int fireWidth=512;
    int fireHeight=512;
    int[][] temperatures=new int[fireHeight][fireWidth];//[alto][ancho]
    int[][] newTemperatures=new int[fireHeight][fireWidth];
    private Thread fireThread;
    private boolean running=true;
    double decayingfactor=1;


    public Fire(){}

    @Override
    public void run(){
        Random random=new Random();
        double sparkPercentage=0.10;
        while(running){
            for (int j=0; j<fireWidth; j++) {
                int value=temperatures[fireHeight-1][j];
                newTemperatures[fireHeight-1][j]=value;
            }

            generateSpark(random,sparkPercentage);
            spreadHeat();
            coolFire(random);

            int[][] temp=temperatures;
            temperatures=newTemperatures;
            newTemperatures=temp;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public int[][] getTemperatures() {
        return temperatures;
    }

    public void startFire(){
        running=true;
        fireThread=new Thread(this);
        fireThread.start();
    }
    public void generateSpark(Random random,double sparkPercentage){
        for (int i=0; i<fireWidth; i++) {
            if (random.nextDouble()<sparkPercentage) {
                newTemperatures[fireHeight-1][i]=1023;

            }
        }
    }
    public void spreadHeat(){
        for (int i=fireHeight-2; i>=0 ; i--) {
            for (int j=0; j<fireWidth; j++) {
                double heatFromDown=0;
                double heatFromRight=0;
                double heatFromLeft=0;

                if (j!=fireHeight -1) {
                    heatFromDown=(newTemperatures[j+1][i] * 0.5);
                }
                if (i!=0) {
                    heatFromLeft=(newTemperatures[j][i-1] * 0.25);
                }
                if (i!=fireWidth -1) {
                    heatFromRight=(newTemperatures[j][i+1] * 0.25);
                }

                newTemperatures[j][i]=(int) Math.round(
                        (heatFromDown+heatFromRight+heatFromLeft) * decayingfactor
                );
            }
        }
    }

    public void coolFire(Random random){
        double coolPercentage=0.05;
        int maxCool=50;
        for (int i=0; i<temperatures.length/2; i++) {
            for (int j=0; j<temperatures[0].length; j++) {
                if (random.nextDouble()<coolPercentage) {
                    newTemperatures[i][j]=Math.max(0, newTemperatures[i][j]-random.nextInt(maxCool));
                }
            }
        }
    }
}

