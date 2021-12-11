
package com.ieszv.ad.archivodevinostoni;

import static com.ieszv.ad.archivodevinostoni.Filing.checkIDAdd;
import static com.ieszv.ad.archivodevinostoni.Filing.deleteWine;
import static com.ieszv.ad.archivodevinostoni.Filing.isFileWritten;
import static com.ieszv.ad.archivodevinostoni.Filing.listaVinos;
import static com.ieszv.ad.archivodevinostoni.Filing.reWriteWine;
import static com.ieszv.ad.archivodevinostoni.Filing.readFileBooleano;
import static com.ieszv.ad.archivodevinostoni.R.id.et_GraduacionAdd;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Adding extends AppCompatActivity {
    Button btadd;
    EditText textoNombre, textoId,textoColor,textoGraduacion,textoOrigen,textFecha,textoBodega;
    private String fileName;

    /**
     * METODO ONCREATE
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initialize();
    }

    public void initialize() {
        btadd = findViewById(R.id.bt_add2);
        textoBodega = findViewById(R.id.et_bodegaAdd);
        textoNombre = findViewById(R.id.et_NombreAdd);
        textoId = findViewById(R.id.et_idAdd);
        textoColor = findViewById(R.id.et_ColorAdd);
        textoGraduacion = findViewById(et_GraduacionAdd);
        textoOrigen = findViewById(R.id.et_OrigenAdd);
        textFecha = findViewById(R.id.et_FechaAdd);
        //SACAMOS CON EL METODO GET STRING EL NOMBRE DEL ARCHIVO DE LA CLASE STRINGS.XML
        fileName = getString(R.string.arhivo);

        readFileBooleano(getFilesDir(),fileName);

        btadd.setOnClickListener((View v) ->{
            alertADD();

        });


    }
    private void alertADD() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setTitle(" ¿Are you sure to ADD it??")
                .setMessage("The operation has been done successfully")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton( android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Long idADD = Long.parseLong(textoId.getText().toString());//Cogemos el id a añadir
                        if (!checkIDAdd(idADD)) {//Lo comprobamos si esta dentro de las lista de Vinos
                            //En caso de que no lo este
                            Vino wine2 = new Vino(Long.parseLong(textoId.getText().toString()),
                                    textoNombre.getText().toString(),
                                    textoBodega.getText().toString(),
                                    textoColor.getText().toString(),
                                    textoOrigen.getText().toString(),
                                    Double.parseDouble(textoGraduacion.getText().toString()),
                                    Integer.parseInt(textFecha.getText().toString()));
                            //UTILIZAMOS EL METODO CSV que coje todos los campos de los getWines los separe por ; y lo escriba asi en el archivo
                            String winetoCsv = Csv.getCsv(wine2);
                            listaVinos.add(wine2);
                            Filing.writeFile(getFilesDir(),fileName,winetoCsv);
                            finish();
                        }else{
                            textoId.setError("This id exists");
                        }
                    }
                });
        builder.create().show();
    }



}






