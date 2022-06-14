package com.example.semestralnapraca.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.semestralnapraca.MainActivity;
import com.example.semestralnapraca.R;
import com.example.semestralnapraca.addedPayment.AddedPayment;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Trieda ShowMonthsActivity
 * Sluzi na zobrazenie zoznamu mesiacov, v ktorych boli uskutocnene platby
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class ShowMonthsActivity extends ShowYearsOrMonthsActivity {

    /**
     * vytvori instanciu triedy ShowMonthsActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showMonths();
    }

    /**
     * zobrazi zoznam mesiacov, v ktorych boli uskutocnene platby pomocou ListView
     */
    public void showMonths() {
        ArrayList<AddedPayment> paymentsList = MainActivity.getPayments();

        HashSet<String> insertedMonths = new HashSet<>();

        ArrayList<String> listForActivity = new ArrayList<>();

        ArrayList<String> listForListView = new ArrayList<>();

        for (AddedPayment item : paymentsList) {
            String date = item.getDateOfPayment();

            String[] split = date.split("/");

            String year = split[2].substring(0, 4);

            String key = split[1] +" "+ year;

            if (!insertedMonths.contains(key)) {
                listForActivity.add(getMonthAsString(split[1]) + " " + key);
                listForListView.add(getMonthAsString(split[1]) + " " + year);
                insertedMonths.add(key);
            }
        }

        title.setText("Months");

        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.month_or_year, listForListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String month = listForActivity.get(position);

                Intent i = new Intent(getApplicationContext(), MonthExpenseActivity.class);
                i.putExtra(MainActivity.EXTRA_YEAR, month);

                startActivity(i);
            }
        });
    }

    /**
     * premeni cislo prislusneho mesiaca na jeho nazov
     * @param input je cislo mesiaca
     * @return je nazov daneho mesiaca
     */
    public String getMonthAsString(String input) {
        String output = "";
        switch (input) {
            case "01":
                output = "January";
                break;
            case "02":
                output = "February";
                break;
            case "03":
                output = "March";
                break;
            case "04":
                output = "April";
                break;
            case "05":
                output = "May";
                break;
            case "06":
                output = "June";
                break;
            case "07":
                output = "July";
                break;
            case "08":
                output = "August";
                break;
            case "09":
                output = "September";
                break;
            case "10":
                output = "October";
                break;
            case "11":
                output = "November";
                break;
            case "12":
                output = "December";
                break;
        }

        return output;
    }
}
