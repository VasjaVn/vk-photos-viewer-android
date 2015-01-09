package ua.vn.v_prokopets.vk_photo_viewer.list_photos;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.R;

public class PhotosArrayAdapter extends ArrayAdapter<Photo> {

    private       int            mResourceLayout;
    private final LayoutInflater mLayoutInflater;

    public PhotosArrayAdapter(Context context, int resourceLayout, List<Photo> objects) {
        super(context, resourceLayout, objects);
        mResourceLayout = resourceLayout;
        mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout photoItemView;

        if ( convertView == null ) {
            photoItemView = new LinearLayout(getContext());
            mLayoutInflater.inflate(mResourceLayout, photoItemView, true);
        } else {
            photoItemView = (LinearLayout) convertView;
        }

        Photo photoItem = getItem(position);

        Bitmap bmpPhoto = photoItem.getBmpImage();
        if ( bmpPhoto != null ) {
            ProgressBar progressBar = (ProgressBar) photoItemView.findViewById(R.id.photos_progress_bar);
            progressBar.setVisibility(View.GONE);

            ImageView imageView = (ImageView) photoItemView.findViewById(R.id.photos_image_view);
            imageView.setImageBitmap(bmpPhoto);
            imageView.setVisibility(View.VISIBLE);
        }

        return  photoItemView;
    }
}
