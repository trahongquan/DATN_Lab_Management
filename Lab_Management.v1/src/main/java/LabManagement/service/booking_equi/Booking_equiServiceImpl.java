package LabManagement.service.booking_equi;


import LabManagement.dao.Booking_equiRepository;
import LabManagement.entity.Booking_equi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Booking_equiServiceImpl implements Booking_equiService {

    private Booking_equiRepository booking_equiRepository;

    @Autowired
    public Booking_equiServiceImpl(Booking_equiRepository booking_equiRepository) {
        this.booking_equiRepository = booking_equiRepository;
    }

    @Override
    public Booking_equi saveBookingEquipment(Booking_equi bookingEquipment) {
        return booking_equiRepository.save(bookingEquipment);
    }

    @Override
    public void deleteBookingEquipment(int id) {
        booking_equiRepository.deleteById(id);
    }

    @Override
    public List<Booking_equi> getAllBookingEquipments() {
        return booking_equiRepository.findAll();
    }

    @Override
    public Booking_equi getBookingEquipmentById(int id) {
        return booking_equiRepository.findById(id).orElse(null);
    }
}
