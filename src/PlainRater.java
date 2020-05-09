import java.util.ArrayList;

public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    public PlainRater(String id, ArrayList<Rating> ratings){
        this.myID = id;
        this.myRatings = ratings;
    }

    public void addRating(String item, double rating){
        myRatings.add(new Rating(item, rating));
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item){
        for(Rating r : myRatings){
            if(r.getItem().compareTo(item)==0){
                return r.getValue();
            }
        }

        return -1;
    }

    public int numRating(){
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated(){
        ArrayList<String> list = new ArrayList<>();

        for(Rating r : myRatings)
            list.add(r.getItem());

        return list;
    }

}
