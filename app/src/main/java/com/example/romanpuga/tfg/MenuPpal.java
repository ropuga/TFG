package com.example.romanpuga.tfg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPpal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ppal);

        final SqlIO bd = new SqlIO(this);

        Button btn_medir = (Button) this.findViewById(R.id.btn_medir);
        Button btn_bluetooth = (Button) this.findViewById(R.id.btn_bluetooth);
        Button btn_server = (Button) this.findViewById(R.id.btn_server);

        // Texto introductorio.
        TextView intro = (TextView) this.findViewById(R.id.intro);
        intro.setText("¡Bienvenido " + getIntent().getExtras().getString("usuario") +
        "! Acabas de acceder a HERAA, tu aplicación para mantener un seguimiento de tu" +
        " frecuencia cardíaca. A continuación tienes las opciones disponibles. Deseamos" +
        " que tengas una experiencia agradable. ¡La salud es lo primero!");

        // TODO Clic en botón MedirFC, lanza la vista TomaDatos.
        btn_medir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPpal.this, TomaDatos.class);
                MenuPpal.this.startActivity(intent);
            }
        });

        // TODO Clic en botón Ajustes bluetooth, lanza la vista Dispositivo.
//        btn_bluetooth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MenuPpal.this, Dispositivo.class);
//                MenuPpal.this.startActivity(intent);
//            }
//        });

        // TODO realiza sincronización con el servidor
        btn_server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
