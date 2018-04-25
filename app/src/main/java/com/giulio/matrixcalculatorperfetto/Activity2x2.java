package com.giulio.matrixcalculatorperfetto;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by giulio on 04/03/18.
 */

public class Activity2x2 extends AppCompatActivity {

    AdView adView;
    EditText unoUno,unoDue,dueUno,dueDue;
    Button determinante,inversa,trasposta,rango;
    String matrixString[] = new String[4];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2x2);

        //Connetto la view ad admob
        admobConnect();

        //Collego gli elementi della view e riempio matrix
        connectView();

    }




    /*
    * Avendo utilizzato una tastiera di tipo numericDecimal
    * non posso inserire lettere e neanche doppi punti quindi
    * devo solo controllare che non venga inserito un punto
    * all'inizio o alla fine del numero (.45324 oppure 3244.)
    * in tal caso do un messaggio di errore
    * */
    private boolean chekCorrectlyInput(String string)
    {

        if(string.length() > 0) {
            if (string.charAt(0) == '.' || string.charAt(string.length()-1) == '.') {
                return false;
            }
        }

        return true;
    }

    /*
    * Collego gli elementi della view
    */
    private void connectView()
    {

        //collego gli edit text
        unoUno = (EditText) findViewById(R.id.editText11);

        unoDue = (EditText) findViewById(R.id.editText12);

        dueUno = (EditText) findViewById(R.id.editText21);

        dueDue = (EditText) findViewById(R.id.editText22);

        this.determinante = (Button) findViewById(R.id.buttonDeterminant);
        this.inversa = (Button) findViewById(R.id.inversaButton);
        this.trasposta = (Button) findViewById(R.id.TraspostaButton);
        this.rango = (Button) findViewById(R.id.rangoButton);


        determinante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Controllo se sono tutti numeri altrimenti mostro un tost
                if(controlloTesto()) {

                    for (int i = 0; i < matrixString.length; i++) {
                        if (!chekCorrectlyInput(matrixString[i])) {
                            Toast.makeText(getApplicationContext(), "INPUT ERRATO", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    // SE SONO QUI ALLORA sono solo numeri e posso fare calcoli
                    PrintMessage.stampoMessaggio("SONO QUI");
                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()),},
                    };


                    double determinante = MatrixCalcolator.calcoloDeterminante(matrice);
                    PrintMessage.stampoMessaggio("il determinante è " + String.valueOf(determinante));
                    showAlertDialog("Determinante",String.valueOf(determinante));


                }


            }
        });

        inversa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Controllo se sono tutti numeri altrimenti mostro un tost
                if(controlloTesto()) {

                    for (int i = 0; i < matrixString.length; i++) {
                        if (!chekCorrectlyInput(matrixString[i])) {
                            PrintMessage.stampoMessaggio("INPUT ERRATO");
                            Toast.makeText(getApplicationContext(), "INPUT ERRATO", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    // SE SONO QUI ALLORA sono solo numeri e posso fare calcoli
                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()),},
                    };


                    double[][] inversa = MatrixCalcolator.invertMatrix(matrice);
                    unoUno.setText(String.valueOf(inversa[0][0]));
                    unoDue.setText(String.valueOf(inversa[0][1]));
                    dueUno.setText(String.valueOf(inversa[1][0]));
                    dueDue.setText(String.valueOf(inversa[1][1]));


                }



            }
        });

        trasposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Controllo se sono tutti numeri altrimenti mostro un tost
                if(controlloTesto()) {

                    for (int i = 0; i < matrixString.length; i++) {
                        if (!chekCorrectlyInput(matrixString[i])) {
                            Toast.makeText(getApplicationContext(), "INPUT ERRATO", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    // SE SONO QUI ALLORA sono solo numeri e posso fare calcoli
                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()),},
                    };

                    double trasposta [][] = MatrixCalcolator.transposeMatrix(matrice);
                    unoUno.setText(String.valueOf(trasposta[0][0]));
                    unoDue.setText(String.valueOf(trasposta[0][1]));
                    dueUno.setText(String.valueOf(trasposta[1][0]));
                    dueDue.setText(String.valueOf(trasposta[1][1]));


                }



            }
        });

        rango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Controllo se sono tutti numeri altrimenti mostro un tost
                if(controlloTesto()) {

                    for (int i = 0; i < matrixString.length; i++) {
                        if (!chekCorrectlyInput(matrixString[i])) {
                            Toast.makeText(getApplicationContext(), "INPUT ERRATO", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    // SE SONO QUI ALLORA sono solo numeri e posso fare calcoli
                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()),},
                    };
                    double rango = MatrixCalcolator.calcoloDelRango(matrice,2);
                    showAlertDialog("Rango",String.valueOf(rango));





                }
            }
        });

    }

    public boolean controlloTesto()
    {


        if (unoUno.getText().toString().length() > 0 && unoDue.getText().toString().length()>0  && dueUno.getText().toString().length() > 0 && dueDue.getText().toString().length() > 0  )
        {
            matrixString[0] = unoUno.getText().toString();
            matrixString[1] = unoDue.getText().toString();
            matrixString[2] = dueUno.getText().toString();
            matrixString[3] = dueDue.getText().toString();
            PrintMessage.stampoMessaggio("INPUT CORRETTO");
            return true;


        }else
        {
            PrintMessage.stampoMessaggio("INPUT ERRATOoo");
            return false;
        }

    }

    private void admobConnect()
    {

        PrintMessage.stampoMessaggio("Activity aperta: Activity2x2");

        MobileAds.initialize(this,"ca-app-pub-7224624747847283/6227792156");
        adView = (AdView) findViewById(R.id.adView);


        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);


    }

    public void showAlertDialog(String titolo,String messaggio)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Activity2x2.this).create();
        alertDialog.setTitle(titolo);
        alertDialog.setMessage(messaggio);
        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"OK",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Se volessi fare qualcosa alla pressione del pulsante
            }
        });

        alertDialog.show();

    }
}
