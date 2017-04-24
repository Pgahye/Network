package Thread;



import java.util.ArrayList;
import java.util.List;

public class MutiThreadEx {

	public static void main(String[] args) {
		//
		
	List list=new ArrayList();
		// TODO Auto-generated method stub
		
	//	for(int i=0;i<=9;i++){
			
		//	System.out.print(i);
			
	//	}
		
		//for(char c='a';c<'z';c++){
			
		//	System.out.println(c);
		//}
		
		Thread t1=new AlphabetThread(list);
		Thread t2=new DigitThread(list);
		Thread t3=new DigitThread(list);
		Thread t4=new Thread(new UpperCaseAlphabetThread() );//일반 클래스를 스레드로 사용
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		
		

	}

}
