package com.alchemist.bianca.controller.task;

import com.alchemist.bianca.dto.storage.request.StorageRequest;
import com.alchemist.bianca.dto.storage.response.StorageList;
import com.alchemist.bianca.service.task.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping("/task/storage")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStorage(@RequestBody @Valid StorageRequest request) {
        storageService.addStorage(request);
    }

    @PutMapping("/task/storage/{storageId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyStorage(@PathVariable("storageId") Long storage_id, @RequestBody StorageRequest task) {
        storageService.modifyStorage(storage_id, task);
    }

    @PostMapping("/task/storage/{storageId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void moveTaskToStorage(@PathVariable("storageId") Long storage_id, @RequestParam("date") String date) {
        storageService.moveStorageToTask(storage_id, date);
    }

    @DeleteMapping("/task/storage/{storageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStorage(@PathVariable("storageId") Long storage_id) {
        storageService.deleteStorage(storage_id);
    }

    @GetMapping("/task/storage")
    public ResponseEntity<List<StorageList>> getStorageList() {
        return storageService.getStorageList();
    }
}
