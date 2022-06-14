package com.example.semestralnapraca.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.semestralnapraca.R;

/**
 * Trieda MoreMenuActivity
 * Reprezentuje aktivitu pre zobrazenia uzivatelskeho menu pre zvolenie si casovej jednotky, o ktorej chce zobrazit informacie
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class MoreMenuActivity extends AppCompatActivity {

    private Button backButton;
    private Button totalButton;
    private Button yearButton;
    private Button monthButton;

    /**
     * vytvori instanciu triedy MoreMenuActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_menu);
        configureBackButton();
        configureTotalButton();
        configureYearButton();
        configureMonthButton();
    }

    /**
     * inicializuje tlacidlo BACK na pouzivanie
     * po stlaceni vrati pouzivatela na predoslu aktivitu
     */
    private void configureBackButton() {
        backButton = (Button) findViewById(R.id.more_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * inicializuje tlacidlo TOTAL RATIO na pouzivanie
     * po stlaceni sa aplikacia presunie do aktivity pre zobrazenie celkovych informacii o platbach
     */
    private void configureTotalButton() {
        totalButton = (Button) findViewById(R.id.more_total);
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TotalExpenseActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * inicializuje tlacidlo YEAR RATIO na pouzivanie
     * po stlaceni sa aplikacia presunie do aktivity pre vybratie si prislusneho roku, o ktorom maju byt zobrazene informacie
     */
    private void configureYearButton() {
        yearButton = (Button) findViewById(R.id.more_year);
        yearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i = new Intent(getApplicationContext(), ShowYearsActivity.class);
                 startActivity(i);

            }
        });
    }

    /**
     * inicializuje tlacidlo MONTH RATIO na pouzivanie
     * po stlaceni sa aplikacia presunie do aktivity pre vybratie prislusneho mesiaca, o ktorom maju byt zobrazene informacie
     */
    private void configureMonthButton() {
        monthButton = (Button) findViewById(R.id.more_month);
        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), ShowMonthsActivity.class);
                startActivity(i);
            }
        });
    }
}
