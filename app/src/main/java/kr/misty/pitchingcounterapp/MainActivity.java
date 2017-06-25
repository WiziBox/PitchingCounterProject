package kr.misty.pitchingcounterapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.util.Log;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Chronometer chrono_chronometer;
    ToggleButton button_startstop;
    Button button_strike;
    Button button_ball;
    Button button_cancel;
    Button button_screen;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;


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
        button_screen = (Button)findViewById(R.id.button_screen);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);
        textView6 = (TextView)findViewById(R.id.textView6);
        textView7 = (TextView)findViewById(R.id.textView7);

        textView3.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
        textView4.setText("볼\n" + String.valueOf(StrikeOrBall.ball));
        textView5.setText("직구\n" + String.valueOf(Velocity.fastball));
        textView6.setText("변화구\n" + String.valueOf(Velocity.breaking));
        textView7.setText(String.format("평균 구속 : %.0f km/h", Velocity.average));

        button_startstop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (button_startstop.isChecked()) {
                    current_time = System.currentTimeMillis();
                    textView2.setText("측정중...");
                } else {
                    elapsed_time = System.currentTimeMillis() - current_time;
                    float velocity = (float)elapsed_time;
                    textView2.setText(String.format(Velocity.getInfo(elapsed_time) + " : %.2f km/h", Velocity.getVelocity(elapsed_time)));
                    textView1.setText(String.valueOf(Velocity.count)+ "구");
                    textView3.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
                    textView4.setText("볼\n" + String.valueOf(StrikeOrBall.ball));
                    textView5.setText("직구\n" + String.valueOf(Velocity.fastball));
                    textView6.setText("변화구\n" + String.valueOf(Velocity.breaking));
                    textView7.setText(String.format("평균 구속 : %.0f km/h", Velocity.average));
                }
            }
        });

        button_strike.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                StrikeOrBall.getResult(1);
                textView3.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
            }
        });

        button_ball.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                StrikeOrBall.getResult(2);
                textView4.setText("볼\n" + String.valueOf(StrikeOrBall.ball));
            }
        });

        button_cancel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                StrikeOrBall.ball = 0;
                StrikeOrBall.strike = 0;
                Velocity.average = 0;
                Velocity.count = 0;
                Velocity.sum_velocity = 0;
                Velocity.fastball = 0;
                Velocity.breaking = 0;

                textView2.setText("리셋!");
                textView3.setText("스트라이크\n" + String.valueOf(StrikeOrBall.strike));
                textView4.setText("볼\n" + String.valueOf(StrikeOrBall.ball));
                textView5.setText("직구\n" + String.valueOf(Velocity.fastball));
                textView6.setText("변화구\n" + String.valueOf(Velocity.breaking));
                textView7.setText(String.format("평균 구속 : %.0f km/h", Velocity.average));
            }
        });

        //캡쳐버튼클릭
        button_screen.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                textView2.setText("스크린샷!");
                String folder = "DCIM"; // 폴더 이름

                View container = getWindow().getDecorView();

                try {
                    // 현재 날짜로 파일을 저장하기
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                    // 년월일시분초
                    Date currentTime_1 = new Date();
                    String dateString = formatter.format(currentTime_1);
                    File sdCardPath = Environment.getExternalStorageDirectory();
                    File dirs = new File(Environment.getExternalStorageDirectory(), folder);

                    if (!dirs.exists()) { // 원하는 경로에 폴더가 있는지 확인
                        dirs.mkdirs(); // Test 폴더 생성
                        Log.d("YA9SCREEN", "Directory Created");
                    }
                    container.buildDrawingCache();
                    Bitmap captureView = container.getDrawingCache();
                    FileOutputStream fos;
                    String save;

                    try {
                        save = sdCardPath.getPath() + "/" + folder + "/" + dateString + ".jpg";
                        // 저장 경로
                        fos = new FileOutputStream(save);
                        captureView.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 캡쳐

                        // 미디어 스캐너를 통해 모든 미디어 리스트를 갱신시킨다.
                        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                                Uri.parse("file://" + Environment.getExternalStorageDirectory())));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), dateString + ".jpg 저장",
                            Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e("Screen", "" + e.toString());
                }
            }
        });
    }
}


