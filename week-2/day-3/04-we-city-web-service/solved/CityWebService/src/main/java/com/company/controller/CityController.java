package com.company.controller;

import com.company.exceptions.NotFoundException;
import com.company.model.City;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CityController {

    private static List<City> cityList = new ArrayList<>(Arrays.asList(
            new City("Chicago", "Illinois", 2679080, false),
            new City("Dallas", "Texas", 1347120, false),
            new City("Denver", "Colorado", 749103, true),
            new City("Los Angeles", "California", 3983540, false),
            new City("New York City", "New York", 8230290, false),
            new City("Sacramento", "California", 525398, true),
            new City("Springfield", "Illinois", 113010, true)
    ));

    @RequestMapping(value = "/city", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public City addCity(@RequestBody @Valid City city) {
        cityList.add(city);

        return city;
    }

    @RequestMapping(value = "/city/{name}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCity(@PathVariable String name, @RequestBody City city) {
        for (int i = 0; i < cityList.size(); i++) {
            if (cityList.get(i).getName().equalsIgnoreCase(name)) {
                cityList.set(i, city);
                return;
            }
        }
        throw new NotFoundException("Attempting to update city that does not exist: " + name);
    }

    @RequestMapping(value = "/city/{name}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable String name) {

        int index = 0;
        boolean found = false;

        for(City city : cityList) {
            if (city.getName().equalsIgnoreCase(name)) {
                found = true;
                break;
            }

            index++;
        }

        if ( found )
            cityList.remove(index);
        else throw new NotFoundException("City not found.");
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<City> getCities() {

        return cityList;
    }

    @RequestMapping(value = "/city/{name}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public City getCity(@PathVariable String name) {

        for(City city : cityList) {
            if (city.getName().equalsIgnoreCase(name))
                return city;
        }

        throw new NotFoundException("City not found.");
    }

}
