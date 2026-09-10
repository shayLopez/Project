package com.example.project;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterN extends RecyclerView.Adapter<AdapterN.ViewHolderN> {
    private ArrayList<Item> itemlist;

    public AdapterN(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public ViewHolderN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderN holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public static class ViewHolderN extends RecyclerView.ViewHolder{
      public TextView nameTv,infoTv;
     public ImageView phtIv;
      public ViewHolderN(View itemView)
      {
           super(itemView);
      }
   }
    public class ItemViewHolder
            extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvDescription;
        @NonNull
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rclview, parent, false);

            return new ItemViewHolder(rowView);
        }
        public ItemViewHolder(@NonNull View rowView)
        {
            super(rowView);

            ivPhoto = rowView.findViewById(R.id.OphtIv);
            tvTitle = rowView.findViewById(R.id.OnameTv);
            tvDescription = rowView.findViewById(R.id.OinfoIv);

        }

        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position)
        {

            Item item = itemlist.get(position);

            holder.tvTitle.setText(item.getName());
            holder.tvDescription.setText(item.getInfo());

            Uri uri = Uri.parse(item.getUri());
            holder.ivPhoto.setImageURI(uri);
        }
    }
}
