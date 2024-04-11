package LabManagement.service.RoomService;

import LabManagement.entity.Room;

import java.util.List;

public interface RoomService {

    Room findByRoomId(int roomId);

    List<Room> getAllRooms();

    Room createRoom(Room room);

    void updateRoom(Room room);

    void deleteRoom(int roomId);
}

