import java.text.DecimalFormat;

public class BusSim{

    //this is where the simulation parameters can be adjusted
    public static int BUS_COUNT = 8;
    public static int EXPRESS_BUS_COUNT = 0;
    public static int LOAD = 10;
    public static PQ agenda = new PQ();
    public static Stop[] stop = new Stop[30];
    public static Statistics stats = new Statistics();

    public static void main(String[] args){

        Bus[] bus = new Bus[BUS_COUNT];
        BusEvent[] busEvents = new BusEvent[BUS_COUNT];
        RiderEvent riderEvent = new RiderEvent();
        Bus[] expressBus = new Bus[EXPRESS_BUS_COUNT];
        BusEvent[] expressBusEvents = new BusEvent[EXPRESS_BUS_COUNT];

        //creating stop Q's for all the bus stops
        for(int i = 0; i < stop.length; i++){
            stop[i] = new Stop();
        }

        //create buses(regular and express) for the bus array
        //create bus events for all the buses
        //add events to the agenda
        for(int j = 0; j < bus.length; j++){
            bus[j] = new Bus((30/bus.length) * j, false);
            busEvents[j] = new BusEvent(bus[j]);
            agenda.add(busEvents[j], 0);
        }
        for(int k = 0; k < expressBus.length; k++){
            expressBus[k] = new Bus(4 * k, true);
            expressBusEvents[k] = new BusEvent(expressBus[k]);
            agenda.add(expressBusEvents[k], 0);
        }

        //adding rider event
        agenda.add(riderEvent, 0);

        //running the simulation for 4 hours
        while(agenda.getCurrentTime() < 14400){
            agenda.remove().run();
            stats.maxQLength(stop);
        }

        //printing out relevant stats
        DecimalFormat df = new DecimalFormat("#####.##");
        System.out.println("");
        System.out.println("Average bus capacity is: " + df.format(stats.averageBusCapacity()));
        System.out.println("The max stop queue length is: " + stats.maxQLength(stop));
        System.out.println("Average rider wait time is: " + df.format(stats.averageWaitTime()) + " seconds or " + df.format(stats.averageWaitTime() / (double)60) + " minutes" );
        System.out.println("The max rider wait time is: " + df.format(stats.maxWaitTime(0)) + " seconds or " + df.format(stats.maxWaitTime(0) / (double)60) + " minutes");
        System.out.println("The average total trip time is: " + df.format(stats.averageTotalTripTime()) + " seconds or " + df.format(stats.averageTotalTripTime() / (double)60) + " minutes");
        System.out.println("The total riders are: " + stats.getTotalRiders());
    }
}