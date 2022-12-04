package com.example.projectg104;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import com.example.projectg104.Adapters.ProductAdapter;
import com.example.projectg104.DB.DBHelper;
import com.example.projectg104.Entities.Product;
import com.example.projectg104.Services.ProductService;

import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    private DBHelper dbHelper;
    private ProductService productService;
    private ListView productListView;
    private ArrayList<Product> arrayProducts;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);

        arrayProducts = new ArrayList<>();
        dbHelper = new DBHelper(this);
        productService = new ProductService();

        //Se usa try y catch para conectarse a DB

        try {

            dbHelper = new DBHelper(this);
           /* byte[] img = "".getBytes();
            //Se insertan datos manualmente hasta crear formulario para llenar DB
            dbHelper.insertData("Chocorramo","Ponquecito", "1200",img);
            dbHelper.insertData("Chocorramo","Ponquecito", "1300",img);
            dbHelper.insertData("Chocorramo","Ponquecito", "1400",img);

            */
            productService = new ProductService();
            Cursor cursor = dbHelper.getData();
            arrayProducts = productService.cursorToArray(cursor);


        }catch (Exception e){
            Log.e("Database",e.toString());
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        /*
//Cuando no hay base de datos se crea una pequena lista de productos
        Product product1 = new Product(R.drawable.product1,"Milky Way","Chocolatina",2500);
        Product product2 = new Product(R.drawable.product2, "M&M's", "Chocolates", 6000);
        Product product3 = new Product(R.drawable.product3, "Chocorramo", "Pastel", 2300);

        arrayProducts.add(product1);
        arrayProducts.add(product2);
        arrayProducts.add(product3);
*/

        productAdapter = new ProductAdapter(this, arrayProducts);
        productListView = (ListView) findViewById(R.id.productListView);
        productListView.setAdapter(productAdapter);



    }



}