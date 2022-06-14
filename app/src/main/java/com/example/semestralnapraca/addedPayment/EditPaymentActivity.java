package com.example.semestralnapraca.addedPayment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.R;


/**
 * Trieda EditPaymentActivity
 * Sluzi na upravu poznamky
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class EditPaymentActivity extends AppCompatActivity {
    private RadioGroup radioEditButtons;
    private RadioButton pressedButtonForCategory;
    private Button backButton;
    private Button editPayment;
    private EditText editPrize;
    private EditText editNote;
    private int index;
    private String category;
    private double prize;
    private String note;
    private String date;

    /**
     * vytvori instanciu triedy EditPaymentActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_payment);

        Intent i = getIntent();

        index = i.getIntExtra(MainActivity.EXTRA_INDEX, 0);
        category = i.getStringExtra(MainActivity.EXTRA_CATEGORY);
        prize = i.getDoubleExtra(MainActivity.EXTRA_PRIZE, 0);
        note = i.getStringExtra(MainActivity.EXTRA_NOTE);
        date = i.getStringExtra(MainActivity.EXTRA_DATE);

        TextView viewDate = (TextView) findViewById(R.id.date_edit_payment);
        viewDate.setText(date);


        editPrize = (EditText) findViewById(R.id.showedPrizeEdit);
        editNote = (EditText) findViewById(R.id.showedNoteEdit);

        backButton = (Button) findViewById(R.id.buttonEditPayment);
        editPayment = (Button) findViewById(R.id.editButtonEditPayment);

        radioEditButtons = (RadioGroup) findViewById(R.id.radioGroupEditCategory);

        editPrize.setText(prize + "");
        editNote.setText(note);
        pickDefaultButton();
        configureBackButton();
        configureEditButton();
    }

    /**
     * inicializuje tlacidlo BACK na pouzivanie
     * po stlaceni vrati pouzivatela na predoslu aktivitu
     */
    public void configureBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * inicializuje tlacidlo EDIT na pouzivanie
     * po stlaceni upravi informacie platby na nove informacie
     * vrati aplikaciu do zaciatocneho menu
     */
    public void configureEditButton() {
        editPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddedPayment edited = MainActivity.getPayment(index);

                boolean change = false;

                int selectedButton = radioEditButtons.getCheckedRadioButtonId();

                pressedButtonForCategory = (RadioButton) findViewById(selectedButton);

                category = pressedButtonForCategory.getText().toString();

                edited.setCategory(category);

                if (editPrize.getText() != null) {
                    if (editPrize.getText().toString().equals("")) {
                        prize = 0;
                    } else {
                        prize = Double.parseDouble(editPrize.getText().toString());
                    }
                    edited.setPrize(prize);
                    change = true;
                }

                if (editNote.getText() != null) {
                    note = editNote.getText().toString();
                    if (note.equals("")) {
                        note = "-";
                    }
                    edited.setNoteOfPayment(note);
                    change = true;
                }

                if (change) {
                    MainActivity.uptadeDatabase();
                }

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                finishAffinity();
                startActivity(i);
            }
        });
    }

    /**
     * ako predvoleny RadioButton nastavy ten, ktory obsahuje povodnu kategoriu platby
     */
    public void pickDefaultButton() {
        switch(category) {
            case "grocery":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_grocery_edit);
                break;
            case "clothes":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_clothes_edit);
                break;
            case "appliances":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_appliances_edit);
                break;
            case "charges":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_charge_edit);
                break;
            case "restaurants":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_restaurants_edit);
                break;
            case "traveling":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_traveling_edit);
                break;
            case "free time":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_free_time_edit);
                break;
            case "other":
                pressedButtonForCategory = (RadioButton) findViewById(R.id.radio_other_edit);
                break;

        }

        pressedButtonForCategory.setChecked(true);
    }
}
