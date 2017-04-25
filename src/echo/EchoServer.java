package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {

	//
	private static final int SERVER_PORT = 6060;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerSocket serverSocket = null; // finally에서 소켓을 닫기 위해서

		try {
			// 1. 서버 소켓 작성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localhostAddress = inetAddress.getHostAddress();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(localhostAddress, SERVER_PORT);

			serverSocket.bind(inetSocketAddress);

			System.out.println("[server] binding" + localhostAddress + ":" + SERVER_PORT);

			// 3.accept (연결요청을 기다림)
			Socket socket = serverSocket.accept(); // blocking 발생

			// 4.연결 성공
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress(); // remote상대편,
																									// 클라이언트
																									// 의미
																									// ,
																									// 자식이라서
																									// 다운캐스팅
																									// 필요

			int remoteHostPort = remoteAddress.getPort();

			String remoteHostAddress = remoteAddress.getAddress().getHostAddress(); // inetaddress에
																					// ip주소를
																					// 가지고
																					// 있음

			System.out.println("[server] connected from " + remoteHostAddress + ":" + remoteHostPort);

			try {

				// 5.IOStream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				// message 이므로 String으로 전달

				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8")); // byte기반의
																													// 소켓에서
																													// 가지고와서
																													// 읽어들임
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); // 오토플러쉬?를
																													// 위한
																													// true

				while (true) {
					
					System.out.println(">>");
					String message = br.readLine(); // 개행을 통해서 구분함, 개행이 꼭 있어야함
													// blocking
					if (message == null) {

						// 클라이언트가 소켓을 닫음
						System.out.println("[server] disconnected by client");
						break;
					}

					System.out.println("[server] received :" + message);

					// 데이터 쓰기
					// pw.print(message+"\n");
					pw.println(message);

				}

			} catch (SocketException e) {

				// 클라이언트가 소켓을 정상적으로 닫지 않고 종료한 경우
				System.out.println("[server] closed by client");

			} catch (IOException e) { // 데이터 통신 소켓
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {

				// 자원정리
				try {
					if (socket != null && socket.isClosed() == false) {

						socket.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			// 자원정리
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {

					serverSocket.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
