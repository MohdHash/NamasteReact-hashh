package ConsoleApplication.RailwayReservation;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("1.Book Ticket \n2.Cancel Ticket \n3.Display Confirmed \n4." +
                    "Display RAC List \n5.Display waiting List");

            int choice = s.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Enter name : ");
                    String name = s.next();
                    System.out.println("Enter age : ");
                    int age = s.nextInt();
                    System.out.println("Enter berth preference: ");
                    char prefernce = s.next().charAt(0);
                    if(prefernce == 'U' || prefernce == 'M' || prefernce == 'L') {
                        TicketBooking.booking(new Passenger(name,age,prefernce));
                        break;
                    }else {
                        System.out.println("Invalid berth preference");
                        break;
                    }
                }
                case 2:{
                    System.out.println("Enter the ticket id: ");
                    int id = s.nextInt();
                    System.out.println(TicketCancelling.cancelling(id));
                    break;
                }
                case 3:{
                    Display.getInstance().displayConfirmed();
                    break;
                }
                case 4:{
                    Display.getInstance().displayRAC();
                    break;
                }
                case 5:{
                    Display.getInstance().displayWaiting();
                    break;
                }
                default:{
                    System.out.println("Thank you!");
                    s.close();
                    flag = false;
                    break;
                }
            }
        }
    }
}
