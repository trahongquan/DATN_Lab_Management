package LabManagement.service.booking_equi;

import LabManagement.entity.Booking_equi;

import java.util.List;

public interface Booking_equiService {
    Booking_equi saveBookingEquipment(Booking_equi bookingEquipment);
    void deleteBookingEquipment(int id);
    List<Booking_equi> getAllBookingEquipments();
    Booking_equi getBookingEquipmentById(int id);
    List<Booking_equi> findAllByBookingId(int id);
    Booking_equi findByBookingIdAndAndEquipmentId(int id1, int id2);
}
