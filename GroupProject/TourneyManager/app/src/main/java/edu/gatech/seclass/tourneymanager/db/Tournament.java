package edu.gatech.seclass.tourneymanager.db;

/**
 * Created by rugrani on 3/2/17.
 */

public class Tournament {
     /*
    +-------------------------------+-----------+------------+
    |   Field                       |   Type    |   Key      |
    +-------------------------------+-----------+------------+
    |   id                          |   INT     |   PRI      |
    |   running                     |   INT     |            |
    |   endedEarly                  |   INT     |            |
    */

    private int id;
    private boolean running;
    private boolean endedEarly;

    public Tournament() {
    }

    public Tournament(int id, boolean running, boolean endedEarly) {
        this.id = id;
        this.running = running;
        this.endedEarly = endedEarly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isEndedEarly() {
        return endedEarly;
    }

    public void setEndedEarly(boolean endedEarly) {
        this.endedEarly = endedEarly;
    }
}
