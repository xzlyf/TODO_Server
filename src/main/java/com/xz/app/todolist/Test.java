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

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
public class Test {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        //String pwd = "1231231607265853310";
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



        //String origin = MD5Util.getMD5(Local.app_id + Local.client_secret + "1607265853310" + Local.version);
        //System.out.println(origin);


        long timestamp = System.currentTimeMillis();
        String pwd = "123abc"+timestamp;
        System.out.println("==="+pwd);
        pwd = pwd.replaceAll(String.valueOf(timestamp),"");
        System.out.println("==="+pwd);

    }
}
