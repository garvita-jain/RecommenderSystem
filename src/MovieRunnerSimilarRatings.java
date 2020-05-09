import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        ArrayList<Rating> avgRatings = test.getAverageRatings(1);

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0)
                System.out.println(r.toString());
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(1980));
        filter.addFilter(new GenreFilter("Romance"));


        ArrayList<Rating> avgRatings = test.getAverageRatingByFilter(1, filter);

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0) {
                System.out.println(r.toString());
                System.out.println("   "+MovieDatabase.getDirector(r.getItem()));
            }
        }
    }

    public static void printSimilarRatings(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        ArrayList<Rating> similarRatings = test.getSimilarRatings("65", 20, 5);

        for(Rating r : similarRatings){
            if(r.getValue()!=0)
                System.out.println(MovieDatabase.getTitle(r.getItem())+ " "+ r.getValue());
        }
    }

    public static void printSimilarRatingsByGenre(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        ArrayList<Rating> similarRatings = test.getSimilarRatingsByFilter("65", 20, 5, new GenreFilter("Action"));

        for(Rating r : similarRatings){
            if(r.getValue()!=0)
                System.out.println(MovieDatabase.getTitle(r.getItem())+ " "+ r.getValue());
        }
    }

    public static void printSimilarRatingsByDirector(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        ArrayList<Rating> similarRatings = test.getSimilarRatingsByFilter("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));

        for(Rating r : similarRatings){
            if(r.getValue()!=0)
                System.out.println(MovieDatabase.getTitle(r.getItem())+ " "+ r.getValue());
        }
    }

    public static void printSimilarRatingsByGenreAndMinute(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(100, 200));
        filter.addFilter(new GenreFilter("Adventure"));

        ArrayList<Rating> similarRatings = test.getSimilarRatingsByFilter("65", 10, 5, filter);

        for(Rating r : similarRatings){
            if(r.getValue()!=0)
                System.out.println(MovieDatabase.getTitle(r.getItem())+ " "+ r.getValue());
        }
    }

    public static void printSimilarRatingsByYearAfterAndMinutes(){
        ForthRatings test = new ForthRatings();

        System.out.println(RaterDatabase.size());

        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(80, 100));
        filter.addFilter(new YearAfterFilter(2000));

        ArrayList<Rating> similarRatings = test.getSimilarRatingsByFilter("65", 10, 5, filter);

        for(Rating r : similarRatings){
            if(r.getValue()!=0)
                System.out.println(MovieDatabase.getTitle(r.getItem())+ " "+ r.getValue());
        }
    }

    public static void main(String[] args){
        MovieDatabase.initialise("ratedmoviesfull.csv");
        RaterDatabase.initialise("ratings.csv");
//        System.out.println(MovieDatabase.size());
//        new MovieRunnerSimilarRatings().printAverageRatingsByYearAfterAndGenre();
        printSimilarRatingsByYearAfterAndMinutes();
    }


}
