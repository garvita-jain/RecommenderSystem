import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;

    public SecondRatings(String movieFile, String ratingsFile){
        FirstRatings file = new FirstRatings();
        myMovies = file.loadMovies(movieFile);
        myRaters = file.loadRaters(ratingsFile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return  myRaters.size();
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

        for(Movie m : myMovies){
            double avgRating = getAverageByID(m.getId(), minimalRaters);
            if(avgRating!=0.0)
                list.add(new Rating(m.getTitle(), avgRating));
        }

        return list;
    }

    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getId().compareTo(id)==0)
                return m.getTitle();
        }

        return "Movie ID not found!";
    }

    public String getID(String title){
        for(Movie m : myMovies){
            if(m.getTitle().compareTo(title)==0)
                return m.getId();
        }

        return "NO SUCH TITLE";
    }
}
