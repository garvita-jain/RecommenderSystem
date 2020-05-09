import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id, ArrayList<Rating> ratings){
        this.myID = id;
        myRatings = new HashMap<>();
        for(Rating r : ratings) {
            myRatings.put(r.getItem(), r);
        }
    }

    public void addRating(String item, double rating){
        myRatings.put(item, new Rating(item, rating));
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item){
        if(myRatings.containsKey(item))
            return myRatings.get(item).getValue();
        else
            return -1;
    }

    public int numRating(){
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated(){
        ArrayList<String> list = new ArrayList<>();
        list.addAll(myRatings.keySet());
        return list;
    }
}
