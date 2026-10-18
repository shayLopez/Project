package com.example.project;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {
    private Button updBt,deleteBt;
    private EditText nameEt,infoEt;
    private Intent intent;
    private int id;
    private  Item item;
    private HelperDB hlp;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        insit();
        item=hlp.getItemById(id);
        updBt.setOnClickListener(V -> saveUpdt());
        deleteBt.setOnClickListener(V -> deleteCur());
    }
    private  void  insit()
    {
        updBt=findViewById(R.id.button7);
        deleteBt=findViewById(R.id.button6);
        nameEt=findViewById(R.id.editTextText4);
        infoEt=findViewById(R.id.editTextText5);
        intent=getIntent();
         id=getIntent().getIntExtra("itemId",-1);

    }
    private void deleteCur()
    {
        hlp.deleteItem(item.getId());
        goBack();
    }
    private void saveUpdt()
    {
        hlp.flexUpdt(item.getId(),nameEt.getText().toString(),infoEt.getText().toString());
        goBack();
    }
    private void goBack()
    {
        intent=new Intent(EditActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}