package ua.vn.v_prokopets.vk_photo_viewer.list_albums;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.utils.ListAlbumsTaskLoaderHelper;
import ua.vn.v_prokopets.vk_photo_viewer.utils.VersionAPI;
import ua.vn.v_prokopets.vk_photo_viewer.utils.XmlContent;

public class ListAlbumsTaskLoader extends AsyncTaskLoader<List<Album>> {

    private static final int VALUE_FIELD_NEED_COVERS = 1;

    private List<Album>                mLoadedListAlbums = null;
    private ListAlbumsTaskLoaderHelper mTaskLoaderHelper = null;

    public ListAlbumsTaskLoader(Context context, String userId, String accessToken) {
        super(context);

        String mUrlMethodPhotosGetAlbumsXml = "https://api.vk.com/method/photos.getAlbums.xml?" +
                                              "owner_id="      + userId +
                                              "&v="            + VersionAPI.VERSION_API +
                                              "&need_covers="  + VALUE_FIELD_NEED_COVERS +
                                              "&access_token=" + accessToken;

        mTaskLoaderHelper = new ListAlbumsTaskLoaderHelper( XmlContent.CONTENT_PHOTOS_GET_ALBUMS_XML, mUrlMethodPhotosGetAlbumsXml );
    }

    @Override
    public List<Album> loadInBackground() {
        return mTaskLoaderHelper.loadingData();
    }

    @Override
    public void deliverResult(List<Album> loadedListAlbums) {
        super.deliverResult(loadedListAlbums);
        mLoadedListAlbums = loadedListAlbums;
    }

    @Override
    public void onStartLoading() {
        super.onStartLoading();
        if ( mLoadedListAlbums != null ) {
            deliverResult(mLoadedListAlbums);
        } else {
            forceLoad();
        }
    }

}
