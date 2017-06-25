package kr.misty.pitchingcounterapp;

/**
 * Created by thslr on 2017-06-05.
 */

public class StrikeOrBall {
    public static int result;
    public static int strike;
    public static int ball;
    public static double ratio;

    StrikeOrBall(int result) {
        StrikeOrBall.result = result;
    }

    public static void getResult (int result){
        if (result == 1) {
            System.out.println("스트라이크");
            strike++;
        } else {
            System.out.println("볼");
            ball++;
        }
    }
}