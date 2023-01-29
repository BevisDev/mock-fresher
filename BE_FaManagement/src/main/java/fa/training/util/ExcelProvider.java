package fa.training.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelProvider {

	public static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
        return cellValue;
    }
}
