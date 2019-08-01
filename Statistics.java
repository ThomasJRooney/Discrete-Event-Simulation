public class Statistics{

    int maxQ;
    double capacityRuns;
    double totalCapacity;
    double riderRuns;
    double totalWaitTime;
    double maxWait;
    double tripRuns;
    double totalTripTime;
    public static int totalRiders;

    public Statistics(){
        maxQ = 0;
        capacityRuns = 0;
        totalCapacity = 0;
        riderRuns = 0;
        totalWaitTime = 0;
        maxWait = 0;
        totalRiders = 1;
    }

    public int maxQLength(Stop[] stop){
        for(int i = 0; i < stop.length; i++){
            if(stop[i].getSize() > maxQ){
                maxQ = stop[i].getSize();
            }
        }
        return(maxQ);
    }

    public void updateCapacityStats(Bus bus){
        capacityRuns++;
        totalCapacity += bus.getSize();
    }

    public double averageBusCapacity(){
       double average = totalCapacity / capacityRuns;
       return(average);
    }

    public double maxWaitTime(double wait){
        if(wait > maxWait){
            maxWait = wait;
        }
        return(maxWait);
    }

    public void updateWaitStats(double wait){
        totalWaitTime += wait;
        riderRuns++;
    }

    public double averageWaitTime(){
        double average = totalWaitTime / riderRuns;
        return(average);

    }

    public void updateTripStats(double tripTime){
        totalTripTime += tripTime;
        tripRuns++;
    }

    public double averageTotalTripTime(){
        double average = totalTripTime / tripRuns;
        return(average);
    }

    public int getTotalRiders(){
        return(totalRiders);
    }

}