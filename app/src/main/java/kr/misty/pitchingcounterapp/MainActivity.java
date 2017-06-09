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
    TextView textView3;
    TextView textView4;
    long elapsed_time;
    long current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_startstop = (ToggleButton)findViewById(R.id.button_startstop);
        button_strike = (Button)findViewById(R.id.button_strike);
        button_ball = (Button)findViewById(R.id.button_ball);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);

        button_startstop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (button_startstop.isChecked()) {
                    current_time = System.currentTimeMillis();
                } else {
                    elapsed_time = System.currentTimeMillis() - current_time;
                    float velocity = (float)elapsed_time;
                    textView3.setText(String.format("측정된 구속 : %.2f km/h", 3600 * (18.44 / 1000 / (velocity / 1000))));
                    textView4.setText(String.format(String.valueOf(velocity)));
                }
            }
        });

        button_cancel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chrono_chronometer.setBase(SystemClock.elapsedRealtime());
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

