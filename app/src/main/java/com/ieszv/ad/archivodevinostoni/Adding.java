
package com.ieszv.ad.archivodevinostoni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.util.ArrayList;

public class Adding extends AppCompatActivity {
    EditText textoNombre, textoId,textoColor,textoGraduacion,textoOrigen,Fecha,textoBodega;
    Button bt_add;
    public static ArrayList<Vino> listaVinos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initialize();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Filing.readFileArrayList(getFilesDir());
        bt_add.setOnClickListener((View v) ->{
            WriteIntheFile();
            Intent intencion = new Intent(Adding.this, MainActivity.class);
            startActivity(intencion);


        });

    }

    private void initialize() {
        bt_add = findViewById(R.id.bt_add2);
        textoBodega = findViewById(R.id.et_bodegaAdd);
        textoNombre = findViewById(R.id.et_NombreAdd);
        textoId = findViewById(R.id.et_idAdd);
        textoColor = findViewById(R.id.et_ColorAdd);
        textoGraduacion = findViewById(R.id.et_GraduacionAdd);
        textoOrigen = findViewById(R.id.et_OrigenAdd);
        Fecha = findViewById(R.id.et_FechaAdd);
    }


    public void WriteIntheFile(){
        long id = Long.parseLong(String.valueOf(textoId.getText()));
        String bodega = String.valueOf(textoBodega.getText());
        String nombre = String.valueOf(textoNombre.getText());
        String color = String.valueOf(textoColor.getText());
        double graduacion = Double.parseDouble(String.valueOf(textoGraduacion));
        String origen = String.valueOf(textoOrigen);
        int fecha = Integer.parseInt(String.valueOf(Fecha));


        if(Filing.checkId(id,listaVinos)){
                Vino v = new Vino(id, nombre, bodega, color, origen, graduacion, fecha);
                String text = Csv.getCsv(v);
                Filing.writeFile(getFilesDir(), text);
        }
        else{
            textoId.setError("No puedes meter el mismo id");
        }

    }




}