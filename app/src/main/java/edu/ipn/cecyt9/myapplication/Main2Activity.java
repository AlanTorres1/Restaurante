package edu.ipn.cecyt9.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Main2Activity extends Activity implements OnSeekBarChangeListener,
        OnClickListener, OnDateSetListener, OnTimeSetListener {

    EditText Nombre;
    EditText Apellido;
    EditText Numero;
    Button Fecha, Hora;
    SeekBar BarraPersonas;
    TextView CPersonas;
    SimpleDateFormat FFecha, FHora; //se le da el fomato a la fecha y hora
    String NumPersonas = "";
    String FechaSelect="", HoraSelect="";
    Date fechaConv;
    String cuantasPersonasFormat = "";
    int personas = 1;
    Calendar Calendario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle datosregresa = new Bundle();
        datosregresa = this.getIntent().getExtras();
        if(datosregresa !=null){

            TextView Jala;
            String NombreV;
            String ApellidoV;
            String NumeroV;
            String FechaV;
            String HoraV;
            int PersonasV;

            Jala = (TextView) findViewById(R.id.Jala);
            NombreV = datosregresa.getString("Nombre");
            ApellidoV = datosregresa.getString("Apellido");
            NumeroV = datosregresa.getString("Numero");
            FechaV = datosregresa.getString("Fecha");
            HoraV = datosregresa.getString("Hora");
            PersonasV = datosregresa.getInt("Personas");

            Jala.setText("Reservaciones Anteriores" + "\n" +
                    "Nombre: " + NombreV + ApellidoV + "\n" +
                    "Numero de Telefono: " + NumeroV + "\n" +
                    "Personas: " + PersonasV + "\n" +
                    "Fecha: " + FechaV + "\n" +
                    "Hora: " + HoraV + "\n"
            );
        }

        CPersonas = (TextView) findViewById(R.id.CPersonas);
        BarraPersonas = (SeekBar) findViewById(R.id.Personas);

        Fecha = (Button) findViewById(R.id.btnFecha);
        Hora = (Button) findViewById(R.id.btnHora);

        BarraPersonas.setOnSeekBarChangeListener(this);
        Nombre = (EditText) findViewById(R.id.txtNombre);
        Apellido = (EditText) findViewById(R.id.txtApellido);
        Numero = (EditText) findViewById(R.id.txtNum);

        cuantasPersonasFormat = CPersonas.getText().toString();
        CPersonas.setText("Personas: 1");

        Calendar fechaSeleccionada = Calendar.getInstance();
        fechaSeleccionada.set(Calendar.HOUR_OF_DAY, 12);
        fechaSeleccionada.clear(Calendar.MINUTE);
        fechaSeleccionada.clear(Calendar.SECOND);

        FFecha = new SimpleDateFormat(Fecha.getText().toString());
        FHora = new SimpleDateFormat("HH:mm");

        Date fechaReservacion = fechaSeleccionada.getTime();
        FechaSelect = FFecha.format(fechaReservacion);
        Fecha.setText(FechaSelect);

        HoraSelect = FHora.format(fechaReservacion);
        Hora.setText(HoraSelect);

        Fecha.setOnClickListener(this);
        Hora.setOnClickListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar barra, int progreso,
                                  boolean delUsuario) {
        NumPersonas = String.format(cuantasPersonasFormat,
                BarraPersonas.getProgress() + 1);
        personas = BarraPersonas.getProgress() + 1;
        CPersonas.setText(NumPersonas);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onClick(View v) {
        if(v == Fecha){
            Calendar Calendario = parseCalendar(Fecha.getText(), FFecha);
            new DatePickerDialog(this, this, Calendario.get(Calendar.YEAR),
                    Calendario.get(Calendar.MONTH),
                    Calendario.get(Calendar.DAY_OF_MONTH)).show();
        }else if (v == Hora){
            Calendar Calendario = parseCalendar(Hora.getText(), FHora);
            new TimePickerDialog(this, this,
                    Calendario.get(Calendar.HOUR_OF_DAY),
                    Calendario.get(Calendar.MINUTE), false).show();
        }
    }

    private Calendar parseCalendar(CharSequence text,
                                   SimpleDateFormat fechaFormat2) {
        try {
            fechaConv = fechaFormat2.parse(text.toString());
        } catch (ParseException e) { // import java.text.ParsedExc
            throw new RuntimeException(e);
        }
        Calendar Calendario = Calendar.getInstance();
        Calendario.setTime(fechaConv);
        return Calendario;
    }

    @Override
    public void onDateSet(DatePicker view, int anio, int mes, int dia) {
        Calendario = Calendar.getInstance();
        Calendario.set(Calendar.YEAR, anio);
        Calendario.set(Calendar.MONTH, mes);
        Calendario.set(Calendar.DAY_OF_MONTH, dia);

        FechaSelect = FFecha.format(Calendario.getTime());
        Fecha.setText(FechaSelect);
    }

    @Override
    public void onTimeSet(TimePicker view, int horas, int minutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, horas);
        calendar.set(Calendar.MINUTE, minutos);

        HoraSelect = FHora.format(calendar.getTime());
        Hora.setText(HoraSelect);
    }

    public void btnReservacion(View v){
        Intent envia = new Intent(this, Main4Activity.class);
        Bundle datos = new Bundle();
        datos.putString("Nombre", Nombre.getText().toString().trim());
        datos.putString("Apellido", Apellido.getText().toString().trim());
        datos.putString("Numero", Numero.getText().toString().trim());
        datos.putString("Fecha", Fecha.getText().toString().trim());
        datos.putString("Hora", Hora.getText().toString().trim());
        datos.putInt("Personas", personas);
        envia.putExtras(datos);
        finish();
        startActivity(envia);
    }
}
