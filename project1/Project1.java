/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 *
 * @author meltem koc
 */
public class Project1 {

     public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int code = 0;
        ArrayList<Person> users = new ArrayList<>();
        ArrayList<Meeting> allMeetings = new ArrayList<>();
        Person currentUser = null;
        


        System.out.println("Create a list of users, -1 to continue with menu");
        do {
            System.out.println("enter username:");
            String name = s.nextLine();
            if (name.equals("-1")){
                break;
            }
            users.add(new Person(name));
        } while (true);

        do {
            printMenu();
            code = Integer.parseInt(s.nextLine());
            if (code == 0) {
                System.out.println("Enter user name to login");
                String name = s.nextLine();
                currentUser = search(users, name);
            }
            
            if (code == 1) {
                createMeeting(currentUser, allMeetings, users);
            }
           
            if (code == 2) {
                cancelMeeting(currentUser, allMeetings);
            }
           
            if (code == 3) {
                attendMeeting(allMeetings, currentUser);
            }
            
            if (code == 4) {
                leaveMeeting(s, currentUser);
            }
            if (code == 5) {
                currentUser.displayMyMeetings();
            }
            if (code == 6) {
                currentUser.displayMyOrganizations();
            }
            
            if (code == 7) {
                currentUser = null;
            }
        } while (code != 8);
        System.out.println("Bye");

    }

    public static void printMenu() {
        System.out.println("");
        System.out.println("0. Login");
        System.out.println("1. Create Host new Meeting");
        System.out.println("2. Cancel a meeting");
        System.out.println("3. Attend an existing meeting");
        System.out.println("4. Leave a meeting");
        System.out.println("5. Display My Meetings");
        System.out.println("6. Display Meetings organized by me");
        System.out.println("7. Logout");
        System.out.println("8. Exit");
        System.out.print("Your choice: ");
    }

    public static void createMeeting(Person currentUser, ArrayList<Meeting> allMeetings, ArrayList<Person> users) {
        Scanner scn= new Scanner(System.in);
        System.out.print("Enter name of the meeting: ");
        String meetingName = scn.nextLine();
        System.out.print("Enter a date (yyyy/mm/dd): ");
        String[] date = scn.nextLine().split("/");
       Person attendee = new Person(meetingName);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        Meeting m = new Meeting(meetingName, LocalDate.of(year, month, day), attendee, currentUser);
        currentUser.organizeMeeting(m);
        allMeetings.add(m);
        System.out.println(meetingName + " " + m.getDate() + " created successfully");
    }
    
    

    public static void attendMeeting(ArrayList<Meeting> allMeetings, Person currentUser) {
        Scanner scn= new Scanner(System.in);
        System.out.println(allMeetings);
        System.out.print("Event name you would like to attend: ");
        String meetingName = scn.nextLine();
        Meeting meeting = null;
        for (Meeting m : allMeetings){
            if (m.getName().equals(meetingName))
                meeting = m;
        }
        System.out.println(currentUser);
        currentUser.addMeeting(meeting);
        System.out.println("ok, the meeting has been added to your agenda");
    }

    public static void cancelMeeting( Person currentUser, ArrayList<Meeting> allMeetings) {
        Scanner scn= new Scanner(System.in);
        currentUser.displayMyOrganizations();
        System.out.print("Enter meeting name to be cancelled: ");
        String meetingName = scn.nextLine();
        Meeting meeting = null;
        for (Meeting m : currentUser.getiOrganize()){
            if (m.getName().equals(meetingName))
                meeting = m;
        }
        currentUser.cancelMeeting(meeting);
        allMeetings.remove(meeting);
        System.out.println("All attendees of " + meetingName +" have been emoved. " + meetingName + " is cancelled.");

    }

    private static void leaveMeeting(Scanner s, Person currentUser) {
        currentUser.displayMyMeetings();
        System.out.print("Enter meeting name to leave: ");
        String meetingName = s.nextLine();
        Meeting meeting = null;
        for (Meeting m : currentUser.getMyMeetings()){
            if (m.getName().equals(meetingName))
                meeting = m;
        }
        currentUser.removeMeeting(meeting);
        System.out.println("you leaved " + meeting.getName());
    }

    private static Person search(ArrayList<Person> users, String name) {
        for (Person p : users) {
            if (p.getName().equals(name))
                return p;
        }
        System.out.println("User is not exist");
        return null;
    }

}
