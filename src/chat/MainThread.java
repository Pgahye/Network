package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Thread.AlphabetThread;

public class MainThread {

	//
	private static final int SERVER_PORT = 6060;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1. 서버 소겟 생성
		ServerSocket serverSocket = null; // finally에서 소켓을 닫기 위해서

		List<PrintWriter> listWriters = new ArrayList<PrintWriter>();

		try {
			// 1. 서버 소켓 작성

			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(localhostAddress, SERVER_PORT);

			serverSocket.bind(inetSocketAddress);

			System.out.println("[server] binding " + localhostAddress + ":" + SERVER_PORT);

			//// 3. 요청 대기
			while (true) {
				Socket socket = serverSocket.accept();

				// new ChatServerThread(socket, listWriters).start();
				Thread thread = new ChatServerThread(socket, listWriters);
				thread.start();

			}

			// 4.연결 성공

		} catch (SocketException e) {

			// 클라이언트가 소켓을 정상적으로 닫지 않고 종료한 경우
			System.out.println("[server] closed by client");

		} catch (IOException e) { // 데이터 통신 소켓
			// TODO Auto-generated catch block
			System.out.println("[server] io 오류");
			e.printStackTrace();
		} finally {
			if (serverSocket != null && serverSocket.isClosed() == false) {
				try {
					serverSocket.close();
				} catch (IOException ex) {

				}
			}
		}

	}

}