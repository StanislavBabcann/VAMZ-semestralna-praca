package com.example.semestralnapraca.addedPayment;


/**
 * Trieda AddedPayment
 * Reprezentuje pridanu platbu do aplikacie
 * @author Stanislav Babčan
 * @version (13.6.2022)
 *
 */
public class AddedPayment {
    private double prize;
    private String category;
    private String noteOfPayment;
    private String dateOfPayment;

    /**
     * vytvori instanicu platby
     * @param paPrize je cena platby
     * @param paCategory je kategoria platby
     * @param paNoteOfPayment je poznamka k platbe
     * @param paDate je datum vykonania platby
     */
    public AddedPayment(double paPrize, String paCategory, String paNoteOfPayment, String paDate) {
        prize = paPrize;
        category = paCategory;
        noteOfPayment = paNoteOfPayment;
        dateOfPayment = paDate;
    }

    /**
     * @return vrati cenu platby
     */
    public double getPrize() {
        return prize;
    }

    /**
     * @return vrati kategoriu platby
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return vrati poznamku k platbe
     */
    public String getNoteOfPayment() {
        return noteOfPayment;
    }

    /**
     * nastavi novu cenu platby
     * @param paPrize je nova cena
     */
    public void setPrize(double paPrize) {
        prize = paPrize;
    }

    /**
     * nastavi novu kategori platbe
     * @param paCategory je nova kategoria
     */
    public void setCategory(String paCategory) {
        category = paCategory;
    }

    /**
     * nastavi novu poznamku k platbe
     * @param paNote je nova poznamka
     */
    public void setNoteOfPayment(String paNote) {
        noteOfPayment = paNote;
    }

    /**
     * @return vrati datum platby
     */
    public String getDateOfPayment() {return dateOfPayment;}

    /**
     * @return vrati informacie o platbe vo forme Stringu
     */
    public String toString() {
        String output =   "Date        : " + dateOfPayment + "≃";
        output = output + "Category: " + category +"≃";
        output = output + "Prize(€)  : " + prize + "≃";
        output = output + "Note        : " + noteOfPayment + "≃";

        return output;
    }
}
