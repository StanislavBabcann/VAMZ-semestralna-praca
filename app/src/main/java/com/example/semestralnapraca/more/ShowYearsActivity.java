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
 * Trieda ShowYearsActivity
 * Sluzi na zobrazenie zoznamu rokov, v ktorych boli uskutocnene platby
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class ShowYearsActivity extends ShowYearsOrMonthsActivity{

    /**
     * vytvori instanciu triedy ShowYearsActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showYears();
    }

    /**
     * zobrazi zoznam rokov, v ktorych boli uskutocnene platby pomocou ListView
     */
    public void showYears() {
        ArrayList<AddedPayment> paymentsList = MainActivity.getPayments();

        HashSet<String> insertedYears = new HashSet<>();

        ArrayList<String> listForListView = new ArrayList<>();

        for (AddedPayment item : paymentsList) {
            String date = item.getDateOfPayment();

            String[] split = date.split("/");

            String year = split[2].substring(0, 4);

            if (!insertedYears.contains(year)) {
                listForListView.add(year);
                insertedYears.add(year);
            }
        }

        title.setText("Years");
        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.month_or_year, listForListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String year = listForListView.get(position);

                Intent i = new Intent(getApplicationContext(), YearExpenseActivity.class);
                i.putExtra(MainActivity.EXTRA_YEAR, year);

                startActivity(i);
            }
        });
    }
}
