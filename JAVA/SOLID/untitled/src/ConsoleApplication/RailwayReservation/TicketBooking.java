package ConsoleApplication.RailwayReservation;

import java.sql.Array;
import java.util.ArrayList;
import java.util.*;

public class TicketBooking {
    private static int berthLimit = 2;
    private static int RAClimit = 1;
    private static int waitingLimit = 1;

    private static int upperSeatNumber = 1;
    private static int middleSeatNumber = 2;
    private  static  int lowerSeatNumber = 3;


    static List<Passenger> confirmedList = new ArrayList<>();

    static  List<Passenger> upperList = new ArrayList<>();
    static  List<Passenger> middleList = new ArrayList<>();
    static List<Passenger> lowerList = new ArrayList<>();

    static Queue<Passenger> RACqueue = new LinkedList<>();
    static Queue<Passenger> waitingList = new LinkedList<>();

    public static void booking(Passenger p){
        if(checkAvailability(p)){
            System.out.println("Ticket Booking success for id: " + p.getId());
            p.setTicketType("Berth");
            confirmedList.add(p);
        }else if(upperList.size() == berthLimit && middleList.size() == berthLimit && lowerList.size() == berthLimit){
            if(updateRac(p)){
                System.out.println("Added to RAC, your id: "+ p.getId());
            }else if(waitingListUpdate(p)){
                System.out.println("Added to waiting list, your id: " + p.getId());
            }else{
                p.setId(p.getId()-1);
                System.out.println("Ticket not available");
            }
        }else{
            p.setId(p.getId()-1);
            System.out.println(p.getPreference()+" is not available");
            availableList();
        }
    }

    private static void availableList() {
        System.out.println("Check out the number of seats");
        System.out.println("Upper berth: "+ (berthLimit - upperList.size()));
        System.out.println("Middle berth: "+ (berthLimit - middleList.size()));
        System.out.println("Lower berth: "+ (berthLimit - lowerList.size()));
    }

    private static boolean waitingListUpdate(Passenger p) {
        if(waitingList.size() < waitingLimit){
            waitingList.offer(p);
            p.setTicketType("waiting list");
            return true;
        }
        return  false;
    }

    private static boolean updateRac(Passenger p) {
        if(RACqueue.size() < RAClimit){
            RACqueue.offer(p);
            p.setTicketType("RAC");
            return true;
        }
        return false;
    }

    private static boolean checkAvailability(Passenger p) {
        Map<Integer,Character> seatWithBerthMap = TicketCancelling.getSeatWithBerthMap();
        if(p.getPreference() == 'U'){
            if(upperList.size() < berthLimit){  // if the upperbirth is not full and the map has some cancelled seats
                if(!seatWithBerthMap.isEmpty()){      // then definitely the map will have the preference that the passenger wants
                    AssigntheCancelled(seatWithBerthMap , p);
                }else{
                    p.setSeatNumber(upperSeatNumber);
                    upperSeatNumber+=3;
                }
                upperList.add(p);
                return true;
            }
        }else if(p.getPreference() == 'M'){
            if(middleList.size() < berthLimit){
                if(!seatWithBerthMap.isEmpty()){
                    AssigntheCancelled(seatWithBerthMap , p);
                }else{
                    p.setSeatNumber(middleSeatNumber);
                    middleSeatNumber+=3;
                }
                middleList.add(p);
                return true;
            }
        }else{
            if(lowerList.size() < berthLimit){
                if(!seatWithBerthMap.isEmpty()){
                    AssigntheCancelled(seatWithBerthMap , p);
                }else{
                    p.setSeatNumber(lowerSeatNumber);
                    lowerSeatNumber+=3;
                }
                lowerList.add(p);
                return true;
            }
        }
        return false;
    }

    private static void AssigntheCancelled(Map<Integer, Character> map, Passenger p) {
        int seatNumber = getSeatFromMap(map , p.getPreference());
        p.setSeatNumber(seatNumber);
        map.remove(seatNumber);
    }

    private static int getSeatFromMap(Map<Integer, Character> map, char preference) {
        int seatNumber = 0;

        for(Map.Entry<Integer,Character> e : map.entrySet()){
            if(e.getValue() == preference){
                seatNumber = (int)e.getKey();
                break;
            }
        }

        return seatNumber;
    }


}
