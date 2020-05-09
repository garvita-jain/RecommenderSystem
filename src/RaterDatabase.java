import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
    public static HashMap<String, Rater> ourRaters;

    private static void initialise(){
        if(ourRaters==null){
            ourRaters = new HashMap<>();
            ourRaters = loadRaters("data/ratings.csv");
        }
    }

    public static void initialise(String filename){
        if(ourRaters==null) {
            ourRaters = new HashMap<>();
            ourRaters = loadRaters("data/" + filename);
        }
    }

    private static HashMap<String, Rater> loadRaters(String filename) {
        HashMap<String, Rater> data = new HashMap<>();

        FirstRatings file = new FirstRatings();
        for(Rater r : file.loadRaters(filename)){
            data.put(r.getID(), r);
        }

        return data;
    }

    public static void addRatings(String filename){
        ourRaters.putAll(loadRaters(filename));
    }

    public static void addRaterRating(String raterID, String movieID, double rating){
        if(ourRaters.containsKey(raterID)){
            ourRaters.get(raterID).addRating(movieID, rating);
        }
        else{
            ArrayList<Rating> x = new ArrayList<>();
            x.add(new Rating(movieID, rating));
            ourRaters.put(raterID, new EfficientRater(raterID, x));
        }
    }

    public static Rater getRater(String id){
        return ourRaters.get(id);
    }

    public static ArrayList<Rater> getRaters(){
        ArrayList<Rater> list = new ArrayList<>();
        list.addAll(ourRaters.values());
        return list;
    }

    public static int size(){
        return ourRaters.size();
    }
}
