package com.example.projectg104;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectg104.DB.DBHelper;
import com.example.projectg104.Entities.Product;
import com.example.projectg104.Services.ProductService;

import java.util.ArrayList;

public class ProductDetail extends AppCompatActivity {
    private DBHelper dbHelper;
    private ProductService productService;
    private Button btnProductInfo;
    private TextView textProductName, textProductDescription, textProductPrice;
    private ImageView imgProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        btnProductInfo = (Button) findViewById(R.id.btn_ProductInfo);
        textProductName = (TextView) findViewById(R.id.textProductName);
        textProductDescription = (TextView)   findViewById(R.id.textProductDescription);
        textProductPrice = (TextView) findViewById(R.id.textProductPrice);
        imgProduct = (ImageView) findViewById(R.id.imageProduct);
        dbHelper = new DBHelper(this);
        productService = new ProductService();

        Intent intentIn= getIntent();
        String id = intentIn.getStringExtra("id");
        ArrayList<Product> list=productService.cursorToArray(dbHelper.getDataById(id));
        Product product = list.get(0);

        textProductName.setText(product.getName());
        textProductDescription.setText(product.getDescription());
        textProductPrice.setText(String.valueOf(product.getPrice()));
        imgProduct.setImageBitmap(productService.byteToBitmap(product.getImage()));


        btnProductInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ProductList.class);
                startActivity(intent);
            }
        });


    }
}