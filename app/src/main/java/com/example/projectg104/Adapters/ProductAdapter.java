package com.example.projectg104.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectg104.Entities.Product;
import com.example.projectg104.ProductDetail;
import com.example.projectg104.R;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> arrayProducts;
    private Button btn_product;


    public ProductAdapter(Context context, ArrayList<Product> arrayProducts) {
        this.context = context;
        this.arrayProducts = arrayProducts;
    }

    @Override
    public int getCount() {
        return this.arrayProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arrayProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    //getView es el que hace todo
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        view= layoutInflater.inflate(R.layout.product_template,null);

        Product product = arrayProducts.get(position);
        btn_product = (Button)  view.findViewById(R.id.btn_productTemplate);
        ImageView imgProductTemplate = (ImageView)  view.findViewById(R.id.imgProductTemplate);
        TextView textNameTemplate = (TextView) view.findViewById(R.id.textNameTemplate);
        TextView textDescriptionTemplate = (TextView) view.findViewById(R.id.textDescriptionTemplate);
        TextView textPriceTemplate = (TextView) view.findViewById(R.id.textPriceTemplate);

        imgProductTemplate.setImageResource(product.getImage());
        textNameTemplate.setText(product.getName());
        textDescriptionTemplate.setText(product.getDescription());
        textPriceTemplate.setText(String.valueOf(product.getPrice()));

        btn_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ProductDetail.class);
                intent.putExtra("title", product.getName());
                intent.putExtra("productDescription", product.getDescription());
                intent.putExtra("imageCode",product.getImage());
                context.startActivity(intent);


               //Toast se utiliza para generar un mensaje
                /*
                Toast.makeText(context.getApplicationContext(), "Presionado: "+ product.getName(), Toast.LENGTH_SHORT).show();
                 */

            }
        });


        return view;
    }
}
