public class BusEvent implements Event {

    Bus bus;
    int currentStop;
    boolean express;
    int[] expressStops = {0, 1, 4, 8, 12, 14, 15, 16, 20, 24, 28, 29};

    public BusEvent(Bus bus) {
        this.bus = bus;
        this.currentStop = bus.getStop();
        this.express = bus.getExpress();
    }

    public int getCurrentStop() {
        return (currentStop);
    }

    public void run() {

        //Unload riders for current stop
        int removeCount = bus.removeRidersAtStop();

        //load riders at current stop
        int loadCount = 0;
        Rider rider;
        double waitTime;
        if (!(express)) {
            while (!(bus.isFull()) && !(BusSim.stop[currentStop].isEmpty())) {
                rider = BusSim.stop[currentStop].dQ();
                waitTime = BusSim.agenda.getCurrentTime() - rider.getArrival();
                BusSim.stats.updateWaitStats(waitTime);
                BusSim.stats.maxWaitTime(waitTime);
                bus.addRider(rider);
                loadCount++;
            }
        } else {
            Stop temp = new Stop();
            boolean addedToBus = false;
            while (!(bus.isFull()) && !(BusSim.stop[currentStop].isEmpty())) {
                rider = BusSim.stop[currentStop].dQ();

                for(int i = 0; i < expressStops.length && !(addedToBus); i++){
                    if(rider.getDropOff() == expressStops[i]) {
                        waitTime = BusSim.agenda.getCurrentTime() - rider.getArrival();
                        BusSim.stats.updateWaitStats(waitTime);
                        BusSim.stats.maxWaitTime(waitTime);
                        bus.addRider(rider);
                        loadCount++;
                        addedToBus = true;
                    }
                }

                if(!(addedToBus)) {
                    temp.nQ(rider);
                }
            }

            while (!(temp.isEmpty())) {
                BusSim.stop[currentStop].nQ(temp.dQ());
            }
        }


        //update current stop to be the next bus stop
        if (!(express)) {
            if (currentStop != 29) {
                currentStop++;
            } else {
                currentStop = 0;
            }
        } else {
            if(currentStop == 29){
                currentStop = 0;
            } else {
                int index = -1;
                for(int j = 0; j < expressStops.length && index == -1; j++){
                    if(currentStop == expressStops[j]){
                        index = j;
                        currentStop = expressStops[j+1];
                    }
                }
            }
        }

        //calculate the time
        int passengerTime = (2 * removeCount) + (3 * loadCount);
        int time = 240;
        if(express){
            if(currentStop == 0 || currentStop == 1 || currentStop == 15 || currentStop == 16 || currentStop == 29){
            } else if(currentStop == 14){
                time *= 2;
            } else if(currentStop == 3) {
                time *= 3;
            } else {
                time *= 4;
            }
        }

        if(passengerTime > 15) {
            time += passengerTime;
        } else {
            time += 15;
        }

        BusSim.stats.updateCapacityStats(bus);

        bus.setStop(currentStop);

        //create new bus event to go to next stop with same bus
        BusEvent busEvent = new BusEvent(bus);

        //send bus to the next stop via the agenda
        BusSim.agenda.add(busEvent, time);

        //Printing stats to know if simulation is working properly
       if(express){
           //System.out.println("Express Bus Event, Current Stop: "+ currentStop +", Time is: "+ BusSim.agenda.getCurrentTime()+ ", Next Bus Stop in: "+ time);
       } else {
           //System.out.println("Bus Event, Current Stop: "+ currentStop +", Time is: "+BusSim.agenda.getCurrentTime()+", Next Bus Stop in: "+ time);
       }
    }
}