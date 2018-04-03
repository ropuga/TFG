package com.example.romanpuga.tfg;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AutoCompleteTextView;
        import android.widget.Button;
        import android.widget.Toast;

        import java.util.List;

public class Main extends AppCompatActivity {

    public AutoCompleteTextView usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SqlIO bd = new SqlIO(this);

        Button btn_reg = (Button) this.findViewById(R.id.btn_registro);
        Button btn_log = (Button) this.findViewById(R.id.btn_login);
        usuario = (AutoCompleteTextView) this.findViewById(R.id.user_name);

        //Evento del boton ¡Regístrate!. Nos lanza a la clase Registro.
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Registro.class);
                Main.this.startActivity(intent);
            }
        });

        //Evento del botón ¡Entra!. Nos lanza a la clase MenuPpal o salta aviso de error.
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idUser = usuario.getText().toString();
                List<String> users = bd.getIdUsers();

                // Login correcto, abre menúPpal.
                if(users.contains(idUser)){
                    Intent intent = new Intent(Main.this, MenuPpal.class);
                    intent.putExtra("usuario",idUser);
                    Main.this.startActivity(intent);
                } else {
                    if (idUser.equals(""))
                        Toast.makeText(getApplicationContext(), "Introduce un Usuario", Toast.LENGTH_SHORT).show();
                    else  Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

