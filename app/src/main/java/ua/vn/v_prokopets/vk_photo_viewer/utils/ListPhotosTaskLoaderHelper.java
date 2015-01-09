package ua.vn.v_prokopets.vk_photo_viewer.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.list_photos.Photo;

public class ListPhotosTaskLoaderHelper {

    private String     mUrlRequest;
    private XmlContent mXmlContent;
    private XmlParser  mXmlParser;

    public ListPhotosTaskLoaderHelper(XmlContent xmlContent, String urlRequest) {

        mXmlContent = xmlContent;
        mUrlRequest = urlRequest;

        mXmlParser = new XmlParser();
    }

    public List<Photo> loadingData() {

        List<Photo>       resultListPhotos  = null;
        InputStreamReader inputStreamReader = null;

        HttpClient        httpClient        = new DefaultHttpClient();
        HttpPost          httpRequest       = new HttpPost(mUrlRequest);

        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            InputStream  inputStream  = httpResponse.getEntity().getContent();

            inputStreamReader = new InputStreamReader(inputStream);
            mXmlParser.init(mXmlContent, inputStreamReader);

            resultListPhotos = mXmlParser.parserPhotosGetXml();

        } catch ( XmlPullParserException | IllegalContentXmlParser | NotInitXmlParser | IOException e) {
            // NOP
        } finally {
            if ( inputStreamReader != null ) {

                try {
                    inputStreamReader.close();
                } catch ( IOException e ) {
                    //NOP
                }
            }
        }

        return resultListPhotos;
    }
}
