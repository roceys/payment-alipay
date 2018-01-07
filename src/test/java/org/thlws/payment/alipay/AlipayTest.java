package org.thlws.payment.alipay;

import com.alipay.trade.model.GoodsDetail;
import org.junit.Before;
import org.junit.Test;
import org.thlws.payment.alipay.core.AlipayCore;
import org.thlws.payment.alipay.model.*;
import org.thlws.payment.alipay.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * 支付宝接口测试
 * Created by HanleyTang on 2017/3/6.
 */
public class AlipayTest {

    /**
     * Alipay 支付相关接口.
     */
    static AlipayCore alipayCore;

    /**
     * 查看地址[应用列表] https://openhome.alipay.com/platform/appManage.htm
     */
    public static final String appid = "2016042501333089";

    /**
     * 支付宝账户
     */
    public static final String seller_email="zhifubao2016@enjar.cn";

    /**
     * 合作者ID https://b.alipay.com/order/signManage.htm?channel=ent
     */
    public static final String partner_id="2088221719313094";

    /**
     * 设置时事先记录,秘钥生成方式 https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=105971&docType=1
     */
    public static final String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOGAI1Nf+T3F/BGb CxLQg04h1tqxtIz31T0GuiPHq25xvRacoyI4HMeJrFt72iSxnNiFjGqCLySTrKs2 zrGjbxA2dGSKkNVDWFjAx4yaDjG/nOK0sXyKwjbaf4gGjW4fq9BzP6KKG+LoZj/e wa5syDvK1UTstAc3fqCKXqOQN24vAgMBAAECgYBr5aIBfMSHWDo6OlNcF0Ucl3h0 f98CLHttjdB22k0etXFiZmRwNSUgHLHJvEMul4WS3KPJOq1P9REDg1/3A1paI345 r6qZdn8dovEX9DG6cVnjLxx7zoobCz/eGt/nWhgIQqPXSdTYhcnlncJPogSQhnUF 0lOUUIsC/RddZYXyMQJBAPlSqy5QXB/LudH8orRSjzRWHlhVswknzIMOJX9o0Tje nlwRM3vqnstvCZKaHc0lbmitHKqDfXNCA4u49zlKB5UCQQDniiVFKY5v+VQm6724 hkVvd8ctYwdx2Qj4HXdnoXfOKxOJQuSRUW2xvTSBFtFcFlC/KZNS4Zr9u01+s5Bh J12zAkAgTWimUH0PFYsWvL+r00KAimPWGAxhNEnX9P5AuqjY0Gb+ELB17pjyTImV 9+fCi4X5g+xB31VimHvzH1zQjFndAkAz0zMGaQfNvmP8ljWP8NXn3kqBuTG4ZNji GE5arsSPLAsiZQueDzjbWLtAzaes0f3e2+Jy4bZ8Zokw++YsKNCLAkBKB1vZplV2 NG9ryG2C7KGG2AgcfkUthcFPT9tXoIYPxPXwYbChjMddMDK3QK99e9CoSKLq+RY/ B0fCEdSbADgS";

    /**
     * 应用,查看支付宝公钥
     */
    public static final String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    /**
     * 进入应用,查看应用公钥
     */
    public static final String public_key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhgCNTX/k9xfwRmwsS0INOIdbasbSM99U9Brojx6tucb0WnKMiOBzHiaxbe9oksZzYhYxqgi8kk6yrNs6xo28QNnRkipDVQ1hYwMeMmg4xv5zitLF8isI22n+IBo1uH6vQcz+iihvi6GY/3sGubMg7ytVE7LQHN36gil6jkDduLwIDAQAB";


    /**
     * MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
     */
    public static String key = "rtsqsqp9wk59t9b82y5af7g7yzp75l60";



    /**
     * 初始化函数.
     */
    @Before
    public  void init(){
        System.out.printf("init.....");
        AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
        alipayCore = clientBuilder.setAlipay_public_key(alipay_public_key).setApp_id(appid).setPrivate_key(private_key).build();
    }


    /***
     * 统一收单线下交易预创建（扫码支付）
     * 暂未开启异步通知,完成预订单创建后,请自行实现Thread调用query()查询支付结果
     */
    @Test
    public void test_precreate(){

        try {
            AlipayQrcodeInput input = new AlipayQrcodeInput();
            input.setSubject("购买商品");
            input.setOutTradeNo(System.currentTimeMillis()+"");
            input.setBody("测试下单");
            input.setOperatorId("990001");
            input.setStoreId("0001025104489");
            input.setTotalAmount("0.01");
            input.setSellerId(partner_id);
            AlipayQrcodeOutput output = alipayCore.precreate(input);
            System.out.println("output="+JsonUtil.format(output));
            assertTrue(output.isSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 统一收单交易支付接口（条码支付）
     */
    @Test
    public void test_pay(){

        try {
            AlipayTradeInput input = new AlipayTradeInput();
            input.setSellerId(partner_id);
            input.setTotalAmount("0.01");
            input.setStoreId("0000102678762");
            input.setOperatorId("operator01");
            input.setBody("test pay");
            input.setDiscountableAmount("0");
            input.setUndiscountableAmount("0");
            input.setOutTradeNo(System.currentTimeMillis()+"");
            input.setSubject("subject01");
            List<GoodsDetail> list = new ArrayList<GoodsDetail>();
            list.add(GoodsDetail.newInstance("g01","name1",10,1));
            list.add(GoodsDetail.newInstance("g02","name2",12,3));
            input.setGoodsDetailList(list);
            input.setAuthCode("289296533713001450");
            AlipayTradeOutput output = alipayCore.pay(input);
            System.out.println("output="+JsonUtil.format(output));
            assertTrue(output.isSuccess());
            //output就是支付结果,具体请参考相关属性说明
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 统一收单交易退款接口
     */
    @Test
    public void  test_refund(){

        try {
            AlipayRefundInput input = new AlipayRefundInput();
            input.setOutTradeNo("1508487673867");
            input.setTradeNo("2017102021001004515315574686");
            input.setRefundAmount("0.01");
            input.setRefundReason("测试退款");
            input.setStoreId("0000102678762");
            input.setTerminalId("10007");
            AlipayRefundOutput output = alipayCore.refund(input);
            System.out.println("output="+JsonUtil.format(output));
            assertTrue(output.isSuccess());
            //output 就是退款结果
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 统一收单线下交易查询
     */
    @Test
    public void test_query(){
        try {
            AlipayQueryOutput output = alipayCore.query("5113811987100800791");
            System.out.println("output="+JsonUtil.format(output));
            assertTrue(output.isSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * H5网页支付接口
     */
    @Test
    public void test_pay_in_h5(){

        try {
            AlipayH5Input input = new AlipayH5Input();
            AlipayH5Input.BizContent bizContent = new AlipayH5Input.BizContent();
            bizContent.setTotal_amount("0.01");
            bizContent.setSubject("测试H5支付");
            bizContent.setSeller_id(partner_id);
            bizContent.setProduct_code("p0001");
            bizContent.setOut_trade_no(System.currentTimeMillis()+"");
            String html = alipayCore.pay_in_h5(input);
            assertNotNull(html);
            //html结果直接显示在页面即可
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * 统一收单交易撤销接口
     */
    @Test
    public void test_cancel(){
        try {
            AlipayCancelOutput output = alipayCore.cancel("1488867095321");
            System.out.println("output="+JsonUtil.format(output));
            assertTrue(output.isSuccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}