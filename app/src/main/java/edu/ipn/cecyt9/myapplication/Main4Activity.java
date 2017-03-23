package edu.ipn.cecyt9.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main4Activity extends Activity {

    String Nombre="", Apellido="", Numero="", Fecha="", Hora="";
    int Personas=0;
    TextView Muestrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Muestrame = (TextView) findViewById(R.id.Muestrame);

        Bundle recibe = new Bundle();
        recibe = this.getIntent().getExtras();

        Nombre = recibe.getString("Nombre");
        Apellido = recibe.getString("Apellido");
        Numero = recibe.getString("Numero");
        Fecha = recibe.getString("Fecha");
        Hora = recibe.getString("Hora");
        Personas = recibe.getInt("Personas");

        Muestrame.setText("Reservacion a nombre de: " + Nombre + Apellido + "\n" +
                "Numero de Telefono: " + Numero + "\n" +
                "Personas: " + Personas + "\n" +
                "Fecha: " + Fecha + "\n" +
                "Hora: " + Hora + "\n"
        );
    }

    public void btnOtraReservacion(View v){
        Intent envia = new Intent(this, Main2Activity.class);
        Bundle datosregresa = new Bundle();
        datosregresa.putString("Nombre", Nombre);
        datosregresa.putString("Apellido", Apellido);
        datosregresa.putString("Numero", Numero);
        datosregresa.putString("Fecha", Fecha);
        datosregresa.putString("Hora", Hora);
        datosregresa.putInt("Personas", Personas);
        envia.putExtras(datosregresa);
        finish();
        startActivity(envia);
    }
}