package showtime.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import showtime.model.*;
import showtime.repository.RiderRepository;
import showtime.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class ShowService {
    ArrayList<Programa> lista;

    @Autowired
    public ShowService(ShowRepository sr, RiderRepository rr) {
        this.sr = sr;
        this.rr = rr;
    }

    private final ShowRepository sr;
    private final RiderRepository rr;
    private final ModelMapper mm = new ModelMapper();

    public Programa getShow(Integer id) {
        return sr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "show no encontrado"));
    }

    public List<RiderDTO> getProgramaRiders(Integer id) {
        try {
            List<Integer> rIdList = sr.findProgramariders(id);
            List<RiderDTO> riders = new ArrayList<>();
            for (Integer rId : rIdList) {
                Rider rider = rr.findById(rId).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST));
                riders.add(mm.map(rider, RiderDTO.class));
            }
            return riders;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity delete(Integer id) {
        try {
            sr.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity add(Programa programa) {
        try {
            System.out.println(programa.getSerie());
            sr.save(programa);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }


    public List<Programa> getAll() {
        return sr.findAll();
    }


    public ResponseEntity update(Integer id, Programa programa) {
        Programa r = sr.findById(id).orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "show no encontrado"));
        r.setSerie(r.getSerie());
        r.setId(programa.getId());
        r.setYear(programa.getYear());
        sr.save(r);
        return ResponseEntity.status(OK).build();
    }

}

