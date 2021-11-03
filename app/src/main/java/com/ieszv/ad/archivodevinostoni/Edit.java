package com.ieszv.ad.archivodevinostoni;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {
    Button bt_edit2,bt_delete;
    TextView tv_id;
    String id;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bt_edit2 = findViewById(R.id.bt_edit2);
        bt_delete = findViewById(R.id.bt_delete2);
        tv_id = findViewById(R.id.tv_id2);

        Bundle bundle = getIntent().getExtras();
        id =bundle.getString("id");
        tv_id.setText(id);
        bt_edit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intencion = new Intent(Edit.this, MainActivity.class);
                startActivity(intencion);

            }
        });

        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencion = new Intent(Edit.this, MainActivity.class);
                startActivity(intencion);
            }
        });

    }
}
