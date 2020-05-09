public class Rating implements Comparable<Rating>{
    private String item;
    private double value;

    public Rating(String item, double value){
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        String x = value+" "+item;
        return x;
    }

    @Override
    public int compareTo(Rating r){
        if(value == r.getValue())
            return 0;
        else if(value > r.getValue())
            return 1;
        else
            return -1;
    }
}
