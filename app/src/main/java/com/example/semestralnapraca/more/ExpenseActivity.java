package com.example.semestralnapraca.more;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.semestralnapraca.R;
import com.example.semestralnapraca.addedPayment.AddedPayment;

/**
 * Trieda ExpenseActivity
 * Abstraktna aktivita, ktora sluzi ako predok pre ine triedy pre zobrazenie informacii o casovej jednotke(mesiac/rok/celkovo)
 * @author Stanislav Babčan
 * @verison (13.6.2022)
 */
abstract class ExpenseActivity extends AppCompatActivity {

    private Button backButton;
    private CountedCategories totalCounted;

    /**
     * vytvori instanciu triedy ExpenseActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        totalCounted = new CountedCategories();

        setContentView(R.layout.activity_ratio_of_expenses);
        configureBackButton();
    }

    /**
     * inicializuje tlacidlo BACK na pouzivanie
     * po stlaceni vrati uzivatela do predoslej aktivity
     */
    public void configureBackButton() {
        backButton = (Button) findViewById(R.id.ratio_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * zobrazi informacie o danom casovom obdobii
     * @param title je nazov casoveho obdobia
     */
    public void showInfo(String title) {
        TextView titleView = (TextView) findViewById(R.id.ratio_name);
        TextView prizeView = (TextView) findViewById(R.id.ratio_total_prize);
        TextView categoryView = (TextView) findViewById(R.id.ratio_categories);

        titleView.setText(title);
        prizeView.setText(totalCounted.getPrizeAsString());
        categoryView.setText(totalCounted.getCatgoriesAsString());
    }

    /**
     * prida platbu do instancie
     * @param payment je pridavana platba
     */
    public void addPayment(AddedPayment payment) {
        totalCounted.addPayment(payment);
    }
}
