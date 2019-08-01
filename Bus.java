import java.util.*;

public class Bus{

    boolean express;
    ArrayList<Rider> busList;
    int currentStop;

    public Bus(int currentStop, boolean express){
        this.currentStop = currentStop;
        busList = new ArrayList<Rider>();
        this.express = express;
    }

    public int getSize(){
        return(busList.size());
    }

    public int getStop(){
        return(currentStop);
    }

    public void setStop(int stop){
        currentStop = stop;
    }

    public boolean getExpress(){
        return(express);
    }

    public boolean addRider(Rider r){
        boolean added = false;

        if(busList.size() < 50){
            busList.add(r);
            added = true;
        }

        return(added);
    }

    public int removeRidersAtStop(){
        int removedRiders = 0;
        double tripTime;
        for(int i = 0; i < busList.size(); i++){
            if(busList.get(i).getDropOff() == currentStop){
                tripTime = BusSim.agenda.getCurrentTime() - busList.get(i).getArrival();
                BusSim.stats.updateTripStats(tripTime);
                busList.remove(i);
                removedRiders++;
            }
        }
        return(removedRiders);
    }

    public boolean isFull(){
        boolean full = false;
        if(busList.size() == 50){
            full = true;
        }
        return(full);
    }
}