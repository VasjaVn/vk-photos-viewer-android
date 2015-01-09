package ua.vn.v_prokopets.vk_photo_viewer.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import ua.vn.v_prokopets.vk_photo_viewer.R;
import ua.vn.v_prokopets.vk_photo_viewer.list_albums.ListAlbumsActivity;

public class LoginActivity extends Activity {

    public static final String EXTRA_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String EXTRA_USER_ID      = "USER_ID";

    private static final String CLIENT_ID     = "4684862";
    private static final String REDIRECT_URI  = "https://oauth.vk.com/blank.html";
    private static final String DISPLAY       = "mobile";
    private static final String SCOPE         = "photos";
    private static final String RESPONSE_TYPE = "token";

    private static final String LOGIN_URL = "https://oauth.vk.com/authorize?" +
                                            "client_id="      + CLIENT_ID +
                                            "&redirect_uri="  + REDIRECT_URI +
                                            "&display="       + DISPLAY +
                                            "&scope="         + SCOPE +
                                            "&response_type=" + RESPONSE_TYPE;

    private String  mUserId;
    private String  mAccessToken;
    //private int     mExpiresIn;
    private WebView mWebView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadLoginPage();
    }

    private void loadLoginPage() {
        mWebView = (WebView) findViewById(R.id.login_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebClient());
        mWebView.loadUrl(LOGIN_URL);
    }

    class WebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            if ( url.startsWith(REDIRECT_URI) ) {

                if ( !url.contains("?error")) {
                    url = url.replace(REDIRECT_URI, "");
                    String params[] = url.split("[&#]\\w+=");

                    mAccessToken = params[1];
                    //mExpiresIn   = Integer.parseInt(params[2]);
                    mUserId      = params[3];

                    Intent albumsIntent = new Intent(LoginActivity.this, ListAlbumsActivity.class);
                    albumsIntent.putExtra(EXTRA_USER_ID, mUserId);
                    albumsIntent.putExtra(EXTRA_ACCESS_TOKEN, mAccessToken);
                    startActivity(albumsIntent);

                } else {
                    url = url.replace(REDIRECT_URI, "");
                    String params[] = url.split("[&#]\\w+=");

                    String errorDescription = params[1];

                    Toast toast = Toast.makeText(getApplicationContext(), errorDescription, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
    }

}
