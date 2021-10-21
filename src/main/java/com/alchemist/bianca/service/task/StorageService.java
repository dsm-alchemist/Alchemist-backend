package com.alchemist.bianca.service.task;

import com.alchemist.bianca.dto.storage.request.AddStorageRequest;
import com.alchemist.bianca.dto.storage.response.StorageList;
import com.alchemist.bianca.entity.storage.Storage;
import com.alchemist.bianca.entity.storage.StorageRepository;
import com.alchemist.bianca.entity.task.Task;
import com.alchemist.bianca.entity.task.TaskRepository;
import com.alchemist.bianca.entity.user.User;
import com.alchemist.bianca.entity.user.UserRepository;
import com.alchemist.bianca.exception.StorageNotFoundException;
import com.alchemist.bianca.exception.UserNotFoundException;
import com.alchemist.bianca.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final UserFacade userFacade;

    @Transactional
    public void addStorage(AddStorageRequest request) {
        User user = userRepository.findByName(userFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        storageRepository.save(Storage.builder()
                .task(request.getTask())
                .email(user)
                .build()
        );
    }

    public ResponseEntity<List<StorageList>> getStorageList() {
        return new ResponseEntity<>(
                storageRepository.getStorageList(userFacade.getEmail()),
                HttpStatus.OK);
    }

    public void modifyStorage(Long storage_id, String task) {
        storageRepository.modifyStorage(storage_id, task);
    }

    public void deleteStorage(Long storage_id) {
        storageRepository.deleteStorage(storage_id);
    }

    @Transactional
    public void moveStorageToTask(Long storage_id, LocalDate date) {
        Storage storage = storageRepository.findById(storage_id)
                .orElseThrow(StorageNotFoundException::new);

        User user = userRepository.findById(userFacade.getEmail())
                .orElseThrow(UserNotFoundException::new);

        taskRepository.save(
                Task.builder()
                        .task(storage.getTask())
                        .date(date)
                        .email(user)
                        .build()
        );

        storageRepository.delete(storage);
    }

}
