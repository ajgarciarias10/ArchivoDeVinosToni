
package com.ieszv.ad.archivodevinostoni;

import static com.ieszv.ad.archivodevinostoni.Filing.writeFile;
import static com.ieszv.ad.archivodevinostoni.MainActivity.fileName;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.ieszv.ad.archivodevinostoni.data.Vino;



public class Edit extends AppCompatActivity {
    Button bt_rewrite,bt_delete;
    EditText editTextId, editTextNombre ,editTextBodega, editTextColor,editTextOrigen,editTextGraduacion,editTextFecha;
    Long id;
    String idtv;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialize();

        Bundle bundle = getIntent().getExtras();
        id =bundle.getLong("id");
        idtv = String.valueOf(id);
        writeontheEditText(id,idtv);
        bt_rewrite.setOnClickListener(v -> {
            Filing.exChangeVinos(id,Filing.buscaVino(id));
            if(Filing.rewriteFile(getFilesDir(),fileName)){
                Intent intencion = new Intent(Edit.this, MainActivity.class);
                startActivity(intencion);
            }


        });

        bt_delete.setOnClickListener(v -> {
            if(Filing.borrado(id)){
                if(writeFile(getFilesDir(),fileName)){

                    Intent intencion = new Intent(Edit.this, MainActivity.class);
                    startActivity(intencion);
                }


            }

        });





}

    private void initialize() {
        editTextId = findViewById(R.id.et_idEdit);
        editTextNombre = findViewById(R.id.et_NombreEdit);
        editTextBodega = findViewById(R.id.et_BodegaEdit);
        editTextColor = findViewById(R.id.et_ColorEdit);
        editTextOrigen = findViewById(R.id.et_OrigenEdit);
        editTextGraduacion = findViewById(R.id.et_GraduacionEdit);
        editTextFecha  = findViewById(R.id.et_FechaEdit);

    }
    public void writeontheEditText(long id,String idtv){
        Vino vino = Filing.buscaVino(id);
        editTextId.setText(idtv);
        editTextNombre.setText(vino.getNombre());
        editTextBodega.setText(vino.getBodega());
        editTextColor.setText(vino.getColor());
        editTextOrigen.setText(vino.getOrigen());
        editTextGraduacion.setText(String.valueOf(vino.getGraduacion()));
        editTextFecha.setText(vino.getFecha());
    }




}

