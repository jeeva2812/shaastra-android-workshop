package com.example.omshri.memesapp.Adapter;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Output;
import android.media.Image;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.omshri.memesapp.Objects.MemesClass;
import com.example.omshri.memesapp.Objects.MemesList;
import com.example.omshri.memesapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MemesAdapter extends RecyclerView.Adapter<MemesAdapter.MyViewHolder> {
    List<MemesClass> memes;
    Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView memeImage;
        public TextView memeName;
        public Button memeSave;
        public MyViewHolder(View itemView) {
            super(itemView);
            memeImage = (ImageView) itemView.findViewById(R.id.memeimage);
            memeName = (TextView) itemView.findViewById(R.id.txt_memename);
            memeSave = (Button) itemView.findViewById(R.id.memesave);
        }
    }
    public MemesAdapter(List<MemesClass> memesClasses,Context context){
        this.memes=memesClasses;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memes_view, parent, false);

        return new MyViewHolder(itemView);

    }


    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final MemesClass meme = memes.get(position);
        holder.memeName.setText(meme.getName());
        holder.memeImage.setMaxHeight(meme.getHeight());
        holder.memeImage.setMaxWidth(meme.getWidth());
        Picasso.get()
                .load(meme.getUrl())
                .into(holder.memeImage);
        holder.memeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File path;
                Bitmap image = ((BitmapDrawable)holder.memeImage.getDrawable()).getBitmap();

                path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),meme.getName()+".jpg");
                OutputStream outputStream=null;
                try {
                    outputStream = new FileOutputStream(path);
                    image.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    Toast.makeText(context,"Image Saved",Toast.LENGTH_SHORT).show();
                    outputStream.flush();
                    outputStream.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return memes.size();
    }
}
