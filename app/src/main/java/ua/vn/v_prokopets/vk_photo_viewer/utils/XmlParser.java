package ua.vn.v_prokopets.vk_photo_viewer.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.list_albums.Album;
import ua.vn.v_prokopets.vk_photo_viewer.list_photos.Photo;

public class XmlParser {

    //PARSE_PHOTOS_GET_ALBUMS_XML
    private static final String TAG_ALBUM       = "album";
    private static final String TAG_ID          = "id";
    private static final String TAG_THUMB_ID    = "thumb_id";
    private static final String TAG_OWNER_ID    = "owner_id";
    private static final String TAG_TITLE       = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_CREATED     = "created";
    private static final String TAG_UPDATED     = "updated";
    private static final String TAG_SIZE        = "size";
    private static final String TAG_THUMB_SRC   = "thumb_src";

    //PARSE_PHOTOS_GET_XML
    private static final String TAG_PHOTO      = "photo";
    //private static final String TAG_ID         = "id";
    private static final String TAG_ALBUM_ID   = "album_id";
    //private static final String TAG_OWNER_ID   = "owner_id";
    private static final String TAG_USER_ID    = "user_id";
    private static final String TAG_PHOTO_75   = "photo_75";
    private static final String TAG_PHOTO_130  = "photo_130";
    private static final String TAG_PHOTO_604  = "photo_604";
    private static final String TAG_PHOTO_807  = "photo_807";
    private static final String TAG_PHOTO_1280 = "photo_1280";
    private static final String TAG_PHOTO_2560 = "photo_2560";
    private static final String TAG_WIDTH      = "width";
    private static final String TAG_HEIGHT     = "height";
    private static final String TAG_TEXT       = "text";
    private static final String TAG_DATE       = "date";

    private XmlPullParser mXmlParser;
    private XmlContent    mXmlContent;


    public void init(XmlContent xmlContent, InputStreamReader inputStreamReader) throws NotInitXmlParser {

        mXmlContent = xmlContent;

        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            mXmlParser = xmlPullParserFactory.newPullParser();
            mXmlParser.setInput(inputStreamReader);

        } catch ( XmlPullParserException e ) {
            throw new NotInitXmlParser();
        }
    }


    public List<Album> parserPhotosGetAlbumsXml() throws XmlPullParserException, IOException, IllegalContentXmlParser {

        if ( mXmlContent != XmlContent.CONTENT_PHOTOS_GET_ALBUMS_XML ) {
            throw new IllegalContentXmlParser();
        }

        List<Album> resultListAlbums = new ArrayList<>();

        Album  album   = null;
        String tagName = "";

        int eventType = mXmlParser.getEventType();
        while ( eventType != XmlPullParser.END_DOCUMENT ) {

            switch ( eventType ) {

                case XmlPullParser.START_TAG:
                    tagName = mXmlParser.getName();
                    if ( tagName.equals(TAG_ALBUM) ) {
                        album = new Album();
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ( mXmlParser.getName().equals(TAG_ALBUM) ) {
                        resultListAlbums.add(album);
                    }
                    tagName = "";
                    break;

                case XmlPullParser.TEXT:
                    String tagValue = mXmlParser.getText();
                    if ( album != null ) {
                        setTagValueToAlbum(album, tagName, tagValue);
                    }
                    break;
            }

            eventType = mXmlParser.next();
        }

        return resultListAlbums;
    }


    public List<Photo> parserPhotosGetXml() throws XmlPullParserException, IOException, IllegalContentXmlParser {

        if ( mXmlContent != XmlContent.CONTENT_PHOTOS_GET_XML ) {
            throw new IllegalContentXmlParser();
        }

        List<Photo> resultListPhotos = new ArrayList<>();

        Photo  photo   = null;
        String tagName = "";

        int eventType = mXmlParser.getEventType();
        while ( eventType != XmlPullParser.END_DOCUMENT ) {

            switch ( eventType ) {

                case XmlPullParser.START_TAG:
                    tagName = mXmlParser.getName();
                    if ( tagName.equals(TAG_PHOTO) ) {
                        photo = new Photo();
                    }
                    break;

                case XmlPullParser.END_TAG:
                    if ( mXmlParser.getName().equals(TAG_PHOTO) ) {
                        resultListPhotos.add(photo);
                    }
                    tagName = "";
                    break;

                case XmlPullParser.TEXT:
                    String tagValue = mXmlParser.getText();
                    if ( photo != null ) {
                        setTagValueToPhoto(photo, tagName, tagValue);
                    }
                    break;
            }

            eventType = mXmlParser.next();
        }

        return resultListPhotos;
    }


    private void setTagValueToAlbum(Album album, String tagName, String tagValue) {

        switch ( tagName ) {
            case TAG_ID:
                album.setId(tagValue);
                break;

            case TAG_THUMB_ID:
                album.setThumbId(tagValue);
                break;

            case TAG_OWNER_ID:
                album.setOwnerId(tagValue);
                break;

            case TAG_TITLE:
                album.setTitle(tagValue);
                break;

            case TAG_DESCRIPTION:
                album.setDescription(tagValue);
                break;

            case TAG_CREATED:
                album.setCreatedDate(tagValue);
                break;

            case TAG_UPDATED:
                album.setUpdatedDate(tagValue);
                break;

            case TAG_SIZE:
                album.setSize(tagValue);
                break;

            case TAG_THUMB_SRC:
                album.setThumbSrc(tagValue);
                break;

            default:
                break;
        }
    }


    private void setTagValueToPhoto(Photo photo, String tagName, String tagValue) {

        switch ( tagName ) {
            case TAG_ID:
                photo.setId(tagValue);
                break;

            case TAG_ALBUM_ID:
                photo.setAlbumId(tagValue);
                break;

            case TAG_OWNER_ID:
                photo.setOwnerId(tagValue);
                break;

            case TAG_USER_ID:
                photo.setUserId(tagValue);
                break;

            case TAG_PHOTO_75:
                photo.setUrlPhoto_75(tagValue);
                break;

            case TAG_PHOTO_130:
                photo.setUrlPhoto_130(tagValue);
                break;

            case TAG_PHOTO_604:
                photo.setUrlPhoto_604(tagValue);
                break;

            case TAG_PHOTO_807:
                photo.setUrlPhoto_807(tagValue);
                break;

            case TAG_PHOTO_1280:
                photo.setUrlPhoto_1280(tagValue);
                break;

            case TAG_PHOTO_2560:
                photo.setUrlPhoto_2560(tagValue);
                break;

            case TAG_WIDTH:
                photo.setWidth(tagValue);
                break;

            case TAG_HEIGHT:
                photo.setHeight(tagValue);
                break;

            case TAG_TEXT:
                photo.setText(tagValue);
                break;

            case TAG_DATE:
                photo.setDate(tagValue);
                break;

            default:
                break;
        }
    }

}

// Exceptions
class IllegalContentXmlParser extends Exception {}
class NotInitXmlParser extends Exception {}
