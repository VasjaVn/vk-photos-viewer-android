package ua.vn.v_prokopets.vk_photo_viewer.list_albums;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.R;
import ua.vn.v_prokopets.vk_photo_viewer.list_photos.ListPhotosActivity;
import ua.vn.v_prokopets.vk_photo_viewer.login.LoginActivity;
import ua.vn.v_prokopets.vk_photo_viewer.utils.ThumbPhotosLoadThread;

public class ListAlbumsActivity extends Activity
                                implements AdapterView.OnItemClickListener,
                                           LoaderManager.LoaderCallbacks<List<Album>>,
                                           ThumbPhotosLoadThread.INotifiableCallback
{
    private static final int LOADER_LIST_ALBUMS_ID = 1;

    public static final String EXTRA_USER_ID      = "USER_ID";
    public static final String EXTRA_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String EXTRA_ALBUM_ID     = "ALBUM_ID";

    private String              mUserId;
    private String              mAccessToken;
    private List<Album>         mListAlbums = new ArrayList<>();
    private ArrayAdapter<Album> mAlbumsArrayAdapter;
    private GridView            mAlbumsGridView;

    private ThumbPhotosLoadThread<Album> mThumbPhotosLoadThread;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_albums);

        mUserId      = (String) getIntent().getExtras().get(LoginActivity.EXTRA_USER_ID);
        mAccessToken = (String) getIntent().getExtras().get(LoginActivity.EXTRA_ACCESS_TOKEN);

        mAlbumsArrayAdapter = new AlbumsArrayAdapter(this, R.layout.albums_item, mListAlbums);

        mAlbumsGridView = (GridView) findViewById(R.id.list_albums_grid_view);
        mAlbumsGridView.setAdapter(mAlbumsArrayAdapter);
        mAlbumsGridView.setOnItemClickListener(this);

        getLoaderManager().initLoader(LOADER_LIST_ALBUMS_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_albums, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu_logout:
                //CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().removeAllCookie();
                Intent loginIntent = new Intent(ListAlbumsActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        Album album = (Album) mAlbumsGridView.getItemAtPosition(position);

        Intent photosIntent = new Intent(ListAlbumsActivity.this, ListPhotosActivity.class);
        photosIntent.putExtra(EXTRA_USER_ID, mUserId);
        photosIntent.putExtra(EXTRA_ACCESS_TOKEN, mAccessToken);
        photosIntent.putExtra(EXTRA_ALBUM_ID, album.getId());
        startActivity(photosIntent);
    }


    @Override
    public Loader<List<Album>> onCreateLoader(int id, Bundle args) {
        return new ListAlbumsTaskLoader(this, mUserId, mAccessToken);
    }

    @Override
    public void onLoadFinished(Loader<List<Album>> loader, List<Album> data) {
        mAlbumsArrayAdapter.clear();

        for(Album album : data) {
            mAlbumsArrayAdapter.add(album);
        }

        mThumbPhotosLoadThread = new ThumbPhotosLoadThread<Album>(mListAlbums, this);
        mThumbPhotosLoadThread.start();
    }

    @Override
    public void onLoaderReset(Loader<List<Album>> loader) {
        mAlbumsArrayAdapter.clear();
        if ( mThumbPhotosLoadThread.isAlive() ) {
            mThumbPhotosLoadThread.stopLoad();
        }
    }




    @Override
    public void notifyThumbPhotosLoaded() {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                mAlbumsArrayAdapter.notifyDataSetChanged();
            }
        });
    }

}
