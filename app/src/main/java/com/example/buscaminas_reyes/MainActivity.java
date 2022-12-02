package com.example.buscaminas_reyes;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Este código es para que la aplicación no se nos ponga en horizontal, ya que no podríamos jugar bien al juego.
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        this.alerta();

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
                       TableLayout grid = findViewById(R.id.grid);
                        switch (which) {
                            case 0:
                                Toast.makeText(context, "Dificultad elegida: 8x8", Toast.LENGTH_SHORT).show();

                                tablero(8);

                                break;
                            case 1:
                                Toast.makeText(context, "Dificultad elegida: 12x12", Toast.LENGTH_SHORT).show();
                                tablero(12);

                                break;

                            case 2:
                                Toast.makeText(context, "Dificultad elegida: 16x16", Toast.LENGTH_SHORT).show();
                                tablero(16);

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

    public void tablero(int tam){
        Button[][] botones = new Button[tam][tam];
        TableLayout grid = findViewById(R.id.grid);
        grid.removeAllViews();

        for (int i = 0; i <tam; i++){
            TableRow fila = new TableRow(this);
            fila.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            for (int j = 0; j <tam; j++){
                botones[i][j] = new Button(this);

                botones[i][j].setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                botones[i][j].setMinHeight(0);
                botones[i][j].setPadding(0, 0, 0, 0);
                botones[i][j].setMinWidth(0);
                botones[i][j].setText("");
                botones[i][j].setId(0);
                int finalI = i;
                int finalJ = j;
                botones[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if ((botones[finalI][finalJ].getId()==-1)){
                            Context context = getApplicationContext();
                            CharSequence text = "¡Qué pena! Has perdido";
                            int duration = Toast.LENGTH_LONG;
                            for (int i = 0; i <tam; i++){

                                for (int j = 0; j <tam; j++){
                                    if (botones[i][j].getId()==-1) botones[i][j].setText("💣");
                                }
                            }

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        } else {
                        checkMinas(0, finalI, finalJ, tam, botones);
                        }
                    }
                });
                fila.addView(botones[i][j]);

            }

            grid.addView(fila);
        }
        if (tam==8) {
            for (int i = 0; i<10; i++) {
                Random r = new Random();
                int coordY = r.nextInt(((tam)-1)+1);
                int coordX = r.nextInt(((tam)-1)+1);

                botones[coordX][coordY].setId(-1);
            }
        } else if (tam==12) {
            for (int i = 0; i<30; i++) {
                Random r = new Random();
                int coordY = r.nextInt(((tam)-1)+1);
                int coordX = r.nextInt(((tam)-1)+1);

                botones[coordX][coordY].setId(-1);
            }
        } else if (tam==16) {
            for (int i = 0; i<60; i++) {
                Random r = new Random();
                int coordY = r.nextInt(((tam)-1)+1);
                int coordX = r.nextInt(((tam)-1)+1);

                botones[coordX][coordY].setId(-1);
            }
        }
    }

    public void checkMinas(int conteoBoom, int finalI, int finalJ, int tam, Button[][] botones){
        int nuevaCordX = 0;
        int nuevaCordY = 0;
        if (conteoBoom!=8) {
            try {
                if (botones[finalI][finalJ].getId() == -1) {

                    conteoBoom++;

                } else {
                    if (conteoBoom==1){
                         nuevaCordX = finalI - 1;
                         nuevaCordY = finalJ - 1;
                    } else if (conteoBoom==2){
                         nuevaCordX = finalI;
                         nuevaCordY = finalJ - 1;
                    } else if (conteoBoom==3){
                         nuevaCordX = finalI + 1;
                         nuevaCordY = finalJ - 1;
                    } else if (conteoBoom==4) {
                         nuevaCordX = finalI - 1;
                         nuevaCordY = finalJ;
                    } else if (conteoBoom==5) {
                         nuevaCordX = finalI + 1;
                         nuevaCordY = finalJ;
                    } else if (conteoBoom==6) {
                        nuevaCordX = finalI - 1;
                        nuevaCordY = finalJ + 1;
                    } else if (conteoBoom==7) {
                        nuevaCordX = finalI ;
                        nuevaCordY = finalJ + 1;
                    } else {
                        nuevaCordX = finalI + 1;
                        nuevaCordY = finalJ + 1;
                    }
                    checkMinas(conteoBoom, nuevaCordX, nuevaCordY, tam, botones);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
                botones[finalI][finalJ].setText(String.valueOf(conteoBoom));
                conteoBoom = 0;

        }
    }

