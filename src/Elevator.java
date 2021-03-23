import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int capacity;
    private int currentFloor;
    private ElevatorDirection elevatorDirection;
    private List<Passenger> passengersInElevator;

    public Elevator(){
        this.currentFloor = 1;
        this.elevatorDirection = ElevatorDirection.Idle;
        this.passengersInElevator= new ArrayList<>();
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public ElevatorDirection getElevatorDirection() {
        return elevatorDirection;
    }

    public void setElevatorDirection(ElevatorDirection elevatorDirection) {
        this.elevatorDirection = elevatorDirection;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void addPassengerToElevator(Passenger passenger){
        passengersInElevator.add(passenger);
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Passenger> getPassengersInElevator() {
        return passengersInElevator;
    }

    public void incrementFloor() {
        this.currentFloor++;
    }

    public void decrementFloor() {
        this.currentFloor--;
    }
}
