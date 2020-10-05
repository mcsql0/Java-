package net.mcsql.controller;

import java.util.Iterator;
import java.util.List;

import net.mcsql.entitles.CommonResult;
import net.mcsql.entitles.Payment;
import net.mcsql.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    DiscoveryClient discoveryClient;

    public PaymentController() {
    }

    @GetMapping({"/payment/discovery"})
    public Object discovery() {
        List<String> services = this.discoveryClient.getServices();
        Iterator var2 = services.iterator();

        while(var2.hasNext()) {
            String element = (String)var2.next();
            System.out.println(element);
        }

        List<ServiceInstance> instances = this.discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        Iterator var6 = instances.iterator();

        while(var6.hasNext()) {
            ServiceInstance instance = (ServiceInstance)var6.next();
            System.out.println(instance);
        }

        return this.discoveryClient;
    }

    @PostMapping({"/payment/create"})
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        int i = this.paymentService.create(payment);
        return i > 0 ? new CommonResult(200, "数据库插入成功! serverPort:" + this.serverPort, i) : new CommonResult(404, "数据库插入数据失败!", (Object)null);
    }

    @GetMapping({"/payment/{id}"})
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        Payment payment = this.paymentService.getPaymentById(id);
        return payment != null ? new CommonResult(200, "数据查询成功! serverPort:" + this.serverPort, payment) : new CommonResult(404, "数据查询失败, 数据不存在!", (Object)null);
    }
}