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



public class Activity3x3 extends AppCompatActivity {

    AdView adView;
    EditText unoUno,unoDue,unoTre,dueUno,dueDue,dueTre,treUno,treDue,treTre;
    Button determinante,inversa,trasposta,rango;
    String matrixString[] = new String[9];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3x3);


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
        unoUno = (EditText) findViewById(R.id.treXtre_11);
        unoDue = (EditText) findViewById(R.id.treXtre_12);
        unoTre = (EditText) findViewById(R.id.treXtre_13);
        dueUno = (EditText) findViewById(R.id.treXtre_21);
        dueDue = (EditText) findViewById(R.id.treXtre_22);
        dueTre = (EditText) findViewById(R.id.treXtre_23);
        treUno = (EditText) findViewById(R.id.treXtre_31);
        treDue = (EditText) findViewById(R.id.treXtre_32);
        treTre = (EditText) findViewById(R.id.treXtre_33);

        this.determinante = (Button) findViewById(R.id.buttonDeterminant_3x3);
        this.inversa = (Button) findViewById(R.id.inversaButton3x3);
        this.trasposta = (Button) findViewById(R.id.TraspostaButton3x3);
        this.rango = (Button) findViewById(R.id.rangoButton_3x3);


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
                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),},
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
                            Toast.makeText(getApplicationContext(), "INPUT ERRATO", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),},
                    };
                    double inversa [][] = MatrixCalcolator.invertMatrix(matrice);
                    unoUno.setText(String.valueOf(inversa[0][0]));
                    unoDue.setText(String.valueOf(inversa[0][1]));
                    unoTre.setText(String.valueOf(inversa[0][2]));
                    dueUno.setText(String.valueOf(inversa[1][0]));
                    dueDue.setText(String.valueOf(inversa[1][1]));
                    dueTre.setText(String.valueOf(inversa[1][2]));
                    treUno.setText(String.valueOf(inversa[2][0]));
                    treDue.setText(String.valueOf(inversa[2][1]));
                    treTre.setText(String.valueOf(inversa[2][2]));
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

                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),},
                    };
                    double trasposta [][] = MatrixCalcolator.transposeMatrix(matrice);
                    unoUno.setText(String.valueOf(trasposta[0][0]));
                    unoDue.setText(String.valueOf(trasposta[0][1]));
                    unoTre.setText(String.valueOf(trasposta[0][2]));
                    dueUno.setText(String.valueOf(trasposta[1][0]));
                    dueDue.setText(String.valueOf(trasposta[1][1]));
                    dueTre.setText(String.valueOf(trasposta[1][2]));
                    treUno.setText(String.valueOf(trasposta[2][0]));
                    treDue.setText(String.valueOf(trasposta[2][1]));
                    treTre.setText(String.valueOf(trasposta[2][2]));




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

                    double matrice [][] = {
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),},
                    };
                    double rango = MatrixCalcolator.calcoloDelRango(matrice,3);
                    showAlertDialog("Rango",String.valueOf(rango));
                }

            }
        });

    }

    public boolean controlloTesto()
    {


        if (unoUno.getText().length() > 0 && unoDue.getText().length()>0  && unoTre.getText().length()>0 &&
                dueUno.getText().length() > 0 && dueDue.getText().length() > 0 && dueTre.getText().length() > 0 &&
                treUno.getText().length() > 0 && treDue.getText().length() > 0 && treTre.getText().length() > 0)
        {
            matrixString[0] = unoUno.getText().toString();
            matrixString[1] = unoDue.getText().toString();
            matrixString[2] = unoTre.getText().toString();
            matrixString[3] = dueUno.getText().toString();
            matrixString[4] = dueDue.getText().toString();
            matrixString[5] = dueTre.getText().toString();
            matrixString[6] = treUno.getText().toString();
            matrixString[7] = treDue.getText().toString();
            matrixString[8] = treTre.getText().toString();
            return true;


        }else
        {
            Toast.makeText(getApplicationContext(),"INPUT ERRATO",Toast.LENGTH_LONG).show();
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
        AlertDialog alertDialog = new AlertDialog.Builder(Activity3x3.this).create();
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
