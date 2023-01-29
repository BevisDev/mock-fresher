package fa.training.service;

import java.util.List;

import fa.training.model.ClassBatchDetailDto;
import fa.training.model.ClassBatchDto;
import fa.training.util.PaginationResult;
import fa.training.model.ClassRequestInfoDto;
import fa.training.model.TraineeClassDto;

import fa.training.util.SearchRequest;

public interface ClassBatchService {
	public void addClass(ClassBatchDetailDto batchDetailDto);

	public List<ClassBatchDto> getAllClassBatch();

	public PaginationResult paginatedClassResult(int pageSize, int pageNum);

	public PaginationResult paginatedClassResultAfterSearch(SearchRequest searchRequest, int pageSize, int pageNum);

	public ClassBatchDto getClassById(Long id);

//	List<ClassBatchDto> getListClassBatchBySearch(SearchRequest searchRequest);

	public ClassBatchDetailDto getDetailClassById(Long id);

	public void updateClassBatch(Long id, ClassBatchDetailDto classBatchDetailDto);

	public void cancelClassBatch(List<Long> listOfClassIds);

	public void submitClassBatch(Long classId);
	
	public void approveSubmittedClassBatch(Long classId);
	
	public void rejectSubmittedClassBatch(ClassBatchDto classInfo);

	public void requestForMoreInfo(ClassRequestInfoDto requestInfoDto);

	public void removeTraineeFromClass(TraineeClassDto traineeClassDto);

	public boolean changeStatus(String originStatus, String newStatus, Long classId);

	public void finishClass(List<Long> classIds); 

	public boolean changeStatusAndRemarks(String originStatus, String newStatus, Long classId, String remarks);

	public void closeClassBatch(List<Long> classIds);
}
