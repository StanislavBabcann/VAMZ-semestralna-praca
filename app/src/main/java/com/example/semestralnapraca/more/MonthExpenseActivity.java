package com.example.semestralnapraca.more;

import android.content.Intent;
import android.os.Bundle;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.addedPayment.AddedPayment;

import java.util.ArrayList;


/**
 * Trieda MonthExpenseActivity
 * Reprezentuje aktivitu pre zobrazenie informacii o prislusnom mesiaci
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class MonthExpenseActivity extends ExpenseActivity{

    private String year;
    private String month;


    /**
     * vytvori instanciu triedy MonthExpenseActivity
     * prida do instancie platby, ktore patria do daneho mesiaca daneho roku
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();

        String extra = i.getStringExtra(MainActivity.EXTRA_YEAR);

        ArrayList<AddedPayment> payments = MainActivity.getPayments();

        String[] split = extra.split(" ");

        month = split[0];
        year = split[2];


        String monthAsNumber = split[1];

        // prejde zoznam vsetkych platieb
        for (AddedPayment current : payments) {
            String date = current.getDateOfPayment();

            String[] splitOfDate = date.split("/");

            String yearOfPayment = splitOfDate[2].substring(0, 4);
            String monthOfPayment = splitOfDate[1];

            // v pripade, ze rok platby je rovnaky ako rok instancie a zaroven mesiac platby je rovnaky ako mesiac instanice,
            // prida platbu do instancie
            if (year.equals(yearOfPayment) && monthAsNumber.equals(monthOfPayment)) {
                super.addPayment(current);
            }
        }

        super.showInfo(month + " " + year);
    }
}
