package LabManagement.dao;

import LabManagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByLabid(int labid);
    Booking findByContentid(int id);
    List<Booking> findAllByBookingDate(Date date);

}

