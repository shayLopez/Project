package com.example.project;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.navigation.fragment.NavHostFragment;

import com.example.project.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button eBt,sBt,sfgBt;
private EditText nameEt,infoEt;
private ImageView phtIv;
private Uri selectedUri;


private ActivityResultLauncher<String> galleryLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        insit();
        registerLaunchers();
        sfgBt.setOnClickListener(v -> chooseFromGallery());
eBt.setOnClickListener(V->addToList());
//




    }
    private void chooseFromGallery() {
        galleryLauncher.launch("image/*");
    }
    private void registerLaunchers() {
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        selectedUri = uri;
                        phtIv.setImageURI(uri);
                    }
                }
        );
    }
    private void addToList()
    {
        if(nameEt.getText().length()==0){
            nameEt.setError("Name is required");
            return;
        }
        if (infoEt.getText().length()==0)
        {
            infoEt.setError("Info is required");
        return;
        }
        String uriString=selectedUri.toString();
        if (uriString.isEmpty()) {
            Toast.makeText(MainActivity.this, "a picture is required", Toast.LENGTH_SHORT).show();
            return;
        }
        //if (nameEt.getText().length()!=0&&infoEt.getText().length()!=0&&!uriString.isEmpty())

    }
    private void insit()
    {   sfgBt=findViewById(R.id.button3);
        eBt=findViewById(R.id.button);
        sBt=findViewById(R.id.button2);
        nameEt=findViewById(R.id.editTextText);
        infoEt=findViewById(R.id.editTextText2);
        phtIv=findViewById(R.id.imageView);

    }
}