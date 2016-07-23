package dragonsofmugloar.dragons_of_mugloar;

/**
 * Created by Travis on 7/20/2016.
 */
public class dragon {
    private int scaleThickness;
    private int clawSharpness;
    private int wingStrengeth;
    private int fireBreath;
    private int points;

    public dragon (int points, int scaleThickness, int clawSharpness, int wingStrengeth, int fireBreath){
        this.points = points;
        this.scaleThickness = scaleThickness;
        this.clawSharpness = clawSharpness;
        this.wingStrengeth = wingStrengeth;
        this.fireBreath = fireBreath;
    }

    public dragon(){
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getScaleThickness() {
        return scaleThickness;
    }

    public void setScaleThickness(int scaleThickness) {
        this.scaleThickness = scaleThickness;
    }

    public int getClawSharpness() {
        return clawSharpness;
    }

    public void setClawSharpness(int clawSharpness) {
        this.clawSharpness = clawSharpness;
    }

    public int getWingStrengeth() {
        return wingStrengeth;
    }

    public void setWingStrengeth(int wingStrengeth) {
        this.wingStrengeth = wingStrengeth;
    }

    public int getFireBreath() {
        return fireBreath;
    }

    public void setFireBreath(int fireBreath) {
        this.fireBreath = fireBreath;
    }

    @Override
    public String toString(){
        return  "Scale Thickness: " + Integer.toString(getScaleThickness()) + "\n" +
                "Claw Sharpness: " + Integer.toString(getClawSharpness()) + "\n" +
                "Wing Strengeth: " + Integer.toString(getWingStrengeth()) + "\n" +
                "Fire Breath: " + Integer.toString(getFireBreath());
    }

}
