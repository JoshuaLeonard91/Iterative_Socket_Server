
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
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * <p>
 * Host Class
 * </p>
 * ----------
 * <p>
 * Runs all the options from the user class
 * </p>
 * 
 */
public class Host {

	/**
	 * <p>
	 * Entry Point
	 * </p>
	 * -------------
	 * <p>
	 * Runs options that the user selects.
	 * </p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1)
			return;

		int port = Integer.parseInt(args[0]);

		try (ServerSocket serverSocket = new ServerSocket(port)) {

			System.out.println("Server is listening on port " + port);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connection Successful with user: /" + socket.getInetAddress());
				InputStream input = socket.getInputStream();
				OutputStream output = socket.getOutputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				PrintWriter writer = new PrintWriter(output, true);
				String text;
				text = reader.readLine();
				// option 1
				if (text.equals("1")) {
					writer.println(new Date().toString());
					writer.close();
				}
				// option 2
				if (text.equals("2")) {
					RuntimeMXBean mxBean = ManagementFactory.getRuntimeMXBean();
					writer.println(mxBean.getUptime());
					writer.close();
				}
				// option 3
				if (text.equals("3")) {
					Runtime rt = Runtime.getRuntime();
					long total_mem = rt.totalMemory();
					long free_mem = rt.freeMemory();
					long used_mem = total_mem - free_mem;
					writer.println(String.valueOf(used_mem));
					writer.close();
				}
				// option 4
				if (text.equals("4")) {

					// NetStat
					String s4 = "";
					Process p = Runtime.getRuntime().exec("netstat -a");
					BufferedReader r4 = new BufferedReader(new InputStreamReader(p.getInputStream()));

					while ((s4 = r4.readLine()) != null) {
						writer.println(s4);
						writer.flush();
					}
					writer.println("\u0004");
					p.destroy();
					r4.close();
					writer.close();
				}
				// option 5
				if (text.equals("5")) {

					// current users
					String s5 = "";
					Process p = Runtime.getRuntime().exec("w");
					BufferedReader r5 = new BufferedReader(new InputStreamReader(p.getInputStream()));

					while ((s5 = r5.readLine()) != null) {
						writer.println(s5);
						writer.flush();
					}
					writer.println("\u0004");
					p.destroy();
					r5.close();
					writer.close();
				}
				// option 6
				if (text.equals("6")) {

					// TaskList
					String s6 = "";
					Process p = Runtime.getRuntime().exec("ps -a");
					BufferedReader r6 = new BufferedReader(new InputStreamReader(p.getInputStream()));

					while ((s6 = r6.readLine()) != null) {
						writer.println(s6);
						writer.flush();
					}
					writer.println("\u0004");
					p.destroy();
					r6.close();
					writer.close();
				}
				// option 7
				if (text.equals("7")) {
					input.close();
					output.close();
					reader.close();
					writer.close();
					socket.close();
					break;
				}
				System.out.println("Waiting for next connection...");
			}

		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
		System.out.println("Program Ending!");
	}
}