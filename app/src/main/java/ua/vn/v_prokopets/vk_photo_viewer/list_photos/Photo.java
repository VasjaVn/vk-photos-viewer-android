package ua.vn.v_prokopets.vk_photo_viewer.list_photos;

import android.graphics.Bitmap;

import ua.vn.v_prokopets.vk_photo_viewer.utils.IImage;

public class Photo implements IImage {

    private String mId;
    private String mAlbumId;
    private String mOwnerId;
    private String mUserId;
    private String mUrlPhoto_75;
    private String mUrlPhoto_130;
    private String mUrlPhoto_604;
    private String mUrlPhoto_807;
    private String mUrlPhoto_1280;
    private String mUrlPhoto_2560;
    private String mWidth;
    private String mHeight;
    private String mText;
    private String mDate;

    private Bitmap mBmpImage;


    public void setId(String id) {
        mId = id;
    }
    public String getId() {
        return mId;
    }

    public void setAlbumId(String albumId) {
        mAlbumId = albumId;
    }
    public String getAlbumId() {
        return mAlbumId;
    }

    public void setOwnerId(String ownerId) {
        mOwnerId = ownerId;
    }
    public String getOwnerId() {
        return mOwnerId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }
    public String getUserId() {
        return mUserId;
    }

    public void setUrlPhoto_75(String urlPhoto_75) {
        mUrlPhoto_75 = urlPhoto_75;
    }
    public String getUrlPhoto_75() {
        return mUrlPhoto_75;
    }

    public void setUrlPhoto_130(String urlPhoto_130) {
        mUrlPhoto_130 = urlPhoto_130;
    }
    public String getUrlPhoto_130() {
        return mUrlPhoto_130;
    }

    public void setUrlPhoto_604(String urlPhoto_604) {
        mUrlPhoto_604 = urlPhoto_604;
    }
    public String getUrlPhoto_604() {
        return mUrlPhoto_604;
    }

    public void setUrlPhoto_807(String urlPhoto_807) {
        mUrlPhoto_807 = urlPhoto_807;
    }
    public String getUrlPhoto_807() {
        return mUrlPhoto_807;
    }

    public void setUrlPhoto_1280(String urlPhoto_1280) {
        mUrlPhoto_1280 = urlPhoto_1280;
    }
    public String getUrlPhoto_1280() {
        return mUrlPhoto_1280;
    }

    public void setUrlPhoto_2560(String urlPhoto_2560) {
        mUrlPhoto_2560 = urlPhoto_2560;
    }
    public String getUrlPhoto_2560() {
        return mUrlPhoto_2560;
    }

    public void setWidth(String width) {
        mWidth = width;
    }
    public String getWidth() {
        return mWidth;
    }

    public void setHeight(String height) {
        mHeight = height;
    }
    public String getHeight() {
        return mHeight;
    }

    public void setText(String text) {
        mText = text;
    }
    public String getText() {
        return mText;
    }

    public void setDate(String date) {
        mDate = date;
    }
    public String getDate() {
        return mDate;
    }

    public void setBmpImage(Bitmap bmpImage) {
        mBmpImage = bmpImage;
    }
    public Bitmap getBmpImage() {
        return mBmpImage;
    }

    @Override
    public String getUrlImage() {
        return getUrlPhoto_604();
    }

    @Override
    public void setImage(Bitmap bmpImage) {
        setBmpImage(bmpImage);
    }
}
