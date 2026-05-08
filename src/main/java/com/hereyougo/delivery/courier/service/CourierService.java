package com.hereyougo.delivery.courier.service;

import com.hereyougo.delivery.courier.entity.Courier;
import com.hereyougo.delivery.courier.repository.CourierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CourierService {

    private final CourierRepository courierRepository;

    public CourierService (CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    public List<Courier> getAllCouriers () {
        return courierRepository.findAll();
    }

    public Courier getCourierById (UUID id){
        return courierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }

    public Courier addCourier(Courier courier){
        return courierRepository.save(courier);
    }

    public void removeCourier(UUID id){
        if(!courierRepository.existsById(id)){
            throw new RuntimeException();
        }
        courierRepository.deleteById(id);
    }

}
