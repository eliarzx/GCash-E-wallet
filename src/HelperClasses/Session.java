package HelperClasses;

public class Session {
    private static Session instance;
    private int accountID;

    // Private constructor to prevent instantiation
    private Session() {}

    // Static method to get the singleton instance
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Getter and Setter for accountID
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
