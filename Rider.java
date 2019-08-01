public class Rider{

    int pickUp;
    int dropOff;
    double arrival;

    public Rider(int pickUp, int dropOff, double arrival){
        this.pickUp = pickUp;
        this.dropOff = dropOff;
        this.arrival = arrival;
    }

    public int getPickUp(){ return(pickUp); }

    public int getDropOff(){
        return(dropOff);
    }

    public double getArrival(){
        return(arrival);
    }
}