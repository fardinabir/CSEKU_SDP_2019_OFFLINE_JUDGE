package Server;

import java.awt.Color;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;


public class ProgramChecker implements Runnable{
	public Queue<Object> q;
	String status,time;
	
	ProgramChecker()
	{
		 q = new LinkedList<>();
	}

	@Override
	public void run() {
		while(true)
		{
			
			System.out.print("");
			if(!q.isEmpty()){
				CodeVar x = (CodeVar) q.remove();
				System.out.print("Printing from queue");
				System.out.println(x.username);
				System.out.println(x.p);
				System.out.println(x.lang);
				System.out.println(x.code);
				
				boolean f = false;
				
				
				///////
				
				try {
					int fg = test.ver(x.code,x.p);
					if(fg==-2)
					{
						status="Compilation Error";
					}
					else if(fg==-1)
					{
						status="Runtime Error";
					}
					else if(fg==-3)
					{
						status="Wrong Answer";
					}
					else if(fg==-4)
					{
						status="Accepted";
						f=true;
					}
					else if(fg>0)
					{
						status="Time Limit Exceed";
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				///////
				
				database db = new database();
				boolean con = db.connect();
				if(con)
				{
					db.submitentry(x.username, x.p, x.lang,status,"15ms");
					db.close();
				}
				q.poll();
				try {
					db.rating_change(x.username, f, Time.Minutes(), x.p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
