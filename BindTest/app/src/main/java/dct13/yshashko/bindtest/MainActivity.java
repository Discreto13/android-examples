package dct13.yshashko.bindtest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyService mService;
    boolean mBound = false;

    Button b_play, b_stop;
    Button b_bind, b_message, b_unbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_play = (Button) findViewById(R.id.b_play);
        b_stop = (Button) findViewById(R.id.b_stop);
        b_bind = (Button) findViewById(R.id.b_bind);
        b_message = (Button) findViewById(R.id.b_message);
        b_unbind = (Button) findViewById(R.id.b_unbind);

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, MyService.class));
            }
        });

        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MyService.class));
            }
        });

        b_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        b_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound == false) {
                    Toast.makeText(MainActivity.this, "Service did not connected", Toast.LENGTH_SHORT).show();
                    return;
                }
                int genNum = mService.getRandNumber();
                Toast.makeText(MainActivity.this, "GenNum: " + genNum, Toast.LENGTH_SHORT).show();
            }
        });

        b_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBound == false) {
                    Toast.makeText(MainActivity.this, "Non-connected yet", Toast.LENGTH_SHORT).show();
                    return;
                }
                unbindService(mConnection);
                mBound = false;
                Toast.makeText(MainActivity.this, "Unbind - done", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "Activity start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "Activity stop", Toast.LENGTH_SHORT).show();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            mService = binder.getService();
            mBound = true;
            Toast.makeText(MainActivity.this, "Service connected", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            Toast.makeText(MainActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
        }
    };
}
