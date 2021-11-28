package com.ieszv.ad.archivodevinostoni;
import static com.ieszv.ad.archivodevinostoni.FileIO.isFileCreated;
import static com.ieszv.ad.archivodevinostoni.FileIO.listaVinos;
import static com.ieszv.ad.archivodevinostoni.FileIO.readFile;
import static com.ieszv.ad.archivodevinostoni.FileIO.writeFile;
import static com.ieszv.ad.archivodevinostoni.FileIO.writeFileEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ieszv.ad.archivodevinostoni.data.Vino;
import com.ieszv.ad.archivodevinostoni.util.Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    /**
     * Inicializamos todos los componentes a utilizar en esta Actividad
     *
     *
     */
    private String fileName;
    Button bt_add;
    Button bt_edit;
    EditText et_idLong;
    TextView tv_lista;
    /**
     * Metodo OnCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }


    private void initialize() {
        fileName = getString(R.string.arhivo);
        bt_add = findViewById(R.id.bt_add);
        bt_edit = findViewById(R.id.bt_edit);
        tv_lista = findViewById(R.id.tv_Lista);
        et_idLong = findViewById(R.id.et_id);
        //We check if the file is created
        if(!isFileCreated(getFilesDir(),fileName)){
            writeFileEmpty(getFilesDir(),fileName);
        }
        else {
            isFileCreated(getFilesDir(),fileName);//Metemos el archivo en un arraylist
        }
        /**
         * Evento OnClick del boton añadir
         */
        bt_add.setOnClickListener(v -> {
                openAdding();
        });


        /**
         * Evento OnClick del boton editar
         */
        bt_edit.setOnClickListener(v -> {
            boolean checking = checkIDAdd((Long.parseLong(et_idLong.getText().toString().trim())));
            if(checking){
               openEdit();

            }else{
                et_idLong.setText("Error this id is not created");
            }
        });
        tv_lista.setText(readFile(getFilesDir(),fileName));
    }

    @Override
    protected void onRestart() {
        tv_lista.setText(readFile(getFilesDir(),fileName));
        super.onRestart();
    }

    private void openAdding() {
        Intent intent = new Intent(this, Adding.class);
        startActivity(intent);
    }
    private void openEdit() {
        Intent intent = new Intent(this, Edit.class);
        intent.putExtra("id",et_idLong.getText().toString());
        startActivity(intent);
    }

    public  boolean checkIDAdd(long id){
        boolean x = false;
        if (listaVinos.size() > 0) {
            for (int i = 0; i < listaVinos.size(); i++) {
                if(listaVinos.get(i) != null){
                    if(listaVinos.get(i).getClass().getSimpleName().equals("Vino")){
                        Long idToCompare = listaVinos.get(i).getId();
                        if (idToCompare.equals(id)) {
                            x = true;
                        }
                    }
                }


            }
        }
        return x;
    }



}