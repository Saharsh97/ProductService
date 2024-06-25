package com.scaler.productservice.controllers;

import com.scaler.productservice.services.LogisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class OrderController {

    LogisticService logisticService;

    @Autowired
    public OrderController(@Qualifier("TruckLogisticService") LogisticService logisticService){
        this.logisticService = logisticService;
    }
}
