/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.service;

import com.portal.interview.entity.Candidate;
import com.portal.interview.repository.DashboardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private DashboardRepository dashboardRepository;

    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public List<Candidate> fetchAllCandidates() {
        return dashboardRepository.findAll();
    }
}
