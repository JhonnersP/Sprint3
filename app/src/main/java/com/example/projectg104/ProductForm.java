package com.example.projectg104;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projectg104.DB.DBHelper;
import com.example.projectg104.Entities.Product;
import com.example.projectg104.Services.ProductService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProductForm extends AppCompatActivity {

    private DBHelper dbHelper;
    private Button btnCreate, btnGet, btnDelete, btnUpdate;
    private EditText editNameFormProduct, editDescriptionFormProduct, editPriceFormProduct, editIdProductForm;
    private ImageView imgFormProduct;
    private ProductService productService;
    ActivityResultLauncher<String> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_form);

        btnCreate = (Button) findViewById(R.id.btnCreateProduct);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        editNameFormProduct = (EditText) findViewById(R.id.editNameFormProduct);
        editDescriptionFormProduct = (EditText) findViewById(R.id.editDescriptionFormProduct);
        editPriceFormProduct = (EditText) findViewById(R.id.editPriceFormProduct);
        editIdProductForm = (EditText) findViewById(R.id.editIdProductForm);
        imgFormProduct = (ImageView) findViewById(R.id.imgFormProduct);

        byte[] img = "".getBytes(StandardCharsets.UTF_8);

        try {
            productService = new ProductService();
            dbHelper = new DBHelper(this);

        }catch (Exception e){
            Log.e("DB",e.toString());
        }

        content = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(result);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            imgFormProduct.setImageBitmap(bitmap);

                        }catch (FileNotFoundException e){
                            e.printStackTrace();
                        }

                    }
                }
        );

        imgFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content.launch("image/*");
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEmpty(editNameFormProduct) && isEmpty(editDescriptionFormProduct) && isEmpty(editPriceFormProduct)){

                    Toast.makeText(getApplicationContext(),"Debe llenar todos los campos",Toast.LENGTH_SHORT).show();

                }else{

                    imgFormProduct.getDrawable();

                    dbHelper.insertData(
                            editNameFormProduct.getText().toString(),
                            editDescriptionFormProduct.getText().toString(),
                            editPriceFormProduct.getText().toString(),
                            productService.imageViewToByte(imgFormProduct)

                    );
                    Intent intent = new Intent(getApplicationContext(),ProductList.class);
                    startActivity(intent);

                }




            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id= editIdProductForm.getText().toString().trim();

                if(id.compareTo("")!=0){
                    ArrayList<Product> list= productService.cursorToArray(dbHelper.getDataById(id));
                    if(list.size()!=0){

                        Product product = list.get(0);
                        imgFormProduct.setImageBitmap(productService.byteToBitmap(product.getImage()));
                        editNameFormProduct.setText(product.getName());
                        editDescriptionFormProduct.setText(product.getDescription());
                        editPriceFormProduct.setText(String.valueOf(product.getPrice()));

                    }else{
                        Toast.makeText(getApplicationContext(),"No existe dato", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese id", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editIdProductForm.getText().toString().trim();
                if(id.compareTo("")!=0){
                    dbHelper.deleteDataById(id);
                    clean();
                }else{
                    Toast.makeText(getApplicationContext(),"No existe dato",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id= editIdProductForm.getText().toString().trim();
                if(id.compareTo("")!=0){
                   dbHelper.updateDataById(
                           id,
                           editNameFormProduct.getText().toString(),
                           editDescriptionFormProduct.getText().toString(),
                           editPriceFormProduct.getText().toString(),
                           productService.imageViewToByte(imgFormProduct)
                   );
                   clean();
                }

            }
        });



    }

    public void clean(){
        editNameFormProduct.setText("");
        editDescriptionFormProduct.setText("");
        editPriceFormProduct.setText("");
        editIdProductForm.setText("");
        imgFormProduct.setImageResource(R.drawable.form);
    }

    private boolean isEmpty(EditText textToCheck){

        if(textToCheck.getText().toString().trim().length()==0){
            return true;
        }else{
            return false;
        }
    }


}