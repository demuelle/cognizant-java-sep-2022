package com.company.reccoll.controller;

import com.company.reccoll.model.Label;
import com.company.reccoll.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LabelController {
    @Autowired
    LabelRepository repo;

    @GetMapping("/labels")
    public List<Label> getLabels() {
        return repo.findAll();
    }

    @GetMapping("/labels/{id}")
    public Label getLabelById(@PathVariable int id) {
        Optional<Label> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/labels")
    @ResponseStatus(HttpStatus.CREATED)
    public Label addLabel(@RequestBody Label label) {
        return repo.save(label);
    }

    @PutMapping("/labels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody Label label) {
        repo.save(label);
    }

    @DeleteMapping("/labels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable int id) {
        repo.deleteById(id);
    }
}
