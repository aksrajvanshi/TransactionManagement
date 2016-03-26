package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.StatusModel;
import model.SumModel;
import model.TransactionModel;

@Service
public interface TransactionService {
	
	List<Integer> getTransactionIdsFromType(String transType);

	StatusModel addNewTransaction(int id, TransactionModel transaction);

	TransactionModel searchTransactionById(Integer id);
	
	SumModel totalAmountByTransactionId(Integer id);
}
