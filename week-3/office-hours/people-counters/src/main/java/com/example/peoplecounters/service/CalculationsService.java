package com.example.peoplecounters.service;

import com.example.peoplecounters.PeopleCountRepository;
import com.example.peoplecounters.model.DayCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationsService {
    @Autowired
    private PeopleCountRepository peopleCountRepository;

    public DayCount getTotalDayCountBySiteId(int siteId, String monthName, int date) {
        // go to repository and look up all the counts for the date
        // remove any counts that are outside of operating hours
        // add up the remaining counts so that we have a total ins and outs for the day
        return null;
    }
}
