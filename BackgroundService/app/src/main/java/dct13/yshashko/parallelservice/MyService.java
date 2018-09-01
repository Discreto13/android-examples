package dct13.yshashko.parallelservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.os.AsyncTask;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = MyService.class.getSimpleName();
    private static AsyncToast toastLoop;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        displayToast("Start service");
        toastLoop = new AsyncToast();
        toastLoop.execute();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        toastLoop.kill();
        displayToast("Stop service");
        super.onDestroy();
    }

    public void displayToast(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private class AsyncToast extends AsyncTask<Void,Void,Void> {
        private Boolean stop = false;
        public Void kill() {
            stop = true;
            return null;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stop = false;

            for (;;) {
                try {
                    Log.d(TAG, "Sleep...");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (stop) break;

                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            Toast toast = Toast.makeText(getApplicationContext(), "AsyncToast",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

