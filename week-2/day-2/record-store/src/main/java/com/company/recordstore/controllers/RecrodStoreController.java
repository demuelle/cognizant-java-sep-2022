package com.company.recordstore.controllers;

import com.company.recordstore.models.Record;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class RecrodStoreController {

    private int currentId = 1;

    private List<Record> recordList = new ArrayList<>(Arrays.asList(
            new Record("The Black Crowes", "Shake Your MoneyMaker", currentId++),
            new Record("Weezer", "Weezer (Blue)", currentId++),
            new Record("Taylor Swift", "Lover", currentId++),
            new Record("Jason Isbell", "Southeastern", currentId++),
            new Record("MC Hammer", "Please Hammer Don't Hurt Em", currentId++)
    ));

    @RequestMapping(value = "/records", method = RequestMethod.GET)
    public List<Record> getRecords() {
        return recordList;
    }
}
