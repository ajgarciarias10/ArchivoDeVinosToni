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
    /**
     * Inicializamos todos los componentes a utilizar en esta Actividad
     *
     *
     */
    Button bt_add;
    Button bt_edit;
    EditText et_id;
    TextView tv_lista;
    public static String fileName="vino.csv";
    public static ArrayList<Vino> listaVinos = new ArrayList<>();

    /**
     * Metodo OnCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        setTheList();//Ponemos en pantalla el campo de texto
        long id;
        try {
            id = Long.parseLong(et_id.getText().toString());
        }catch (NumberFormatException ignored){

        }



        /**
         * Evento OnClick del boton añadir
         */
        bt_add.setOnClickListener(v -> {
            Intent intencion = new Intent(MainActivity.this, Adding.class);
            startActivity(intencion);

        });

        /**
         * Evento OnClick del boton editar
         */
        bt_edit.setOnClickListener(v -> {
            if ((Filing.checkId(id, listaVinos))) {
                Intent intencion = new Intent(MainActivity.this, Edit.class);
                Bundle bn = new Bundle();
                bn.putLong("id", id);
                intencion.putExtras(bn); // lo manda con el put extras
                startActivity(intencion);
            } else {
                Snackbar.make(v, "Error,este id no existe", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void initialize() {
        bt_add = findViewById(R.id.bt_add);
        bt_edit = findViewById(R.id.bt_edit);
        et_id= findViewById(R.id.et_id);
        tv_lista = findViewById(R.id.tv_Lista);
    }
    public void setTheList(){
        tv_lista.setText(Filing.ReadFile(getFilesDir()));
    }








}