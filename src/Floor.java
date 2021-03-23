import java.util.LinkedList;

public class Floor {
    private LinkedList<Passenger> passengerWaitingList;


    public Floor(){
        this.passengerWaitingList = new LinkedList<>();
    }

    public LinkedList<Passenger> getPassengerWaitingList() {
        return passengerWaitingList;
    }

    public void addToWaitList(Passenger passenger){
        passengerWaitingList.add(passenger);
    }

}
