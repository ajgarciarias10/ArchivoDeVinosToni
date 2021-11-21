package com.ieszv.ad.archivodevinostoni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.ieszv.ad.archivodevinostoni.data.Vino;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button bt_add;
    Button bt_edit;
    EditText et_id;
    TextView tv_lista;
    private  long id;
    String et;
    public static String fileName="vino.csv";
    public static ArrayList<Vino> listaVinos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        Filing.readFileArrayList(getFilesDir());

        tv_lista.setText(Filing.ReadFile(getFilesDir(),fileName));


        bt_add.setOnClickListener(v -> {
            Intent intencion = new Intent(MainActivity.this, Adding.class);
            startActivity(intencion);

        });
        bt_edit.setOnClickListener(v -> {
                if((Filing.checkId(id, listaVinos))){
                    Intent intencion = new Intent(MainActivity.this, Edit.class);
                    Bundle bn = new Bundle();
                    bn.putLong("id",id);
                    intencion.putExtras(bn); // lo manda con el put extras
                    startActivity(intencion);
                }else{
                    Snackbar.make(v, "Error,este id no existe",Snackbar.LENGTH_SHORT).show();
                }
        });
    }

    private void initialize() {
        bt_add = findViewById(R.id.bt_add2);
        bt_edit = findViewById(R.id.bt_edit);
        et_id= findViewById(R.id.et_id);
        tv_lista = findViewById(R.id.tv_Lista);
         et = String.valueOf(et_id.getText());
         id= Long.parseLong(et);
    }








}