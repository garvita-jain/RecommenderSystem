import java.util.ArrayList;
import java.util.Collections;

public class ForthRatings {
    private double getAverageByID(String id, int minimalRaters){
        int count = 0;
        double sum = 0;

        for(Rater r : RaterDatabase.ourRaters.values()){
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

    public double getAverageBySimilarRaters(String id, int minimalRaters, ArrayList<Rating> similarRaters){
        int count = 0;
        double sum = 0;

        for(Rating rat : similarRaters){
            Rater r = RaterDatabase.getRater(rat.getItem());
            if(r.getItemsRated().contains(id)){
                count++;
                sum += r.getRating(id);
            }
        }

        //System.out.println(count);

        if(count>=minimalRaters){
            //System.out.println(count);
            return sum/count;
        }
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

    private double dotProduct(Rater me, Rater r){
        double product = 0;

        Rater r1 = me.numRating()>r.numRating() ? me : r;
        Rater r2 = r1.getID()==me.getID() ? r : me;

        for(String s : r1.getItemsRated()){
            double rat1 = r1.getRating(s);
            double rat2 = r2.getRating(s);

            if(rat2!=-1) {
                product += (rat1 - 5) * (rat2 - 5);
                //System.out.println((rat1 - 5) + " " + (rat2 - 5) + " " + product);
            }
        }

        return product;
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> raterSimilarity = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        for(String rID : RaterDatabase.ourRaters.keySet()){
            if(rID.compareTo(id)==0)
                continue;
            Rater r = RaterDatabase.getRater(rID);
            double similarity = dotProduct(me, r);
            //System.out.println(r.getID()+" "+similarity);
            if(similarity>0)
                raterSimilarity.add(new Rating(rID, similarity));
        }

        Collections.sort(raterSimilarity, Collections.reverseOrder());

        return raterSimilarity;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<Rating> similarRaters = getSimilarities(id);

        if(numSimilarRaters<similarRaters.size()){
            for(int i = numSimilarRaters; i<similarRaters.size(); i++)
                similarRaters.remove(i--);
        }
            //similarRaters = similarRaters.subList(0, numSimilarRaters);

        //System.out.println(similarRaters.size());

        for(String m : MovieDatabase.ourMovies.keySet()){
            double rating = getAverageBySimilarRaters(m, minimalRaters, similarRaters);
            if(rating>0) {
                ratings.add(new Rating(m, rating));
                //System.out.println(m + " " + rating);
            }
        }

        Collections.sort(ratings, Collections.reverseOrder());

        //System.out.println(ratings.size());

        return ratings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratings = new ArrayList<>();
        ArrayList<Rating> similarRaters = getSimilarities(id);

        if(numSimilarRaters<similarRaters.size()){
            for(int i = numSimilarRaters; i<similarRaters.size(); i++)
                similarRaters.remove(i--);
        }

        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for(String m : movies){
            double rating = getAverageBySimilarRaters(m, minimalRaters, similarRaters);
            if(rating>=0)
                ratings.add(new Rating(m, rating));
        }

        Collections.sort(ratings, Collections.reverseOrder());

        return ratings;
    }
}
