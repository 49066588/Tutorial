package writer_;

import main_.writer_.IWriter;
import controller_.ICsvController;

/**
 * An {@link ICsvWriter} is used to write to a csv file.
 * 
 * @author Christian Oedingen
 */
public interface ICsvWriter extends IWriter {

	/**
	 * Set csv controller.
	 * @param iCsvController_csvController An {@link ICsvController}.
	 */
	public void setCsvController(ICsvController iCsvController_csvController);

	/**
	 * Get csv controller.
	 * @return An {@link ICsvController}.
	 */
	public ICsvController getCsvController();
	
}