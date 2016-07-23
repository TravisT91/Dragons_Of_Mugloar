package dragonsofmugloar.dragons_of_mugloar;

/**
 * Created by Travis on 7/20/2016.
 */
public class knight {
    private String name;
    private int attack;
    private int armor;
    private int agility;
    private int endurace;


    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public int getAgility() {
        return agility;
    }

    public int getEndurace() {
        return endurace;
    }

    public knight(String name, int attack, int armor, int agility, int endurace) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.agility = agility;
        this.endurace = endurace;
    }

    @Override
    public String toString(){
        return  "Attack: " + Integer.toString(attack) + "\n" +
                "Armor: " + Integer.toString(armor) + "\n" +
                "Agility: " + Integer.toString(agility) + "\n" +
                "Endurace: " + Integer.toString(endurace) + "\n";
    }

}
