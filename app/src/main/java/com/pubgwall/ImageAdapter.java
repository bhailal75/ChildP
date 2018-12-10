package com.pubgwall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private List<Upload> uploadList;

    public ImageAdapter(Context mContext, List<Upload> uploadList) {
        this.mContext = mContext;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_image,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int position) {
        Upload currentUpload = uploadList.get(position);
        viewHolder.mTextViewName.setText(currentUpload.getmName());
        Picasso.with(mContext).load(currentUpload.getmImageUrl())
                .fit()
                .centerCrop()
                .into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName;
        public ImageView mImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_name);
            mImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
