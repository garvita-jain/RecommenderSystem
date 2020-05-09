public class DirectorsFilter implements Filter {
    private String[] myDirector;

    public DirectorsFilter(String director){
        myDirector = director.split(",");
    }

    public boolean satisfies(String id){
        for(String d : myDirector){
            if(MovieDatabase.getDirector(id).contains(d))
                return true;
        }
        return false;
    }
}
