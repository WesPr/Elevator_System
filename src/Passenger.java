public class Passenger implements Comparable<Passenger> {

    private int destination_floor;

    public Passenger(int destination_floor){
        this.destination_floor = destination_floor;
    }

    public int getDestination_floor() {
        return destination_floor;
    }

    public void setDestination_floor(int destination_floor) {
        this.destination_floor = destination_floor;
    }

    @Override
    public int compareTo(Passenger o) {
        if(getDestination_floor() > o.getDestination_floor()){
            return 1;
        }
        else if(getDestination_floor() < o.getDestination_floor()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
