import java.util.Scanner;

public class First_Come_First_Serve_KINPUT {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int i, j, k, l, m, x, y, temp;
		float total_turnaroundtime = 0, total_waitingtime = 0, average_turnaroundtime = 0, average_waitingtime = 0;
		System.out.println("First Come First Serve - NON PREEMPTIVE");
		System.out.println("---------------------------------------\n");

		System.out.print("Enter Number of Processes: ");
		int numberofprocesses = input.nextInt();

		int process_id[] = new int[numberofprocesses]; // declaring process id array
		int arrival_time[] = new int[numberofprocesses]; // declaring arrival time array
		int burst_time[] = new int[numberofprocesses]; // declaring burst time array
		int completion_time[] = new int[numberofprocesses]; // declaring completion time array
		int turnaround_time[] = new int[numberofprocesses]; // declaring turnaround time array
		int waiting_time[] = new int[numberofprocesses]; // declaring waiting time array

		for (i = 0; i < numberofprocesses; i++) {
			System.out.print("\nEnter Process " + (i + 1) + " Arrival and Burst Time: "); // give a space between arrival and burst time
			arrival_time[i] = input.nextInt();
			burst_time[i] = input.nextInt();
			process_id[i] = i + 1;
		}

		// sorting list of process according to arrival times (bubble sort)
		for (j = 0; j < numberofprocesses; j++) {
			for (k = 0; k < numberofprocesses - (j + 1); k++) {
				if (arrival_time[k] > arrival_time[k + 1]) {
					temp = arrival_time[k];
					arrival_time[k] = arrival_time[k + 1];
					arrival_time[k + 1] = temp;
					temp = burst_time[k];
					burst_time[k] = burst_time[k + 1];
					burst_time[k + 1] = temp;
					temp = process_id[k];
					process_id[k] = process_id[k + 1];
					process_id[k + 1] = temp;
				}
			}
		}

		// finding completion time
		for (l = 0; l < numberofprocesses; l++) {
			if (l == 0) {
				completion_time[l] = arrival_time[l] + burst_time[l];
			} else {
				if (arrival_time[l] > completion_time[l - 1]) {
					completion_time[l] = arrival_time[l] + burst_time[l];
				} else
					completion_time[l] = completion_time[l - 1] + burst_time[l];
			}
			turnaround_time[l] = completion_time[l] - arrival_time[l]; // turnaround time = completion time - arrival time
			waiting_time[l] = turnaround_time[l] - burst_time[l]; // waiting time = turnaround time - burst time
			total_turnaroundtime = total_turnaroundtime + turnaround_time[l]; // total turnaround time
			total_waitingtime = total_waitingtime + waiting_time[l]; // total waiting time	
		}
		System.out.println("\nProcess  Arrival  Duration  Completion  Turnaround  Waiting");
		System.out.println("  ID      Time      Time       Time        Time      Time");
		System.out.println("--------------------------------------------------------------");

		for (m = 0; m < numberofprocesses; m++) {
			System.out.println(" P" + process_id[m] + "\t   " + arrival_time[m] + "\t     " + burst_time[m] + "\t\t"
					+ completion_time[m] + "\t    " + turnaround_time[m] + "\t      " + waiting_time[m]);
		}
		
		System.out.println("\n\t\t   GANTT CHART");
		System.out.println("\t\t   ***********");
		
		// print top bar
		System.out.print(" ");
	    for(x = 0; x < numberofprocesses; x++) {
	        for(y = 0; y < burst_time[x]; y++) { 
	        	System.out.print("--");
	        }
	    	System.out.print(" ");
	    }
	    System.out.print("\n|");
	    
	    // printing process id in the middle
	    for(x = 0; x < numberofprocesses; x++) {
	        for(y = 0; y < burst_time[x] - 1; y++) 
	        	 System.out.print(" ");
	        System.out.print("P" + process_id[x]);
	        for(y = 0; y < burst_time[x] - 1; y++) 
	        	 System.out.print(" ");
	        System.out.print("|");
	    }
	    System.out.println();
	 
	    // print bottom bar
	    System.out.print(" ");
	    for(x = 0; x < numberofprocesses; x++) {
	    	for(y = 0; y < burst_time[x]; y++) { 
	    		System.out.print("--");
		}
	 	System.out.print(" ");
	    }
	    System.out.println();
	    
	    // printing the completion timeline
	    System.out.print("0");
	    for(x = 0; x < numberofprocesses; x++) {
	        for(y = 0; y < burst_time[x]; y++) 
	        	System.out.print(" ");
	        System.out.print("      " + completion_time[x]);
	    }

		average_turnaroundtime = (total_turnaroundtime / numberofprocesses); // average turnaround time
		average_waitingtime = (total_waitingtime / numberofprocesses); // average waiting time
		
		System.out.println("\n\nTotal Turnaround Time: " + total_turnaroundtime); // print total turnaround time
		System.out.println("Total Waiting Time   : " + total_waitingtime); // print total waiting time
		System.out.println("Total Number of Processes: " + numberofprocesses); //print total number of processes
		System.out.println("\nAverage Turnaround Time: " + average_turnaroundtime); // print average turnaround time
		System.out.println("Average Waiting Time   : " + average_waitingtime); // print average waiting time
	}
}