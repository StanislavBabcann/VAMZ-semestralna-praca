package com.example.semestralnapraca.more;

import com.example.semestralnapraca.addedPayment.AddedPayment;

/**
 * Trieda CountedCategories
 * Sluzi na spracovanie udajov o prislusnom casovom obdobi(mesiac/rok/celkovo)
 * @author Stanislav Babčan
 * @version (13.6.2022)
 */
public class CountedCategories {

    private double totalPrize;
    private double grocery;
    private double clothes;
    private double appliances;
    private double charges;
    private double restaurants;
    private double traveling;
    private double freeTime;
    private double other;

    /**
     * vytvori instanciu triedy a vsetky jej atributy inicializuje na nulovu hodnotu
     */
    public CountedCategories() {
        totalPrize = 0;
        grocery = 0;
        clothes = 0;
        appliances = 0;
        charges = 0;
        restaurants = 0;
        traveling = 0;
        freeTime = 0;
        other = 0;
    }

    /**
     * @return vrati sumu daneho obdobia vo forme Stringu
     */
    public String getPrizeAsString() {
        return "Money spent: " + roundNumber(totalPrize) + "€";
    }

    /**
     * @return vrati sumy danych kategorii za prislusne obdobie vo forme Stringu
     */
    public String getCatgoriesAsString() {
        double dividor;

        if (totalPrize == 0) {
            dividor = 1;
        } else {
            dividor = totalPrize;
        }


        String output = "Grocery: " + roundNumber(grocery) + "€ (" + roundNumber(grocery / dividor * 100) + "%)" + "\n";
        output += "Clothes: " + roundNumber(clothes) + "€ (" + roundNumber(clothes / dividor * 100) + "%)" + "\n";
        output += "Appliances: " + roundNumber(appliances) + "€ (" + roundNumber(appliances / dividor * 100) + "%)" + "\n";
        output += "Charges: " + roundNumber(charges) + "€ (" + roundNumber(charges / dividor * 100) + "%)" + "\n";
        output += "Restaurants: " + roundNumber(restaurants) + "€ (" + roundNumber(restaurants / dividor * 100) + "%)" + "\n";
        output += "Traveling: " + roundNumber(traveling) + "€ (" + roundNumber(traveling / dividor * 100) + "%)" + "\n";
        output += "Free time: " + roundNumber(freeTime) + "€ (" + roundNumber(freeTime / dividor * 100) + "%)" + "\n";
        output += "Other: " + roundNumber(other) + "€ (" + roundNumber(other / dividor * 100) + "%)" + "\n";

        return output;
    }

    /**
     * prida platbu do instancie
     * @param payment je trieda pridavanej platby
     */
    public void addPayment(AddedPayment payment) {
        double prize = payment.getPrize();
        String category = payment.getCategory();

        totalPrize += prize;
        addPrizeToCategory(prize, category);
    }

    /**
     * prida cenu k prislusnej kategorii
     * @param paPrize je cena
     * @param paCategory je kategoria, do ktorej sa bude pridavat cena
     */
    public void addPrizeToCategory(double paPrize, String paCategory) {
        switch (paCategory) {
            case "grocery":
                grocery += paPrize;
                break;
            case "clothes":
                clothes += paPrize;
                break;
            case "appliances":
                appliances += paPrize;
                break;
            case "charges":
                charges += paPrize;
                break;
            case "restaurants":
                restaurants += paPrize;
                break;
            case "traveling":
                traveling += paPrize;
                break;
            case "free time":
                freeTime += paPrize;
                break;
            case "other":
                other += paPrize;
                break;
        }
    }

    /**
     * zaokruhli desatinne cislo na dve desatinne miesta
     * @param number je zaokruhlovane cislo
     * @return je zaoukruhlene cislo na dve desatinne miesta vo forme Stringu
     */
    public String roundNumber(double number) {
        return String.format("%.2f", number);
    }
}
