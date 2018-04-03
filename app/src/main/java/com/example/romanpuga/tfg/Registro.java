package com.example.romanpuga.tfg;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class Registro extends AppCompatActivity {

    public EditText user;
    public EditText name;
    public RadioGroup radioGroup;
    public RadioButton female;
    public RadioButton male;
    public DatePicker Bdate;
    public Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final SqlIO bd = new SqlIO(this);

        user = (EditText) this.findViewById(R.id.usuario);
        name = (EditText) this.findViewById(R.id.nombre);
        radioGroup = (RadioGroup) this.findViewById(R.id.opSex);
        female = (RadioButton) this.findViewById(R.id.radio_mujer);
        male = (RadioButton) this.findViewById(R.id.radio_hombre);
        Bdate = (DatePicker) this.findViewById(R.id.datePicker);
        registrar = (Button) this.findViewById(R.id.btn_reg);


        registrar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String usuario = user.getText().toString();
                String nombre = name.getText().toString();

                String sexo = "";

                if (male.isChecked())
                    sexo = "hombre";
                else
                    sexo = "mujer";

                int day = Bdate.getDayOfMonth();
                int month = Bdate.getMonth();
                int year = Bdate.getYear();

                // Concatenar los datos de la fecha en un String.
                String fechaN = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);

                // Datos recogidos, se comprueba su validez.
                List<String> usuarios = bd.getIdUsers();
                if (usuarios.contains(usuario))
                    Toast.makeText(getApplicationContext(), "Usuario existente, introduce uno que no esté registrado.", Toast.LENGTH_SHORT).show();
                else if (usuario.equals(""))
                    Toast.makeText(getApplicationContext(), "Rellene el campo 'Usuario'", Toast.LENGTH_SHORT).show();
                else if (nombre.equals(""))
                    Toast.makeText(getApplicationContext(), "Rellene el campo 'Nombre'", Toast.LENGTH_SHORT).show();
                else if (!male.isChecked() && !female.isChecked())
                    Toast.makeText(getApplicationContext(), "Seleccione un sexo", Toast.LENGTH_SHORT).show();
                else if (!checkDate(day, month, year))
                    Toast.makeText(getApplicationContext(), "Seleccione una fecha correcta", Toast.LENGTH_SHORT).show();
                else
                    bd.insertUser(usuario, nombre, sexo, fechaN);
                Toast.makeText(getApplicationContext(), "Usuario " + usuario + " registrado correctamente", Toast.LENGTH_SHORT).show();
                Registro.this.finish();
            }


        });
    }


    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }


    // Fecha introducida correcta?
    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean checkDate(int day, int month, int year){

        Calendar c = Calendar.getInstance();
        int toDay = c.get(Calendar.DAY_OF_MONTH);
        int toMonth = c.get(Calendar.MONTH) + 1;
        int toYear = c.get(Calendar.YEAR);

        boolean fechaOK = false;

        if (toYear > year)
            fechaOK = true;
        else if (toYear == year)
            if (toMonth > month)
                fechaOK = true;
            else if (toMonth == month)
                if (toDay > day)
                    fechaOK = true;


        return fechaOK;
    }
}
