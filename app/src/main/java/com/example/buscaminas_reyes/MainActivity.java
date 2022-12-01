package com.example.buscaminas_reyes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Este código es para que la aplicación no se nos ponga en horizontal, ya que no podríamos jugar bien al juego.
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        this.alerta();
        TextView campo = new TextView(getApplicationContext());



    }

    public void alerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instrucciones");
        builder.setMessage("Para ganar debes seguir las reglas del buscaminas normal, es decir, intenta descubrir el tablero sin explotar ninguna mina!");
        builder.setPositiveButton("De Acuerdo!", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public boolean onClickConfig(MenuItem view){
    Context context = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Dificultad");
        builder.setItems(new CharSequence[]
                        {"8x8", "12x12", "16x16"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                Toast.makeText(context, "Dificultad elegida: 8x8", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(context, "Dificultad elegida: 12x12", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(context, "Dificultad elegida: 16x16", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
        builder.create().show();
        return true;
        };

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        findViewById(R.id.action_settings);

        return true;
    }

    }
