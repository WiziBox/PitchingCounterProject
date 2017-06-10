package kr.misty.pitchingcounterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Chronometer chrono_chronometer;
    ToggleButton button_startstop;
    Button button_strike;
    Button button_ball;
    Button button_cancel;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    static long elapsed_time;
    long current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_startstop = (ToggleButton)findViewById(R.id.button_startstop);
        button_strike = (Button)findViewById(R.id.button_strike);
        button_ball = (Button)findViewById(R.id.button_ball);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView7 = (TextView)findViewById(R.id.textView7);

        textView3.setText("던진 공\n"+ String.valueOf(Velocity.count)+ "구");
        textView4.setText(String.format("평균 구속 \n %.0f km/h", Velocity.average));
        textView5.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
        textView6.setText("볼\n" + String.valueOf(StrikeOrBall.ball));


        button_startstop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (button_startstop.isChecked()) {
                    current_time = System.currentTimeMillis();
                    textView2.setText("측정중...");
                } else {
                    elapsed_time = System.currentTimeMillis() - current_time;
                    float velocity = (float)elapsed_time;
                    textView1.setText(String.valueOf(" "+Velocity.getInfo(elapsed_time)));
                    textView2.setText(String.format("측정된 구속 : %.2f km/h", Velocity.getVelocity(elapsed_time)));
                    textView3.setText("던진 공\n"+ String.valueOf(Velocity.count)+ "구");
                    textView4.setText(String.format("평균 구속 \n %.0f km/h", Velocity.average));
                    textView7.setText(String.valueOf(String.valueOf(elapsed_time)));
                }
            }
        });

        button_strike.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                StrikeOrBall.getResult(1);
                textView5.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
            }
        });

        button_ball.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                StrikeOrBall.getResult(2);
                textView6.setText("볼\n" + String.valueOf(StrikeOrBall.ball));
            }
        });

        button_cancel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
               StrikeOrBall.ball = 0;
               StrikeOrBall.strike = 0;
               Velocity.average = 0;
               Velocity.count = 0;
               Velocity.sum_velocity = 0;
                textView3.setText("던진 공\n"+ String.valueOf(Velocity.count)+ "구");
                textView4.setText(String.format("평균 구속 \n %.0f km/h", Velocity.average));
                textView5.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
                textView6.setText("볼\n" + String.valueOf(StrikeOrBall.ball));
            }
        });

    /*public static void main(String args[]){
        int strike;
        int ball;
        int count;

        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.printf("소요된 시간을 입력하세요: ");
            double seconds = input.nextDouble();
            Velocity.getVelocity(seconds);
            Velocity.getInfo(seconds);
            System.out.printf("투구의 결과를 입력하세요: ");
            int result = input.nextInt();
            System.out.println("");
            System.out.printf("측정된 구속 : %.2f km/h\n", Velocity.getVelocity(seconds));
            System.out.println(Velocity.getInfo(seconds));
            StrikeOrBall.getResult(result);
            System.out.printf("현재까지의 스트라이크 개수 : %d\n", StrikeOrBall.strike);
            System.out.printf("현재까지의 볼 개수 : %d\n", StrikeOrBall.ball);
            System.out.println("");
            System.out.println("");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String time = sdf.format(new Date(System.currentTimeMillis()));
        }*/
    }
}

