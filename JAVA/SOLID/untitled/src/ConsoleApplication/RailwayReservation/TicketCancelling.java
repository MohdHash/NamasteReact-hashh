package ConsoleApplication.RailwayReservation;


import java.util.HashMap;
import java.util.Map;

public class TicketCancelling {

  private static Character preferenceTracker = null;
   private static int CancelledseatNumber = 0;

    private static Map<Integer,Character> seatWithBerthMap= new HashMap<>();

    public static String cancelling(int id){

        for(Passenger p : TicketBooking.confirmedList){
            if(p.getId() == id){
                cancel(p);
                return "success";
            }
        }

        for(Passenger p : TicketBooking.RACqueue){
            if(p.getId() == id) {
                cancel(p);
                return "success";
            }
        }

        for(Passenger p : TicketBooking.waitingList){
            if(p.getId() == id){
                cancel(p);
                return "success";
            }
        }

        return "Invalid id";
    }

    private static void cancel(Passenger p) {
        if(p.getTicketType().equals("Berth")){
            preferenceTracker = p.getPreference();
            CancelledseatNumber = p.getSeatNumber();

            seatWithBerthMap.put(CancelledseatNumber,preferenceTracker);

            deletefromAllList(p);
            addRactoBerth(TicketBooking.RACqueue.poll());
            addWaitingtoRAC(TicketBooking.waitingList.poll());
        }else if(p.getTicketType().equals("RAC")){
            TicketBooking.RACqueue.remove(p);
            addWaitingtoRAC(TicketBooking.waitingList.poll());
        }else{
            TicketBooking.waitingList.remove(p);
        }
    }

    private static void addWaitingtoRAC(Passenger p) {
        if(p != null){
            TicketBooking.RACqueue.offer(p);
            p.setTicketType("RAC");
        }
    }

    private static void addRactoBerth(Passenger p) {
        if(p != null){
            p.setTicketType("Berth");
            p.setSeatNumber(CancelledseatNumber);
            p.setPreference(preferenceTracker);

            if(p.getPreference() == 'U'){
                TicketBooking.upperList.add(p);
            }else if(p.getPreference() == 'M'){
                TicketBooking.middleList.add(p);
            }else{
                TicketBooking.lowerList.add(p);
            }

            seatWithBerthMap.remove(CancelledseatNumber);
            TicketBooking.confirmedList.add(p);
        }
    }

    private static void deletefromAllList(Passenger p) {
        TicketBooking.confirmedList.remove(p);
        TicketBooking.upperList.remove(p);
        TicketBooking.middleList.remove(p);
        TicketBooking.lowerList.remove(p);
    }

    public static Map<Integer,Character> getSeatWithBerthMap(){
        return seatWithBerthMap;
    }


}
