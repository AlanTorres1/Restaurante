package edu.ipn.cecyt9.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Entrar(View Vista){
        Intent envia = new Intent(this, Main2Activity.class);
        finish();
        startActivity(envia);
    }

    public void btnInformacion(View Vista2){
        Intent envia2 = new Intent(this, Main5Activity.class);
        finish();
        startActivity(envia2);
    }
}
