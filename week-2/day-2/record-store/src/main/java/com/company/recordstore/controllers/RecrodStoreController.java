package com.company.recordstore.controllers;

import com.company.recordstore.models.Record;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Record> getRecords() {
        return recordList;
    }

    @RequestMapping(value = "/record", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Record addARecord(@RequestBody Record record) {
        record.setId(currentId);
        currentId = currentId + 1;
        recordList.add(record);

        return record;
    }

    @RequestMapping(value = "/record/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Record lookupRecordById(@PathVariable int id) {
        for (int i = 0; i < recordList.size(); i++) {
            if (recordList.get(i).getId() == id) {
                return recordList.get(i);
            }
        }
        return null;
    }


    @RequestMapping(value="/record/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRecord(@PathVariable int id, @RequestBody Record record) {
        record.setId(id);
        for (int i = 0; i < recordList.size(); i++) {
            if (recordList.get(i).getId() == id) {
                recordList.set(i, record);
                break;  // ends the closest for loop. effectively ending the method
            }
        }
    }

    @RequestMapping(value="/record/{id}", method=RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecord(@PathVariable int id) {
        for (int i = 0; i < recordList.size(); i++) {
            if (recordList.get(i).getId() == id) {
                recordList.remove(i);
                return; // ends the method
            }
        }
    }

}