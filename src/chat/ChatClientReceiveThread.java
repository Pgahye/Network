package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

public class ChatClientReceiveThread extends Thread {

	private BufferedReader br = null;

	public ChatClientReceiveThread(BufferedReader br) {

		this.br = br;

	}

	@Override
	public void run() {

		/* reader를 통해 읽은 데이터 콘솔에 출력하기 (message 처리) */

		try {

			while (true) {

				
				String receiveString = br.readLine();

				if (receiveString == null) {

					System.out.println("연결이 끊겼습니다. ");
					break;
				}

				System.out.println(receiveString);

			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
