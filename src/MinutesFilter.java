public class MinutesFilter implements Filter{
    private int minTime;
    private int maxTime;

    public MinutesFilter(int min,int max){
        minTime = min;
        maxTime = max;
    }

    public boolean satisfies(String id){
        int x = MovieDatabase.getMinutes(id);
        if(x>=minTime && x<=maxTime)
            return true;
        return false;
    }
}
