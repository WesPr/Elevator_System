public class Main {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Instantiating building, passengers and passengers requests on each floor.
         */
        Building building1 = new Building(4,1);
        building1.getElevator(1).setCapacity(4);

        Passenger passenger1 = new Passenger(3);
        Passenger passenger2 = new Passenger(2);

        Passenger passenger3 = new Passenger(4);
        Passenger passenger4 = new Passenger(1);

        Passenger passenger5 = new Passenger(2);
        Passenger passenger6 = new Passenger(3);
        Passenger passenger7 = new Passenger(1);

        Passenger passenger8 = new Passenger(4);


        building1.getFloor(1).addToWaitList(passenger1);
        building1.getFloor(1).addToWaitList(passenger2);

        building1.getFloor(3).addToWaitList(passenger3);
        building1.getFloor(3).addToWaitList(passenger4);

        building1.getFloor(4).addToWaitList(passenger5);
        building1.getFloor(4).addToWaitList(passenger6);
        building1.getFloor(4).addToWaitList(passenger7);

        building1.getFloor(3).addToWaitList(passenger8);

        ElevatorController ev = new ElevatorController(building1);


        /**
         *  Running elevator
         */
        for(int i = 0; i < 10; i++){
            ev.moveElevator();
        }

    }
}
