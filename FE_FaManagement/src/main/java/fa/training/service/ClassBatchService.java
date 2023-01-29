package fa.training.service;

import java.util.List;

import fa.training.model.ClassBatchDetailDto;
import fa.training.model.ClassBatchDto;
import fa.training.model.ClassRequestInfoDto;
import fa.training.model.ResponseObject;
import fa.training.model.TraineeClassDto;
import fa.training.util.PaginationResult;

public interface ClassBatchService {

	public String addClass(ClassBatchDetailDto batchDetailDto);

	public ClassBatchDetailDto getDetailClassById(Long id);

	public String updateDetailClassById(Long classId, ClassBatchDetailDto batchDetailDto);

	public String requestMoreInfo(ClassRequestInfoDto classRequestInfoDto);

	public String removeTraineeFromClass(TraineeClassDto traineeClassDto);

	public PaginationResult paginatedClassResult(int pageSize, int pageNum, ClassBatchDto searchData);

	public List<ClassBatchDto> getAllClassBatch();

	public String cancelClassesByIds(List<Long> classIds);

	public String finishClassById(List<Long> classIds);

	public String submitClassById(Long classId);

	public String approveSubmittedClass(Long classId);
	
	public String rejectSubmittedClass(ClassBatchDto classInfo);

	public ResponseObject acceptClass(Long classId, String newStatus);

	public ResponseObject startClass(Long classId, String newStatus);

	public ResponseObject declineClass(Long classId, String newStatus, String remarks);

	public String closeClassById(List<Long> classIds);

}
