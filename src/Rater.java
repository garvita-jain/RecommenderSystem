import java.util.ArrayList;

public interface Rater {
    public void addRating(String item, double rating);
    public String getID();
    public double getRating(String item);
    public int numRating();
    public ArrayList<String> getItemsRated();
}
