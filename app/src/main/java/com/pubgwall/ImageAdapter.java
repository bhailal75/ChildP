package com.pubgwall;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;
    private List<Upload> uploadList;
    private HomeClickListner homeClickListner;

    public ImageAdapter(Context mContext, List<Upload> uploadList,HomeClickListner homeClickListner) {
        this.mContext = mContext;
        this.uploadList = uploadList;
        this.homeClickListner = homeClickListner;
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
//        viewHolder.mTextViewName.setText(currentUpload.getmName());
        Picasso.with(mContext).load(currentUpload.getmImageUrl())
                .fit()
                .centerCrop()
                .into(viewHolder.mImageView);
//        viewHolder.fab.setTag(position);
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public TextView mTextViewName;
        public ImageView mImageView;
        public FloatingActionButton fab;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            mTextViewName = itemView.findViewById(R.id.textview_name);
            mImageView = itemView.findViewById(R.id.imageView);
            fab = itemView.findViewById(R.id.fab);
            fab.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

            Bitmap bitmap = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
            WallpaperManager myWallpaperManager
                    = WallpaperManager.getInstance(mContext);
            try {
                myWallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


//            if (homeClickListner != null){
//                int pos = (int) v.getTag();
//                homeClickListner.onHomeClick(pos);
//            }

        }
    }

    public interface HomeClickListner {
        void onHomeClick(int pos);
    }
}
