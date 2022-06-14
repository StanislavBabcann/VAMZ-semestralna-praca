package com.example.semestralnapraca.more;

import android.content.Intent;
import android.os.Bundle;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.addedPayment.AddedPayment;

import java.util.ArrayList;

/**
 * Trieda YearExpenseActivity
 * Reprezentuje aktivitu pre zobrazenie informacii o prislusnom roku
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class YearExpenseActivity extends ExpenseActivity{

    private String year;

    /**
     * vytvori instanciu triedy YearExpenseActivity
     * priradi do instanice platby, ktore patria do daneho roku
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        year = i.getStringExtra(MainActivity.EXTRA_YEAR);

        ArrayList<AddedPayment> payments = MainActivity.getPayments();

        // prejde zoznam vsetkych platieb
        for (AddedPayment current : payments) {
            String date = current.getDateOfPayment();

            String[] split = date.split("/");

            String yearOfPayment = split[2].substring(0, 4);

            // v pripade, ze rok platby je rovnaky ako rok instancie, prida platbu do instanice
            if (year.equals(yearOfPayment)) {
                super.addPayment(current);
            }
        }

        super.showInfo("Year " + year);
    }
}
