package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
	
	private static final int SERVER_PORT=6060;
	private static final String SERVER_IP="192.168.1.34";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Socket socket=null;
		Scanner scanner=new Scanner(System.in);
		
		//String message=scanner.nextLine();
		
		

		
		try{
		//1.소켓생성
		socket=new Socket();
		
		//2.서버연결
		socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
	
		//3.iostream 받아오기 
		
		//InputStream is=socket.getInputStream();
		//OutputStream os=socket.getOutputStream();
	
		
		 BufferedReader br= new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8")); //byte기반의 소켓에서 가지고와서 읽어들임 
		 PrintWriter pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true); //오토플러쉬?를 위한 true
		 
		 
		 while(true){
			 
			 
			 
			 
			 System.out.print(">>");
			 String message=scanner.nextLine();
			 
			 if("exit".equals(message)){
				 break;
			 }
			 
			 //메세지 보내기
			 pw.println(message);
			 
			 //에코받기 
			 String echoMessage=br.readLine();
			 	if(echoMessage==null){
			 		
			 		System.out.println("[client] disconnected by server");
			 		break;
			 	}
			 	
			 	//출력
			 	System.out.println("<<"+message);
			 
		 }
		 
		 
		 
		 
		 
		 
		 
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			//자원정리
		try{
			if(socket != null && socket.isClosed()==false ){
				
				socket.close();
			}} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scanner.close();
		} //소켓 닫아주기 

	}

		
		

}
