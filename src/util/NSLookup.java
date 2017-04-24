package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//
			InetAddress[] inetAddress=null;

			while (true) {

				System.out.print(">>");
				Scanner hostname = new Scanner(System.in);
				String grade = hostname.nextLine();

				if (grade.equals("exit")) {

					break;

				} else {

					inetAddress = InetAddress.getAllByName(grade);

					for (int i = 0; i < inetAddress.length; i++) {

						System.out.println(inetAddress[i] + " ");
					}
				}

				
				
				
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
