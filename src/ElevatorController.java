import java.util.*;

public class ElevatorController {

    Building building;
    int idleRequest = 0;

    public ElevatorController(Building building) {
        this.building = building;
    }

    /**
     * Simulation of elevator moving to each floor.
     */
    public void moveElevator() throws InterruptedException {

        //print current floor.
        int currentFloor = building.getElevator(1).getCurrentFloor();
        System.out.println("Current floor: " + currentFloor);

        //print number of passengers exiting floor.
        List<Passenger> passengers = building.getElevator(1).getPassengersInElevator();
        int exiting = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getDestination_floor() == currentFloor) {
                exiting++;
            }
        }
        System.out.println("Passengers exiting elevator: " + exiting);

        //remove passengers from elevator if correct floor.
        passengers.removeIf(passenger -> passenger.getDestination_floor() == currentFloor);

        //print list of waiting passengers.
        List<Passenger> waitingPassengers = building.getFloor(currentFloor).getPassengerWaitingList();
        int sizeOfList = waitingPassengers.size();
        System.out.println("Currently waiting passengers: " + waitingPassengers.size());

        //check to see if elevator is empty.
        if (passengers.isEmpty()) {
            //check to see if passengers waiting on floor.
            if (!(waitingPassengers.isEmpty())) {
                //peek first passenger to determine direction.
                int firstWaitingPas = Objects.requireNonNull(building.getFloor(currentFloor).getPassengerWaitingList()
                        .peekFirst()).getDestination_floor();
                //if first passenger is higher than currentFloor, going up.
                if (firstWaitingPas > currentFloor) {
                    building.getElevator(1).setElevatorDirection(ElevatorDirection.Up);
                    int count = 0;
                    //add passengers to elevator, remove from waitingList
                    while (building.getElevator(1).getPassengersInElevator().size() <= building.
                            getElevator(1).getCapacity() && (count < sizeOfList)) {
                        //additional list to avoid concurrency issues with removing and adding to LinkedList at the same
                        //time.
                        List<Passenger> temp = new ArrayList<>(waitingPassengers);
                        for (Passenger passenger : temp) {
                            if (passenger.getDestination_floor() > currentFloor) {
                                passengers.add(passenger);
                                waitingPassengers.remove(passenger);
                            }
                            count++;
                        }
                    }
                    //sort passengers in elevator in ascending order and print size.
                    Collections.sort(building.getElevator(1).getPassengersInElevator());
                    int inElevator = building.getElevator(1).getPassengersInElevator().size();
                    System.out.println("Passengers now in elevator " + inElevator);

                    //first waiting passenger going down and no one in lift.
                } else {
                    building.getElevator(1).setElevatorDirection(ElevatorDirection.Down);
                    int count = 0;
                    while (building.getElevator(1).getPassengersInElevator().size() <= building.
                            getElevator(1).getCapacity() && (count < sizeOfList)) {
                        //additional list to avoid concurrency issues with removing and adding to LinkedList at the same
                        //time.
                        List<Passenger> temp = new ArrayList<>(waitingPassengers);
                        for (Passenger passenger : temp) {
                            if (passenger.getDestination_floor() < currentFloor) {
                                passengers.add(passenger);
                                waitingPassengers.remove(passenger);
                            }
                            count++;
                        }
                    }
                    //sort passengers in elevator in descending order and print size.
                    building.getElevator(1).getPassengersInElevator().sort(Collections.reverseOrder());
                    int inElevator = building.getElevator(1).getPassengersInElevator().size();
                    System.out.println("Passengers now in elevator: " + inElevator);
                }
                //passengers empty and floor level empty
                //check to see if there is a floor request - direction continues
                //if not, set idle
            } else {
                    if(idleRequest == 0){
                        idleRequest = lookForRequest();
                    }
                    if(building.getElevator(1).getElevatorDirection() == ElevatorDirection.Up){
                        building.getElevator(1).incrementFloor();
                        System.out.println("Current floor: " + currentFloor);
                        System.out.println("Currently waiting passengers: " + waitingPassengers.size());
                        int inElevator = building.getElevator(1).getPassengersInElevator().size();
                        System.out.println("Passengers now in elevator " + inElevator);
                    }
                    else{
                        building.getElevator(1).decrementFloor();
                        System.out.println("Current floor: " + currentFloor);
                        System.out.println("Currently waiting passengers: " + waitingPassengers.size());
                        int inElevator = building.getElevator(1).getPassengersInElevator().size();
                        System.out.println("Passengers now in elevator " + inElevator);
                    }
                }
            //Elevator has passengers
        } else {
            int count = 0;
            while (building.getElevator(1).getPassengersInElevator().size() <= building.
                    getElevator(1).getCapacity() && (count < sizeOfList)) {
                if (building.getElevator(1).getElevatorDirection() == ElevatorDirection.Up) {
                    for (Passenger passenger : waitingPassengers) {
                        if (passenger.getDestination_floor() > currentFloor) {
                            passengers.add(passenger);
                            waitingPassengers.remove(passenger);
                        }
                        count++;
                    }
                } else if (building.getElevator(1).getElevatorDirection() == ElevatorDirection.Down) {
                    for (Passenger passenger : waitingPassengers) {
                        if (passenger.getDestination_floor() < currentFloor) {
                            passengers.add(passenger);
                            waitingPassengers.remove(passenger);
                        }
                        count++;
                    }
                }
            }
            int inElevator = building.getElevator(1).getPassengersInElevator().size();
            System.out.println("Passengers now in elevator " + inElevator);
        }
        if (building.getElevator(1).getElevatorDirection() == ElevatorDirection.Up) {
            building.getElevator(1).incrementFloor();
        } else if (building.getElevator(1).getElevatorDirection() == ElevatorDirection.Down
                && currentFloor > 1) {
            building.getElevator(1).decrementFloor();
        }
    }

    //compare current floor to next closest floor request using the difference of going up or down if middle floors.
    public int lookForRequest() {
        int currentFloor = building.getElevator(1).getCurrentFloor();
        int temp = currentFloor;
        boolean containsRequest = !(building.getFloor(currentFloor).getPassengerWaitingList().isEmpty());
        int upwardsCount = 0;
        int downwardsCount = 0;
        int maxFloors = building.getMaxFloors();
        int minFloor = 1;
        int upwardRequest;
        int downwardRequest;

        //upwards count
        while ((currentFloor < maxFloors) || (!(containsRequest))) {
            currentFloor++;
            upwardsCount++;
        }
        upwardRequest = currentFloor;
        building.getElevator(1).setCurrentFloor(temp);
        //downwards count
        while (currentFloor > minFloor || !(containsRequest)) {
            currentFloor--;
            downwardsCount++;
        }
        downwardRequest = currentFloor;
        building.getElevator(1).setCurrentFloor(temp);

        if (upwardsCount < downwardsCount) {
            building.getElevator(1).setElevatorDirection(ElevatorDirection.Up);
            return upwardRequest;
        } else if (currentFloor == building.getMaxFloors()) {
            building.getElevator(1).setElevatorDirection(ElevatorDirection.Down);
            return downwardRequest;
        } else if (currentFloor == 1) {
            building.getElevator(1).setElevatorDirection(ElevatorDirection.Up);
            return upwardRequest;
        } else {
            building.getElevator(1).setElevatorDirection(ElevatorDirection.Down);
            return downwardRequest;
        }
    }
}


