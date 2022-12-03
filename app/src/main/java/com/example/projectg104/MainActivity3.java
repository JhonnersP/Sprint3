package com.example.projectg104;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    private Button btnProductInfo;
    private TextView textProductTitle, textProductDescription;
    private ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnProductInfo = (Button) findViewById(R.id.btn_ProductInfo);
        textProductTitle = (TextView) findViewById(R.id.textProductTitle);
        textProductDescription = (TextView)   findViewById(R.id.textProductInfo);

        Intent intentInt= getIntent();
        textProductTitle.setText(intentInt.getStringExtra("title"));
        textProductDescription.setText(intentInt.getStringExtra("productDescription"));
        int codeImage=intentInt.getIntExtra("imageCode",0);
        imgProduct = (ImageView) findViewById(R.id.imageProduct);
        imgProduct.setImageResource(codeImage);

        btnProductInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
            }
        });


    }
}