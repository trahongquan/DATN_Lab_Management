package LabManagement.service.BookingService;

import LabManagement.entity.Booking;
import LabManagement.dao.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Booking findByBookingId(int bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public List<Booking> findAllByLab_id(int labid){
        List<Booking> bookings = bookingRepository.findAllByLabid(labid);
        if (bookings.isEmpty()){
            return new ArrayList<>();
        } else {return bookings;}
    }

    @Override
    public Booking findByContent_id(int id){
        return bookingRepository.findByContentid(id);
    }
}

