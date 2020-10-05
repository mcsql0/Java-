package net.mcsql.service;

import net.mcsql.entitles.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment var1);

    Payment getPaymentById(@Param("id") Long var1);
}
