package io.github.zbrant.hostelapi.services;

import io.github.zbrant.hostelapi.models.RoomModel;
import io.github.zbrant.hostelapi.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class RoomService {

  private final RoomRepository roomRepository;
  private final String uploadDir;

  public RoomService(RoomRepository roomRepository, @Value("${file.upload-dir}") String fileUploadDir) {
    this.roomRepository = roomRepository;
    this.uploadDir = fileUploadDir;
  }

  public RoomModel save(RoomModel room) {
    if (roomRepository.existsByAddress(room.getAddress())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um quarto registrado neste endereço.");
    }

    return roomRepository.save(room);
  }

  public RoomModel update(RoomModel room) {
    return roomRepository.save(room);
  }

  public Page<RoomModel> findAllByCountryAndCity(Pageable pageable, String country, String city) {
    return roomRepository.findAllByCountryAndCity(country, city, pageable);
  }

  public RoomModel findById(Long id) {
    return roomRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quarto não encontrado"));
  }

  public void delete(RoomModel room) {
    roomRepository.delete(room);
  }

  public String savePhoto(MultipartFile file) throws IOException {
    if (file.isEmpty()) throw new IOException ("File is empty.");

    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
    Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
    String filePath = uploadPath + "/" + fileName;

    File dir = new File(uploadDir);

    if (Boolean.FALSE.equals(dir.exists())) dir.mkdirs();

    File photoFile = new File(filePath);
    file.transferTo(photoFile);

    return filePath;
  }

  public Resource getPhoto(String photoPath) {

    Path path = Paths.get(uploadDir).resolve(photoPath);

    if (Files.notExists(path)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    try {
      return new FileSystemResource(path.toFile());
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
