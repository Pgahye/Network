package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {
	
	private static final int SERVER_PORT=5050;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	

		ServerSocket serverSocket=null; //finally에서 소켓을 닫기 위해서 
		
		
		try {
			//1. 서버 소켓 작성
		 serverSocket = new ServerSocket();
		 
		 
		 //2. 바인딩(binding)
		 InetAddress inetAddress=InetAddress.getLocalHost();
		 String localhostAddress=inetAddress.getHostAddress();
		 InetSocketAddress inetSocketAddress=new InetSocketAddress(localhostAddress, SERVER_PORT);
		 
		 serverSocket.bind(inetSocketAddress);
		 
		 System.out.println("[server] binding"+localhostAddress+ ":"+ SERVER_PORT);
		 
		 //3.accept (연결요청을 기다림)
		 Socket socket = serverSocket.accept(); //blocking 발생
		 
		 //4.연결 성공
		 InetSocketAddress remoteAddress=(InetSocketAddress) socket.getRemoteSocketAddress(); //remote상대편, 클라이언트 의미 , 자식이라서 다운캐스팅 필요 
		 
		 int remoteHostPort=remoteAddress.getPort();
		 
		 String remoteHostAddress=remoteAddress.getAddress().getHostAddress(); //inetaddress에 ip주소를 가지고 있음 
		 
		 System.out.println("[server] connected from " + remoteHostAddress+":"+remoteHostPort);
		 
		 try{
		
			//5.IOStream 받아오기 
		 InputStream is= socket.getInputStream();
		 OutputStream os=socket.getOutputStream();
		 
		 while(true){
		 //6. 데이터 읽기 
		 byte[] buffer=new byte[256];
		 int readByteCount = is.read(buffer); //blocking 
		 
		 if(readByteCount <= -1){
			 
			 //클라이언트가 소켓을 닫은 경우 
			 
			 System.out.println("[server] disconnected by client");
			// return ;
			 break;
					 
		 }
		 String data = new String(buffer,0,readByteCount,"utf-8");
		 System.out.println("[server] reveived: "+data);
		 
		 //7.데이터 쓰기 
		 os.write(data.getBytes("utf-8"));
		 
		 }//
		 
		 
		 }catch(SocketException e){
				
			 //클라이언트가 소켓을 정상적으로 닫지 않고 종료한 경우 
			 System.out.println("[server] closed by client");
				
			}catch(IOException e) { //데이터 통신 소켓 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 finally{
				
				//자원정리
				try{
				if(socket != null && socket.isClosed()==false ){
					
					socket.close();
				}} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		 
		 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			//자원정리
			try{
			if(serverSocket != null && serverSocket.isClosed()==false ){
				//
				serverSocket.close();
			}} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}

}
