package showtime.controller;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import showtime.model.Rider;
import showtime.service.RiderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rider")
@CrossOrigin(origins = "*")
public class RiderController {

    @Autowired
    private RiderService rs;

    @PostMapping("")
    public ResponseEntity addRider(@RequestBody final @NotNull Rider r) {
        return rs.add(r);
    }

    @GetMapping("/getAll")
    public List<Rider> getAll() {
        return rs.getAll();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updateRider(@PathVariable final @NotNull Integer id, @RequestBody final @NotNull Rider r) {
        return rs.update(id, r);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deleteRider(@PathVariable final @NotNull Integer id) {
        return rs.delete(id);
    }

    @GetMapping("/{id}")
    public Rider getRider(@PathVariable final @NotNull Integer id) {
        return rs.getRider(id);
    }



}
