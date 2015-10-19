package storage;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

	public static final int SAME = 0;
	private static final int GREATER_THAN = 1;
	private static final int LESSER_THAN = -1;

	@Override
	public int compare(Task first, Task second) {
		// TODO Auto-generated method stub
		int result = SAME;

		if (first.getStartDate() != null && second.getStartDate() != null) {
			result = first.getStartDate().compareTo(second.getStartDate());

			// If Start Dates are the same, compare End Dates
			if (result == SAME && first.getEndDate() != null && second.getEndDate() != null) {
				result = first.getEndDate().compareTo(second.getEndDate());
			}

		} else if (first.getStartDate() == null && second.getStartDate() != null) {
			result = LESSER_THAN;
		} else if (first.getStartDate() != null && second.getStartDate() == null) {
			result = GREATER_THAN;
		} else {

		}

		// Either no dates, or start/end dates are the same
		if (result == SAME) {

			result = first.getName().compareTo(second.getName());

			// Both Task names are same, attempt to sort by details
			if (result == SAME && first.getDetails() != null && second.getDetails() != null) {
				result = first.getDetails().compareTo(second.getDetails());
			} 
		}
		System.out.println("Sorting :" +first.getName() + second.getName()+ result);
		return result;

	}

}
