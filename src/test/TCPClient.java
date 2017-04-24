package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient {
	
	private static final int SERVER_PORT=5050;
	private static final String SERVER_IP="192.168.1.34";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket socket=null;
		
		try{
		//1.소켓생성
		socket=new Socket();
		
		//2.서버연결
		socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
	
		//3.iostream 받아오기 
		
		InputStream is=socket.getInputStream();
		OutputStream os=socket.getOutputStream();
		
		//4.쓰기 /읽기
		
		
		String data="hello\n";
		
		//서버로 넘김
		os.write(data.getBytes("utf-8"));
		
		//서버가 쓴걸 읽어들임
		
		byte[] buffer=new byte[256];
		int readCountByte=is.read(buffer);
		if(readCountByte<=-1){
			
			System.out.println("[client] disconnected by server");
			return;
		}
		
		 data=new String(buffer,0,readCountByte,"utf-8");
		System.out.println("[client] reveived: "+data);
		 
		 
		 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			//자원정리
		//	try{
		//	if(socket != null && socket.isClosed()==false ){
				
			//	socket.close();
			//}} catch (IOException e) {
		//		// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
			
		}

	}

}
