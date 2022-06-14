package com.example.semestralnapraca.addedPayment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.R;


/**
 * Trieda DeletePaymentActivity
 * Sluzi na potvrdenie rozhodnutia o zmazani existujucej platby
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class DeletePaymentActivity extends AppCompatActivity {

    private Button noButton;
    private Button yesButton;
    private int indexOfDeleted;


    /**
     * vytvori instanciu triedy DeletePaymentActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        indexOfDeleted = i.getIntExtra(MainActivity.EXTRA_INDEX, 0);
        setContentView(R.layout.activity_delete_payment);
        configureNoButton();
        configureYesButton();
    }

    /**
     * inicializuje tlacidlo NO na pouzivanie
     * po stlaceni vrati uzivatela na aktivitu zobrazovania informacii o platbe
     */
    private void configureNoButton() {
        noButton = (Button) findViewById(R.id.delete_no);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * inicializuje tlacidlo YES na pouzivanie
     * po stlaceni vymaze prislusnu platbu z aplikacie a vrati uzivatela do hlavneho menu
     */
    private void configureYesButton() {
        yesButton = (Button) findViewById(R.id.delete_yes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.deletePayment(indexOfDeleted);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishAffinity();
                startActivity(i);
            }
        });
    }
}
