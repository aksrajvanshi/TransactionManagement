package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.StatusModel;
import model.SumModel;
import model.TransactionModel;
import service.TransactionService;

@RestController
@RequestMapping(value = "/transactionservice")
@ComponentScan(basePackageClasses = TransactionService.class)
public class TransactionController {
	
	@Autowired
	TransactionService transService;

	/**
	 * @param id the transactionId to be passed for the record.
	 * @param transaction Details about transaction
	 * @return a JSON string returning the status of the operation.
	 */
	@RequestMapping(path = "/transaction/{id}", method = RequestMethod.PUT)
	public String addTransaction(@PathVariable("id") int id, @RequestBody TransactionModel transaction) {
		StatusModel returnStatus = transService.addNewTransaction(id, transaction);
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(returnStatus);
		return response;
		
	}
	
	/**
	 * @param id the transactionId to be passed for the record.
	 * @return JSON string returning the transaction details if the record exists.
	 */
	@RequestMapping(path = "/transaction/{id}", method = RequestMethod.GET)
	public String getTransactionDetails(@PathVariable("id") int id){
		TransactionModel trans = new TransactionModel();
		trans = transService.searchTransactionById(id);
		if(trans!=null)
		{
		Gson gson = new GsonBuilder().create();
		String response = gson.toJson(trans);
		return response;
		}
		else
		{
			return "Record Not Found";
		}
	}
	
	/**
	 * @param type String specifying the type of transaction to look in the memory.
	 * @return List of transactionIds having the type specified to look for.
	 */
	@RequestMapping(path = "/types/{type}", method = RequestMethod.GET)
	public List<Integer> getTransactionType(@PathVariable("type") String type){
		List<Integer> transId = new ArrayList<Integer>();
		transId = transService.getTransactionIdsFromType(type);
		return transId;
		
	}
	
	/**
	 * @param id the transactionId to be passed for the record.
	 * @return Sum of all the transactions having transactionId/parent_id as the specified ID.
	 */
	@RequestMapping(path = "/sum/{id}", method = RequestMethod.GET)
	public String getTransactionTotalSum(@PathVariable("id") int id){
		SumModel totalSum = transService.totalAmountByTransactionId(id);
		Gson gson = new GsonBuilder().create();
		String sum = gson.toJson(totalSum);
		return sum;
	}

}
