package LabManagement.service.RoomService;

import LabManagement.entity.Room;
import LabManagement.dao.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findByRoomId(int roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(int roomId) {
        roomRepository.deleteById(roomId);
    }
}

