package by.tms.service;

import by.tms.storage.InMemoryOperationStorage;
import by.tms.storage.OperationStorage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {
    private static OperationService instance;
    private final OperationStorage operationStorage = new InMemoryOperationStorage();
    private OperationService() {
    }

    public static OperationService getInstance() {
        if (instance == null) {
            instance = new OperationService();
        }
        return instance;
    }

    public double calculate(CalculatorOperation operation) {
        operation.process();
        operationStorage.save(operation);
        return operation.getFinalResult();
    }

    public List<CalculatorOperation> findAllByUsername(String username) {
        return operationStorage.findAllByUsername(username);
    }
}
