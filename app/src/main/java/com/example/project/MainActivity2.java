package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
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
private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.output);
        inits();
        setupRecyclerView();

    }
    private void inits()
    {
     intent=getIntent();
     itList= (ArrayList<Item>) intent.getSerializableExtra("list");
     itemsRc=findViewById(R.id.Rv);
    }
    private void setupRecyclerView()
    {
        itemsRc.setLayoutManager(new LinearLayoutManager(this));
        AdapterN adapter = new AdapterN(itList);
        itemsRc.setAdapter(adapter);
    }
}