package com.kou.dao;

import com.kou.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author dell
 */
public interface IAccountDao {

    /**
     * 查询所有账户 并且获取每个账户所属的用户信息
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            @Result(id =true,column = "id",property ="id"),
            @Result(column = "uid",property ="uid"),
            @Result(column = "money",property ="money"),
    @Result(property = "user",column = "uid",
            one=@One(select="com.kou.dao.IUserDao.findById",
            fetchType = FetchType.EAGER))
    })
    List<Account> findAllAccount();

    /**
     * 通过用户id查询账户
     * @return
     * @param uid
     */
    @Select("select * from account where uid=#{uid}")
    List<Account> findAccountByUid(Integer uid);



}
