package com.example.buscaminas_reyes;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int victoria = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Este código es para que la aplicación no se nos ponga en horizontal, ya que no podríamos jugar bien al juego.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        this.alerta();

    }

    public void alerta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Instrucciones");
        builder.setMessage("Para ganar debes seguir las reglas del buscaminas normal, es decir, intenta descubrir el tablero sin explotar ninguna mina!");
        builder.setPositiveButton("De Acuerdo!", null);


        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public boolean onClickConfig(MenuItem view) {
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
    }

    ;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        findViewById(R.id.action_settings);

        return true;
    }

    public void tablero(int tam) {
        Button[][] botones = new Button[tam][tam];
        TableLayout grid = findViewById(R.id.grid);
        grid.removeAllViews();

        for (int i = 0; i < tam; i++) {
            TableRow fila = new TableRow(this);
            fila.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    1.0f));

            for (int j = 0; j < tam; j++) {
                botones[i][j] = new Button(this);
                botones[i][j].setBackgroundColor(Color.WHITE);
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
                botones[i][j].setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        botones[finalI][finalJ].setText("\uD83D\uDEA9");
                        return true;
                    }
                });
                botones[i][j].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if ((botones[finalI][finalJ].getId() == -1)) {
                            Context context = getApplicationContext();
                            CharSequence text = "¡Qué pena! Has perdido";
                            int duration = Toast.LENGTH_LONG;
                            for (int i = 0; i < tam; i++) {

                                for (int j = 0; j < tam; j++) {
                                    if (botones[i][j].getId() == -1) botones[i][j].setText("💣");
                                }
                            }

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        } else if((botones[finalI][finalJ].getId() == -2)){
                            botones[finalI][finalJ].setBackgroundColor(Color.GRAY);
                            botones[finalI][finalJ].setTextColor(Color.RED);
                        } else if((botones[finalI][finalJ].getId() == 0)){
                            botones[finalI][finalJ].setId(-2);
                            try {

                                int cont = 0;

                                for (int i = -1; i < 1; i++) {

                                    for (int j = -1; i < 1; i++) {

                                        if ((botones[finalI+i][finalJ+j]).getId() == -1) {
                                            cont++;
                                        } else{

                                        }
                                    }
                                }

                                botones[finalI][finalJ].setText(String.valueOf(cont));

                            }catch(Exception e) {
                                e.printStackTrace();
                            }

                            botones[finalI][finalJ].setBackgroundColor(Color.GRAY);
                            botones[finalI][finalJ].setTextColor(Color.RED);

                          checkMinas(0, finalI + 1, finalJ, botones, tam);
                          checkMinas(0, finalI - 1, finalJ, botones, tam);
                          checkMinas(0, finalI , finalJ+ 1, botones, tam);
                          checkMinas(0, finalI , finalJ+1, botones, tam);



                        }



                        if (tam == 8) {
                            for (int i = 0; i < tam; i++) {

                                for (int j = 0; j < tam; j++) {

                                    if (botones[i][j].getId()==-1&&botones[i][j].getText()=="\uD83D\uDEA9") victoria++;

                                }

                            }

                            if (victoria == 10) {

                                LinearLayout ll1 = findViewById(R.id.linear1);
                                ll1.removeAllViewsInLayout();

                                Toast toast = Toast.makeText(getApplicationContext(), "Has ganado", 2);
                                toast.show();


                            }

                        } else if (tam == 12) {
                            for (int i = 0; i < tam; i++) {

                                for (int j = 0; j < tam; j++) {

                                    if (botones[i][j].getId()==-1&&botones[i][j].getText()=="\uD83D\uDEA9") victoria++;

                                }

                            }
                            if (victoria == 30) {

                                LinearLayout ll = findViewById(R.id.linear1);
                                ll.removeAllViewsInLayout();

                                Toast toast = Toast.makeText(getApplicationContext(), "Has ganado", 2);
                                toast.show();

                            }
                        } else if (tam == 16) {
                            for (int i = 0; i < tam; i++) {

                                for (int j = 0; j < tam; j++) {

                                    if (botones[i][j].getId()==-1&&botones[i][j].getText()=="\uD83D\uDEA9") victoria++;

                                }

                            }

                            if (victoria == 60) {

                                LinearLayout ll1 = findViewById(R.id.linear1);
                                ll1.removeAllViewsInLayout();

                                Toast toast = Toast.makeText(getApplicationContext(), "Has ganado", 2);
                                toast.show();

                            }
                        }

                    }
                });
                fila.addView(botones[i][j]);

            }

            grid.addView(fila);
        }
        if (tam == 8) {
            for (int i = 0; i < 10; i++) {
                Random r = new Random();
                int coordY = r.nextInt(((tam) - 1) + 1);
                int coordX = r.nextInt(((tam) - 1) + 1);

                botones[coordX][coordY].setId(-1);

            }
        } else if (tam == 12) {
            for (int i = 0; i < 30; i++) {
                Random r = new Random();
                int coordY = r.nextInt(((tam) - 1) + 1);
                int coordX = r.nextInt(((tam) - 1) + 1);

                botones[coordX][coordY].setId(-1);

            }
        } else if (tam == 16) {
            for (int i = 0; i < 60; i++) {
                Random r = new Random();
                int coordY = r.nextInt(((tam) - 1) + 1);
                int coordX = r.nextInt(((tam) - 1) + 1);

                botones[coordX][coordY].setId(-1);

            }
        }
    }

    private void checkMinas(int c, int x, int y, Button[][] botones, int tam) {

        int cont2 = 0;

        try {

            int cont = 0;

            for (int i = -1; i < 1; i++) {

                for (int j = -1; i < 1; i++) {

                    if ((botones[x+i][y+j]).getId() == -1) {
                        cont++;
                    } else{

                    }
                }
            }

            cont2 = cont;

        }catch(Exception e) {
            e.printStackTrace();
        }

        try {

                if (botones[x][y].getId() == 0 && botones[x][y].getText()!="\uD83D\uDEA9") {

                    botones[x][y].setText(String.valueOf(cont2));

                    botones[x][y].setBackgroundColor(Color.GRAY);
                    botones[x][y].setTextColor(Color.RED);

                    botones[x][y].setId(-2);

                    checkMinas(0, x + 1, y, botones, tam);
                    checkMinas(0, x - 1, y, botones, tam);
                    checkMinas(0, x , y+ 1, botones, tam);
                    checkMinas(0, x , y+ 1, botones, tam);



                } else {
                    return;
                }
            } catch (Exception e) {
                return;
            }
        return;
    }
}



