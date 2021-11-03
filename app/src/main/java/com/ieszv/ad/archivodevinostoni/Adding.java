package com.ieszv.ad.archivodevinostoni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Adding extends AppCompatActivity {
    EditText textoNombre, textoId,textoColor,textoGraduacion,textoOrigen,Fecha,textoBodega;
    Button bt_add;
    public static String fileName="vino.csv";
    public static ArrayList<Vino> listaVinos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        bt_add = findViewById(R.id.btadd);


        readFile();
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

        if(!checkId(id,listaVinos)){
            Vino v = new Vino(id,Nombre,Bodega,Color,Origen,graduacion,fecha);
            String text = Csv.getCsv(v);
            Vino v2 = Csv.getVino(text);
            writeFile(getFilesDir(), text);
        }

    }
    public static  boolean checkId(long id ,ArrayList<Vino>listaVinos){
        boolean ok=false;
        for (int i = 0; i < listaVinos.size(); i++) {
            Long idtoCompare = listaVinos.get(i).getId();
            if(idtoCompare.equals(id))
                return ok =  true;
        }
        return ok;

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
    public boolean readFile(){
        File f = new File(getFilesDir(), fileName);
        boolean ok = true;
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while ((linea = br.readLine()) != null) {
                Adding.listaVinos.add((Vino) Csv.getVino(linea));
            }
            br.close();
        } catch (Exception e){
            ok = false;
        }
        return ok;
    }

}
