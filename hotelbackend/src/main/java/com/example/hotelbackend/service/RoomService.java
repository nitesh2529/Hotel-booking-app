package com.example.hotelbackend.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.hotelbackend.exception.ResourceNotFoundException;
import com.example.hotelbackend.model.Room;
import com.example.hotelbackend.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {

    private final RoomRepository roomRepository;
    private final Cloudinary cloudinary;

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice, BigDecimal roomNo) throws IOException {

        Room room = new Room();
        room.setRoomType(roomType.toUpperCase());
        room.setRoomPrice(roomPrice);
        room.setRoomNo(roomNo);

        if (file != null && !file.isEmpty()) {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap("folder", "hotel-rooms")
            );
            room.setPhotoUrl((String) uploadResult.get("secure_url"));
            room.setPhotoPublicId((String) uploadResult.get("public_id"));
        }

        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // No longer needed for byte-streaming — frontend uses room.getPhotoUrl() directly.
    // Kept only if you still want a redirect-style endpoint; otherwise delete this method
    // and the controller endpoint that calls it.
    @Override
    public String getRoomPhotoUrl(String roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return room.getPhotoUrl();
    }

    @Override
    public void deleteRoom(String roomId) throws IOException {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (room.getPhotoPublicId() != null) {
            cloudinary.uploader().destroy(room.getPhotoPublicId(), ObjectUtils.emptyMap());
        }

        roomRepository.deleteById(roomId);
    }

    @Override
    public Room updateRoom(String roomId, String roomType, BigDecimal roomPrice, BigDecimal roomNo, MultipartFile photo) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        if (roomType != null) room.setRoomType(roomType);
        if (roomPrice != null) room.setRoomPrice(roomPrice);
        if (roomNo != null) room.setRoomNo(roomNo);

        if (photo != null && !photo.isEmpty()) {
            try {
                if (room.getPhotoPublicId() != null) {
                    cloudinary.uploader().destroy(room.getPhotoPublicId(), ObjectUtils.emptyMap());
                }

                Map uploadResult = cloudinary.uploader().upload(
                        photo.getBytes(),
                        ObjectUtils.asMap("folder", "hotel-rooms")
                );

                room.setPhotoUrl((String) uploadResult.get("secure_url"));
                room.setPhotoPublicId((String) uploadResult.get("public_id"));

            } catch (IOException e) {
                throw new RuntimeException("Error storing room photo: " + e.getMessage());
            }
        }

        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findAll()
                .stream()
                .map(Room::getRoomType)
                .distinct()
                .toList();
    }
}
