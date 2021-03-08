package com.cognizant.demo.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.demo.dao.MedicineStockDAO;
import com.cognizant.demo.model.Medicine;
import com.cognizant.demo.service.MedicineStockService;


@RestController
public class StockController {

	
	@Autowired
	private MedicineStockService medicineStockService;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	/*
	 * @RequestMapping(value = "/Medicine-Stock-Information", method =
	 * RequestMethod.GET) public List<Medicine> getMedicineList(){ List<Medicine>
	 * medicine = new ArrayList<>(); dao.findAll().forEach(medicine::add); return
	 * medicine; }
	 */
	//----------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/MedicineStockInformation")
	public ResponseEntity<?> getMedicineStockInformation() {
		List<Medicine> medicineStockInformation = null;	
		medicineStockInformation = medicineStockService.getMedicineStockInformation();
		return new ResponseEntity<>(medicineStockInformation, HttpStatus.OK);
	}

	@RequestMapping(value="/byTreatingAilment/{treatingAilment}",method=RequestMethod.GET)
	public ResponseEntity<?> getMedicineByTreatingAilment(@PathVariable("treatingAilment") String treatingAilment) {
		List<String> medicines = new ArrayList<>();
		List<Medicine> medicineByTargetAilment = medicineStockService.getMedicineByTargetAilment(treatingAilment);		
		for (Iterator iterator = medicineByTargetAilment.iterator(); iterator.hasNext();) {
			Medicine medicineStock = (Medicine) iterator.next();
			medicines.add(medicineStock.getName());
		}
		return new ResponseEntity<>(medicines.toArray(new String[0]), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-stock-count/{medicine}" , method = RequestMethod.GET)
	public ResponseEntity<?> getStockCountForMedicine(@PathVariable("medicine") String medicine) {
		Medicine medicineStock = null;
		medicineStock = medicineStockService.getNumberOfTabletsInStockByName(medicine);
		
		return new ResponseEntity<>(medicineStock.getNumberOfStock(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-stock/{medicine}/{count}", method = RequestMethod.GET)
	public Boolean updateNumberOfTabletsInStockByName(@PathVariable("medicine") String medicine, @PathVariable("count") int count) {
		Boolean ans = false;
		ans = medicineStockService.updateNumberOfTabletsInStockByName(medicine, count);
		return ans;
		
	}
	
	

	
}
