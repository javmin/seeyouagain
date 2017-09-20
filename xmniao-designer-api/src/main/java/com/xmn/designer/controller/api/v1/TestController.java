package com.xmn.designer.controller.api.v1;

import com.xmn.designer.base.AbstractController;
import com.xmn.designer.base.GlobalConfig;
import com.xmn.designer.base.ThriftBuilder;
import com.xmn.designer.base.thrift.common.ResponseData;
import com.xmn.designer.base.thrift.service.OrderPayService;
import com.xmn.designer.base.thrift.service.SellerAddressService;
import com.xmn.designer.controller.api.v1.vo.TestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * create 2016/11/14
 *
 * @author yangQiang
 */

@Controller
@RequestMapping(value = "api/v1/test")
public class TestController extends AbstractController {

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public void test(@Valid TestRequest request, BindingResult result) throws IOException {
        success();
    }
    
    @Autowired
    GlobalConfig globalConfig;
    
    @RequestMapping(value = "do", method = RequestMethod.GET)
    public void doTest(){
    	try {
			ResponseData responseData = null;
			SellerAddressService.Client client = ThriftBuilder.build(globalConfig.getThriftBusHost(),
					Integer.parseInt(globalConfig.getThriftBusPort()), "sellerAddressService", SellerAddressService.Client.class);
			ThriftBuilder.open();
			Map<String,String> map=new HashMap<>();
			map.put("sellerid", "118");
			map.put("id", "336");
			Map<String, String> deleteSellerAddress = client.deleteSellerAddress(map);
			System.out.println(deleteSellerAddress);
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ThriftBuilder.close();
			}
    }
    
    @RequestMapping(value = "myTest", method = RequestMethod.GET)
    public void myTest(){
    	try {
			OrderPayService.Client client = ThriftBuilder.build(globalConfig.getThriftOrderHost(),
					Integer.parseInt(globalConfig.getThriftOrderPort()), "OrderPayService", OrderPayService.Client.class);
			ThriftBuilder.open();
			Map<String,String> map=new HashMap<>();
			map.put("order_no", "20161117094433428000");
			map.put("status", "1");
			map.put("pay_type", "1000000");
			map.put("total_amount", "605");
			map.put("amount", "605");
			map.put("sellerid", "112");
			ResponseData updateOrderInfo = client.updateOrderInfo(map);
			System.out.println(updateOrderInfo.getMsg());
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ThriftBuilder.close();
			}
    }
}
