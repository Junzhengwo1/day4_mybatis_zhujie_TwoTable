package com.kou.dao;

import com.kou.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author dell
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    @Select(value = {"select * from user"})
    @Results(id = "userMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "address",property = "address"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "birthday",property = "birthday"),
    @Result(property = "accounts",column = "id",
            many=@Many(select = "com.kou.dao.IAccountDao.findAccountByUid",
            fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据Id查询用户
     * @param userId
     * @return
     */
    @Select("select*from user where id=#{id}")
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户
     * @param username
     * @return
     */
    List<User> findUserByName(String username);

    /**
     * 查询所有用户对应的角色
     * @return
     */
    List<User> findAllUserAndRole();

}
