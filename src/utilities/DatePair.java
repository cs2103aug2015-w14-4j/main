//@@author A0125369Y
package utilities;

import java.util.Date;

/**
 * DatePair is a class representation of a pair of dates.
 * 
 * @author Barnabas
 *
 */

public class DatePair {
	private Date dateOne;
	private Date dateTwo;

	public DatePair(Date dateOne, Date dateTwo) {
		this.setDateOne(dateOne);
		this.setDateTwo(dateTwo);
	}

	public Date getDateOne() {
		return dateOne;
	}

	public void setDateOne(Date dateOne) {
		this.dateOne = dateOne;
	}

	public Date getDateTwo() {
		return dateTwo;
	}

	public void setDateTwo(Date dateTwo) {
		this.dateTwo = dateTwo;
	}
}
