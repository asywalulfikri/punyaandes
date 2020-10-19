package test.uts.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_sreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pindahHalaman();
    }

    private void pindahHalaman(){
        //fungsi pindah halaman setelah 3 detik di splashscreen
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                Intent intent= new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}