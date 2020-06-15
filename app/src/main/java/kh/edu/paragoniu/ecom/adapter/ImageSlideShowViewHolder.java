package kh.edu.paragoniu.ecom.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kh.edu.paragoniu.ecom.R;

public class ImageSlideShowViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgSlide;

    public ImageSlideShowViewHolder(@NonNull View itemView) {
        super(itemView);

        imgSlide = itemView.findViewById(R.id.img_slide);
    }

    public void bind(int imageId){
        imgSlide.setImageResource(imageId);
    }

}
