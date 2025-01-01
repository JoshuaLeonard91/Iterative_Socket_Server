
/*
 * Author: Joshua Leonard, Shoto Fukuda
 * Course: CNT4504
 * Project #: 1
 * Title: Iterative Socket Server 
 * Due Date: 03/24/2023
 *
 * This project demonstrates the use of:
 * 1. Client and Server Connection
 * 2. Threading
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>
 * User Class
 * </p>
 * ----------
 * <p>
 * Runs the display menu for the user, as well as contains the threading for the
 * user class.
 * </p>
 * 
 * @author Joshua Leonard - Shoto Fukuda
 *
 */
public class User {
	private static Scanner scnr = new Scanner(System.in);
	private static String hostname;
	private static int port;

	/**
	 * <p>
	 * Entry Point
	 * </p>
	 * -----------
	 * <p>
	 * Entry point of the Program
	 * </p>
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length < 2)
			return;

		hostname = args[0];
		port = Integer.parseInt(args[1]);
		Socket socket = null; // = new Socket("127.0.0.1", 1002);
		while (true) {
			if (PrintMenu(socket)) {
				break;
			}
		}
		System.out.println("Program Ending!");
	}

	/**
	 * <p>
	 * Current Users Method
	 * </p>
	 * --------------------
	 * <p>
	 * Method that gets current users from the Host/server
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Runnable currentUsers() throws IOException {
		String text;
		Socket socket = new Socket(hostname, port);
		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		text = "6";
		// current user
		writer.println(text);
		String g6 = null;
		while (!(g6 = reader.readLine()).equals("\u0004")) {
			System.out.println(g6);
		}
		socket.close();
		return null;
	}

	/**
	 * <p>
	 * Task List Method
	 * </p>
	 * ----------------
	 * <p>
	 * Method that gets current task list from the Host/server
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Runnable taskList() throws IOException {
		String text;
		Socket socket = new Socket(hostname, port);
		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		text = "5";
		// tasklist
		writer.println(text);
		String g5 = null;
		while (!(g5 = reader.readLine()).equals("\u0004")) {
			System.out.println(g5);
		}
		return null;
	}

	/**
	 * <p>
	 * Net Stat Method
	 * </p>
	 * ---------------
	 * <p>
	 * Method that gets the current net stat from the Host/server
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Runnable netstat() throws IOException {
		double s = System.currentTimeMillis();
		String text;
		Socket socket = new Socket(hostname, port);
		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);
		text = "4";
		// NetStat
		writer.println(text);
		String g4 = null;
		while (!(g4 = reader.readLine()).equals("\u0004")) {
			System.out.println(g4);
		}
		socket.close();
		double l = System.currentTimeMillis();
		System.out.print(" " + (l - s) + " ms\n");
		return null;
	}

	/**
	 * <p>
	 * Memory Method
	 * </p>
	 * -------------
	 * <p>
	 * Method that gets the current memory from the Host/server
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Runnable mem() throws IOException {
		String text;
		Socket socket = new Socket(hostname, port);
		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);

		text = "3";
		// mem
		writer.println(text);

		String memoryUse = reader.readLine();
		System.out.println(memoryUse + " bytes");
		socket.close();
		return null;
	}

	/**
	 * <p>
	 * Uptime Method
	 * </p>
	 * -------------
	 * <p>
	 * Method that gets the current uptime from the Host/server
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 */
	private static Runnable upTime() throws IOException {
		String text;
		Socket socket = new Socket(hostname, port);
		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);

		text = "2";
		writer.println(text);
		String uptime = reader.readLine(); // Get the uptime in millisecond
		long milliseconds = Long.parseLong(uptime); // Convert milllisecodn to Minutes and seconds
		long minutes = (milliseconds / 1000) / 60;
		long seconds = (milliseconds / 1000) % 60;
		System.out.println(minutes + " minutes and " + seconds + " seconds.");
		socket.close();
		return null;
	}

	/**
	 * <p>
	 * Date and Time Method
	 * </p>
	 * --------------------
	 * <p>
	 * Method that gets the current date and time from the Host/server
	 * </p>
	 * 
	 * @param i
	 * @return
	 * @throws IOException
	 */
	private static Runnable dateTime(int i) throws IOException {
		Long s = System.currentTimeMillis();
		String text;
		Socket socket = new Socket(hostname, port);
		InputStream input = socket.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		OutputStream output = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(output, true);

		text = "1";
		// Current Date and time
		writer.println(text);
		String time = reader.readLine();
		System.out.print(time + " Thread " + i);
		socket.close();
		Long l = System.currentTimeMillis();
		System.out.print(" " + (l - s) + " ms\n");
		return null;
	}

	/**
	 * <p>
	 * Time Function Method
	 * </p>
	 * --------------------
	 * <p>
	 * Method that gets the time taken in milliseconds for threads to finish
	 * </p>
	 * 
	 * @param n
	 * @param s
	 */
	private static void timeFunction(int n, double s) {
		double d = (System.currentTimeMillis() - s);
		System.out.println("Average Time: " + d / n + "ms");
		System.out.println("Total Time: " + d + "ms");
		scnr = new Scanner(System.in);
	}

	/**
	 * <p>
	 * Selection Method
	 * </p>
	 * --------------------
	 * <p>
	 * Method that gives the user options
	 * </p>
	 * 
	 * @param inputFlag
	 * @return
	 */
	public static String selection(boolean inputFlag) {
		System.out.println("Please make a selection: ");
		System.out.println("\n1) Host Current Date/Time");
		System.out.println("2) Host Uptime");
		System.out.println("3) Host Memory Use");
		System.out.println("4) Host Netstat");
		System.out.println("5) Host Current Users");
		System.out.println("6) Host Running Processes");
		System.out.println("7) Exit");

		String input = "";
		while (!inputFlag) {
			input = scnr.nextLine();
			if (input.matches("1|2|3|4|5|6|7")) {
				inputFlag = true;
			} else {
				System.out.println("Invalid Choice! Please enter 1-7:");
			} // end if/else
		} // end while
		return input;
	}

	/**
	 * <p>
	 * Print Menu Method
	 * </p>
	 * --------------------
	 * <p>
	 * Method that loops the menu options
	 * </p>
	 * 
	 * @param socket
	 * @return
	 * @throws IOException
	 */
	public static boolean PrintMenu(Socket socket) throws IOException {
		boolean flag = true;
		boolean inputFlag = false;
		Boolean close = false;

		do {
			String input = selection(inputFlag);
			inputFlag = false;
			int n;
			double s;
			switch (input) {

			// User selection option 1
			case "1":
				System.out.println(
						"How many times do you want to run the Date and Time command?\nEnter only integers!\n");
				n = scnr.nextInt();
				s = System.currentTimeMillis();

				Thread[] start = new Thread[n];
				for (int i = 0; i < n; i++) {
					start[i] = new Thread(dateTime(i));
				}
				for (int i = 0; i < n; i++) {
					start[i].start();
				}
				timeFunction(n, s);
				break;
			// User selection option 2
			case "2":
				System.out.println("How many times do you want to run the Uptime command?\nEnter only integers!\n");
				n = scnr.nextInt();
				s = System.currentTimeMillis();

				// Uptime
				Thread[] upTime = new Thread[n];
				for (int i = 0; i < n; i++) {
					upTime[i] = new Thread(upTime());
				}
				for (int i = 0; i < n; i++) {
					upTime[i].start();
				}
				timeFunction(n, s);
				break;
			// User selection option 3
			case "3":
				System.out.println("How many times do you want to run the Memory command?\nEnter only integers!\n");
				n = scnr.nextInt();
				s = System.currentTimeMillis();
				// Mem
				Thread[] Mem = new Thread[n];
				for (int i = 0; i < n; i++) {
					Mem[i] = new Thread(mem());
				}
				for (int i = 0; i < n; i++) {
					Mem[i].start();
				}
				timeFunction(n, s);
				break;
			// User selection option 4
			case "4":
				System.out.println("How many times do you want to run the netStat command?\nEnter only integers!\n");
				n = scnr.nextInt();
				s = System.currentTimeMillis();

				// netStat
				Thread[] netStat = new Thread[n];
				for (int i = 0; i < n; i++) {
					netStat[i] = new Thread(netstat());
				}
				for (int i = 0; i < n; i++) {
					netStat[i].start();
				}

				timeFunction(n, s);
				break;
			// User selection option 5
			case "5":
				System.out.println("How many times do you want to run the Task List command?\nEnter only integers!\n");
				n = scnr.nextInt();
				s = System.currentTimeMillis();
				// TaskList
				Thread[] TaskList = new Thread[n];
				for (int i = 0; i < n; i++) {
					TaskList[i] = new Thread(taskList());
				}
				for (int i = 0; i < n; i++) {
					TaskList[i].start();
				}

				timeFunction(n, s);
				break;
			// User selection option 6
			case "6":
				System.out.println(
						"How many times do you want to run the Current Users command?\nEnter only integers!\n");
				n = scnr.nextInt();
				s = System.currentTimeMillis();
				// Current Users
				Thread[] currentUsers = new Thread[n];
				for (int i = 0; i < n; i++) {
					currentUsers[i] = new Thread(currentUsers());
				}
				for (int i = 0; i < n; i++) {
					currentUsers[i].start();
				}
				timeFunction(n, s);
				break;
			// User selection option 7
			case "7":
				socket = new Socket(hostname, port);
				InputStream inputS = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputS));
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);
				String text = "7";
				writer.println(text);
				writer.println(0);
				System.out.println("Programing Exiting");
				flag = false;
				close = true;
				break;
			}
		} while (flag);// end do-while
		scnr.close();
		return close;
	}// end displayMenu

}