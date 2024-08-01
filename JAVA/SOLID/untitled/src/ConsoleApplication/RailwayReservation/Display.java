package ConsoleApplication.RailwayReservation;

public class Display {
    // singleton class
    private static Display instance = null;

    public static Display getInstance() {
        if(instance == null){
            return new Display();
        }

        return instance;
    }

    public void displayConfirmed(){
        System.out.println("--------------------------");
        for(Passenger p : TicketBooking.confirmedList){
            System.out.println(p.toString());
            System.out.println("--------------------------");
        }
    }

    public void displayRAC(){
        System.out.println("--------------------------");
        for(Passenger p : TicketBooking.RACqueue){
            System.out.println(p.toString());
            System.out.println("--------------------------");
        }
    }

    public void displayWaiting(){
        System.out.println("--------------------------");
        for(Passenger p : TicketBooking.waitingList){
            System.out.println(p.toString());
            System.out.println("--------------------------");
        }
    }
}
