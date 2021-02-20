import java.io.Serializable;
import java.util.regex.Pattern;

public class Contact implements Serializable, Comparable<Contact>{
	
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String homeNum;
	private String phone;

	public Contact(String fName, String lName, String homeNum, String phone) throws PhoneBookException {
		setfName(fName);
		setlName(lName);
		if (homeNum == null && phone == null) {
			throw new PhoneBookException("\nYou Must Enter At Least One Valid, Mobile Or Home Number! \n");
		}
		else {
			setHomeNum(homeNum);
			setPhone(phone);
		}
	}
	
	/*
	 * compare contacts by fisrt name, if they equal compare by last name 
	 * 
	 */

	@Override
	public int compareTo(Contact o) {
		if (this.getfName().compareTo(o.getfName()) > 0) {
			return 1;
		}else if (this.getfName().compareTo(o.getfName()) < 0) {
			return -1;
		}else {
			if(this.getlName().compareTo(o.getlName()) > 0) {
				return 1;
			}else if (this.getlName().compareTo(o.getlName()) < 0) {
				return -1;
			}else {
				return 0;
			}

		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((homeNum == null) ? 0 : homeNum.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Contact other = (Contact) obj;
		if (!fName.equals(other.fName)) {
			if(!lName.equals(other.lName)) {
				return false;
			}
		}
		return true;
	}
	
	

	public String getfName() {
		return fName;
	}

	

	public void setfName(String fName) throws PhoneBookException {
		if(fName.length() < 3) throw new PhoneBookException("First Name Can Not be less then 3 characters!\n");
		this.fName = fName;
	}

	

	public String getlName() {
		return lName;
	}

	

	public void setlName(String lName) throws PhoneBookException {
		if(lName.length() < 3) throw new PhoneBookException("Last Name Can Not be less then 3 characters!\n");
		this.lName = lName;
	}

	

	public String getHomeNum() {
		return homeNum;
	}

	
	
	public void setHomeNum(String homeNum) throws PhoneBookException {
		if(homeNum != null)
			if(!Pattern.matches("[0-9]{2}-?[0-9]{7}", homeNum)) throw new PhoneBookException("Home Number is not valid!\n");
		this.homeNum = homeNum;

	}

	

	public String getPhone() {
		return phone;
	}

	

	public void setPhone(String phone) throws PhoneBookException {
		if(phone != null)
			if(!Pattern.matches("[0-9]{3}-?[0-9]{7}", phone)) throw new PhoneBookException("Mobile Number is not valid!\n");
		this.phone = phone;

	}

	
	@Override
	public String toString() {
		return fName + " "  + lName +": " + homeNum + "  ," + phone ;
	}
	
}
