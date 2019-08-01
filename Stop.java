/*
This is instantiable for each bus stop on Route 3
Each location has a queue associated with it, a name, a number to designate what stop it is
There is exactly 16 bus stops locations and 30 bus stops numbered and named.
*/

import java.util.*;

public class Stop{

    ArrayList<Rider> line;

    public Stop(){
       line = new ArrayList<Rider>();
    }

    public int getSize(){
        return(line.size());
    }

    public void nQ(Rider r){
        line.add(r);
    }

    public Rider dQ(){
        Rider top = line.get(0);
        line.remove(0);
        return(top);
    }

    public boolean isEmpty(){
        boolean empty = false;
        if(line.size() == 0){
            empty = true;
        }
        return(empty);
    }
}