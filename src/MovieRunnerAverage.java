import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings test = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");

        System.out.println(test.getMovieSize());
        System.out.println(test.getRaterSize());

        ArrayList<Rating> avgRatings = test.getAverageRatings(3);

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            System.out.println(r.toString());
        }
    }

    public void getAverageRatingOneMovie(){
        SecondRatings test = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");

        ArrayList<Rating> avgRatings = test.getAverageRatings(3);

        for(Rating r : avgRatings){
            if(r.getItem().compareTo("The Godfather")==0){
                System.out.println(r.getValue());
                break;
            }
        }


    }

//    public static void main(String[] args){
//        MovieRunnerAverage basic = new MovieRunnerAverage();
//        basic.printAverageRatings();
//        basic.getAverageRatingOneMovie();
//
//
//    }
}
