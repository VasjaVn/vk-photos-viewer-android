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

import ua.vn.v_prokopets.vk_photo_viewer.list_albums.Album;

public class ListAlbumsTaskLoaderHelper {

    private String     mUrlRequest;
    private XmlContent mXmlContent;
    private XmlParser  mXmlParser;

    public ListAlbumsTaskLoaderHelper(XmlContent xmlContent, String urlRequest) {
        mUrlRequest = urlRequest;
        mXmlContent = xmlContent;

        mXmlParser = new XmlParser();
    }

    public List<Album> loadingData() {

        List<Album>       resultListAlbums  = null;
        InputStreamReader inputStreamReader = null;

        HttpClient        httpClient        = new DefaultHttpClient();
        HttpPost          httpRequest       = new HttpPost(mUrlRequest);

        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            InputStream  inputStream  = httpResponse.getEntity().getContent();

            inputStreamReader = new InputStreamReader(inputStream);
            mXmlParser.init(mXmlContent, inputStreamReader);

            resultListAlbums = mXmlParser.parserPhotosGetAlbumsXml();

        } catch ( NotInitXmlParser | XmlPullParserException | IllegalContentXmlParser | IOException e ) {
            // NOP
        } finally {

            try {
                if ( inputStreamReader != null ) {
                    inputStreamReader.close();
                }
            } catch ( IOException e ) {
                //NOP
            }
        }

        return resultListAlbums;
    }

}
