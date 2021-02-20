import java.util.Comparator;

public class SortByHomeNum  implements Comparator<Contact>{
	@Override
	public int compare(Contact o1, Contact o2) {
		return o1.getHomeNum().compareTo(o2.getHomeNum());
	}
}
