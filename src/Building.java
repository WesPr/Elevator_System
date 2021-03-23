import java.util.HashMap;

public class Building {
    private final int maxFloors;
    private HashMap<Integer, Elevator> elevators;
    private HashMap<Integer, Floor> floors;

    /**
     *
     * @param numOfFloors
     * @param numOfElevators
     *
     */
    public Building(int numOfFloors, int numOfElevators) {
        this.floors = new HashMap<>();
        this.maxFloors = numOfFloors;
        this.elevators = new HashMap<>();

        for (int i = 1; i <= numOfElevators; i++) {
            elevators.put(i, new Elevator());
        }
        for (int i = 1; i <= numOfFloors; i++) {
            floors.put(i, new Floor());
        }
    }


    public Elevator getElevator(int elevatorNumber) {
        return elevators.get(elevatorNumber);
    }

    public Floor getFloor(int floorNumber){
        return floors.get(floorNumber);
    }

    public int getMaxFloors() {
        return maxFloors;
    }
}
