import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

        ArrayList<Rating> avgRatings = test.getAverageRatings(1);

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0)
                System.out.println(r.toString());
        }
    }

    public void printAverageRatingsByYear(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

        ArrayList<Rating> avgRatings = test.getAverageRatingByFilter(1, new YearAfterFilter(2000));

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0)
                System.out.println(r.toString());
        }
    }

    public void printAverageRatingsByGenre(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

        ArrayList<Rating> avgRatings = test.getAverageRatingByFilter(1, new GenreFilter("Crime"));

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0) {
                System.out.println(r.toString());
                System.out.println("   "+MovieDatabase.getGenres(r.getItem()));
            }
        }
    }

    public void printAverageRatingsByMinutes(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

        ArrayList<Rating> avgRatings = test.getAverageRatingByFilter(1, new MinutesFilter(110, 170));

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0) {
                System.out.println(r.toString());
                System.out.println("Time :   "+MovieDatabase.getMinutes(r.getItem()));
            }
        }
    }

    public void printAverageRatingsByDirector(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

        ArrayList<Rating> avgRatings = test.getAverageRatingByFilter(1, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0) {
                System.out.println(r.toString());
                System.out.println("   "+MovieDatabase.getDirector(r.getItem()));
            }
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

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

    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings test = new ThirdRatings("data/ratings_short.csv");

        System.out.println(test.getRaterSize());

        AllFilters filter = new AllFilters();
        filter.addFilter(new MinutesFilter(30, 170));
        filter.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));


        ArrayList<Rating> avgRatings = test.getAverageRatingByFilter(1, filter);

        Collections.sort(avgRatings);

        for(Rating r : avgRatings){
            if(r.getValue()!=0) {
                System.out.println(r.toString());
                System.out.println("   "+MovieDatabase.getDirector(r.getItem()));
            }
        }
    }

}
