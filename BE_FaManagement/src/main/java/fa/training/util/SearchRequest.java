package fa.training.util;

import java.util.List;

import fa.training.model.ClassBatchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SearchRequest {
	private List<String> columns;

	private ClassBatchDto searchData;
}