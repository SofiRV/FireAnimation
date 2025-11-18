package fire.model;

import java.util.Random;

public class Fire implements Runnable{
    int[][] temperatures=new int[512][512];//[alto][ancho]
    int[][] newTemperatures=new int[512][512];
    private Thread fireThread;
    private boolean running=true;


    public Fire(){
        fireThread=new Thread(this);
        fireThread.start();
    }

    @Override
    public void run(){
        Random random=new Random();
        double sparkPercentage=0.10;
        while(running){
            //generate spark
            for (int i = 0; i < temperatures[0].length; i++) {
                if (random.nextDouble()<sparkPercentage) {
                    temperatures[temperatures.length-1][i]=1023;
                }
            }
            //spread temperatures
            for (int i = temperatures.length-2; i >=0 ; i--) {
                for (int j = 0; j < temperatures[0].length; j++) {
                    int value=weightedAverage(temperatures,i,j);
                    //decay
                    value=Math.max(0,value-random.nextInt(3));
                    newTemperatures[i][j]=value;
                }
            }
            for (int j = 0; j < temperatures[0].length; j++) {
                newTemperatures[temperatures.length-1][j]=temperatures[temperatures.length-1][j];
            }
            double coolPercentage=0.05;
            int maxCool=50;
            for (int i = 0; i < temperatures.length/2; i++) {
                for (int j = 0; j < temperatures[0].length; j++) {
                    if(random.nextDouble()<coolPercentage){
                        newTemperatures[i][j]=Math.max(0,newTemperatures[i][j]-random.nextInt(maxCool));
                    }
                }
            }

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

    public int weightedAverage(int[][] temperatures, int i, int j){
        int currentBox=temperatures[i][j];
        int width=temperatures[0].length;
        int downBox;
        int leftDownBox;
        int rightDownBox;
        int rightBox;
        int leftBox;
        int weightCurrentBox=2;
        int weightDownBox=4;
        int weightleftDownBoxBox=3;
        int weightrightDownBoxBox=3;
        int weightrightBoxBox=1;
        int weightleftBoxBox=1;

        downBox=temperatures[i+1][j];
        if(j-1>=0){
            leftDownBox=temperatures[i+1][j-1];
        }else {
            leftDownBox=downBox;
        }
        if(j+1<width){
            rightDownBox=temperatures[i+1][j+1];
        }else {
            rightDownBox=downBox;
        }
        if(j-1>=0){
            leftBox=temperatures[i][j-1];
        }else {
            leftBox=currentBox;
        }
        if(j+1<width){
            rightBox=temperatures[i][j+1];
        }else {
            rightBox=currentBox;
        }
        int weightSum=weightCurrentBox+
                weightDownBox+
                weightleftDownBoxBox+
                weightrightDownBoxBox+
                weightrightBoxBox+
                weightleftBoxBox;
        int sum=(currentBox * weightCurrentBox)+
                (downBox * weightDownBox)+
                (leftDownBox * weightleftDownBoxBox)+
                (rightDownBox * weightrightDownBoxBox)+
                (rightBox * weightrightBoxBox)+
                (leftBox * weightleftBoxBox);
        return sum/weightSum;
    }
}
