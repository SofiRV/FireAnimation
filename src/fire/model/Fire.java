package fire.model;

import java.util.Random;

public class Fire implements Runnable{
    int fireWidth=200;
    int fireHeight=200;
    int[][] temperatures=new int[fireHeight][fireWidth];
    int[][] newTemperatures=new int[fireHeight][fireWidth];
    private Thread fireThread;
    private boolean running=true;
    double decayingfactor=0.975;


    public Fire(){}

    @Override
    public void run(){
        Random random=new Random();
        double sparkPercentage=0.10;
        while(running){

            generateSpark(random,sparkPercentage);
            spreadHeat();
            coolFire(random);

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
                temperatures[fireHeight-1][i]=1023;

            }
        }
    }

    public void spreadHeat(){
        for (int i = fireHeight - 2; i >= 0; i--) {
            for (int j = 0; j < fireWidth; j++) {

                double heatFromDown = temperatures[i + 1][j] * 0.5;

                double heatFromLeft = 0;
                if (j > 0) {
                    heatFromLeft = temperatures[i][j - 1] * 0.25;
                }

                double heatFromRight = 0;
                if (j < fireWidth - 1) {
                    heatFromRight = temperatures[i][j + 1] * 0.25;
                }

                temperatures[i][j] = (int) Math.round(
                        (heatFromDown + heatFromLeft + heatFromRight) * decayingfactor
                );
            }
        }
    }


    public void coolFire(Random random){
        for (int i = 0; i <fireWidth ; i++) {
            temperatures[fireHeight-1][i]=(int) Math.round(temperatures[fireHeight-1][i]*0.8);
        }
    }
}

