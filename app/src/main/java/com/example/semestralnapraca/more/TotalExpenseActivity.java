package com.example.semestralnapraca.more;

import android.os.Bundle;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.addedPayment.AddedPayment;

import java.util.ArrayList;

/**
 * Trieda TotalExpenseActivity
 * Sluzi na zobrazenie celkovych informacii o platbach
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class TotalExpenseActivity extends ExpenseActivity {


    /**
     * vytvori instanciu triedy TotalExpenseActivity
     * priradi do instancie vsetky platby
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ArrayList<AddedPayment> payments = MainActivity.getPayments();

        for (AddedPayment current : payments) {
            super.addPayment(current);
        }

        super.configureBackButton();
        super.showInfo("Total expenses");
    }
}
