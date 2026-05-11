package com.example.library.service;

import com.example.library.entity.KozhanovAbdualimLoan;
import com.example.library.repository.KozhanovAbdualimLoanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KozhanovAbdualimScheduledTasks {

    private final KozhanovAbdualimLoanRepository loanRepository;
    private final KozhanovAbdualimNotificationService notificationService;

    @Scheduled(fixedRate = 60000)
    public void checkOverdueLoans() {
        log.info("Running scheduled task: checking overdue loans");

        List<KozhanovAbdualimLoan> activeLoans = loanRepository.findByReturnDateIsNull();
        LocalDate today = LocalDate.now();

        for (KozhanovAbdualimLoan loan : activeLoans) {
            if (loan.getDueDate().isBefore(today)) {
                notificationService.notifyOverdueReminder(loan);
            }
        }

        log.info("Scheduled task completed");
    }
}