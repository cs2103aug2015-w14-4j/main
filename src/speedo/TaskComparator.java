package speedo;
import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

	public static final int SAME_DATE = 0;

	@Override
	public int compare(Task first, Task second) {
		// TODO Auto-generated method stub
		int result = SAME_DATE;
		
		if (first.getStartDate() != null && second.getStartDate() != null) {
			result = first.getStartDate().compareTo(second.getStartDate());
		}
		if (result == SAME_DATE && first.getEndDate() != null && second.getEndDate() != null) {
			return first.getEndDate().compareTo(second.getEndDate());
		} else {
			return result;
		}
	}

}
