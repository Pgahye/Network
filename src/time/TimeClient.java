package time;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class TimeClient {

	private static final String SERVER_IP="192.168.1.34";
	private static final int SERVER_PORT=8080;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		DatagramSocket socket=null;
		
	
		
		try {
			//1.소켓생성
			socket=new DatagramSocket();
			String message="";
			
				
				
				System.out.print(">>");
			
				
				//2.전송패킷 생성
				byte[] sendData=message.getBytes("utf-8");
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, new InetSocketAddress(SERVER_IP,SERVER_PORT));
				
				//3.전송
				socket.send(sendPacket);
				
				//4.수신
				DatagramPacket receivePacket=new DatagramPacket(new byte[1024],1024);
				socket.receive(receivePacket);//blocking
				
				//5. 화면 출력
				
				message=new String(receivePacket.getData(),0,receivePacket.getLength(), "utf-8");
				
				System.out.println("[UDP Echo Client] reveived : "+ message);
			
				////
				//
				
			}
		
			
		
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) { //데이터 통신 소켓 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(socket!=null && socket.isClosed()==false){
				
				socket.close();
			}
		
		}
		
	}

}
