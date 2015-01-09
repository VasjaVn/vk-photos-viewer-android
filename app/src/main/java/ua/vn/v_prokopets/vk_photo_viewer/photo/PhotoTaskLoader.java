package ua.vn.v_prokopets.vk_photo_viewer.photo;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PhotoTaskLoader extends AsyncTaskLoader<Bitmap> {

    private String mUrlImage;
    private Bitmap mLoadedBmpPhoto;

    public PhotoTaskLoader(Context context, String urlImage) {
        super(context);
        mUrlImage = urlImage;
    }

    @Override
    public Bitmap loadInBackground() {

        Bitmap            loadedBmpPhoto = null;
        HttpURLConnection httpConnection = null;

        try {
            URL url = new URL( mUrlImage );
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.connect();
            loadedBmpPhoto = BitmapFactory.decodeStream(httpConnection.getInputStream());
        } catch ( IOException e) {
            // NOP
        } finally {
            if ( httpConnection != null ) {
                httpConnection.disconnect();
            }
        }

        return loadedBmpPhoto;
    }

    @Override
    public void onStartLoading() {
        if ( mLoadedBmpPhoto != null ) {
            deliverResult( mLoadedBmpPhoto );
        } else {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(Bitmap loadedBmpPhoto) {
        super.deliverResult( loadedBmpPhoto );
        mLoadedBmpPhoto = loadedBmpPhoto;
    }
}
