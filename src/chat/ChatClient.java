package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import Thread.AlphabetThread;

public class ChatClient {

	private static final int SERVER_PORT = 6060;
	private static final String SERVER_IP = "192.168.1.34";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Socket socket = null;
		Scanner scanner = null;
		List<PrintWriter> listWriters = null;
		// String message=scanner.nextLine();

		BufferedReader br = null;
		PrintWriter pw = null;

		try {
			// 1.소켓생성
			scanner = new Scanner(System.in);

			socket = new Socket();

			// 2.서버연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			// 3.iostream 받아오기

			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")); // byte기반의
																								// 소켓에서
																								// 가지고와서
																								// 읽어들임
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // 오토플러쉬?를
																									// 위한
																									// true

			System.out.print("클라이언트>>");
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);
			pw.flush();
			br.readLine();
			// new ChatServerThread(socket).start();
			Thread thread = new ChatClientReceiveThread(br);
			thread.start();

			while (true) {

				String input = scanner.nextLine();

				if ("quit".equals(input) == true) {
					// 8. quit 프로토콜 처리
					pw.println("quit");
					pw.flush();

					break;
				} else {
					pw.println("message:" + input);
					pw.flush();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// 자원정리
			try {
				if (socket != null && socket.isClosed() == false) {

					socket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // 소켓 닫아주기

	}

}