package app.Utilities;

import java.util.Random;

public class Utils {

    private static int THINKING_BOUNDARY = 15;
    private static int HUNGRY_BOUNDARY = 7;
    private static int EATING_BOUNDARY = 15;

    public static int randomIntThink(){
        int randTime = new Random().nextInt(THINKING_BOUNDARY);
        return (randTime+1)*1000; //1 to THINKING_BOUNDARY sec
    }

    public static int randomIntHungry() {
        int randTime = new Random().nextInt(HUNGRY_BOUNDARY);
        return (randTime+1)*1000; //1 to HUNGRY_BOUNDARY sec
    }

    public static int randomIntEat(){
        int randTime = new Random().nextInt(EATING_BOUNDARY);
        return (randTime+1)*1000; //1 to EATING_BOUNDARY sec
    }

}
