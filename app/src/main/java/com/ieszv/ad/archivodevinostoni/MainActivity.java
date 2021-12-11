package com.ieszv.ad.archivodevinostoni;
import static com.ieszv.ad.archivodevinostoni.Filing.checkIDAdd;
import static com.ieszv.ad.archivodevinostoni.Filing.isFileCreated;
import static com.ieszv.ad.archivodevinostoni.Filing.listaVinos;
import static com.ieszv.ad.archivodevinostoni.Filing.readFile;
import static com.ieszv.ad.archivodevinostoni.Filing.readFileBooleano;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

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

            try {
                File  file =  new File(getFilesDir(),fileName);
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






        /**
         * Evento OnClick del boton editar
         */
        bt_edit.setOnClickListener(v -> {

                if(!et_idLong.getText().toString().isEmpty()){
                    boolean checking = checkIDAdd((Long.parseLong(et_idLong.getText().toString().trim())));
                    if(checking){
                        openEdit();
                        et_idLong.setText("");

                    }else{
                        et_idLong.setError("Error this id is not created");
                    }
                }
                else{
                    et_idLong.setError("ERROR NO HAS METIDO NINGUN ID");
                }



        });

    }

    @Override
    protected void onRestart() {
        tv_lista.setText(readFile(getFilesDir(),fileName));
       // readFileBooleano(getFilesDir(),fileName);
        super.onRestart();

    }

    @Override
    protected void onDestroy() {
       // readFileBooleano(getFilesDir(),fileName);
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //if(readFile(getFilesDir(),fileName).isEmpty() || readFile(getFilesDir(),fileName) == null){
        if(listaVinos.isEmpty() || listaVinos ==null ){
            bt_edit.setEnabled(false);
            et_idLong.setEnabled(false);
        }else {
            bt_edit.setEnabled(true);
            et_idLong.setEnabled(true);
        }
        et_idLong.setText("");
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