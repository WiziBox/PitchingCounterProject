package kr.misty.pitchingcounterapp;

/**
 * Created by thslr on 2017-06-05.
 */

public class Velocity {
    private static double velocity;
    private static double distance;
    private static double seconds;

    Velocity(double seconds) { ;
        Velocity.seconds = seconds;
    }

    public static double getVelocity (double seconds){
        distance = 18.44;
        velocity = 3600 * (distance / 1000 / seconds);
        return velocity;
    }
}
