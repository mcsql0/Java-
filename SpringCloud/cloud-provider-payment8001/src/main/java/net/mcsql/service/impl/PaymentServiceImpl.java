package net.mcsql.service.impl;

import net.mcsql.dao.PaymentDao;
import net.mcsql.entitles.Payment;
import net.mcsql.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentDao paymentDao;

    public PaymentServiceImpl() {
    }

    public int create(Payment payment) {
        return this.paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return this.paymentDao.getPaymentById(id);
    }
}