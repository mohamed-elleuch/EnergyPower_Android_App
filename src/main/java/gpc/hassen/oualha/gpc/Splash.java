package gpc.hassen.oualha.gpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread  splashscreen = new Thread() {
            @Override
            public void run()
            {
                try{
                    synchronized(this){
                        wait(3000);
                    }
                }
                catch(Exception e){}
                finally{
                    Intent i=new Intent(Splash.this,Authentification.class);
                    startActivity(i);
                    finish();			}}};

        splashscreen.start();
    }
}
