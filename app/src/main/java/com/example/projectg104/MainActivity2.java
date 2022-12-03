package com.example.projectg104;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private Button btnProduct1, btnProduct2, btnProduct3;
    private TextView textProduct1, textProduct2, textProduct3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnProduct1 = (Button)  findViewById(R.id.btn_Product1);
        btnProduct2 = (Button) findViewById(R.id.btn_Product2);
        btnProduct3 = (Button) findViewById(R.id.btn_Product3);

        textProduct1 = (TextView) findViewById(R.id.textProduct1);
        textProduct2 = (TextView) findViewById(R.id.textProduct2);
        textProduct3 = (TextView) findViewById(R.id.textProduct3);

        btnProduct1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("title",textProduct1.getText().toString());
                intent.putExtra("productDescription",textProduct1.getText().toString());
                intent.putExtra("imageCode",R.drawable.product1);
                startActivity(intent);
            }
        });

        btnProduct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("title",textProduct2.getText().toString());
                intent.putExtra("productDescription",textProduct2.getText().toString());
                intent.putExtra("imageCode",R.drawable.product2);
                startActivity(intent);
            }
        });

        btnProduct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("title",textProduct3.getText().toString());
                intent.putExtra("productDescription",textProduct3.getText().toString());
                intent.putExtra("imageCode",R.drawable.product3);
                startActivity(intent);
            }
        });
    }



}