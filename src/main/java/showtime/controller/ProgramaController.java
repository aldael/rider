package showtime.controller;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import showtime.model.Programa;
import showtime.model.RiderDTO;
import showtime.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/programa")
@CrossOrigin(origins = "*")
public class ProgramaController {
    @Autowired
    private ShowService ss;

    @PostMapping("")
    public ResponseEntity addPrograma(@RequestBody final @NotNull Programa p) {
        return ss.add(p);
    }

    @GetMapping("/getAll")
    public List<Programa> getAll() {
        return ss.getAll();
    }

    @PostMapping("/{id}/update")
    public ResponseEntity updatePrograma(@PathVariable final @NotNull Integer id, @RequestBody final @NotNull Programa p) {
        return ss.update(id, p);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity deletePrograma(@PathVariable final @NotNull Integer id) {
        return ss.delete(id);
    }

    @GetMapping("/{id}")
    public Programa getPrograma(@PathVariable final @NotNull Integer id) {
        return ss.getShow(id);
    }

    @GetMapping("/{id}/riders")
    public List<RiderDTO> getProgramaRiders(@PathVariable Integer id) {
        return ss.getProgramaRiders(id);
    }

}
