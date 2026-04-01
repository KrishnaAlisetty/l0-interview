/**
 * @author Krishna Alisetty
 * @date 30-03-2026
 */

package com.portal.interview.controller;

import com.portal.interview.entity.Candidate;
import com.portal.interview.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> getCandidates() {
        List<Candidate> candidates = dashboardService.fetchAllCandidates();
        return new ResponseEntity<>(
                candidates,
                HttpStatus.OK
        );
    }
}
