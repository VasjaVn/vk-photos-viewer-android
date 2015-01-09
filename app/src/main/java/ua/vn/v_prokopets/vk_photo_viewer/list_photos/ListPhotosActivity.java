package ua.vn.v_prokopets.vk_photo_viewer.list_photos;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.webkit.CookieManager;

import java.util.ArrayList;
import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.R;
import ua.vn.v_prokopets.vk_photo_viewer.list_albums.ListAlbumsActivity;
import ua.vn.v_prokopets.vk_photo_viewer.login.LoginActivity;
import ua.vn.v_prokopets.vk_photo_viewer.photo.PhotoActivity;
import ua.vn.v_prokopets.vk_photo_viewer.utils.ThumbPhotosLoadThread;

public class ListPhotosActivity extends Activity
                                implements AdapterView.OnItemClickListener,
                                           LoaderManager.LoaderCallbacks<List<Photo>>,
                                           ThumbPhotosLoadThread.INotifiableCallback {

    private static final int LOADER_LIST_PHOTOS_ID = 2;

    public static final String EXTRA_PHOTO_URL = "PHOTO_URL";

    private String mUserId      = null;
    private String mAccessToken = null;
    private String mAlbumId     = null ;

    private List<Photo>                  mListPhotos            = new ArrayList<>();
    private GridView                     mPhotosGridView        = null;
    private ArrayAdapter<Photo>          mPhotosArrayAdapter    = null;
    private ThumbPhotosLoadThread<Photo> mThumbPhotosLoadThread = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_photos);

        mUserId      = (String) getIntent().getExtras().getString(ListAlbumsActivity.EXTRA_USER_ID);
        mAccessToken = (String) getIntent().getExtras().getString(ListAlbumsActivity.EXTRA_ACCESS_TOKEN);
        mAlbumId     = (String) getIntent().getExtras().getString(ListAlbumsActivity.EXTRA_ALBUM_ID);

        mPhotosArrayAdapter = new PhotosArrayAdapter(this, R.layout.photos_item, mListPhotos);

        mPhotosGridView = (GridView) findViewById(R.id.list_photos_grid_view);
        mPhotosGridView.setAdapter(mPhotosArrayAdapter);
        mPhotosGridView.setOnItemClickListener(this);

        getLoaderManager().initLoader(LOADER_LIST_PHOTOS_ID, null, this);
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
                Intent loginActivity = new Intent(ListPhotosActivity.this, LoginActivity.class);
                startActivity(loginActivity);
                break;

            case R.id.menu_back:
                onBackPressed();
                break;
        }

        return true;
    }
    @Override
    public void notifyThumbPhotosLoaded() {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                mPhotosArrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setExtraPhotoUrl(Intent intent, Photo photo) {

        /*if ( photo.getUrlPhoto_2560() != null ) {
            intent.putExtra(EXTRA_PHOTO_URL, photo.getUrlPhoto_2560());
        } else if ( photo.getUrlPhoto_1280() != null ) {
            intent.putExtra(EXTRA_PHOTO_URL, photo.getUrlPhoto_1280());
        } else*/
        if ( photo.getUrlPhoto_807() != null ) {
            intent.putExtra(EXTRA_PHOTO_URL, photo.getUrlPhoto_807());
        } else if ( photo.getUrlPhoto_604() != null ) {
            intent.putExtra(EXTRA_PHOTO_URL, photo.getUrlPhoto_604());
        } else if ( photo.getUrlPhoto_130() != null ) {
            intent.putExtra(EXTRA_PHOTO_URL, photo.getUrlPhoto_130());
        } else if ( photo.getUrlPhoto_75() != null ) {
            intent.putExtra(EXTRA_PHOTO_URL, photo.getUrlPhoto_75());
        }

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Photo photo = (Photo) mPhotosGridView.getItemAtPosition(position);

        Intent photoIntent = new Intent(ListPhotosActivity.this, PhotoActivity.class);
        setExtraPhotoUrl(photoIntent, photo);
        startActivity(photoIntent);
    }



    @Override
    public Loader<List<Photo>> onCreateLoader(int id, Bundle args) {
        return new ListPhotosTaskLoader(this, mUserId, mAccessToken, mAlbumId);
    }

    @Override
    public void onLoadFinished(Loader<List<Photo>> loader, List<Photo> listPhotos) {
        mPhotosArrayAdapter.clear();

        for ( Photo photo : listPhotos ) {
            mPhotosArrayAdapter.add(photo);
        }

        mThumbPhotosLoadThread = new ThumbPhotosLoadThread<Photo>(mListPhotos, this);
        mThumbPhotosLoadThread.start();

    }

    @Override
    public void onLoaderReset(Loader<List<Photo>> loader) {
        mPhotosArrayAdapter.clear();
        if ( mThumbPhotosLoadThread.isAlive() ) {
            mThumbPhotosLoadThread.stopLoad();
        }
    }
}
