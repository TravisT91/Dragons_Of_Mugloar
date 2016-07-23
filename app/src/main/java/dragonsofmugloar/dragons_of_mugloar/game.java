package dragonsofmugloar.dragons_of_mugloar;

/**
 * Created by Travis on 7/20/2016.
 */
public class game {

    public game(int ID, knight knight) {
        this.ID = ID;
        Knight = knight;
    }

    private int ID;
    private knight Knight;

    public knight getKnight() {
        return Knight;
    }

    public void setKnight(knight knight) {
        Knight = knight;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
