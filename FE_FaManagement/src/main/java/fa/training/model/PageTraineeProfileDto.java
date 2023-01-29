package fa.training.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageTraineeProfileDto {

	private List<TraineeProfileDto> content;

	private Integer totalElements;

	private Integer totalPages;

	private Integer number;

	private Integer size;

	private Integer numberOfElements;

}
