import java.util.Comparator;

public class SortByFullName implements Comparator<Contact>{
	@Override
	public int compare(Contact o1, Contact o2) {
		return o1.compareTo(o2);
	}
}
