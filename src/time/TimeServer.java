package time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {
//
	
	private static final int PORT=8080;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		DatagramSocket datagramSocket = null;
		
		
		
		try {
			
			while(true){
			datagramSocket = new DatagramSocket(PORT);
			
			DatagramPacket receivePacket=new DatagramPacket(new byte[1024],1024);
			//3.데이터 수신대기
			datagramSocket.receive(receivePacket); //blocking 
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS a");
			
			String message=format.format(new Date());
			
			System.out.println("[UDP Echo Server] reveived : "+ message);
			
			//5.에코잉
			
			byte[] sendData=message.getBytes("utf-8");
			DatagramPacket sendPacket=new DatagramPacket(sendData,sendData.length,receivePacket.getSocketAddress());
			//데이터, 데이터 길이 , 받는  사람 ip주소+포트 등이 들어있음
			
			datagramSocket.send(sendPacket);
			
			}
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //생성하면서 바인딩
		catch(IOException e) { //데이터 통신 소켓 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			if(datagramSocket !=null && datagramSocket.isClosed()==false)
			datagramSocket.close();
			
			
		}

	}

}
