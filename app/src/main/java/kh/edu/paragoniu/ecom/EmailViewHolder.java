package kh.edu.paragoniu.ecom;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

public class EmailViewHolder extends RecyclerView.ViewHolder {

    private TextView txtSender;
    private TextView txtSubject;
    private TextView txtBody;
    private SimpleDraweeView imgSender;

    public EmailViewHolder(@NonNull View itemView) {
        super(itemView);

        txtSender = itemView.findViewById(R.id.txt_sender);
        txtSubject = itemView.findViewById(R.id.txt_subject);
        txtBody = itemView.findViewById(R.id.txt_body);
        imgSender = itemView.findViewById(R.id.img_sender);
    }

    public void bind(Email email){
        txtSender.setText(email.getSender());
        txtSubject.setText(email.getSubject());
        txtBody.setText(email.getBody());
        imgSender.setImageURI(email.getSenderProfile());
    }

}
