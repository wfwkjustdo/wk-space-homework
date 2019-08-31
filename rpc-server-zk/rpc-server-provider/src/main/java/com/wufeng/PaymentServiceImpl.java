package com.wufeng;

/**
 * @Author wangkai
 * @Create 2019/6/17-16:39.
 * @Description
 */
@RpcService(value = IPaymentService.class,version = "v2.0")
public class PaymentServiceImpl implements IPaymentService{
    @Override
    public void doPay() {
        System.out.println("执行doPay方法");
    }
}
