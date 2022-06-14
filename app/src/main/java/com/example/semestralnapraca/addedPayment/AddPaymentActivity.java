package com.example.semestralnapraca.addedPayment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Trieda AddPaymentActivity
 * Sluzi na pridanie novej platby do aplikacie
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */

public class AddPaymentActivity extends AppCompatActivity {

    private RadioButton pressedButton;
    private EditText inputFromUser;
    private RadioGroup radioButtons;
    private Button addButton;
    private Button backButton;
    private EditText noteToPaymentEditText;
    private TextView dateOfPayment;

    /**
     * vytvori instanciu triedy AddPaymentActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        SimpleDateFormat sdf = new SimpleDateFormat("E, dd/MM/yyyy HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_payment);
        dateOfPayment = (TextView)findViewById(R.id.date_add_payment);
        dateOfPayment.setText(currentDateandTime);
        configureBackButton();
        configureAddButton();
    }


    /**
     * inicializuje tlacidlo BACK na pouzivanie
     */
    private void configureBackButton() {

        backButton = (Button) findViewById(R.id.back_button_add_payment);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    /**
     * inicializuje tlacidlo ADD na pouzivanie
     * po stlaceni prida novu platbu do aplikacie s parametrami, ktore boli zadane
     */
    private void configureAddButton() {
        addButton = (Button) findViewById(R.id.add_payment_button);
        inputFromUser = (EditText)findViewById(R.id.suma);
        radioButtons = (RadioGroup) findViewById(R.id.radioGroup);
        noteToPaymentEditText = (EditText) findViewById(R.id.note_of_payment) ;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double prizeOfPayment;

                if (inputFromUser.getText().toString().equals("")) {
                    prizeOfPayment = 0;
                } else {
                    prizeOfPayment = Double.parseDouble(inputFromUser.getText().toString());
                }

                int selectedButton = radioButtons.getCheckedRadioButtonId();

                pressedButton = (RadioButton) findViewById(selectedButton);

                String category;

                if (pressedButton == null) {
                    category = "other";
                } else {
                    category = pressedButton.getText().toString();
                }

                String noteToPayment;
                if (noteToPaymentEditText.getText().toString().equals("")) {
                    noteToPayment = "-";
                } else {
                    noteToPayment = noteToPaymentEditText.getText().toString();
                }

                String date = dateOfPayment.getText().toString();

                AddedPayment newAddedPayment = new AddedPayment(prizeOfPayment, category, noteToPayment, date);

                addToDatabase(newAddedPayment);


                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finishAffinity();
                startActivity(i);

            }
        });
    }

    /**
     * prida pridavanu platbu do SharedPreferences
     * @param addedPayment je pridavana platba
     */
    private void addToDatabase(AddedPayment addedPayment) {
        MainActivity.uptadeDatabase();
        MainActivity.addPayment(addedPayment);

    }


}
