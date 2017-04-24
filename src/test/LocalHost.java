package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	//]  Local Host 이름 및 IP 주소 구하기

		
		
		try {
			InetAddress inetAddress=InetAddress.getLocalHost();
			String hostAddress=inetAddress.getHostAddress(); //String 
			
			System.out.println(hostAddress);
			
			String hostName=inetAddress.getHostName();
			
			System.out.println(hostName);
			
			
			byte[] addresses=inetAddress.getAddress(); //byte형태로 반환
			
			for(int i=0; i<addresses.length;i++){
				
				int address=addresses[i]&0x000000ff; //부호비트를 0으로 만들어주는 과정...
				
				System.out.print(address+" "); //바이트는 -128~127 char는 0~255 라서 부호비트를 유지하기 때문에 -값이 나옴 /  2의 보수를 사용해서 -를 표시 (1의 보수를 구한다음+1)
				if(i<3){
					System.out.print(".");
				}
			
			}
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
