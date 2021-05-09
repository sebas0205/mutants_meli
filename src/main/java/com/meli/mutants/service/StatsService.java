package com.meli.mutants.service;

import com.meli.mutants.controller.dto.response.StatsResponse;
import org.springframework.stereotype.Service;

@Service
public interface StatsService {

    StatsResponse getStats();

}
