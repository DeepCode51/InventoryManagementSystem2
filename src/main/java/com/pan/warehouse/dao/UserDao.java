package com.pan.warehouse.dao;

import com.pan.warehouse.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface UserDao {
    User login(@Param("username") String username, @Param("password") String password);
    User findUserById(@Param("id")String id);
    int updateUser(@Param("id") String id,@Param("password")String password,@Param("phone")String phone);
    ArrayList<Shelve> shelveList(@Param("id") String id);
    int shelveSum(@Param("id") String id);
    int addShelve(@Param("sid") String sid,@Param("uid") String uid);
    Shelve findShelveById(@Param("sid") String id);
    int updateShelve(@Param("sid") String id);
    int deleteShelvePutGood(@Param("sid") String sid);
    int updatedeleteputgoodremeber(@Param("sid") String sid,@Param("gptid")String gptid);
    int updatedeleteputgoodremeberAmount(@Param("sid") String sid,@Param("gptid")String gptid);
    int adddeleteputgoodremeber(@Param("sid") String sid,@Param("gptid")String gptid);
    ArrayList<goodInfo> goodInfoList();
    int addproductstorage(@Param("psid")String psid, @Param("gid")String gid, @Param("uid")String uid,@Param("amount")Integer amount, @Param("badtime")Date badtime);
    int productstorageSum();
    ArrayList<Productstorage> prodList(@Param("uid") String uid);
    int productstorageSumById(@Param("uid") String uid);
    int addputgood(@Param("psid")String psid,@Param("sid") String sid,@Param("amount")Integer amount);
    int addputgoodremeber(@Param("gptid")String gptid,@Param("psid")String psid,@Param("sid") String sid,@Param("amount")Integer amount);
    int updateshelvestate(@Param("sid")String sid);
    int gptSum();
    ArrayList<putGoodInfo> pgs(@Param("uid") String uid);
    goodPut findPsById(@Param("sid") String sid);
    int addtakegoodremeber(@Param("gptid")String gptid,@Param("psid")String psid,@Param("sid") String sid,@Param("amount")Integer amount);
    int findAmountById(@Param("sid") String sid);
    int updateShelvePutGood(@Param("sid") String sid,@Param("amount")Integer amount);
    ArrayList<putGoodRemeber> pgts(@Param("uid")String uid);
    ArrayList<Productstorage> psBadList(@Param("uid") String uid);
    int applygoodSum();
    int applygood(@Param("appid")String appid,@Param("fromid")String fromid,@Param("gid") String gid,@Param("amount")Integer amount,@Param("needtime")Integer needtime);
    ArrayList<Applygood> appList(@Param("fromid")String fromid);
    ArrayList<orderinfo> orderList(@Param("uid") String uid);
    ArrayList<orderinfo> orderListByGid(@Param("gid") String gid,@Param("uid") String uid);
    Order findOrderById(@Param("oid")String oid);
    int addorderremeber(@Param("gptid")String gptid,@Param("psid")String psid,@Param("sid") String sid,@Param("amount")Integer amount);
    int addorderto(@Param("oid")String oid,@Param("psid")String psid);
    int updateorderstate(@Param("oid")String oid);
    int orderSum(@Param("uid") String uid);
    int allocationSum(@Param("uid")String uid);
    ArrayList<Allocation> allList(@Param("uid")String uid);
    Allocation findAllocationById(@Param("alid") String alid);
    int updateallocationstate(@Param("alid")String alid);
    int addallocationremeber(@Param("gptid")String gptid,@Param("psid")String psid,@Param("sid") String sid,@Param("amount")Integer amount);
    int findShelveState(@Param("uid")String uid,@Param("state") String state);
    int findSumByMonth(@Param("uid")String uid,@Param("time") Integer time );
}
