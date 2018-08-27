package com.example.adamstelmaszyk.myscraping;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ImageViewHolder>
{

    private Context mContext;
    private List<Upload> mUploads;



    public WorkAdapter(Context context, List<Upload> mUploads)
    {
        mContext = context;
        this.mUploads = mUploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final Upload uploadCurrent = mUploads.get(position);
        holder.textViewHead.setText(uploadCurrent.getmName());

        /*Picasso.with(mContext)
                .load(uploadCurrent.getImgUrl())
                .resize(200,200)
                .centerCrop()
                .into(holder.imageViewHead);*/
        holder.imageViewHead.setImageResource(uploadCurrent.getImgUrl());

        holder.textViewHead.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                String name = uploadCurrent.getmWebUrl();

                Intent intentMoreInfo = new Intent(mContext,MoreInformationActivity.class);
                intentMoreInfo.putExtra("photo_job", uploadCurrent.getImgWebFinder());
                intentMoreInfo.putExtra("name_job", uploadCurrent.getmName());
                intentMoreInfo.putExtra("url_job", uploadCurrent.getmWebUrl());
                intentMoreInfo.putExtra("description_job",uploadCurrent.getmDescription());
                mContext.startActivity(intentMoreInfo);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
        public ImageView imageViewHead;



        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewHead = itemView.findViewById(R.id.text_view_name);
            imageViewHead = itemView.findViewById(R.id.ITEM_IMAGE);
        }

    }

}
