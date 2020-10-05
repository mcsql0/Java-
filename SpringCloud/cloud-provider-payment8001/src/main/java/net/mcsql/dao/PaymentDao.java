package net.mcsql.dao;

import net.mcsql.entitles.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    int create(Payment var1);

    Payment getPaymentById(@Param("id") Long var1);
}
