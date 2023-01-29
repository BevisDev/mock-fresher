package fa.training.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResult {
	private int currentPage;
	private int totalPages;
	private int totalItems;
	private int allItems;
	private List<?> listOfObjects;
	private int pageSize;
}
