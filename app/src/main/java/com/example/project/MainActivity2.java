package com.example.project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hlpD=new HelperDB(this);
        setContentView(R.layout.output);
        inits();
        setupRecyclerView();
        searchBt.setOnClickListener(V->searchBy());
        SeeAllBt.setOnClickListener(V ->seeAll());

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
    private void seeAll()
    {
        AdapterN adapter = new AdapterN(itList);
        itemsRc.setAdapter(adapter);
    }
    private void setupRecyclerView()
    {
        itemsRc.setLayoutManager(new LinearLayoutManager(this));
        itList=hlpD.getAllItems();
        AdapterN adapter = new AdapterN(itList);
        itemsRc.setAdapter(adapter);
    }


}