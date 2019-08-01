Discrete Bus Simulation

Authors: Tia Hannes & Thomas Rooney
Emails: hanne123@umn.edu & roone194@umn.edu

To run this project on a new machine, download all the files in the same directory, navigate the terminal of your
machine, and then compile and run as follows:

    javac BusSim.java
    java BusSim

Project Hierarchy/Data Structures used:

This simulation is dependent on the use of an agenda which implements a priority queue PQ(). Thus "Events" are added to the
agenda, and are finally executed once they are removed from the agenda in BusSim. It is in BusSim that all bus objects (both
express and regular buses) are created. Stop objects are also created in BusSim and stored in an array so they are easily
accessible. Statistics are then printed out in BusSim.

It must be noted that in order for PQ() to run, the following classes must also be downloaded in the same directory, as PQ
references them:

    Q1Gen
    NGen
    QGen
    Segment

The Rider Event class creates rider objects and stores information regarding a riders destination, pick-up stop, and arrival time. From
this information statistics can be calculated regarding riders wait time. Our rider event creates one rider and selects a random bus stop and destination based on the bus stop it was spawned in, after the rider is created it is placed in the proper bus stop queue.

The Bus class creates bus objects. Buses are essentially ArrayLists of Riders. Thus, bus contains methods to both add and remove riders
to the ArrayList of Riders. An ArrayList was chosen as the underlying data structure for the Bus class because it allowed for better
time complexity.

The Stop class creates stop queues which is implemented with an ArrayList of Riders as the underlying data structure. The stop queue array in
BusSim stores these stop queues. An ArrayList was chosen as the underlying data structure for the Stop class because it enabled all of Stop's
methods to have a time complexity of O(1).Additionally, the ArrayList was chosen as the underlying data structure since it allows for dynamic sizing,
which allowed for cleaner, simpler code. The stop queue array in BusSim was implemented as an array and not an ArrayList because the size
of the array does not change throughout the project, this dynamic sizing is unecessary.

Both BusEvent and RiderEvent implement the Event interface. This is so when either RiderEvent or a BusEvent are placed on the agenda,
the run() method will be implemented correctly. A BusEvent occurs when a bus has reached a new stop, therefore the
BusEvent constructor takes in a bus. When a BusEvent runs it removes all riders that wish to get off, board all riders who wish to get
on the bus, and moves to the next stop and therefore creates a new BusEvent while passing in the same bus with the updated stop.
The RiderEvent spawns riders to a random bus stop and then adds them to the correct bus stop queue.

Statistics is used to generate stats about the simulation. It was found to simplest to instantiate a statistics object in BusSim that is public
and static so it can be used throughout the entire project. All of the actual calculations are generated in the Statistics class, however,
some variables that are instantiated in Statistics such as totalRiders are made public and static so these values can be incremented while the simulation
runs. Incrementing statistic methods to update totals are called by through referencing the static statistic object in both RiderEvent and
BusEvent. Totals are printed to the terminal in BusSim, after all events have been removed from the agenda.

The variables in BusSim for regular bus count, express bus count, and load are made global in BusSim so they can easily be adjusted for
testing. Thus these must be hardcoded in, and are not adjustable via the terminal.

There are no known bugs or issues.