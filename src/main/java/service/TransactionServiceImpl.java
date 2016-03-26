package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;
import model.StatusModel;
import model.SumModel;
import model.TransactionModel;

@Service
public class TransactionServiceImpl implements TransactionService {

	private Map<Integer, TransactionModel> transactionMap = new HashMap<Integer, TransactionModel>();

	public void setTransactionMap(Map<Integer, TransactionModel> transactionMap) {
		this.transactionMap = transactionMap;
	}

	/* (non-Javadoc)
	 * 
	 * Adds new transaction to the hashMap if a record with given transactionId doesn't exist. Else returns an error message.
	 * 
	 * @see service.TransactionService#addNewTransaction(int, model.TransactionModel)
	 */
	@Override
	public StatusModel addNewTransaction(int id, TransactionModel transaction) {
		// TODO Auto-generated method stub
		if(transactionMap.get(id)!=null)
		{
			return new StatusModel("Error. A transaction with same transaction Id already exists!");
		}
		transactionMap.put(id, transaction);
		return new StatusModel("ok");
	}

	
	/* (non-Javadoc)
	 * Searches the memory for transaction for a given transactionId and returns it.
	 * @see service.TransactionService#searchTransactionById(java.lang.Integer)
	 */
	@Override
	public TransactionModel searchTransactionById(Integer id) {
		// TODO Auto-generated method stub
		TransactionModel transaction = new TransactionModel();
		transaction = transactionMap.get(id);
		return transaction;
	}

	/* (non-Javadoc)
	 * Returns a list of transaction ID's having the type specified.
	 * @see service.TransactionService#getTransactionIdsFromType(java.lang.String)
	 */
	@Override
	public List<Integer> getTransactionIdsFromType(String transType) {
		// TODO Auto-generated method stub
		List<Integer> transactionIds = new ArrayList<Integer>();
		
		for (Entry<Integer, TransactionModel> entry : transactionMap.entrySet()) {
			if(entry.getValue().getType().equals(transType)){
				transactionIds.add(entry.getKey());
			}
		}
		
		return transactionIds;
	}

	/* (non-Javadoc)
	 * Returns the sum of transactions having transactionID/ParentID equal to given Id.
	 * @see service.TransactionService#totalAmountByTransactionId(java.lang.Integer)
	 */
	@Override
	public SumModel totalAmountByTransactionId(Integer id) {
		// TODO Auto-generated method stub
		SumModel sum = new SumModel();
		sum.setSum(transactionMap.get(id).getAmount());
		for (Entry<Integer, TransactionModel> entry : transactionMap.entrySet()) {
			if(entry.getValue().getParent_id() == id){
				sum.addAmount(entry.getValue().getAmount());;
			}
			
		}
		return sum;
	}

}
