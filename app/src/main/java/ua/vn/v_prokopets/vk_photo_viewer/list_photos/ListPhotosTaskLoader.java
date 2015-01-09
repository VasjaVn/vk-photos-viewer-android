package ua.vn.v_prokopets.vk_photo_viewer.list_photos;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.utils.ListPhotosTaskLoaderHelper;
import ua.vn.v_prokopets.vk_photo_viewer.utils.VersionAPI;
import ua.vn.v_prokopets.vk_photo_viewer.utils.XmlContent;

public class ListPhotosTaskLoader extends AsyncTaskLoader<List<Photo>> {

    private ListPhotosTaskLoaderHelper mTaskLoaderHelper = null;
    private List<Photo>                mLoadedListPhotos = null;

    public ListPhotosTaskLoader(Context context, String userId, String accessToken, String albumId) {
        super(context);

        String mUrlMethodPhotosGet = "https://api.vk.com/method/photos.get.xml?" +
                                     "&owner_id=" + userId +
                                     "&v=" + VersionAPI.VERSION_API +
                                     "&album_id=" + albumId +
                                     "&access_token=" + accessToken;

        mTaskLoaderHelper = new ListPhotosTaskLoaderHelper( XmlContent.CONTENT_PHOTOS_GET_XML, mUrlMethodPhotosGet );
    }

    @Override
    public List<Photo> loadInBackground() {
        return mTaskLoaderHelper.loadingData();
    }

    @Override
    public void deliverResult(List<Photo> loadedListPhotos) {
        super.deliverResult(loadedListPhotos);
        mLoadedListPhotos = loadedListPhotos;
    }

    @Override
    public void onStartLoading() {
        if ( mLoadedListPhotos != null ) {
            deliverResult(mLoadedListPhotos);
        } else {
            forceLoad();
        }
    }

}
