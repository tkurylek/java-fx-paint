package com.futureprocessing.fxpaint.services;

import com.futureprocessing.fxpaint.model.operations.Operation;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

@Singleton
public class OperationService {

    private static final Logger LOGGER = Logger.getLogger(OperationService.class);
    private static final Operation EMPTY_OPERATION = new Operation() {
        @Override
        public String id() {
            return null;
        }

        @Override
        public void operate(Window ownerWindow) {
            LOGGER.debug("Called non existing operation");
        }
    };
    private Map<String, Operation> operations;

    @Inject
    public OperationService(Set<Operation> set) {
        this.operations = set.stream().collect(toMap(Operation::id, operation -> operation));
    }

    public Operation getOperationById(String id) {
        return operations.getOrDefault(id, EMPTY_OPERATION);
    }
}
