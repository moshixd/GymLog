package net.moshi.gymlog.service;

import net.moshi.gymlog.repository.RecordsRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordsService {

    private final RecordsRepository recordsRepository;

    public RecordsService(RecordsRepository recordsRepository) {
        this.recordsRepository = recordsRepository;
    }
}
