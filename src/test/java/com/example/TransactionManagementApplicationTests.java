package com.example;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import model.StatusModel;
import model.SumModel;
import model.TransactionModel;
import service.TransactionService;
import service.TransactionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TransactionManagementApplication.class)
@WebAppConfiguration
public class TransactionManagementApplicationTests {

	@Autowired
	TransactionService transService;
	
	@Autowired
	TransactionServiceImpl transServiceImpl = new TransactionServiceImpl();

	Map<Integer, TransactionModel> testTransMap = new HashMap<Integer, TransactionModel>();
	
	@Test
	public void addTransactions() {
		transServiceImpl.setTransactionMap(testTransMap);
		int id = 1;
		TransactionModel trans = new TransactionModel(100, "clothes");

		StatusModel status = transService.addNewTransaction(id, trans);
		assertEquals("ok", status.getStatus());
		assertTrue(testTransMap.containsKey(id));
		assertEquals(trans, testTransMap.get(id));
	}
	
	@Test
	public void getTransactionFromId(){
		int key1 = 10;
		int key2 = 11;
		TransactionModel trans1 = new TransactionModel(5000, "cars");
		TransactionModel trans2 = new TransactionModel(10000, "shopping", key1);
		testTransMap.put(key1, trans1);
		testTransMap.put(key2, trans2);
		transServiceImpl.setTransactionMap(testTransMap);
		
		int transactionId = key2;
		
		TransactionModel transaction = transService.searchTransactionById(transactionId);
		assertEquals(transaction, testTransMap.get(transactionId));
	}
	
	
	@Test
	public void getTransactionsFromType(){
		int key1 = 10;
		int key2 = 11;
		TransactionModel trans1 = new TransactionModel(5000, "cars");
		TransactionModel trans2 = new TransactionModel(10000, "shopping", key1);
		testTransMap.put(key1, trans1);
		testTransMap.put(key2, trans2);
		transServiceImpl.setTransactionMap(testTransMap);

		String type = "cars";

		List<Integer> trans = transService.getTransactionIdsFromType(type);
		assertTrue(trans.size() == 1);
		assertTrue(trans.contains(key1));
	}
	
	@Test
	public void getAmountById(){
		int key1 = 10;
		int key2 = 11;
		TransactionModel trans1 = new TransactionModel(5000, "cars");
		TransactionModel trans2 = new TransactionModel(10000, "shopping", key1);
		testTransMap.put(key1, trans1);
		testTransMap.put(key2, trans2);
		transServiceImpl.setTransactionMap(testTransMap);
		
		int parentId = key1;

		SumModel sum = transService.totalAmountByTransactionId(parentId);
		assertTrue(trans1.getAmount() + trans2.getAmount() == sum.getSum());

	}

}
