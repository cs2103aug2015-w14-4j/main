//@@author A0125369Y
package storage;

import java.util.Comparator;

/**
 * TaskComparator handles how Tasks are sorted
 * <p>
 * Currently tasks are sorted in the following order; Tasks without dates are at
 * the top (Sorted by task name, then details), followed by tasks with dates
 * (Sorted by starting dates, then ending dates).
 * 
 * @author Barnabas
 *
 */
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
		return result;
	}
}
