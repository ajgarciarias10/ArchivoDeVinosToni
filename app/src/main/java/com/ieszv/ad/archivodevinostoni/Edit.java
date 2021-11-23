package com.ieszv.ad.archivodevinostoni;
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

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialize();

        long iddelBundle;

        Bundle bundle = getIntent().getExtras();
        iddelBundle = bundle.getLong("id");

        writeontheEditText(iddelBundle);

        bt_rewrite.setOnClickListener(v -> {
            Filing.exchangeTheWine(iddelBundle,Filing.searchTheWine(iddelBundle));
            if(Filing.rewritetheFile(getFilesDir(),fileName)){
                Intent intencion = new Intent(Edit.this, MainActivity.class);
                startActivity(intencion);
            }


        });

        bt_delete.setOnClickListener(v -> {
            if(Filing.deleteTheWine(iddelBundle)){
                if(Filing.rewritetheFile(getFilesDir(),fileName)){
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
        bt_rewrite = findViewById(R.id.bt_Rewrite);
        bt_delete = findViewById(R.id.bt_Delete);

    }

    public void writeontheEditText(long id){
        Vino vino = Filing.searchTheWine(id);
        String idtv;
        idtv = String.valueOf(id);
        editTextId.setText(idtv);
        editTextNombre.setText(vino.getNombre());
        editTextBodega.setText(vino.getBodega());
        editTextColor.setText(vino.getColor());
        editTextOrigen.setText(vino.getOrigen());
        editTextGraduacion.setText(String.valueOf(vino.getGraduacion()));
        editTextFecha.setText(String.valueOf(vino.getFecha()));
    }




}





