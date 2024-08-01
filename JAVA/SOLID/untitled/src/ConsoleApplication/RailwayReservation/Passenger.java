package ConsoleApplication.RailwayReservation;

public class Passenger {
    private static int idProvider = 0;
    private String name ;
    private int age;
    private int id;
    private char preference;
    private String ticketType;
    private int seatNumber;

    public Passenger(String name , int age , char preference){
        this.name = name;
        this.age = age;
        this.preference = preference;
        this.id = ++idProvider;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        Passenger.idProvider = id;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public char getPreference(){
        return this.preference;
    }

    public String getTicketType(){
        return this.ticketType;
    }

    public void setTicketType(String ticketType){
        this.ticketType = ticketType;
    }

    public int getSeatNumber(){
        return this.seatNumber;
    }

    public void setSeatNumber(int seatNumber){
        this.seatNumber = seatNumber;
    }

    public void setPreference(char preference){
        this.preference = preference;
    }

    @Override
    public String toString(){
        return "Passenger Ticket id: "+id+" \nPassenger Name: "+name+"\nPassenger age: "+age
                +"\nPassenger Seat no: "+seatNumber+"\nPassenger preference: "+preference;
    }


}
