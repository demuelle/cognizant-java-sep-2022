package com.company.reccoll.controller;

import com.company.reccoll.service.ServiceLayer;
import com.company.reccoll.viewmodel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/completeAlbum")
public class AlbumViewModelController {
    @Autowired
    private ServiceLayer serviceLayer;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumViewModel addANewAlbum(@RequestBody AlbumViewModel albumViewModel) {
        return serviceLayer.saveAlbum(albumViewModel);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlbumViewModel lookUpAlbumById(@PathVariable int id) {
        return serviceLayer.findAlbum(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumViewModel> getAllAlbums() {
        return serviceLayer.getAllAlbums();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public void updateAnAlbum(@RequestBody AlbumViewModel albumViewModel, @PathVariable int id) {
        if (albumViewModel.getId() != id) {
            // freak the heck out!!!!! with an exception!!!!
            System.out.println("Caller has provided different ids in path and body. So, look out!");
        }
        albumViewModel.setId(id);
        serviceLayer.updateAlbum(albumViewModel);
    }

}
