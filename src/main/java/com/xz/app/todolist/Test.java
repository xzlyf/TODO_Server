package com.xz.app.todolist;

import com.xz.app.todolist.constant.Local;
import com.xz.app.todolist.utils.RSAUtil;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Author: xz
 * @Date: 2020/12/3
 */
public class Test {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String pwd = "123456";
        String rsaSrt = RSAUtil.publicEncrypt(pwd,RSAUtil.getPublicKey(Local.publicKey));
        //XK3ctUqciE89Mah-RHIDaW6EOagzbN33UoDviT_xDk2vxV1eldTLHeg7Ec22Re-UYDD9C3O5Z7Xchua_T1nXhEeZWLSrR394Qo0e-Yq5Rs8cCw9jSiUpmCxQJ6qfy0-0SDhrazKqhz8E_SikaMemXbFCDg9EMxbNxphQFQXEnUE
        System.out.println("公钥加密=========="+rsaSrt);

    }
}
