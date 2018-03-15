package model;

import java.util.*;

import airport.Passenger;
import interfaces.Observer;
import interfaces.Subject;

public class CheckIn implements Subject {
	
	static ArrayList<Passenger> passenger_queue;
	
	static String flight_code_destination;
	static String flight_info;
	
	String check_in_info;
	int desk_no;
	
	private List<Observer> registeredObservers = new LinkedList<Observer>();
	
	public void registerObserver(Observer obs) {
		registeredObservers.add(obs);
	}

	public void removeObserver(Observer obs) {
		registeredObservers.remove(obs);
	}
	
	public void notifyObservers() {
		for(Observer obs : registeredObservers)
			obs.update();
	}
	
	public ArrayList<Passenger> get_passenger_queue() {
		return passenger_queue;
	}
	
	public String get_flight_code_destination() {
		return flight_code_destination;
	}
	
	public String get_flight_info() {
		return flight_info;
	}
	
	public String get_check_in_info() {
		return check_in_info;
	}
	
	public int get_desk_no() {
		return desk_no;
	}
	
	private void update_observers() {
		Iterator<Observer> it = registeredObservers.iterator();
		while(it.hasNext()) {
			//Call update method
			//it.hasNext().update();
			it.next().update();
		}
	}
	
	public synchronized void update_check_in_desk(int desk_no, String check_in_info) {
		
		this.desk_no = desk_no;
		this.check_in_info = check_in_info;
		System.out.println(desk_no);
		//update_observers();
		notifyObservers();
	}
	
	public void update_flight_info(String flight_code_destination, String flight_info) {
		this.flight_code_destination = flight_code_destination;
		this.flight_info = flight_info;
		notifyObservers();
	}
	
	public void update_passenger_queue(ArrayList<Passenger> passenger_queue) {
		this.passenger_queue = passenger_queue;
		notifyObservers();
		//update_observers();
	}
	
	public CheckIn() {
		
	}
}