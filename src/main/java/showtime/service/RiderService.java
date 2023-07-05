package showtime.service;


import showtime.model.Rider;
import showtime.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class RiderService {
    ArrayList<Rider> lista;

    @Autowired
    public RiderService(RiderRepository rr) {
        this.rr = rr;
    }

    private final RiderRepository rr;

    public Rider getRider(Integer id) {
        return rr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "rider no encontrado"));
    }

    public ResponseEntity delete(Integer id) {
        try {
            rr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity add(Rider raida) {
        try {
            rr.save(raida);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }


    public List<Rider> getAll() {
        return rr.findAll();
    }


    public ResponseEntity update(Integer id, Rider raidaa) {
        Rider r = rr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "rider no encontrado"));
        r.setRider(raidaa.getRider());
        r.setId(raidaa.getId());
        r.setIdentidad(raidaa.getIdentidad());
        r.setProgramas(raidaa.getProgramas());
        r.setArmor(raidaa.getArmor());
        r.setSerie(raidaa.getSerie());
        r.setFav(raidaa.isFav());
        rr.save(r);
        return ResponseEntity.status(OK).build();
    }

}
