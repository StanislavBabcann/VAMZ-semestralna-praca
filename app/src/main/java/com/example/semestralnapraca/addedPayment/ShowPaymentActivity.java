package com.example.semestralnapraca.addedPayment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.R;

/**
 * Trieda ShowPaymentActivity
 * Sluzi na zobrazenie informacii o zvolenej platbe
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class ShowPaymentActivity extends AppCompatActivity {

    private Button backButton;
    private Button editButton;
    private Button deleteButton;
    private int index;
    private String category;
    private double prize;
    private String note;
    private String date;


    /**
     * vytvori instanciu triedy ShowPaymentActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_payment);

        Intent i = getIntent();
        category = i.getStringExtra(MainActivity.EXTRA_CATEGORY);
        prize = i.getDoubleExtra(MainActivity.EXTRA_PRIZE, 0);
        note = i.getStringExtra(MainActivity.EXTRA_NOTE);
        date = i.getStringExtra(MainActivity.EXTRA_DATE);

        TextView textView1 = (TextView) findViewById(R.id.showedCategory);
        TextView textView2 = (TextView) findViewById(R.id.showedPrize);
        TextView textView3 = (TextView) findViewById(R.id.showedNote);
        TextView textView4 = (TextView) findViewById(R.id.date_show_payment);

        index = i.getIntExtra(MainActivity.EXTRA_INDEX, 0);
        textView1.setText(category);
        textView2.setText(prize + "€");
        textView3.setText(note);
        textView4.setText(date);

        configureBackButton();
        configureEditButton();
        configureDeleteButton();

    }

    /**
     * inicializuje tlacidlo BACK na pouzivanie
     * po stlaceni vrati uzivatela do havneho menu
     */
    public void configureBackButton() {
        backButton = (Button) findViewById(R.id.back_from_show);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * inicializuje tlacidlo EDIT na pouzivanie
     * po stlaceni posunie uzivatela do aktivity pre upravu platby
     */
    public void configureEditButton() {
        editButton = (Button) findViewById(R.id.edit_show);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditPaymentActivity.class);
                i.putExtra(MainActivity.EXTRA_INDEX, index);
                i.putExtra(MainActivity.EXTRA_CATEGORY, category);
                i.putExtra(MainActivity.EXTRA_PRIZE, prize);
                i.putExtra(MainActivity.EXTRA_NOTE, note);
                i.putExtra(MainActivity.EXTRA_DATE, date);
                startActivity(i);
            }
        });
    }

    /**
     * inicializuje tlacidlo DELETE na pouzivanie
     * po stlaceni posunie uzivatela do aktivity pre potvrdenie zmazania poznamky
     */
    public void configureDeleteButton() {
        deleteButton = (Button) findViewById(R.id.delete_show);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DeletePaymentActivity.class);
                i.putExtra(MainActivity.EXTRA_INDEX, index);
                startActivity(i);
            }
        });
    }
}
