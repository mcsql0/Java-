package net.mcsql.controller;

import net.mcsql.entitles.CommonResult;
import net.mcsql.entitles.Payment;
import net.mcsql.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OpenFeignController {
    // OpenFeign
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        System.out.println("++++++");
        return paymentService.paymentSQL(id);
    }
}
