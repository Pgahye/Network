package Thread;

import java.util.List;

public class AlphabetThread extends Thread {

	
	private List list;
	
	public AlphabetThread(List list){
		
		
		this.list=list;
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	for(char c='a';c<='z';c++){
		
		System.out.print(c);
		synchronized(list){
			list.add(c); //동기화
		}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	

}
