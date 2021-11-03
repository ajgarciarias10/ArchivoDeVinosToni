package com.ieszv.ad.archivodevinostoni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Adding extends AppCompatActivity {
    EditText textoNombre, textoId,textoColor,textoGraduacion,textoOrigen,Fecha,textoBodega;
    Button bt_add;
    public static String fileName="vino.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bt_add = findViewById(R.id.btadd);



        bt_add.setOnClickListener((View v) ->{
            WriteIntheFile();
            Intent intencion = new Intent(Adding.this, MainActivity.class);
            startActivity(intencion);


        });
    }


    public void WriteIntheFile(){
        textoBodega = findViewById(R.id.et_bodega);
        textoNombre = findViewById(R.id.et_Nombre);
        textoId = findViewById(R.id.et_id);
        textoColor = findViewById(R.id.et_color);
        textoGraduacion = findViewById(R.id.et_graduacion);
        textoOrigen = findViewById(R.id.et_origen);
        Fecha = findViewById(R.id.et_fecha);
        String Bodega, Nombre,Color,Origen;
        long id;
        double graduacion;
        int fecha;
        Bodega =  textoBodega.getText().toString();
        Nombre = textoNombre.getText().toString();
        Color = textoColor.getText().toString();
        Origen = textoOrigen.getText().toString();
        id= Long.parseLong(textoId.getText().toString());
        fecha = Integer.parseInt(Fecha.getText().toString());
        graduacion= Double.parseDouble(textoGraduacion.getText().toString());


        Vino v = new Vino(id,Nombre,Bodega,Color,Origen,graduacion,fecha);
        String text = Csv.getCsv(v);
        Vino v2 = Csv.getVino(text);
        writeFile(getFilesDir(), text);
    }

    public static boolean writeFile(File file, String string) {
        File f = new File(file, fileName);
        FileWriter fw = null;
        boolean ok = true;
        try {
            fw = new FileWriter(f, true);
            fw.write(string);
            fw.write("\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            ok = false;
        }
        return ok;
    }
}
