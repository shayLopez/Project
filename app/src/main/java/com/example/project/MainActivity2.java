package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.ViewTreeOnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
private ArrayList <Item>itList=new ArrayList<>();
private RecyclerView itemsRc;
private HelperDB hlpD;
private Intent intent;
private Button searchBt,SeeAllBt;
private EditText sEt;

private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.output);
        hlpD=new HelperDB(this);
        inits();
        setupRecyclerView();
        searchBt.setOnClickListener(V->searchItems());
        SeeAllBt.setOnClickListener(V ->showAllItems());

    }
    private void inits()
    {
     intent=getIntent();
    // itList= (ArrayList<Item>) intent.getSerializableExtra("list");
     itemsRc=findViewById(R.id.Rv);
     searchBt=findViewById(R.id.button4);
     SeeAllBt=findViewById(R.id.button5);
     sEt=findViewById(R.id.editTextText3);
    }
    private void searchBy()
    {
       ArrayList <Item> itemsL=new ArrayList<>();
        if (sEt.getText().length()==0)
            sEt.setError("Empty");
        else
        {
            itemsL = hlpD.getItemsByTitle(sEt.getText().toString());
            AdapterN adapter = new AdapterN(itemsL);
            itemsRc.setAdapter(adapter);
        }
    }
    private void searchItems() {
        String title =
                sEt.getText().toString();
        new Thread(() -> {
            ArrayList<Item> results = hlpD.getItemsByTitle(title);
            handler.post(() -> {
                itList =results;
                AdapterN adapter = new AdapterN(itList);
                itemsRc.setAdapter(adapter);
            });
        }).start();
    }
    private void seeAll()
    {
        AdapterN adapter = new AdapterN(itList);
        itemsRc.setAdapter(adapter);
    }
    private void showAllItems() {
        new Thread(() -> {
            ArrayList<Item> newItems = hlpD.getAllItems();
            handler.post(() -> {
                itList = newItems;
                AdapterN adapter = new AdapterN(itList);
                itemsRc.setAdapter(adapter);
            });
        }).start();
    }
    private void setupRecyclerView()
    {
        itemsRc.setLayoutManager(new LinearLayoutManager(this));
        itList=hlpD.getAllItems();
        AdapterN adapter = new AdapterN(itList);
        itemsRc.setAdapter(adapter);
    }


}