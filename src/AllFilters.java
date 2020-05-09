import java.util.ArrayList;

public class AllFilters implements Filter {
    ArrayList<Filter> allFilters;

    public AllFilters(){
        allFilters = new ArrayList<>();
    }

    public void addFilter(Filter f){
        allFilters.add(f);
    }

    @Override
    public boolean satisfies(String id){
        for(Filter f : allFilters){
            if(!f.satisfies(id))
                return false;
        }
        return true;
    }
}
