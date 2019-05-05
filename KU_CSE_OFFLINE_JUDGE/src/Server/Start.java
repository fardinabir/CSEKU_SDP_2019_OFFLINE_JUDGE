package Server;


import java.time.LocalDateTime;
import java.util.Date;

;

public class Start {
	public static ProgramChecker checker;
	public static Date stime;
	public static void main(String[] args)
	{
		Main main = new Main();
		Server server = new Server();
		checker = new ProgramChecker();
		Thread thread1 = new Thread(main);
		Thread thread2 = new Thread(server);
		Thread thread3 = new Thread(checker);
		thread1.start();
		thread2.start();
		thread3.start();
		
	}

}
