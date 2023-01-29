package fa.training.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseObjectPaginated {
	private String status;
	private String message;
	private PaginationResult data;
}
