package kh.edu.paragoniu.ecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import kh.edu.paragoniu.ecom.R;

public class ImageSlideShowAdapter extends PagerAdapter {

    // Dataset
    private int[] imageIds;

    public ImageSlideShowAdapter(int[] imageIds) {
        this.imageIds = imageIds;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // Inflate a layout file
        LayoutInflater layoutInflater = LayoutInflater.from(container.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_image_slide_item, container, false);

        // Find an ImageView
        int imageId = imageIds[position];
        ImageView imgSlide = itemView.findViewById(R.id.img_slide);
        imgSlide.setImageResource(imageId);

        // Add the inflated view into the container
        container.addView(itemView);

        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);

    }

}
