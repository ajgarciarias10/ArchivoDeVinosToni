package com.ieszv.ad.archivodevinostoni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    Button bt_add;
    Button bt_edit;
    EditText et_Elid;
    TextView tv_lista;
    public static String fileName="vino.csv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_add = findViewById(R.id.bt_add);
        bt_edit = findViewById(R.id.bt_edit);
        et_Elid= findViewById(R.id.et_id);
        tv_lista = findViewById(R.id.tv_Lista);



        tv_lista.setText(readFile(getFilesDir(),fileName));
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(MainActivity.this, Adding.class);
                startActivity(intencion);

            }
        });
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(MainActivity.this, Edit.class);
                Bundle bn = new Bundle();
                bn.putString("id",et_Elid.getText().toString());
                intencion.putExtras(bn); // lo manda con el put extras
                startActivity(intencion);
            }
        });
    }


    public static String readFile(File file, String fileName) {
        File f = new File(file, fileName);
        String texto = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
        } catch (IOException e) {
            texto = null;
        }
        return texto;
    }






}