package com.company.reccoll.controller;

import com.company.reccoll.service.ServiceLayer;
import com.company.reccoll.viewmodel.AlbumViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
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

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAnAlbum(@RequestBody AlbumViewModel albumViewModel) {
        serviceLayer.updateAlbum(albumViewModel);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable int id) {
        serviceLayer.deleteAlbum(id);
    }
}
