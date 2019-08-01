import java.util.Random;

public class RiderEvent implements Event{

    Random rand = new Random();
    int[] stopSelect = {0, 0, 1, 1, 29, 29, 14, 14, 15, 15, 16, 16, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28};
    int[] eastBound = {1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 14, 15, 15};
    int[] westBound = {16, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 29, 0, 0};
    double[] arrivalPercents = {.75, .75, .5, .5, .5, .2, .2, .2, .2, 0, 0, -.2, -.2, -.2, -.5, -.5, -.5, -.75, -.75};
    int[] popStop = {0, 1, 29, 14, 15, 16};
    int currentStop;

    public RiderEvent(){
        currentStop = stopSelect[rand.nextInt(stopSelect.length)];
    }

    public int getCurrentStop(){
        return(currentStop);
    }

    public void run(){

        currentStop = stopSelect[rand.nextInt(stopSelect.length)];

        //assigning the rider the proper destination based on where they are picked up
        Random r = new Random();
        int destination = -1;
        if(currentStop >= 0 && currentStop <=14){
            while(!(destination > currentStop)){
                destination = eastBound[r.nextInt(eastBound.length)];
            }
        } else if(currentStop > 14 && currentStop <= 29) {
            while(!(destination > currentStop) && !(destination == 0)){
                destination = westBound[r.nextInt(westBound.length)];
            }
        }

        //creating the proper time to add to agenda
        double time = arrivalPercents[r.nextInt(19)];
        boolean popular = false;

        for(int j = 0; j < popStop.length; j++){
            if(popStop[j] == destination){
                popular = true;
            }
        }

        if(time != 0){
            time = BusSim.LOAD + (time * BusSim.LOAD);
        } else {
            time = BusSim.LOAD;
        }

        if(popular){
            time = time / 2;
        }

        //create rider
        Rider rider = new Rider(currentStop, destination, BusSim.agenda.getCurrentTime());

        Statistics.totalRiders++;

        //place rider in the correct Stop Q
        BusSim.stop[currentStop].nQ(rider);

        //Rider event rescheduling itself
        BusSim.agenda.add(this, time);

        //Printing stats to know if simulation is working properly
       // System.out.println("Rider Event Current Stop: " + this.getCurrentStop() + ", Time is: "+ BusSim.agenda.getCurrentTime() + ", Next Passenger in: " + time);

    }
}