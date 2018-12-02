package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id="userMap",
            value= {
                    @Result(id=true,column="id",property="userId"),
                    @Result(column="username",property="userName"),
                    @Result(column="sex",property="userSex"),
                    @Result(column="address",property="userAddress"),
                    @Result(column="birthday",property="userBirthday"),
                    @Result(column = "id",property = "accounts",many =@Many(
                            select = "com.itheima.dao.IUserDao.findByUid",fetchType = FetchType.LAZY)
                    )
            })
    List<User> findAll();
    @Select("select * from account where uid=#{uid}")
    List<Account> findByUid(Integer uid);

   @Select("select * from account")
   @Results(id = "accountMap",value = {@Result(id = true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
           @Result(column = "uid",property = "user",one = @One(select ="com.itheima.dao.IUserDao.findById",fetchType = FetchType.LAZY))
    })
    List<Account> findAllAcount();




    @Select("select * from user where id=#{uid}")
    @ResultMap("userMap")
    User findById(Integer userId);

/*    @Insert("insert  into user(username,sex,birthday,address)values(#{username},#{sex},#{birthday},#{address})")
    @ResultMap("userMap")
    @SelectKey(keyColumn="id",keyProperty="userId",resultType=Integer.class,before  =false, statement = { "select last_insert_id()" })
    int saveUser(User user);*/
    /**
     * 保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    @SelectKey(keyColumn="id",keyProperty="id",resultType=Integer.class,before = false,  statement = { "select last_insert_id()" })
    int saveUser(User user);


    @Select("select * from user where username like #{AA} ")
    List<User> findByName(String name);

}
