package kr.misty.pitchingcounterapp;

/**
 * Created by thslr on 2017-06-05.
 */

public class Velocity {
    public static int count = 0;
    public static double velocity;
    public static double sum_velocity;
    public static double distance = 18.44;
    public static double average;
    public static double seconds;
    public static int fastball = 0;
    public static int breaking = 0;

    Velocity(double seconds) { ;
        Velocity.seconds = seconds;
    }

    public static double getVelocity (double seconds){
        //distance = 18.44;
        seconds = MainActivity.elapsed_time;
        velocity = 3600 * (distance  / 1000 / (seconds / 1000));
        return velocity;
    }

    public static String getInfo (double seconds){
        count++;
        seconds = MainActivity.elapsed_time;
        sum_velocity += getVelocity(seconds);
        average = sum_velocity / count;
        if (getVelocity(seconds) + 5 >= average) {
            fastball++;
            return "직구";
        } else {
            breaking++;
            return "변화구";
        }
    }
}