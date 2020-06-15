package kh.edu.paragoniu.ecom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kh.edu.paragoniu.ecom.R;

public class ImageSlideShow2Adapter extends RecyclerView.Adapter<ImageSlideShowViewHolder> {

    // Dataset
    private int[] imageIds;

    public ImageSlideShow2Adapter(int[] imageIds) {
        this.imageIds = imageIds;
    }

    @NonNull
    @Override
    public ImageSlideShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Load a layout file
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.view_image_slide_item, parent, false);

        return new ImageSlideShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSlideShowViewHolder holder, int position) {
        int imageId = imageIds[position];
        holder.bind(imageId);
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }
}
