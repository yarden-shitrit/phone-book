import java.util.Collections;
import java.util.Scanner;

/*
 * Java Project Made By Yarden Shitrit,
 * Student At Afeka Collage Of Engineering.
 * 
 * 
 * 
 * The main idea of this project is to make a digital phone book.
 * That can add contact, save and load contacts to/from files, search, sort and remove contacts
 * by name/mobile/home number
 * 
 */



public class Main {

	public static void Program(Scanner scn) throws PhoneBookException{
		ContactsFunctions cf = new ContactsFunctions();
		boolean tryAgain = true;
		while (tryAgain) {	
			System.out.println("- - - - - - - - - - - - - -\n- - - Contact Creator - - -\n- - - - - - - - - - - - - -");
			System.out.println("1. Add/Update Contact\n" + 
					"2. Remove Contact\n" + 
					"3. Save Contacts To File\n"+
					"4. Load Contacts From File\n"+
					"5. Sort Contacts\n" + 
					"6. Search Contact\n"+
					"7. Print All Contacts\n" + 
					"- - - - - - - - - - - - - - -");
			System.out.println("Choose your option or any other key to EXIT.\n" + "Your Option:");
			String option = scn.next();

			switch (option) {
			case "1":																//add contacts		
				System.out.println("\nYour Option: "+ option+ "\n"+"ADD/UPDATE CONTACT:");
				addContact(scn,cf);
				break;



			case "2":																//remove contacts
				System.out.println("\nYour Option: "+ option+ "\n"+"REMOVE CONTACT:");
				removeContact(scn,cf);
				break;



			case "3":															//Save Contacts To File
				System.out.println("\nYour Option: "+ option+"\n"+"SAVE CONTACT:");
				cf.save();
				break;



			case "4":															//Load Contacts From File 
				System.out.println("\nYour Option: "+ option+"\n"+"LOAD CONTACT:");
				cf.load();
				break;



			case "5":
				System.out.println("\nYour Option: "+ option+ "\n"+"SORT CONTACT:");
				sortContact(scn,cf);
				break;



			case "6":															//Search Contact By Name 
				System.out.println("\nYour Option: "+ option + " SEARCH CONTACT:");
				searchContact(scn,cf);
				break;



			case "7":															//Print All Contacts
				System.out.println("\nYour Option: "+ option+"\n"+"PRINT CONTACT:");
				cf.print();
				break;



			default:															//end of program
				System.out.println("\nYour Option: " + option);						
				System.out.println("Finished! \n" + "Program ends now..");
				tryAgain = false;
				System.exit(0);
			}
		}
	}
	
	

	/* 
	 * add or update contacts
	 * 
	 * 
	 * first, asking and getting the full Name, Home num, Mobile 
	 * then creating new contact with the full Name/Home num/Mobile that entered 
	 * then checking if the contact is added or updated by checking the size  
	 */

	public static void addContact(Scanner scn, ContactsFunctions cf) {
		System.out.println("Enter First Name: ");
		String fName = scn.next();
		System.out.println("Enter Last Name: ");
		String lName = scn.next();
		System.out.println("Enter Home Number: ");
		String homeNum = scn.next();
		System.out.println("Enter Mobile Number: ");
		String phone = scn.next();
		int size = cf.size();
		try {
			Contact c = new Contact(fName, lName, homeNum, phone);
			boolean x = cf.add(c);
			if (x == true) {
				if(size != cf.size()) {
					System.out.println("Result: Contect Added!\n");
				}else {
					System.out.println("Result: Contect Updated!\n");
				}
			}
		}catch (PhoneBookException e) {
			System.out.println("ADD/UPDATE CONTACT EXCEPTION: "+ e.getMessage());
		}
	}


	/* 
	 * remove contacts by Name or by home number or by mobile number
	 * 
	 * 
	 * first, asking and getting the Name/Home num/Mobile 
	 * second, sorting the collection (by which the user chose)
	 * then creating new contact with the Name/Home num/Mobile that entered 
	 * and searching for the right contact and after we picked him we remove that contact
	 * 
	 */



	public static void removeContact(Scanner scn, ContactsFunctions cf) throws PhoneBookException {
		System.out.println( "1. By Name\n" + 
				"2. By Home\n" + 
				"3. By Mobile");
		Contact c  = null;
		int index = -1;
		String answer = scn.next();
		if(answer.equals("1")) { 		//by name
			System.out.println("\nYour Option: "+ answer+ "\n");
			System.out.println("Enter First Name: ");
			String firstNameRemove = scn.next();
			System.out.println("Enter Last Name: ");
			String lastNameRemove = scn.next();	
			Collections.sort(cf, new SortByFullName());
			c = new Contact(firstNameRemove, lastNameRemove, "00-0000000", "000-0000000");
			index = Collections.binarySearch(cf, c, new SortByFullName());
			if(index == -1) {
				System.out.println("\nResult: Contact Not Found!");
			}else {
				System.out.println("\nAre you Sure?(y/n) ");
				String ans1 = scn.next().toLowerCase();
				if (ans1.equals("y")) {
					cf.remove(index);
					System.out.println("\nResult: Contact Removed!");
				}
			}			

		}else if(answer.equals("2")) { //by home number
			System.out.println("\nYour Option: "+ answer+ "\n");
			System.out.println("Enter Home Number: ");
			String home = scn.next();
			Collections.sort(cf, new SortByHomeNum());
			c = new Contact("jhon", "duoe", home, "000-0000000");
			index = Collections.binarySearch(cf, c, new SortByHomeNum());
			if(index == -1) {
				System.out.println("Result: Contact Not Found!");
			}else {
				System.out.println("\nAre you Sure?(y/n) ");
				String ans1 = scn.next().toLowerCase();
				if (ans1.equals("y")) {
					cf.remove(index);
					System.out.println("\nResult: Contact Removed!");
				}
			}		

		}else if(answer.equals("3")) { //by mobile
			System.out.println("\nYour Option: "+ answer+ "\n");
			System.out.println("Enter Mobile Number: ");
			String mobile = scn.next();
			c = new Contact("jhon", "duoe", "00-0000000", mobile);
			Collections.sort(cf, new SortByPhone());
			index = Collections.binarySearch(cf, c, new SortByPhone());
			if(index == -1) {
				System.out.println("Result: Contact Not Found!");
			}else {
				System.out.println("\nAre you Sure?(y/n) ");
				String ans1 = scn.next().toLowerCase();
				if (ans1.equals("y")) {
					cf.remove(index);
					System.out.println("\nResult: Contact Removed!");
				}
			}			
		}
	}

	/* 
	 * sorting contacts by Name (which is also the default),by home number and by mobile number
	 * 
	 * asking and getting the Name/Home num/Mobile 
	 * sorting the collection (by which the user chose)
	 * and print the collection
	 */


	public static void sortContact(Scanner scn, ContactsFunctions cf) {
		System.out.println("1. By Name\n" + 
				"2. By Home\n" + 
				"3. By Mobile");
		String answer = scn.next();
		if (answer.equals("1")) {		//by name
			System.out.println("\nYour Option: "+ answer+ "\n");
			Collections.sort(cf, new SortByFullName());
			cf.print();

		}else if(answer.equals("2")) {		//by home number
			System.out.println("\nYour Option: "+ answer+ "\n");
			Collections.sort(cf, new SortByHomeNum());
			cf.print();

		}else if(answer.equals("3")) {		//by mobile
			System.out.println("\nYour Option: "+ answer+ "\n");
			Collections.sort(cf, new SortByPhone());
			cf.print();
		}

	}


	/*
	 * searching contact by Name (which is also the default),by home number and by mobile number
	 * 
	 * sorting the collection (by which the user chose)
	 * then creating new contact with the Name/Home num/Mobile that entered 
	 * and searching for the right contact and after we picked him we print that contact
	 * 
	 */

	public static void searchContact(Scanner scn, ContactsFunctions cf) throws PhoneBookException {
		System.out.println("1. By Name\n" + 
				"2. By Home\n" + 
				"3. By Mobile");
		String answer = scn.next();
		Contact c = null;
		int index;
		if (answer.equals("1")) {		//by name
			System.out.println("\nYour Option: "+ answer+ "\n");
			System.out.println("Enter First Name: ");
			String firstNameRemove = scn.next();
			System.out.println("Enter Last Name: ");
			String lastNameRemove = scn.next();	
			Collections.sort(cf, new SortByFullName());
			c = new Contact(firstNameRemove, lastNameRemove, "00-0000000", "000-0000000");
			index = Collections.binarySearch(cf, c, new SortByFullName());
			if(index == -1) {
				System.out.println("\nResult: Contact Not Found!");
			}else {
				System.out.println("\nResult: "+cf.get(index));
			}

		}else if(answer.equals("2")) {		//by home number
			System.out.println("\nYour Option: "+ answer+ "\n");
			System.out.println("Enter Home Number: ");
			String ans = scn.next();
			Collections.sort(cf, new SortByHomeNum());
			c = new Contact("john", "duoe", ans, "000-0000000");
			index = Collections.binarySearch(cf, c, new SortByHomeNum());
			if(index == -1) {
				System.out.println("\nResult: Contact Not Found!");
			}else {
				System.out.println("\nResult: "+cf.get(index));
			}

		}else if(answer.equals("3")) {		//by mobile
			System.out.println("\nYour Option: "+ answer+ "\n");
			System.out.println("Enter Mobile Number: ");
			String ans = scn.next();
			c = new Contact("john", "duoe", "00-0000000", ans);
			Collections.sort(cf, new SortByPhone());
			index = Collections.binarySearch(cf, c, new SortByPhone());
			if(index == -1) {
				System.out.println("\nResult: Contact Not Found!");
			}else {
				System.out.println("\nResult: "+cf.get(index));
			}
		}
	}


	public static void main(String[] args) throws PhoneBookException  {
		Scanner scn = new Scanner(System.in);
		Program(scn);
		scn.close();
	}	

}
