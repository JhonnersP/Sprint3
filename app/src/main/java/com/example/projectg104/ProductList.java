package com.example.projectg104;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;


import com.example.projectg104.Adapters.ProductAdapter;
import com.example.projectg104.Entities.Product;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    private ListView productListView;
    private ArrayList<Product> arrayProducts;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        arrayProducts = new ArrayList<>();

//Como no hay base de datos se crea una pequena lista de productos
        Product product1 = new Product(R.drawable.product1,"Milky Way","Chocolatina",2500);
        Product product2 = new Product(R.drawable.product2, "M&M's", "Chocolates", 6000);
        Product product3 = new Product(R.drawable.product3, "Chocorramo", "Pastel", 2300);



        arrayProducts.add(product1);
        arrayProducts.add(product2);
        arrayProducts.add(product3);

        productAdapter = new ProductAdapter(this, arrayProducts);
        productListView = (ListView) findViewById(R.id.productListView);
        productListView.setAdapter(productAdapter);



    }



}