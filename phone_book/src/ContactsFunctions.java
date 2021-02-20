import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


public class ContactsFunctions extends LinkedList<Contact> implements Iterable<Contact>{
	
	private static final long serialVersionUID = 1L;
	private Comparator <Contact> comparator;

	public ContactsFunctions() {
		this.comparator = new SortByFullName();
	}
	
	
	/*
	 * add contact to the linked list in the right place by searching in the collection and setting it 
	 * return true if added or updated, return false if not
	 * 
	 */
	
	public boolean add(Contact c) {
		if(c == null) {
			return false;
		}
		Collections.sort(this, new SortByFullName());
		int index = Collections.binarySearch(this, c, new SortByFullName());
		if (index >= 0)
			super.set(index, c);
		else
			super.add(Math.abs(index + 1), c); // the first place in the linked list
		sort();
		return true;
	}
	
	
	/*
	 * sort the list by given comparator 
	 */
	
	public void sort() {
		Collections.sort(this, comparator);
	}

	
	/*
	 * get contact by given index
	 */
	
	public Contact get (int index) {
		if (index >= 0 && index<size()) {
			return super.get(index);
		}
		return null;
	}

	/*
	 * remove contact by given index
	 */
	
	public Contact remove(int index) {
		if (index >= 0 && index < size()){
			return super.remove(index);
		}
		return null;
	}
	
	
	
	/*
	 * creating new file called "contacts.txt" and placing the contacts into the file
	 * 
	 */

	public void save() {
		try (ObjectOutputStream oOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("contacts.txt")))) {
			if(size() != 0) { // size = size of the linked list
				oOut.writeInt(size()); // write the num of contacts into file 
				for (Contact c: this) {
					oOut.writeObject(c);
				}
				System.out.println("Contacts Saved To File: " + size());
			}else {
				System.out.println("List Is Empty.\n");
				oOut.writeInt(-1);
			}
		}catch (FileNotFoundException e) {
			System.out.println("File Not Found!\n");
		}catch (IOException e) {
			System.out.println("Load Exception: " + e.getMessage());
		}
	}
	
	

	
	/*
	 *load contacts from file called "contacts.txt", add or update them to the list
	 *
	 */
	
	public void load() {
		int updated = 0;
		int added = 0;
		int size = size();
		try (ObjectInputStream oIn = new ObjectInputStream( new BufferedInputStream(new FileInputStream("contacts.txt")))) {
			int size1 = oIn.readInt(); //read the num of contacts 
			if (size1 == -1) {
				System.out.println("File Is Empty.");
			}else {
				while (size1 > 0) {
					boolean x = add((Contact) oIn.readObject());
					if (x == true) {
						if(size == size()) { //is the size of the current collection =  
							updated++;
						}else {
							added++;
							size++;
						}
					}
					size1--;
				}
			}
			
			System.out.println(size+" Contacts Loaded From File: Added: " + added + " Updated: " + updated + "\n");
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!\n");
		} catch (IOException e) {
			System.out.println("Load Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Load Exception: File recipes.obj Not Contains Recipe Data!");
		}
	}

	

	
	/*
	 *  print the linked list
	 *  
	 */
	
	public void print() {
		if (this.isEmpty()) {
			System.out.println("Contast List is Empty!\n");
		}else {
			System.out.println("Contacts: \n");
			for (int i = 0; i < size(); i++) {
				System.out.println(this.get(i).getfName().charAt(0) + ": ");
				System.out.println("\t" + this.get(i));
			}
		}
	}

	@Override
	public String toString() {
		return "size=" + size() + ", arr=" + this ;
	}
	
	
	/*
	 * inner class that implementing iterator interface
	 * helps us do direct action into the linked list 
	 * 
	 */
	
	class ContactIterator implements Iterator<Contact> {
		private ContactsFunctions list;
		private int index;

		@Override
		public boolean hasNext() {
			return (index + 1) < size();
		}

		@Override
		public Contact next() {
			if(hasNext()) {
				return list.get(index++);
			}
			return null;
		}

		public void remove() {
			if(index>=0 && index< list.size())
				list.remove(index);
		}

		
		//getters and setters
		public ContactsFunctions getList() {
			return list;
		}

		public void setList(ContactsFunctions list) {
			this.list = list;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}
	}
	
}
