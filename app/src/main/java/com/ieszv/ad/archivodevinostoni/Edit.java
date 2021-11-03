package com.ieszv.ad.archivodevinostoni;

import static com.ieszv.ad.archivodevinostoni.MainActivity.fileName;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Edit extends AppCompatActivity {
    Button bt_edit2,bt_delete;
    TextView tv_id;
    EditText editTextNombre ,editTextBodega, editTextColor,editTextOrigen,editTextGraduacion,editTextFecha;
    String id;
    public static ArrayList<Vino> listaVinos = new ArrayList<>();
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
            initialize();




    }

    private void initialize() {
        editTextNombre=findViewById(R.id.et_Nombre);
        bt_edit2 = findViewById(R.id.bt_edit2);
        bt_delete = findViewById(R.id.bt_delete2);
        tv_id = findViewById(R.id.tv_id2);
        editTextBodega=findViewById(R.id.et_bodega);
        editTextColor = findViewById(R.id.et_origen);
        editTextGraduacion =findViewById(R.id.et_graduacion);
        editTextFecha = findViewById(R.id.et_fecha);
        editTextOrigen = findViewById(R.id.et_origen);
        Bundle bundle = getIntent().getExtras();
        id =bundle.getString("id");

        tv_id.setText(id);
        escribeEnElTv(bundle);
        Long idLong ;
        idLong = Long.parseLong(id);
        bt_edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    exChangeVinos(idLong,buscaVino(idLong));
                 if(writeFile(getFilesDir(),fileName)){
                     Intent intencion = new Intent(Edit.this, MainActivity.class);
                     startActivity(intencion);
                 }


            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(borrado(idLong)){
                    if(writeFile(getFilesDir(),fileName)){

                        Intent intencion = new Intent(Edit.this, MainActivity.class);
                        startActivity(intencion);
                    }


                }

            }
        });



    }
        public void escribeEnElTv(Bundle bundle){
            Long idLong ;
            idLong = Long.parseLong(id);
            Vino vino = buscaVino(idLong);
            editTextNombre.setText(vino.getNombre());
            editTextBodega.setText(vino.getBodega());
            editTextColor.setText(vino.getColor());
            editTextOrigen.setText(vino.getOrigen());
            editTextGraduacion.setText(String.valueOf(vino.getGraduacion()));
            editTextFecha.setText(vino.getFecha());
        }


    private void exChangeVinos(long id, Vino vinito){
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() ==  id){
                listaVinos.set(i, vinito);
            }
        }
    }
private boolean borrado(long id){
        boolean sehaBorrad;
        for (int i = 0; i < listaVinos.size(); i++) {
            if (listaVinos.get(i).getId() ==  id){
                listaVinos.remove(i);
               return sehaBorrad = true;

            }
        }
        return  sehaBorrad = false;
    }

    private Vino buscaVino(long idLong){
        Vino v = new Vino();
        for (Vino vinillos: listaVinos){
            long idToCompare;
            idToCompare = vinillos.getId();
            if(idToCompare == idLong){
                 v=vinillos;
            }

        }
        return  v;
    }
    public static boolean writeFile(File file, String string) {
        File f = new File(file, fileName);

        boolean ok = true;
        if (f.exists()){
            f.delete();
            f = new File(file,fileName);
            FileWriter fw = null;
            String csv;
            for (int i = 0; i <listaVinos.size() ; i++) {
                if(listaVinos.get(i).getId() != 0){
                    csv = Csv.getCsv(listaVinos.get(i));
                    try {

                        fw = new FileWriter(f, true);
                        fw.write(string);
                        fw.write(csv +"\n");
                        fw.flush();
                        fw.close();
                    } catch (IOException e) {
                        ok = false;
                }

                }
            }
        }

        return ok;
    }


}
