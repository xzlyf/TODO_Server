package com.xz.app.todolist;

import com.xz.app.todolist.constant.Local;
import com.xz.app.todolist.pojo.Event;
import com.xz.app.todolist.pojo.vo.CreateEvent;
import com.xz.app.todolist.utils.MD5Util;
import com.xz.app.todolist.utils.MyBeanUtils;
import com.xz.app.todolist.utils.RSAUtil;
import org.springframework.beans.BeanUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
public class Test {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //String pwd = "1234561607441730757";
        //String rsaSrt = RSAUtil.publicEncrypt(pwd, RSAUtil.getPublicKey(Local.publicKey));
        //123456
        //XK3ctUqciE89Mah-RHIDaW6EOagzbN33UoDviT_xDk2vxV1eldTLHeg7Ec22Re-UYDD9C3O5Z7Xchua_T1nXhEeZWLSrR394Qo0e-Yq5Rs8cCw9jSiUpmCxQJ6qfy0-0SDhrazKqhz8E_SikaMemXbFCDg9EMxbNxphQFQXEnUE
        //123123
        //qzkH0uldHak6ycUrDONFCeanJgYuhJcR2b1YaO3jAsjZJneqOPGypJAhCN4vJtxXnQeIla-c_ClsaEWUgO0cw0jy1YCbTmz1mWGRp-GZ6a5yqjfSBtPMNygi4poIw1_ygk8jnzBqkVdWXUOcpaKLo8Ws9erJzKSacbqegh1zBI0
        //System.out.println("公钥加密==========" + rsaSrt);


        //CreateEvent createEvent = new CreateEvent();
        //createEvent.setContent("我是内容");
        //createEvent.setShortTitle("测试1");
        //createEvent.setDone(false);
        //
        //Event eventList = new Event();
        //eventList.setId("1234567");
        //
        //BeanUtils.copyProperties(createEvent, eventList, MyBeanUtils.getNullPropertyNames(createEvent));
        //
        //System.out.println(createEvent.toString());
        //System.out.println(eventList.toString());

        //String origin = MD5Util.getMD5(Local.app_id + Local.client_secret + "1607441730757" + Local.version);
        //System.out.println(origin);


        //long timestamp = System.currentTimeMillis();
        //String pwd = "123abc"+timestamp;
        //System.out.println("==="+pwd);
        //pwd = pwd.replaceAll(String.valueOf(timestamp),"");
        //System.out.println("==="+pwd);

        //float t = 17.2f;
        //
        //System.out.println("t=" + t);
        //System.out.println("t2="+t%100/10);
        //System.out.println("t3="+t%10);
        //System.out.println("t4="+t%1*10);
        //
        //System.out.println("ss="+(t*10)%10);

        //String rsaSt = "N7s3d8QJiFHACs0QNdPhuXMZ4CY_TBwD-PAa2utqZn1inemHTI_RXas7ZOU-RMNR4aHYxF6OcRfGoGOabQY4DPW8qme_o4qwP9d46tYoonedAfdujBK65kuUed9msVpC-NYKtLjPJkuBMpx3ntz5k_UiqPv40cqcuqXC13LWPT8";
        //String rsaSt2 = "HuYlefpBc2E-_t-hlxKfxNjQmLrmgoME_K6nXY07RJHcU_oDoMEH_lNWhiqMXm8UmK0Jh5V5GkNO1BoI1CUFGY_nCZF-53q7l1b4KZ47XzbxkZxHID3xNm4v3uLoykry2n6N7xnEg9i_nGrZt75RuLnOGSJtr4vqbxQ6tUEGbLw";
        //String rsaSt3 = "pTFVnr-8JNyN55z8TF7rDZifGzWknXdPLTKZoLmsM63nAywgpFoAMbhcPXFmAc9mos0ipLIUglL3lGk9eOBypPEouTSVSZ0xB7x-nWARPaVsLEROI846lNbMXEH9BUcalSEIs5DhddqLNFXjmP_AL_qYMP9enz90jG2DipWozB8";
        //String st = RSAUtil.privateDecrypt(rsaSt3,RSAUtil.getPrivateKey(Local.privateKey));
        ////String st = RSAUtil.publicEncrypt("123321",RSAUtil.getPublicKey(Local.publicKey));
        //System.out.println(st);

        //Map<String, Object> params = new HashMap<>();
        //params.put("A",1);
        //params.put("B","文本");
        //params.put("C",true);
        //params.put("d",true);
        //params.put("e",false);
        //params.put("f",12323);
        //
        //for (Map.Entry<String, Object> entry : params.entrySet()) {
        //    String mapKey = entry.getKey();
        //    String mapValue = entry.getValue().toString();
        //    System.out.println(mapKey+":"+mapValue);
        //}

        String st = "AABBCC12138";
        String sm = "12138";
        st.replaceAll(sm,"");
        System.out.println(st.replaceAll(sm,""));

    }
}
