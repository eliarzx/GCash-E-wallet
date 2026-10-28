package HelperClasses;

public class BankSession {
    private static String selectedBank;

    public static void setSelectedBank(String bankName) {
        selectedBank = bankName;
    }

    public static String getSelectedBank() {
        return selectedBank;
    }
}
