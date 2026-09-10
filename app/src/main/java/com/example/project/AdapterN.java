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

public class AdapterN extends RecyclerView.Adapter<AdapterN.ViewHolderN> {
    private ArrayList<Item> itemlist;

    public AdapterN(ArrayList<Item> itemlist) {
        this.itemlist = itemlist;
    }

    @NonNull
    @Override
    public ViewHolderN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rclview, parent, false);
        return new ViewHolderN(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderN holder, int position) {
        Item item = itemlist.get(position);

        holder.nameTv.setText(item.getName());
        holder.infoTv.setText(item.getInfo());

        Uri uri = Uri.parse(item.getUri());
        holder.phtIv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public static class ViewHolderN extends RecyclerView.ViewHolder {
        public TextView nameTv, infoTv;
        public ImageView phtIv;

        public ViewHolderN(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.OnameTv);
            infoTv = itemView.findViewById(R.id.OinfoIv);
            phtIv = itemView.findViewById(R.id.OphtIv);
        }
    }
}