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
    //getView es el que carga elementos en la lista
    // Realiza una especie de For para cargar todos los elementos en la lista
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        //Se referencia la plantilla de carga de elementos
        view= layoutInflater.inflate(R.layout.product_template,null);

        Product product = arrayProducts.get(position);
        //Cargar boton de plantilla
        btn_product = (Button)  view.findViewById(R.id.btn_productTemplate);
        //Cargar imagen de plantilla
        ImageView imgProductTemplate = (ImageView)  view.findViewById(R.id.imgProductTemplate);
        //Cargar Nombre producto plantilla
        TextView textNameTemplate = (TextView) view.findViewById(R.id.textNameTemplate);
        //Cargar descripcion producto plantilla
        TextView textDescriptionTemplate = (TextView) view.findViewById(R.id.textDescriptionTemplate);
        //Cargar precio producto plantilla
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
