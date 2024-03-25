package com.richflow.api.controller.history;

import com.richflow.api.domain.history.History;
import com.richflow.api.request.history.CreateHistoryDTO;
import com.richflow.api.request.history.UpdateHistoryDTO;
import com.richflow.api.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;


    @PostMapping
    public ResponseEntity<History> createHistory(@RequestBody CreateHistoryDTO createDTO) {
        History createdHistory = historyService.createHistory(createDTO);
        return new ResponseEntity<>(createdHistory, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<History> updateHistory(@RequestBody UpdateHistoryDTO updateDTO) {
        History updateHistory = historyService.updateHistory(updateDTO);
        return new ResponseEntity<>(updateHistory, HttpStatus.ACCEPTED);
    }
}
