package com.richflow.api.service.history;

import com.richflow.api.domain.enumType.MoneyType;
import com.richflow.api.domain.history.History;
import com.richflow.api.repository.history.HistoryRepository;
import com.richflow.api.request.history.CreateHistoryDTO;
import com.richflow.api.request.history.UpdateHistoryDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public History createHistory(CreateHistoryDTO createHistoryDTO) {
        History createHistory =  History.builder()
                .historyActEither(createHistoryDTO.getHistoryActEither())
//                .historyAcMoneyType(createHistoryDTO.getHistoryAcMoneyType())
                .historyAcMoneyType(MoneyType.CASH)
                .historyName(createHistoryDTO.getHistoryName())
                .historyAmounts(createHistoryDTO.getHistoryAmounts())
                .historyMemo(createHistoryDTO.getHistoryMemo())
                .build();
        return historyRepository.save(createHistory);
    }


    public History updateHistory(UpdateHistoryDTO updateHistoryDTO) {
        History existingHistory = historyRepository.findById(updateHistoryDTO.getHistoryIdx())
                .orElseThrow(() -> new EntityNotFoundException("해당 내역이 존재하지 않습니다."));

        existingHistory.updateDetails(updateHistoryDTO);
        return historyRepository.save(existingHistory);
    }
}
