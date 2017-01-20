package reader_;

import controller_.ICsvController;
import main_.reader_.IReader;

/**
 * An {@link ICsvReader} is used to read from a csv file.
 * 
 * @author Christian Oedingen
 */
public interface ICsvReader extends IReader {
	
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