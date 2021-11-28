package com.ieszv.ad.archivodevinostoni;
import static com.ieszv.ad.archivodevinostoni.FileIO.deleteWine;
import static com.ieszv.ad.archivodevinostoni.FileIO.listaVinos;
import static com.ieszv.ad.archivodevinostoni.FileIO.reWriteWine;
import static com.ieszv.ad.archivodevinostoni.FileIO.readFile;
import static com.ieszv.ad.archivodevinostoni.FileIO.searchWine;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Edit extends AppCompatActivity {
    private String fileName;
    Vino vino = new Vino();
    Button bt_rewrite,bt_delete;
    EditText editTextId, editTextNombre ,editTextBodega, editTextColor,editTextOrigen,editTextGraduacion,editTextFecha;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        fileName = getString(R.string.arhivo);
        readFile(getFilesDir(),fileName);
        //WE USE THE METHOD OF SETTING THE FIELD
        createField();
        initialize();
    }
    private void initialize() {
        Bundle bundle = getIntent().getExtras();
        String idText = bundle.getString("id");
       // Este intent es el que me ha llamado, entonces me da la informacion de la actividad q me ha llamado.
        editTextId = findViewById(R.id.et_idEdit);
        editTextNombre = findViewById(R.id.et_NombreEdit);
        editTextBodega = findViewById(R.id.et_BodegaEdit);
        editTextColor = findViewById(R.id.et_ColorEdit);
        editTextOrigen = findViewById(R.id.et_OrigenEdit);
        editTextGraduacion = findViewById(R.id.et_GraduacionEdit);
        editTextFecha  = findViewById(R.id.et_FechaEdit);

        bt_rewrite = findViewById(R.id.bt_Rewrite);
        bt_rewrite.setOnClickListener(v -> {
            Vino wine = new Vino(Long.parseLong(editTextId.getText().toString()),
                            editTextNombre.getText().toString(),editTextBodega.getText().toString(),editTextOrigen.getText().toString(),
                            editTextColor.getText().toString(),Double.parseDouble(editTextGraduacion.getText().toString()),Integer.parseInt(editTextFecha.getText().toString()));

            reWriteWine(Integer.parseInt(idText),wine);
            if(writeFile()){
                finish();
            }

        });

        bt_delete = findViewById(R.id.bt_Delete);
        bt_delete.setOnClickListener(v -> {
            deleteWine(Integer.parseInt(editTextId.getText().toString()));
            if(writeFile()){
                finish();
            }

        });

    }
    public void createField(){
        Bundle bundle = getIntent().getExtras();
        String idText = bundle.getString("id");
      //findVIEWBYID DE TODOS LOS CAMPOS DE LOS EDITTEXT
        editTextId = findViewById(R.id.et_idEdit);
        editTextNombre = findViewById(R.id.et_NombreEdit);
        editTextBodega = findViewById(R.id.et_BodegaEdit);
        editTextColor = findViewById(R.id.et_ColorEdit);
        editTextOrigen = findViewById(R.id.et_OrigenEdit);
        editTextGraduacion = findViewById(R.id.et_GraduacionEdit);
        editTextFecha  = findViewById(R.id.et_FechaEdit);
        //We setThe text about the textfield
        Vino wine = searchWine(Integer.parseInt(idText));
        editTextId.setText(idText);
        editTextNombre.setText(wine.getNombre());
        editTextBodega.setText(wine.getBodega());
        editTextColor.setText(wine.getColor());
        editTextOrigen.setText(wine.getOrigen());
        editTextGraduacion.setText(String.valueOf(wine.getGraduacion()));
        editTextFecha.setText(String.valueOf(wine.getFecha()));
    }
    public boolean writeFile(){
        File f = new File(getFilesDir(), fileName);
        boolean ok = true;
        if (f.exists()) {
            f.delete();
            f = new File(getFilesDir(), fileName);
            FileWriter fw = null; //FileWriter(File f,boolean append)
            String vcsv;
            for (int i = 0; i < listaVinos.size(); i++) {
                if (listaVinos.get(i).getId() != 0){
                    vcsv = Csv.getCsv(listaVinos.get(i));
                    try {
                        fw = new FileWriter(f, true);
                        fw.write( vcsv + "\n");
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





