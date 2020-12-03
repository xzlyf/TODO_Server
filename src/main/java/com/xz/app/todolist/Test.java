package com.xz.app.todolist;

import com.xz.app.todolist.constant.Local;
import com.xz.app.todolist.pojo.EventList;
import com.xz.app.todolist.pojo.vo.CreateEvent;
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
      /*  String pwd = "123123";
        String rsaSrt = RSAUtil.publicEncrypt(pwd,RSAUtil.getPublicKey(Local.publicKey));
        //123456
        //XK3ctUqciE89Mah-RHIDaW6EOagzbN33UoDviT_xDk2vxV1eldTLHeg7Ec22Re-UYDD9C3O5Z7Xchua_T1nXhEeZWLSrR394Qo0e-Yq5Rs8cCw9jSiUpmCxQJ6qfy0-0SDhrazKqhz8E_SikaMemXbFCDg9EMxbNxphQFQXEnUE
        //123123
        //qzkH0uldHak6ycUrDONFCeanJgYuhJcR2b1YaO3jAsjZJneqOPGypJAhCN4vJtxXnQeIla-c_ClsaEWUgO0cw0jy1YCbTmz1mWGRp-GZ6a5yqjfSBtPMNygi4poIw1_ygk8jnzBqkVdWXUOcpaKLo8Ws9erJzKSacbqegh1zBI0
        System.out.println("公钥加密=========="+rsaSrt);*/


        CreateEvent createEvent = new CreateEvent();
        createEvent.setContent("我是内容");
        createEvent.setShortTitle("测试1");
        createEvent.setDone(false);

        EventList eventList = new EventList();
        eventList.setId("1234567");



        BeanUtils.copyProperties(createEvent, eventList, MyBeanUtils.getNullPropertyNames(createEvent));

        System.out.println(createEvent.toString());
        System.out.println(eventList.toString());


    }
}
