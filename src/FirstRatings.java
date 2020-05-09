import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> data = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filename);

            CSVReader csvReader = new CSVReader(fileReader);

            String[] record;

            String[] recordx = csvReader.readNext();

            while ((record = csvReader.readNext())!=null){
                Movie movie = new Movie(record[0], record[1], Integer.parseInt(record[2]), record[4], record[5], record[3], Integer.parseInt(record[6]), record[7]);
                data.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<EfficientRater> loadRaters(String filename){
        ArrayList<EfficientRater> data = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filename);

            CSVReader csvReader = new CSVReader(fileReader);

            String[] record;

            String[] recordx = csvReader.readNext();

            HashMap<String, ArrayList<Rating>> raters = new HashMap<>();

            while ((record = csvReader.readNext())!=null){
                String id = record[0];
                Rating r = new Rating(record[1], Double.parseDouble(record[2]));
                ArrayList<Rating> x;
                if(raters.containsKey(id)){
                    x = raters.get(id);
                    x.add(r);
                    raters.replace(id, x);
                }
                else{
                    x = new ArrayList<>();
                    x.add(r);
                    raters.put(id, x);
                }
            }

            for(String id : raters.keySet())
                data.add(new EfficientRater(id, raters.get(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
