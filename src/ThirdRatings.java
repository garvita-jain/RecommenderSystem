import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings(){
        this("data/ratings.csv");
    }

    public ThirdRatings(String ratingFile){
        myRaters = new FirstRatings().loadRaters(ratingFile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double sum = 0;

        for(EfficientRater r : myRaters){
            if(r.getItemsRated().contains(id)){
                count++;
                sum += r.getRating(id);
            }
        }

        if(count>=minimalRaters)
            return sum/count;
        else
            return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> list = new ArrayList<>();

        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

        for(String id : movies){
            double avgRating = getAverageByID(id, minimalRaters);
            list.add(new Rating(id, avgRating));
        }

        return list;
    }

    public ArrayList<Rating> getAverageRatingByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> list = new ArrayList<>();

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for(String id : movies){

            double avgRating = getAverageByID(id, minimalRaters);
            list.add(new Rating(id, avgRating));
        }

        return list;
    }
}
