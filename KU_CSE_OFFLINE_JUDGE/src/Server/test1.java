package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class test1 {
	static String rer = "";
	public static void main(String[] args) throws IOException, InterruptedException {
		
        long startTime = System.currentTimeMillis();
        
        
        //verdict('A');
        long endTime = System.currentTimeMillis();
        //System.out.println("That took " + (endTime - startTime) + " milliseconds");
        
	}
	static int verdict(char pname) throws IOException
	{
		ProcessBuilder builder = null;
		if(pname=='A')
		{
			builder = new ProcessBuilder("cmd.exe", "/k"," cd .. && cd .. && cd .. && cd fardin~abir && cd desktop && vA.bat");
		}
		else if(pname=='B')
		{
			builder = new ProcessBuilder("cmd.exe", "/k"," cd .. && cd .. && cd .. && cd fardin~abir && cd desktop && vB.bat");
		}
        builder.redirectErrorStream(true);
        Process p = builder.start();
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader er = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        System.out.print("\nChecking ans......\n\n ");
        
        int flag=0;
        
        String err = null;
        while((line= er.readLine()) != null)
        {
        	err+=line;
        }
        if(err!=null)
        {
        	System.out.println("Runtime...!");
        }
        int mi,se;
        String strt = null,end = null;
        int ff=0,compile_err=0,rtime_err=1,wrong_a=0,time_l=0,right_ans=0;
        line="";
        while (true) {
        	System.out.println(line);
        	if(line.equals(""))
        	{
        		line=r.readLine();
        		System.out.println(line);
        		while(!line.contains("s;"))
        		{
        			rer+="\n"+line;
        			line=r.readLine(); 
        			System.out.println(rer);
        		}
        		strt=line;
        		if(!rer.isEmpty())
        		{
        			System.out.println(line);
        			compile_err=1;
            		break;
        		}
        		
        	}
        	line=r.readLine();
        	System.out.println(line);
        	if(line.contains("e;")==true)
        	{
        		end=line;
        		continue;
        	} 
        	else if(line.equals("terminate"))
        		break;
        	
        	if(line.equals("FC: no differences encountered"))
        	{
        		
        		right_ans=1;
        		rtime_err=0;
        		break;
        	} 
        	else if(line.contains("*****")==true)
        	{
        		wrong_a=1;
        		flag=1;
        		rtime_err=0;
        		break;
        	}   	        	
        }
        if(compile_err==1)
        {
        	System.out.println("Compilation Error...!\n\n" + rer);
        	return -2;
        }
        else if(rtime_err==1)
        {
        	System.out.println("Runtime Error...!\n\n" + rer);
        	return -1;
        }
        
        
        // TLE calculation ******      /////  
        int res = 0,start_time = 0,end_time=0,i,j=strt.length();
        for (i=2; i < j-2; i++) {
            char c = strt.charAt(i);
            if (c >= '0' && c <= '9') 
            {
            	res = res * 10 + (c - '0');
            }
            else
            	start_time=start_time*60+res;         	            
        }
        start_time*=100;
        res=strt.charAt(i);
        start_time+=((strt.charAt(i)-48)*10+(strt.charAt(i+1)-48));
        
        res=0;
        for (i=2; i < j-2; i++) {
            char c = end.charAt(i);
            if (c >= '0' && c <= '9') 
            {
            	res = res * 10 + (c - '0');
            }
            else
            	end_time=end_time*60+res;         	            
        }
        end_time*=100;
        res=end.charAt(i);
        end_time+=((end.charAt(i)-48)*10+(end.charAt(i+1)-48));
        ///*************************/////
        
        
        System.out.println("runtime : "+(end_time-start_time)*10);
        
        if((end_time-start_time)>100 )
        {
        	System.out.println("Time Limit Exceed...! ( "+(end_time-start_time)*10+"ms )\n\n");
        	return (end_time-start_time)*10;
        }
        
        else if(wrong_a==1 && right_ans==0)
        {
        	System.out.println("Wrong answer...!\n\n");
        	return -3;
        }
        else if(right_ans==1)
        {
        	System.out.println("Accepted....!!!");
        	return -4;
        }
        System.out.println("\nTerminated Successfully\n\n");
		return 100;
	}

}
