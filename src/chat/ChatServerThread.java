package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {
	
	private String nickname;
	private Socket socket;
	List<PrintWriter> listWriters;
	

	public ChatServerThread( Socket socket, List<PrintWriter> listWriters ) {
		   this.socket = socket;
		   this.listWriters = listWriters;
		}


	
	@Override
	public void run() {
		
		
		 BufferedReader br=null;
		 PrintWriter pw=null;
		// TODO Auto-generated method stub
		 //4.연결 성공
	
		//1. Remote Host Information
		 try{
			 
			 br= new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8")); //byte기반의 소켓에서 가지고와서 읽어들임 
				pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true); //오토플러쉬?를 위한 true
		
		 InetSocketAddress remoteAddress=(InetSocketAddress) socket.getRemoteSocketAddress(); //remote상대편, 클라이언트 의미 , 자식이라서 다운캐스팅 필요 
		 
		 int remoteHostPort=remoteAddress.getPort();
		 
		 String remoteHostAddress=remoteAddress.getAddress().getHostAddress(); //inetaddress에 ip주소를 가지고 있음 
		 
		 consoleLog( "connected from " + remoteHostAddress + ":" + remoteHostPort ); 

	

		//3. 요청 처리 
			while (true) {

				String request = br.readLine(); // 개행을 통해서 구분함, 개행이 꼭 있어야함
												// blocking
				if (request == null) {

					// 클라이언트가 소켓을 닫음
					consoleLog("클라이언트로부터 연결 끊김");
					 doQuit(pw);
					break;
				}
				// 4. 프로토콜 분석

			

				String[] tokens = request.split(":");

			//	System.out.println(tokens[0]+" "+tokens[1]);
				
				
				if ("join".equals(tokens[0])) {

					
					doJoin(tokens[1], pw);
					

				} else if ("message".equals(tokens[0])) {

					doMessage(tokens[1]);

				} else if ("quit".equals(tokens[0])) {

					doQuit(pw);
					break;

				} else {

					System.out.println("에러:알수 없는 요청(" + tokens[0] + ")");
				}

			}
		 	 
		 }catch(SocketException e){
				
			 //클라이언트가 소켓을 정상적으로 닫지 않고 종료한 경우 
			 consoleLog( "closed by client" ); 
				
			}catch(IOException e) { //데이터 통신 소켓 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 finally{
				
				//자원정리
				try{
					
					br.close();  
					pw.close();  

				if(socket != null && socket.isClosed()==false ){
					
					socket.close();
				}} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}

		}

	}
	

	private void doJoin( String nickName, PrintWriter pw ) {
		
		
		   this.nickname = nickName;
				
		   String data = nickName + "님이 참여하였습니다."; 
	
		   broadcast( data );
				
		   /* writer pool에  저장 */
		   addWriter(pw);

		   // ack
		   pw.println( "join:ok" );
		   pw.flush();
		}
	private void doMessage( String message ) {

		String data = nickname + ":" + message; 
		 broadcast( data );
	       /* 잘 구현 해 보기 */	

	}private void doQuit( PrintWriter writer ) {
		   removeWriter( writer );
			
		   String data = nickname + "님이 퇴장 하였습니다."; 
		   broadcast( data );
		}

	private void removeWriter(PrintWriter writer ) {
		synchronized( listWriters ) {
		      listWriters.remove( writer );
		   }
		   /* 잘 구현 해보기 */	
	}


	private void addWriter( PrintWriter writer ) {
		
		 
		   synchronized( listWriters ) {
		      listWriters.add( writer );
		   }
		}

	private void broadcast( String data ) {

	
		   synchronized( listWriters ) {
			   

				int count = listWriters.size(); 
				
				for( int i = 0; i < count; i++ ) {
		    	  PrintWriter printWriter = listWriters.get( i );  
		    		printWriter.println( data );  
		    		printWriter.flush(); 

		      }

		   }

		}

	private void consoleLog(String message) {

		System.out.println("[server:" + getId() + "]" + message);

	}

}
