package com.example.semestralnapraca.more;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralnapraca.R;

/**
 * Trieda ShowYearsOrMonthsActivity
 * Sluzi ako predok tried pre zobrazenie zoznamu rokov alebo mesiacu pomocou ListView
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class ShowYearsOrMonthsActivity extends AppCompatActivity {

    protected Button backButton;
    protected ListView listView;
    protected TextView title;

    /**
     * vytvori instanciu triedy ShowYearsOrMonthsActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_years_or_months);

        backButton = (Button)findViewById(R.id.expense_back_button);
        listView = (ListView) findViewById(R.id.expense_list_view);
        title = (TextView) findViewById(R.id.expense_title_list_view);

        configureBackButton();
    }

    /**
     * inicializuje tlacidlo BACK na pouzivanie
     * po stlaceni vrati uzivatela na predoslu aktivitu
     */
    public void configureBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }

}
