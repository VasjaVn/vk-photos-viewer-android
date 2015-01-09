package ua.vn.v_prokopets.vk_photo_viewer.list_albums;

import android.graphics.Bitmap;

import ua.vn.v_prokopets.vk_photo_viewer.utils.IImage;

public class Album implements IImage {

    private String mId;
    private String mThumbId;
    private String mOwnerId;
    private String mTitle;
    private String mDescription;
    private String mCreatedDate;
    private String mUpdatedDate;
    private String mSize;
    private String mThumbSrc;

    private Bitmap mThumbImage;

    public void setId(String id) {
        mId = id;
    }
    public String getId() {
        return mId;
    }

    public void setThumbId(String thumbId) {
        mThumbId = thumbId;
    }
    public String getThumbId() {
        return mThumbId;
    }

    public void setOwnerId(String ownerId) {
        mOwnerId = ownerId;
    }
    public String getOwnerId() {
        return mOwnerId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
    public String getDescription() {
        return mDescription;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }
    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        mUpdatedDate = updatedDate;
    }
    public String getUpdatedDate() {
        return mUpdatedDate;
    }

    public void setSize(String size) {
        mSize = size;
    }
    public String getSize() {
        return mSize;
    }

    public void setThumbSrc(String thumbSrc) {
        mThumbSrc = thumbSrc;
    }
    public String getThumbSrc() {
        return  mThumbSrc;
    }

    public void setThumbImage(Bitmap thumbImage) {
        mThumbImage = thumbImage;
    }
    public Bitmap getThumbImage() {
        return mThumbImage;
    }

    @Override
    public String getUrlImage() {
        return getThumbSrc();
    }

    @Override
    public void setImage(Bitmap bmpImage) {
        setThumbImage(bmpImage);
    }

}
