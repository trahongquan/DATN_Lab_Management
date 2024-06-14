package LabManagement.ClassSuport;

import LabManagement.dto.BookingDTO;

import java.util.Comparator;

/** Hỗ trợ sắp xếp BookingDTO theo ngày */

public class BookingDateComparator implements Comparator<BookingDTO> {
    @Override
    public int compare(BookingDTO booking1, BookingDTO booking2) {
        /** Sắp xếp lại bookingDTOs theo thứ tự ngày gần nhất đến xa nhất */
        return booking2.getBooking_Date().compareTo(booking1.getBooking_Date());
    }
}
