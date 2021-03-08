package com.cognizant.demo.service;

import java.util.List;

import com.cognizant.demo.model.Medicine;

public interface MedicineStockService {
	/**
	 * Get the medicine stock information from the database
	 */
	List<Medicine> getMedicineStockInformation();

	/**
	 * Get the medicines by target ailment
	 */
	List<Medicine> getMedicineByTargetAilment(String treatingAilment);

	/**
	 * Get the count of tablets present in the stock for a given medicine
	 */
	Medicine getNumberOfTabletsInStockByName(String medicine);

	/**
	 * Updates the number of tablets in stock by name of medicine
	 */
	Boolean updateNumberOfTabletsInStockByName(String medicine, int count);
}
