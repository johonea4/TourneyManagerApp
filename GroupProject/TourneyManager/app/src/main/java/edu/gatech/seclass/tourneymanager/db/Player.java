package edu.gatech.seclass.tourneymanager.db;

/**
 * Created by rugrani on 3/1/17.
 */

public class Player {

    /*
    +-------------------------------+-----------+------------+
    |   Field                       |   Type    |   Key      |
    +-------------------------------+-----------+------------+
    |   id                          |   INT     |   PRI      |
    |   name                        |   STRING  |            |
    |   user_name                   |   STRING  |   PRI      |
    |   phone_number                |   STRING  |            |
    |   deck_choice                 |   STRING  |            |
    */

    private int id;
    private String name;
    private String userName;
    private String phoneNumber;
    private String deckChoice;

    public Player() {
    }

    public Player(int id, String name, String userName, String phoneNumber, String deckChoice) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.deckChoice = deckChoice;
    }

    public Player(String name, String userName, String phoneNumber, String deckChoice) {
        this.name = name;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.deckChoice = deckChoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeckChoice() {
        return deckChoice;
    }

    public void setDeckChoice(String deckChoice) {
        this.deckChoice = deckChoice;
    }
}
