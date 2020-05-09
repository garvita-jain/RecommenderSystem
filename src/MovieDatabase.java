import java.util.ArrayList;
import java.util.HashMap;

public class MovieDatabase {
    public static HashMap<String, Movie> ourMovies;
    private static ArrayList<Filter> filters;

    private static void initialise(){
        if(ourMovies==null){
            ourMovies = new HashMap<>();
            ourMovies = loadMovies("data/ratedmoviesfull.csv");
        }
    }

    public static void initialise(String movieFile){
        if(ourMovies==null) {
            ourMovies = new HashMap<>();
            ourMovies = loadMovies("data/" + movieFile);
        }
    }

    private static HashMap<String, Movie> loadMovies(String filename){
        HashMap<String, Movie> data = new HashMap<>();

        FirstRatings file = new FirstRatings();
        for(Movie m : file.loadMovies(filename))
            data.put(m.getId(), m);

        return data;
    }

    public static boolean containsID(String id){
        initialise();
        return ourMovies.containsKey(id);
    }

    public static String getTitle(String id) {
        initialise();
        return ourMovies.get(id).getTitle();
    }

    public static int getYear(String id) {
        initialise();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id) {
        initialise();
        return ourMovies.get(id).getGenres();
    }

    public static String getDirector(String id) {
        initialise();
        return ourMovies.get(id).getDirector();
    }

    public static String getCountry(String id) {
        initialise();
        return ourMovies.get(id).getCountry();
    }

    public static int getMinutes(String id) {
        initialise();
        return ourMovies.get(id).getMinutes();
    }

    public static String getPoster(String id) {
        initialise();
        return ourMovies.get(id).getPoster();
    }

    public static int size(){
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f){
        initialise();
        ArrayList<String> list = new ArrayList<>();
        for(String id : ourMovies.keySet()){
            //System.out.println(MovieDatabase.ourMovies.get(id).toString());
            if(f.satisfies(id))
                list.add(id);
        }
        return list;
    }

    public static void addFilter(Filter f){
        filters.add(f);
    }

    public static boolean satisfies(String movieID){
        for(Filter f : filters){
            if(!f.satisfies(movieID))
                return false;
        }
        return true;
    }


}
