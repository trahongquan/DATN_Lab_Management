package LabManagement.service.BookingService;

import LabManagement.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking findByBookingId(int bookingId);

    List<Booking> getAllBookings();

    Booking createBooking(Booking booking);

    void updateBooking(Booking booking);

    void deleteBooking(int bookingId);

    List<Booking> findAllByLab_id(int labid);

    Booking findByContent_id(int id);
}
