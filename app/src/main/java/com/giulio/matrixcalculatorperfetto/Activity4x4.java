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
 * Created by giulio on 07/03/18.
 */

public class Activity4x4 extends AppCompatActivity {

    AdView adView;
    EditText unoUno,unoDue,unoTre,unoQuattro,
            dueUno,dueDue,dueTre,dueQuattro,
            treUno,treDue,treTre,treQuattro,
            quattroUno,quattroDue,quattroTre,quattroQuattro;
    Button determinante,inversa,trasposta,rango;
    String matrixString[] = new String[16];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4x4);


        //Connetto la view ad admob
        admobConnect();

        //Collego gli elementi della view e riempio matrix
        //connectView();
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
        unoUno = (EditText) findViewById(R.id.quattroXquattro11);
        unoDue = (EditText) findViewById(R.id.quattroXquattro12);
        unoTre = (EditText) findViewById(R.id.quattroXquattro13);
        unoQuattro = (EditText) findViewById(R.id.quattroXquattro14);

        dueUno = (EditText) findViewById(R.id.quattroXquattro21);
        dueDue = (EditText) findViewById(R.id.quattroXquattro22);
        dueTre = (EditText) findViewById(R.id.quattroXquattro23);
        dueQuattro = (EditText) findViewById(R.id.quattroXquattro24);


        treUno = (EditText) findViewById(R.id.quattroXquattro31);
        treDue = (EditText) findViewById(R.id.quattroXquattro32);
        treTre = (EditText) findViewById(R.id.quattroXquattro33);
        treQuattro = (EditText) findViewById(R.id.quattroXquattro34);


        quattroUno = (EditText) findViewById(R.id.quattroXquattro41);
        quattroDue = (EditText) findViewById(R.id.quattroXquattro42);
        quattroTre = (EditText) findViewById(R.id.quattroXquattro43);
        quattroQuattro = (EditText) findViewById(R.id.quattroXquattro44);




        this.determinante = (Button) findViewById(R.id.bottoneD);
        this.inversa = (Button) findViewById(R.id.BottoneI);
        this.trasposta = (Button) findViewById(R.id.BottoneT);
        this.rango = (Button) findViewById(R.id.BottoneR);


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
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),Double.valueOf(unoQuattro.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),Double.valueOf(dueQuattro.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),Double.valueOf(treQuattro.getText().toString()),},
                            {Double.valueOf(quattroUno.getText().toString()),Double.valueOf(quattroDue.getText().toString()), Double.valueOf(quattroTre.getText().toString()),Double.valueOf(quattroQuattro.getText().toString()),},
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
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),Double.valueOf(unoQuattro.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),Double.valueOf(dueQuattro.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),Double.valueOf(treQuattro.getText().toString()),},
                            {Double.valueOf(quattroUno.getText().toString()),Double.valueOf(quattroDue.getText().toString()), Double.valueOf(quattroTre.getText().toString()),Double.valueOf(quattroQuattro.getText().toString()),},
                    };
                    double inversa [][] = MatrixCalcolator.invertMatrix(matrice);

                    unoUno.setText(String.valueOf(inversa[0][0]));
                    unoDue.setText(String.valueOf(inversa[0][1]));
                    unoTre.setText(String.valueOf(inversa[0][2]));
                    unoQuattro.setText(String.valueOf(inversa[0][3]));

                    dueUno.setText(String.valueOf(inversa[1][0]));
                    dueDue.setText(String.valueOf(inversa[1][1]));
                    dueTre.setText(String.valueOf(inversa[1][2]));
                    dueQuattro.setText(String.valueOf(inversa[1][3]));

                    treUno.setText(String.valueOf(inversa[2][0]));
                    treDue.setText(String.valueOf(inversa[2][1]));
                    treTre.setText(String.valueOf(inversa[2][2]));
                    treQuattro.setText(String.valueOf(inversa[2][3]));

                    quattroUno.setText(String.valueOf(inversa[3][0]));
                    quattroDue.setText(String.valueOf(inversa[3][1]));
                    quattroTre.setText(String.valueOf(inversa[3][2]));
                    quattroQuattro.setText(String.valueOf(inversa[3][3]));
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
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),Double.valueOf(unoQuattro.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),Double.valueOf(dueQuattro.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),Double.valueOf(treQuattro.getText().toString()),},
                            {Double.valueOf(quattroUno.getText().toString()),Double.valueOf(quattroDue.getText().toString()), Double.valueOf(quattroTre.getText().toString()),Double.valueOf(quattroQuattro.getText().toString()),},
                    };
                    double trasposta [][] = MatrixCalcolator.transposeMatrix(matrice);

                    unoUno.setText(String.valueOf(trasposta[0][0]));
                    unoDue.setText(String.valueOf(trasposta[0][1]));
                    unoTre.setText(String.valueOf(trasposta[0][2]));
                    unoQuattro.setText(String.valueOf(trasposta[0][3]));

                    dueUno.setText(String.valueOf(trasposta[1][0]));
                    dueDue.setText(String.valueOf(trasposta[1][1]));
                    dueTre.setText(String.valueOf(trasposta[1][2]));
                    dueQuattro.setText(String.valueOf(trasposta[1][3]));

                    treUno.setText(String.valueOf(trasposta[2][0]));
                    treDue.setText(String.valueOf(trasposta[2][1]));
                    treTre.setText(String.valueOf(trasposta[2][2]));
                    treQuattro.setText(String.valueOf(trasposta[2][3]));

                    quattroUno.setText(String.valueOf(trasposta[3][0]));
                    quattroDue.setText(String.valueOf(trasposta[3][1]));
                    quattroTre.setText(String.valueOf(trasposta[3][2]));
                    quattroQuattro.setText(String.valueOf(trasposta[3][3]));




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
                            {Double.valueOf(unoUno.getText().toString()), Double.valueOf(unoDue.getText().toString()), Double.valueOf(unoTre.getText().toString()),Double.valueOf(unoQuattro.getText().toString()),},
                            {Double.valueOf(dueUno.getText().toString()),Double.valueOf(dueDue.getText().toString()), Double.valueOf(dueTre.getText().toString()),Double.valueOf(dueQuattro.getText().toString()),},
                            {Double.valueOf(treUno.getText().toString()),Double.valueOf(treDue.getText().toString()), Double.valueOf(treTre.getText().toString()),Double.valueOf(treQuattro.getText().toString()),},
                            {Double.valueOf(quattroUno.getText().toString()),Double.valueOf(quattroDue.getText().toString()), Double.valueOf(quattroTre.getText().toString()),Double.valueOf(quattroQuattro.getText().toString()),},
                    };
                    double rango = MatrixCalcolator.calcoloDelRango(matrice,3);
                    showAlertDialog("Rango",String.valueOf(rango));
                }

            }
        });

    }

    public boolean controlloTesto()
    {


        if (unoUno.getText().length() > 0 && unoDue.getText().length()>0  && unoTre.getText().length()>0 && unoQuattro.getText().length()>0 &&
                dueUno.getText().length() > 0 && dueDue.getText().length() > 0 && dueTre.getText().length() > 0 && dueQuattro.getText().length()>0 &&
                treUno.getText().length() > 0 && treDue.getText().length() > 0 && treTre.getText().length() > 0 && treQuattro.getText().length()>0 &&
                quattroUno.getText().length() > 0 && quattroDue.getText().length() > 0 && quattroTre.getText().length() > 0 && quattroQuattro.getText().length()>0 )

            {
            matrixString[0] = unoUno.getText().toString();
            matrixString[1] = unoDue.getText().toString();
            matrixString[2] = unoTre.getText().toString();
            matrixString[3] = unoQuattro.getText().toString();

            matrixString[4] = dueUno.getText().toString();
            matrixString[5] = dueDue.getText().toString();
            matrixString[6] = dueTre.getText().toString();
            matrixString[7] = dueQuattro.getText().toString();

            matrixString[8] = treUno.getText().toString();
            matrixString[9] = treDue.getText().toString();
            matrixString[10] = treTre.getText().toString();
            matrixString[11] = treTre.getText().toString();

            matrixString[12] = quattroUno.getText().toString();
            matrixString[13] = quattroDue.getText().toString();
            matrixString[14] = quattroTre.getText().toString();
            matrixString[15] = quattroQuattro.getText().toString();

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
        AlertDialog alertDialog = new AlertDialog.Builder(Activity4x4.this).create();
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
