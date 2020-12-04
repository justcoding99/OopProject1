/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 *
 * @author meltem koc
 */
public class Meeting {
    
    private LocalDate date;
    private ArrayList<Person> attendees = new ArrayList<>();
    private final Person  host; 
    private String name;

    public Meeting(String name, LocalDate date, Person attendee, Person host) {
        this.name = name;
        this.date = date;
        this.host = host;
        host.addMeeting(this);
        if (attendee.addMeeting(this)) {
            this.attendees.add(attendee);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Person> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<Person> attendees) {
        this.attendees = attendees;
    }

    public Person getHost() {
        return host;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Meeting) {
            return true;
        }

        //Check if obj is an instance of Person
        if (!(obj instanceof Meeting)) {
            return false;
        }

        Meeting m = (Meeting) obj;
        return this.date.equals(m.getDate()) &&
                this.host.equals(m.getHost()) &&
                this.attendees.equals(m.getAttendees());
    }

    public boolean addAttendee(Person person) {
        if (!attendees.contains(person)) {
            if (person.addMeeting(this)) 
            attendees.add(person);
            return true;
        }
        return false;
    }
    
    

    public void removeAttendee() {
        for (Person p : this.attendees) {
            p.removeMeeting(this);
        }
    }

    @Override
    public String toString() {
        return this.name + " " + this.date;
    }

    

    

    

    
}
