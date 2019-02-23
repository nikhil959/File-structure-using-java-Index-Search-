package simpleIndex;

import java.io.IOException;

import java.util.Scanner;

public class stuMain {
	public static void main(String args[]) throws IOException{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		stuRecord stu = new stuRecord();
		stu.loadIndex();
		while (true){
			System.out.println("Simple Index"); 
			System.out.println("Enter your choice: "); 
			System.out.println("1.Enter student details\n2.Unpack\n3.Search\n 4.Delete \n 5.Exit");
		
			int choice = scanner.nextInt();
		
			switch(choice){
		
				case 1 :stu.getStu();
						stu.pack();
						stu.loadIndex();
						break;
				case 2: stu.unPack();
						break;
				case 3: stu.search();
						break;
				case 4: stu.delete();
						break;
				case 5: System.out.println("Exiting..");
						System.exit(0);
				default : System.out.println("Enter a valid choice!");
			}
		}
	}
}

