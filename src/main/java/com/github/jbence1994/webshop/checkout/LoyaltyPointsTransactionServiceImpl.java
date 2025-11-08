package com.github.jbence1994.webshop.checkout;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoyaltyPointsTransactionServiceImpl implements LoyaltyPointsTransactionService {
    private final LoyaltyPointsTransactionRepository loyaltyPointsTransactionRepository;

    @Override
    public void createLoyaltyPointsTransaction(LoyaltyPointsTransaction transaction) {
        loyaltyPointsTransactionRepository.save(transaction);
    }
}
