package com.example.project;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditActivity extends AppCompatActivity {
    private Button updBt,deleteBt;
    private EditText nameEt,infoEt;
    private TextView nameTv,infoTv;
    private Intent intent;
    private int id;
    private  Item item;
    private HelperDB hlp;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        hlp = new HelperDB(this);
        insit();
        loadSelectedItem();

        item=hlp.getItemById(id);
        updBt.setOnClickListener(V -> saveChanges());
        deleteBt.setOnClickListener(V -> deleteCurrentItem());
    }
    private  void  insit()
    {
        updBt=findViewById(R.id.button7);
        deleteBt=findViewById(R.id.button6);
        nameEt=findViewById(R.id.editTextText4);
        infoEt=findViewById(R.id.editTextText5);
        nameTv=findViewById(R.id.textView3);
        infoTv=findViewById(R.id.textView4);
        intent=getIntent();
        id=getIntent().getIntExtra("ItemId",-1);

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
    private void loadSelectedItem() {
        new Thread(() -> {
            Item loadedItem = hlp.getItemById(id);
            handler.post(() -> {
                if (loadedItem == null) {
                    finish();
                    return;
                }
                item = loadedItem;
                nameTv.setText(item.getName());
                infoTv.setText(item.getInfo());
            });
        }).start();
    }
    private void saveChanges() {
        String newTitle = nameEt.getText().toString();
        String newDescription = infoEt.getText().toString();
        if (nameEt.getText().toString().isEmpty()&&infoEt.getText().toString().isEmpty())
            return;

        new Thread(() -> {
            hlp.flexUpdt(item.getId(), newTitle, newDescription);
            handler.post(() -> {
                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
    private void deleteCurrentItem() {

        new Thread(() -> {
            hlp.deleteItem(item.getId());
            handler.post(() -> {
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }

}