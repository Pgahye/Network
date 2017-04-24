package echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {

	
	private static final int PORT=6060;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatagramSocket datagramSocket = null;
		

		
		
		try {
			
			//1.소켓생성
		datagramSocket = new DatagramSocket(PORT); //생성하면서 바인딩
		while(true){
		//2.수신패킷 생성
		
		DatagramPacket receivePacket=new DatagramPacket(new byte[1024],1024);
		//3.데이터 수신대기
		datagramSocket.receive(receivePacket); //blocking 
		
		//4.수신
		
		String message=new String(receivePacket.getData(),0,receivePacket.getLength(),"utf-8");
		
		System.out.println("[UDP Echo Server] reveived : "+ message);
		
		//5.에코잉
		
		byte[] sendData=message.getBytes();
		DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,receivePacket.getSocketAddress());
		//데이터, 데이터 길이 , 받는  사람 ip주소+포트 등이 들어있음
		
		datagramSocket.send(sendPacket);
		
		}
		
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException e) { //데이터 통신 소켓 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(datagramSocket !=null && datagramSocket.isClosed()==false)
			datagramSocket.close();
			
			
		}
	}

}
