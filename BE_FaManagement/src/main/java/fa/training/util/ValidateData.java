package fa.training.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import fa.training.entity.Audit;
import fa.training.entity.BudgetDetail;

@Component
public class ValidateData {

	public boolean validateStartDate(LocalDate startDate, LocalDate endDate)
	{
		return startDate.isBefore(endDate);
	}
	
	public boolean validateBudgetDetail(BudgetDetail budgetDetail) {
		boolean rs = true;
		try {
			if (budgetDetail.getItem().equals("") || budgetDetail.getItem() == null)
				rs = false;
			if (budgetDetail.getUnit().equals("") || budgetDetail.getUnit() == null)
				rs = false;
			if (budgetDetail.getSum() == 0 || budgetDetail.getSum() == null)
				rs = false;
		} catch (NullPointerException e) {
			rs = false;
		}
		return rs;
	}

	public boolean validateAudit(Audit audit) {
		boolean rs = true;
		try {
			if (audit.getDate() == null)
				rs = false;
			if (audit.getAction().equals("") || audit.getAction() == null)
				rs = false;
			if (audit.getPic().equals("") || audit.getPic() == null)
				rs = false;
			if (audit.getDeadline() == null)
				rs = false;
		} catch (NullPointerException e) {
			rs = false;
		}
		return rs;
	}
	
	public boolean validateEmail(String email)
	{
		return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
	}
	
	public String validatePhone(String phone)
	{
		String err = "";
		if(!phone.startsWith("0"))
			err = MESSAGE.MSG5.msg;
		else if(!phone.matches("^\\d{10,14}"))
			err = MESSAGE.MSG24.msg;
		return err;
	}
}
