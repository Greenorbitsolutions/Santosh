package com.example.greenorbit2.mvc;

/**
 * Created by Green Orbit 2 on 11/1/2016.
 */

import android.app.Activity;

        import android.app.Activity;
        import android.os.Bundle;
        import android.content.Intent;

class Splash extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent intent = new Intent(Splash.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        timer.start();
    }}


