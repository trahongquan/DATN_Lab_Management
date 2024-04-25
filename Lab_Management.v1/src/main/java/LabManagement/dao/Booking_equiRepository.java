package LabManagement.dao;

import LabManagement.entity.Booking_equi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Booking_equiRepository  extends JpaRepository<Booking_equi, Integer> {
    List<Booking_equi> findAllByBookingId(int id);
    Booking_equi findByBookingIdAndAndEquipmentId(int id1, int id2);
}
