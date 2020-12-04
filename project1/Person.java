/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author meltem koc
 */
public class Person {
    private String name;
    private String id;
    private ArrayList<Meeting> myMeetings = new ArrayList<>();
    private ArrayList<Meeting> iOrganize = new ArrayList<>();

    Person(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Meeting> getMyMeetings() {
        return myMeetings;
    }

    public void setMyMeetings(ArrayList<Meeting> myMeetings) {
        this.myMeetings = myMeetings;
    }

    public ArrayList<Meeting> getiOrganize() {
        return iOrganize;
    }

    public void setiOrganize(ArrayList<Meeting> iOrganize) {
        this.iOrganize = iOrganize;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

    
        if (!(obj instanceof Person)) {
            return false;
        }

        Person p = (Person) obj;
        return this.name.equals(p.getName()) &&
                this.id.equals(p.getId()) &&
                this.iOrganize.equals(p.getiOrganize()) &&
                this.myMeetings.equals(p.getMyMeetings());
    }

    public boolean addMeeting(Meeting newMeeting) {
        for (Meeting m : this.myMeetings) {
            if (newMeeting.getDate().equals(m.getDate()))
                return false;
        }
        this.myMeetings.add(newMeeting);
        newMeeting.getAttendees().add(this);
        return true;
    }

    public void removeMeeting(Meeting meeting) {
        this.myMeetings.remove(meeting);
    }

    public void organizeMeeting(Meeting meeting) {
        this.iOrganize.add(meeting);
    }

    public void cancelMeeting(Meeting meeting) {
        meeting.removeAttendee();
        this.myMeetings.remove(meeting);
        this.iOrganize.remove(meeting);
    }

    public void displayMyMeetings() {
        if (!this.myMeetings.isEmpty())
            for (Meeting m : this.myMeetings) {
                System.out.println(m.getName() + " " + m.getDate() + ", host by: " + m.getHost());
            }
        else
            System.out.println("your agenda is empty.");

    }

    public void displayMyOrganizations() {
        if (!this.iOrganize.isEmpty())
            for (Meeting m : this.iOrganize) {
                System.out.println(m.getName() + " " + m.getDate());
            }
        else
            System.out.println("You haven't organized any meeting yet!");
    }

    @Override
    public String toString() {
        return "Person: name: " + this.name;
    }

    
   
    
}
