package com.example.semestralnapraca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.semestralnapraca.addedPayment.AddPaymentActivity;
import com.example.semestralnapraca.addedPayment.AddedPayment;
import com.example.semestralnapraca.addedPayment.ShowPaymentActivity;
import com.example.semestralnapraca.more.MoreMenuActivity;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * Trieda Main
 * Sluzi na vytvorenie hlavneho menu aplikacie
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class MainActivity extends AppCompatActivity {
    private Button addPaymentButton;
    private Button moreButton;
    private static SharedPreferences database;
    private ListView listView;
    private static ArrayList<AddedPayment> payments;

    public static final String DATA_OF_DATABASE = ".com.example.semestralnapraca.data24";
    public static final String DATABASE_KEY = ".com.example.semestralnapraca.database";
    public static final String EXTRA_CATEGORY = "com.example.semestralnapraca.category";
    public static final String EXTRA_PRIZE = "com.example.semestralnapraca.prize";
    public static final String EXTRA_NOTE = "com.example.semestralnapraca.note";
    public static final String EXTRA_INDEX = ".com.example.semestralnapraca.index";
    public static final String EXTRA_DATE = ".com.example.semestralnapraca.dateOfPayment";
    public static final String EXTRA_YEAR = ".com.example.semestralnapraca.year";

    /**
     * vytvori instanciu triedy MainActivity
     * @param savedInstanceState reprezentuje ulozeny stav instancie
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addPayment();
        more();
        listView = (ListView) findViewById(R.id.list_View);
        database = getApplicationContext().getSharedPreferences(DATABASE_KEY, 0);

        String dataInDatabase = database.getString(DATABASE_KEY, null);
        SharedPreferences.Editor editor = database.edit();

        if (dataInDatabase == null) {
            editor.putString(DATABASE_KEY, "");
        }


        editor.apply();
        payments = new ArrayList<>();
        showInsertedPayments();
    }

    /**
     * po stlaceni prislusneho tlacidla zmeni aktivitu aplikacie na aktivitu pridavania platby
     */
    private void addPayment() {

        addPaymentButton = (Button) findViewById(R.id.plus_button);
        addPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), AddPaymentActivity.class);
                startActivity(i);

            }
        });

        uptadeDatabase();

    }

    /**
     * po stlaceni prislusneho tlacidla zmeni aktivitu aplikacie na aktivitu MoreMenuActivity
     */
    private void more() {
        moreButton = (Button) findViewById(R.id.viac);
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MoreMenuActivity.class);
                startActivity(i);
            }
        });
    }

    /**
     * nacita data zo SharedPreferences
     * @return vrati data zo SharedPreferences ulozene v zozname
     */
    public LinkedList<String> getDataFromDatabase() {
        LinkedList<String> listData = new LinkedList<>();

        String dataFromDatabase = database.getString(DATA_OF_DATABASE, null);

        int counter = 0;
        String line = "";

        if (dataFromDatabase != null && dataFromDatabase.length() != 0) {
            for (int i = 0; i < dataFromDatabase.length(); i++) {
                char characterr = dataFromDatabase.charAt(i);


                if (characterr != '≃') {
                    line += dataFromDatabase.charAt(i);
                } else {
                    line += dataFromDatabase.charAt(i);
                    counter++;
                }

                if (counter == 4) {
                    listData.add(0,line);
                    counter = 0;
                    line = "";
                }
            }
        }

        return listData;
    }

    /**
     * ulozi nove data do databazy vzhladom na stav aplikacie
     */
    public static void uptadeDatabase() {
        String savedData = "";
        if (payments != null) {
            for (int i = 0; i < payments.size(); i++) {
                AddedPayment current = payments.get(i);
                String line = current.toString();
                savedData = current.toString() + savedData;
            }
            SharedPreferences.Editor editor = database.edit();
            editor.putString(DATA_OF_DATABASE, savedData);
            editor.apply();
        }
    }


    /**
     * ukaze zaznamenane platby v aplikacii pomocou ListView
     * po kliknuti na danu platbu zmeni aktivitu na ShowPaymentActivity
     */
    private void showInsertedPayments() {
        LinkedList<String> listData = getDataFromDatabase();

        ArrayList<String> listForPayment = new ArrayList<>(listData.size());

        for (String line : listData) {
            String[] split = line.split("≃");

            String newLine = split[0] + "\n" + split[1] + "\n" + split[2] + "\n";
            listForPayment.add(newLine);
        }


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listForPayment);
        listView.setAdapter(adapter);
        createPaymentsToArrayList(listData);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AddedPayment clickedPayment = payments.get(position);

                String category = clickedPayment.getCategory();
                double prize = clickedPayment.getPrize();
                String note = clickedPayment.getNoteOfPayment();
                String date = clickedPayment.getDateOfPayment();

                Intent i = new Intent(getApplicationContext(), ShowPaymentActivity.class);
                i.putExtra(EXTRA_CATEGORY, category);
                i.putExtra(EXTRA_PRIZE, prize);
                i.putExtra(EXTRA_NOTE, note);
                i.putExtra(EXTRA_INDEX, position);
                i.putExtra(EXTRA_DATE, date);
                startActivity(i);
            }
        });

    }

    /**
     * vytvory objekty AddedPayment z udajov zo SharedPreferences
     * @param data su nacitane data zo SharedPreferences
     */
    public void createPaymentsToArrayList(LinkedList<String> data) {
        for (String item : data) {
            String line = item;

            String[] split = line.split("≃");

            String[] firstLine = split[0].split(": ");
            String[] secondLine = split[1].split(": ");
            String[] thirdLine = split[2].split(": ");
            String[] fourthLine = split[3].split(": ");

            String category = secondLine[1];
            double prize = Double.parseDouble(thirdLine[1]);
            String note = fourthLine[1];
            String date = firstLine[1];

            AddedPayment added = new AddedPayment(prize, category, note, date);

            payments.add(added);
        }
    }

    /**
     * vymaze prislusnu platbu z aplikacie a SharedPreferences
     * @param index je index mazanej platby
     */
    public static void deletePayment(int index) {
        payments.remove(index);
        uptadeDatabase();

    }

    /**
     * prida prislusnu platbu do aplikacie a SharedPreferences
     * @param payment je pridavana platba
     */
    public static void addPayment(AddedPayment payment) {
        payments.add(payment);

        uptadeDatabase();
    }

    /**
     * @param index je index hladanej platby
     * @return je objekt platby zo zoznamu vzhladom na index
     */
    public static AddedPayment getPayment(int index) {
        return payments.get(index);
    }

    /**
     * @return vrati zoznam ulozenych platieb
     */
    public static ArrayList<AddedPayment> getPayments() {
        return payments;
    }
}