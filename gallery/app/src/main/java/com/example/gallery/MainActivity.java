package com.example.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gallery g = (Gallery) findViewById(R.id.gallery);
        g.setAdapter(new ImageAdapter(this));
    }

    public class ImageAdapter extends BaseAdapter {

        int mGallerystyle;

        private Context mContext;
        private Integer[] mImageIds = {
                R.drawable.photo1,
                R.drawable.photo2,
                R.drawable.photo3,
                R.drawable.photo4,
                R.drawable.photo5,
                R.drawable.photo6,
                R.drawable.photo7,
                R.drawable.photo8
        };

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
            mGallerystyle = a.getResourceId(
                    R.styleable.Gallery_android_galleryStyle, 0);
            a.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIds[position]);
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            i.setLayoutParams(new Gallery.LayoutParams(136, 88));

            return i;
        }

    }
}