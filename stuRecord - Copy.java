package simpleIndex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;

public class stuRecord {
	private String stuName,stuUsn,stuBranch,stuSem,stuPhno,stuGender;
	private SimpleIndex[] sI = new SimpleIndex[20];
	int reccount = 0;
		
	public void getStu(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the students's yout usn: ");
		stuUsn = scanner.next();
		System.out.println("Enter the students's name: ");
		stuName = scanner.next();
		System.out.println("Enter the students's branch: ");
		stuBranch = scanner.next();
		System.out.println("Enter the students's sem: ");
		stuSem = scanner.next();
		System.out.println("Enter the students's phone number: ");
		stuPhno = scanner.next();
		System.out.println("Enter the students's gender: ");
		stuGender = scanner.next();
	}
	
	public void pack(){
		String packed = stuUsn +"|"+  stuName +"|"+ stuBranch +"|"+ stuSem +"|"+ stuPhno +"|"+ stuGender +"|";
		System.out.println(packed);
		try{			
			RandomAccessFile recordfile = new RandomAccessFile ("/home/vadiraja/packed.txt","rw");
			recordfile.seek(recordfile.length());
			long pos = recordfile.getFilePointer();
			recordfile.writeBytes(packed+"\n");
			recordfile.close();
			
			RandomAccessFile indexfile = new RandomAccessFile ("/home/vadiraja/index.txt","rw");
			indexfile.seek(indexfile.length());
			indexfile.writeBytes(stuUsn+"|"+pos+"\n");
			indexfile.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
	}
	
	public void unPack(){
		try{
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader("/home/vadiraja/packed.txt"));
    		String line;
                try{
              
                while((line = reader.readLine())!= null){
                	int count = 0;
                	StringTokenizer st = new StringTokenizer(line,"|");
                	while (st.hasMoreTokens()){
           	    	 count += 1;
           	    	 if(count==1) {
           	         System.out.println("USN: "+st.nextToken());
           	    	 System.out.println("NAME: "+st.nextToken());
           	    	 System.out.println("BRANCH: "+st.nextToken());
           	    	 System.out.println("SEM: "+st.nextToken());
           	    	 System.out.println("PHONE: "+st.nextToken());
           	    	 System.out.println("GENDER: "+st.nextToken());
           	    	 System.out.println();
           	    	 }
           	    	 
           	    	 else
           	    		 break;
                }
                }
                }
                catch(Exception e){return;}
    		
    		}
			catch(IOException e){
				return;
			}
	}
	
	@SuppressWarnings("resource")
	public void search(){
					System.out.println("Enter the USN to search: ");
					Scanner scanner = new Scanner(System.in);
					String usn = scanner.next();
					
					int pos = binarySearch(sI, 0, reccount-1, usn);
					
					if (pos == -1) {
						System.out.println("Record not found in the record file");
						return ;
					}
					
					RandomAccessFile recordfile;
					try {
						recordfile = new RandomAccessFile ("/home/vadiraja/packed.txt","rw");
						try {
							recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
							String record = recordfile.readLine();
							StringTokenizer st = new StringTokenizer(record,"|");
							
							int count = 0;
		               	    
		                	while (st.hasMoreTokens()){
		                		     count += 1;
		                  	    	 if(count==1){
		                  	    	 String tmp_usn = st.nextToken();
									 System.out.println("USN: "+tmp_usn);
		                  	         this.stuUsn = tmp_usn;
		                  	    	
		                  	          String tmp_name = st.nextToken();
		                     	      System.out.println("NAME: "+tmp_name);
		                     	      this.stuName = tmp_name;
		                     	       
		                     	       String tmp_branch = st.nextToken();
		                     	       System.out.println("BRANCH: "+tmp_branch);
		                     	       this.stuBranch = tmp_branch;
		                  	    	 
		                     	        String tmp_sem = st.nextToken();
		                     	        System.out.println("SEM: "+tmp_sem);
		                     	        this.stuSem = tmp_sem;
		                     	      
		                     	        String tmp_ph = st.nextToken();
		                     	        System.out.println("PHONE: "+tmp_ph);
		                     	        this.stuPhno = tmp_ph;
		                     	     
		                     	        String tmp_g = st.nextToken();
		                     	        System.out.println("GENDER: "+tmp_g);
		                     	        this.stuGender = tmp_g;
		                  	    	 	System.out.println();
		                  	    	 	
		                  	    	 }
		                  	    	 else
		                  	    		 break;
		                       }
		                	
						} 
							catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						}
												
	                	catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
					

    int binarySearch(SimpleIndex s[], int l, int r, String usn) {
    	
    	int mid;
    	while (l<=r) {
    		mid = (l+r)/2;
    		if(s[mid].getStuUSN().compareTo(usn)==0) {return mid;}
    		if(s[mid].getStuUSN().compareTo(usn)<0) l = mid + 1;
    		if(s[mid].getStuUSN().compareTo(usn)>0) r = mid - 1;
    	}
    	return -1;
    }


	public void delete() {
		 
		
		
	
	}
	
	@SuppressWarnings("resource")
	public void loadIndex(){
		
		String line,usn = null,pos = null;
		int count = 0;
		int sIIndex = 0;
		reccount=0;
		RandomAccessFile indexfile;
		try {
			indexfile = new RandomAccessFile("/home/vadiraja/index.txt", "rw");
			try {
				
				while((line = indexfile.readLine())!= null){
					count = 0;
					
					StringTokenizer st = new StringTokenizer(line,"|");
					while (st.hasMoreTokens()){
					 count += 1;
					 if(count==1)
				     usn = st.nextToken();
					 pos = st.nextToken();
				    }
					sI[sIIndex] = new SimpleIndex();
					sI[sIIndex].setRecPos(pos);
					sI[sIIndex].setStuUSN(usn);
					reccount++;
					sIIndex++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} //true tells to append data.
		
		System.out.println("total records" + reccount);
		if (reccount==1) { return;}
		sortIndex();
	}
	
	
	public void sortIndex() {
		SimpleIndex temp = new SimpleIndex();
		
		for(int i=0; i<reccount; i++)
		    {	
				for(int j=i+1; j<reccount; j++) {
					if(sI[i].getStuUSN().compareTo(sI[j].getStuUSN())  > 0)
		            {
		                temp.setStuUSN(sI[i].getStuUSN()); 
				        temp.setRecPos(sI[i].getRecPos());
				
			        	sI[i].setStuUSN(sI[j].getStuUSN());
			        	sI[i].setRecPos(sI[j].getRecPos());
				
			        	sI[j].setStuUSN(temp.getStuUSN());
			        	sI[j].setRecPos(temp.getRecPos());
		            }
				}
					
			}	
		
	}
	
}

	
	


