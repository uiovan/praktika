public class Earthquake {
    private String id;
    private int depth;
    private String magnitudeType;
    private Double magnitude;
    private String state;
    private int year;
    private String overTime;

    public Earthquake(String id, int depth, String magnitudeType, Double magnitude, String state, int year, String over_time){
        state = state.toUpperCase();
        this.id = id;
        this.depth = depth;
        this.magnitudeType = magnitudeType;
        this.magnitude = magnitude;
        this.state = state;
        this.year = year;
        this.overTime = over_time;
    }

    public String toString(){
        return "'"+id+"',"+Integer.toString(depth)+",'"+magnitudeType+"',"+Double.toString(magnitude)+",'"+state+"',"+year+",'"+overTime+"');";
    }

    public String getId() {
        return id;
    }

    public int getDepth() {
        return depth;
    }

    public String getMagnitudeType() {
        return magnitudeType;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getState() {
        return state;
    }

    public int getYear() {
        return year;
    }
    public String getOverTime(){
        return overTime;
    }
}
