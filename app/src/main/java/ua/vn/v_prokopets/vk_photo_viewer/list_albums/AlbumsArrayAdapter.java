package ua.vn.v_prokopets.vk_photo_viewer.list_albums;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ua.vn.v_prokopets.vk_photo_viewer.R;

public class AlbumsArrayAdapter extends ArrayAdapter<Album> {

    private       int            mResourceLayout;
    private final LayoutInflater mLayoutInflater;

    public AlbumsArrayAdapter(Context context, int resourceLayout, List<Album> objects) {
        super(context, resourceLayout, objects);
        mResourceLayout = resourceLayout;
        mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LinearLayout albumItemView;

        if ( convertView == null ) {
            albumItemView = new LinearLayout(getContext());
            mLayoutInflater.inflate(mResourceLayout, albumItemView, true);
        } else {
            albumItemView = (LinearLayout) convertView;
        }

        Album albumItem = getItem(position);

        //TITLE
        TextView titleView = (TextView) albumItemView.findViewById(R.id.albums_item_txt_title);
        titleView.setText(albumItem.getTitle());

        //DESCRIPTION
        String description = albumItem.getDescription();
        if ( description != null ) {
            TextView descriptionView = (TextView) albumItemView.findViewById(R.id.albums_item_description);
            descriptionView.setVisibility(View.VISIBLE);

            TextView descriptionTxtView = (TextView) albumItemView.findViewById(R.id.albums_item_txt_description);
            descriptionTxtView.setText(description);
        }

        //SIZE
        TextView sizeView = (TextView) albumItemView.findViewById(R.id.albums_item_txt_size);
        sizeView.setText(albumItem.getSize());

        Bitmap bmpThumbImage = albumItem.getThumbImage();
        if ( bmpThumbImage != null ) {
            //IMAGE
            ImageView imageView = (ImageView) albumItemView.findViewById(R.id.albums_item_image);
            imageView.setImageBitmap(bmpThumbImage);
            imageView.setVisibility(ImageView.VISIBLE);

            ProgressBar progressBar = (ProgressBar) albumItemView.findViewById(R.id.albums_item_progress_bar);
            progressBar.setVisibility(ProgressBar.GONE);
        }

        return albumItemView;
    }
}
