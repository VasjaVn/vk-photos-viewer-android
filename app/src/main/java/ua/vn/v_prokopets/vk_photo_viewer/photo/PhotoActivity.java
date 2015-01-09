package ua.vn.v_prokopets.vk_photo_viewer.photo;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ua.vn.v_prokopets.vk_photo_viewer.R;
import ua.vn.v_prokopets.vk_photo_viewer.list_photos.ListPhotosActivity;
import ua.vn.v_prokopets.vk_photo_viewer.login.LoginActivity;

public class PhotoActivity extends Activity
                           implements LoaderManager.LoaderCallbacks<Bitmap> {

    private static final int LOADER_PHOTO_ID = 3;

    private Bitmap      mBmpFullPhoto;
    private ImageView   mImageView;
    private ProgressBar mProgressBar;
    private String      mUrlPhoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mImageView   = (ImageView) findViewById(R.id.photo_image_view);
        mProgressBar = (ProgressBar) findViewById(R.id.photo_progress_bar);

        mUrlPhoto = (String) getIntent().getExtras().get(ListPhotosActivity.EXTRA_PHOTO_URL);

        getLoaderManager().initLoader( LOADER_PHOTO_ID, null, this );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch ( item.getItemId() ) {
            case R.id.menu_logout:
                //CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().removeAllCookie();
                Intent loginIntent = new Intent(PhotoActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;

            case R.id.menu_back:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
        return new PhotoTaskLoader(this, mUrlPhoto);
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap bmpFullPhoto) {
        mBmpFullPhoto = bmpFullPhoto;
        mProgressBar.setVisibility(View.GONE);

        mImageView.setImageBitmap(mBmpFullPhoto);
        mImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {

    }
}
