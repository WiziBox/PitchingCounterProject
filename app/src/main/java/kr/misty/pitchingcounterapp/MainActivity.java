package kr.misty.pitchingcounterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public static void main(String args[]) {

        int strike;
        int ball;
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.printf("소요된 시간을 입력하세요: ");
            double seconds = input.nextDouble();
            Velocity.getVelocity(seconds);
            System.out.printf("투구의 결과를 입력하세요: ");
            int result = input.nextInt();
            System.out.println(Velocity.getVelocity(seconds));
            StrikeOrBall.getResult(result);
            System.out.println(StrikeOrBall.strike);
            System.out.println(StrikeOrBall.ball);
        }
    }
}


/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

