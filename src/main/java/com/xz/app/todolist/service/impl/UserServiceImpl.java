package com.xz.app.todolist.service.impl;

import com.xz.app.todolist.constant.Local;
import com.xz.app.todolist.constant.StatusEnum;
import com.xz.app.todolist.pojo.User;
import com.xz.app.todolist.pojo.vo.ApiResult;
import com.xz.app.todolist.pojo.vo.UserPublicDataVO;
import com.xz.app.todolist.repository.UserRepository;
import com.xz.app.todolist.service.UserService;
import com.xz.app.todolist.utils.AccountGenerate;
import com.xz.app.todolist.utils.MD5Util;
import com.xz.app.todolist.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;


    /**
     * 注册接口
     */
    @Override
    public ApiResult register(String name, String password, String phone) {
        User user = new User();
        user.setUserName(name);
        user.setUserPwd(password);
        user.setUserPhone(phone);

        User checkAgain = findUserPhone(phone);
        //手机号存在
        if (checkAgain != null) {
            return new ApiResult(StatusEnum.FAILED_USER_EXIST, new UserPublicDataVO(checkAgain.getUserName(), checkAgain.getUserNo()));
        }
        //生成账号，并查询数据库是否存在此账号，如果存在则重新生成账号
        String tempAccount;
        do {
            tempAccount = AccountGenerate.makeAccount(8);
            checkAgain = findUserNo(tempAccount);
        } while (checkAgain != null);
        user.setUserNo(tempAccount);
        try {
            userRepo.save(user);
            return new ApiResult(StatusEnum.SUCCESS, new UserPublicDataVO(user.getUserName(), user.getUserNo()));
        } catch (Exception e) {
            //数据插入失败，可能存在相同项
            System.out.println("=========error==========:" + e.getMessage());
            return new ApiResult(StatusEnum.ERROR, null, e.getMessage());
        }
    }

    /**
     * 登录接口
     *
     * @return
     */
    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public String login(User user, String rsaPwd, long timestamp) {

        if (!validatePwd(user, rsaPwd,timestamp)) {
            return null;
        }

        //生成token 参考生成规则
        String token = MD5Util.getMD5(user.getUserNo() + user.getUserPhone() + user.getUserPwd() + Local.token_secret + System.currentTimeMillis());
        userRepo.updateStateByToken(user.getUuid(), token);
        return token;
    }

    /**
     * 注销登录
     *
     * @param userNo
     * @return
     */
    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public ApiResult logout(String userNo, String token) {
        User user;
        try {
            //账号登录
            user = userRepo.findByUserNo(userNo);
            if (user == null) {
                return new ApiResult(StatusEnum.FAILED_USER_LOGIN_NO_USER_NO, null);
            }
            if (!token.equals(user.getToken())) {
                return new ApiResult(StatusEnum.ERROR_TOKEN, null);
            }
            userRepo.updateStateByToken(user.getUuid(), null);
            return new ApiResult(StatusEnum.SUCCESS, null);
        } catch (Exception e) {
            return new ApiResult(StatusEnum.ERROR, e.getMessage());
        }
    }

    /**
     * 验证密码
     *
     * @return true 正确 false 失败
     */
    @Override
    public boolean validatePwd(User user, String rsaPwd,long timestamp) {
        //使用私钥解密密文
        String pwd = null;
        try {
            pwd = RSAUtil.privateDecrypt(rsaPwd, RSAUtil.getPrivateKey(Local.privateKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
        //明文密码+时间戳
        String originPwd = user.getUserPwd()+timestamp;
        return originPwd.equals(pwd);
    }


    /**
     * 根据账号查询用户信息
     */
    @Override
    public User findUserNo(String userNo) {
        User user = null;
        try {
            user = userRepo.findByUserNo(userNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public User findUserName(String userName) {
        User user = null;
        try {
            user = userRepo.findByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public User findUserPhone(String phone) {
        return userRepo.findByUserPhone(phone);
    }

    @Override
    public User findUserToken(String token) {
        return userRepo.findByToken(token);
    }

    @Override
    public User findUUID(String uuid) {
        return userRepo.findByUuid(uuid);
    }


    /**
     * 查询表里所有用户
     */
    @Override
    public List<User> findAll() {
        List<User> allList = null;
        try {
            allList = userRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allList;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<User> getAllUserByOnlyPage(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Order.desc("createTime"));//根据createTime字段降序排列
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return userRepo.findAll(pageable);
    }


    /**
     * 更新用户名
     *
     * @param uuid
     * @param newUserName
     */
    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public void alterUserName(String uuid, String newUserName) {
        userRepo.updateStateByUserName(uuid, newUserName, new Date(System.currentTimeMillis()));
    }

    /**
     * 更新密码
     *
     * @param uuid
     * @param newUserPwd
     */
    @Transactional//开启事务，否则执行update/delete时将失败
    @Override
    public void alterUserPwd(String uuid, String newUserPwd) {
        //解密使用公钥加密的密文
        String pwd = null;
        try {
            pwd = RSAUtil.privateDecrypt(newUserPwd, RSAUtil.getPrivateKey(Local.privateKey));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        userRepo.updateStateByUserPwd(uuid, pwd, new Date(System.currentTimeMillis()));
    }
}
