package com.ieszv.ad.archivodevinostoni;
import static com.ieszv.ad.archivodevinostoni.Filing.checkIDAdd;
import static com.ieszv.ad.archivodevinostoni.Filing.isFileCreated;
import static com.ieszv.ad.archivodevinostoni.Filing.listaVinos;
import static com.ieszv.ad.archivodevinostoni.Filing.readFile;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;


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
        tv_lista.setText(readFile(getFilesDir(),fileName));
        //We check if the file is created
        if(!isFileCreated(getFilesDir(),fileName)){
               File  file =  new File(getFilesDir(),fileName);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        if(readFile(getFilesDir(),fileName).isEmpty()){
            bt_edit.setEnabled(false);
        }
        else{
            bt_edit.setEnabled(true);
        }

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





}