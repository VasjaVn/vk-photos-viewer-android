package ua.vn.v_prokopets.vk_photo_viewer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ThumbPhotosLoadThread<T extends IImage> extends Thread {

    private List<T>             mList;
    private INotifiableCallback mCallbackNotify;

    private volatile boolean    mIsStopLoad;

    public ThumbPhotosLoadThread(List<T> list, INotifiableCallback callbackNotify) {
        mList           = list;
        mCallbackNotify = callbackNotify;
        mIsStopLoad     = false;
    }

    public synchronized void stopLoad() {
        mIsStopLoad = true;
    }

    @Override
    public void run() {

        for ( T item : mList ) {

            synchronized (this) {
                if ( mIsStopLoad ) { break; }
            }

            if ( item.getUrlImage() != null ) {
                HttpURLConnection httpUrlConnection = null;

                try {
                    httpUrlConnection =(HttpURLConnection) new URL(item.getUrlImage()).openConnection();
                    httpUrlConnection.connect();

                    InputStream inputStream = httpUrlConnection.getInputStream();
                    Bitmap bmpThumbImage = BitmapFactory.decodeStream(inputStream);
                    item.setImage(bmpThumbImage);
                    mCallbackNotify.notifyThumbPhotosLoaded();

                } catch ( IOException e ) {
                    // NOP
                } finally {
                    if ( httpUrlConnection != null ) {
                        httpUrlConnection.disconnect();
                    }
                }
           }
        }
        //mCallbackNotify.notifyThumbPhotosLoaded();
    }

    public interface INotifiableCallback {
        public void notifyThumbPhotosLoaded();
    }
}
