package net.mcsql.service.Impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import net.mcsql.dao.OrderDao;
import net.mcsql.domain.Order;
import net.mcsql.service.AccountService;
import net.mcsql.service.OrderService;
import net.mcsql.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService
{
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order){
        System.out.println("----->开始新建订单");
        //新建订单
        orderDao.create(order);

        //扣减库存
        System.out.println("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        System.out.println("----->订单微服务开始调用库存，做扣减end");

        //扣减账户
        System.out.println("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("----->订单微服务开始调用账户，做扣减end");


        //修改订单状态，从零到1代表已经完成
        System.out.println("----->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        System.out.println("----->修改订单状态结束");

        System.out.println("----->下订单结束了");

    }
}
