package com.pan.warehouse.dao;

import com.pan.warehouse.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface AdminDao {
    Admin login(@Param("username") String username, @Param("password") String password);
    Admin findAdminById(@Param("id") String id);
    int updateAdmin(@Param("id")String id,@Param("password")String password,@Param("phone")String phone);
    int userSum();
    ArrayList<User> userList();
    int addWarehouse(@Param("id")String id,@Param("name")String name,@Param("username")String username,@Param("password")String password,@Param("phone")String phone,@Param("address")String address,@Param("city") String city);
    User findUserByUsername(@Param("username") String username);
    ArrayList<goodInfo> goodInfoList();
    ArrayList<Factory> factoryList();
    int goodSum();
    int addGood(@Param("gid") String gid,@Param("gname")String gname,@Param("fid") String fid);
    goodInfo findGoodById(@Param("gid")String gid);
    int updateGoodById(@Param("gid")String gid,@Param("gname")String gname);
    ArrayList<Ticket> ticketList();
    int ticketSum();
    int addticket(@Param("tid") String id,@Param("tname") String name);
    Ticket findTicketByName(@Param("tname")String name);
    ArrayList<ticketGood> ticketGoodList(@Param("tid") String tid);
    ArrayList<Ticket> ticketListByGood(@Param("gid") String gid);
    int addgoodticket(@Param("tid")String tid,@Param("gid") String gid);
    int addfactory(@Param("fid") String fid ,@Param("fname") String fname ,@Param("faddress") String faddress, @Param("fphone") String fphone);
    int factorySum();
    ArrayList<productionInfo> proList();
    ArrayList<regularorderInfo> regList();
    int addproduction(@Param("pid")String pid,@Param("gid")String gid,@Param("amount") Integer amount);
    int addregularorder(@Param("rid") String rid, @Param("gid")String gid,  @Param("amount") Integer amount, @Param("rtime") Integer rtime,@Param("rnumber")Integer rnumber);
    int productionSum();
    int regularorderSum();
    ArrayList<adminGoodPutInfo> gpis();
    ArrayList<Applygood> appList();
    int accessapplygood(@Param("appid")String appid);
    int refuseapplygood(@Param("appid")String appid);
    ArrayList<Transport> transportList();
    int addtransport( @Param("tsid") String tsid, @Param("fromid")String fromid, @Param("toid") String toid, @Param("tname")String tname, @Param("needtime") Integer needtime,@Param("speed")Integer speed);
    int transportSum();
    Transport findTransportById(@Param("tsid") String tsid);
    int deletetransport(@Param("tsid")String tsid);
    int updatetransport(@Param("tsid") String tsid, @Param("fromid")String fromid, @Param("toid") String toid, @Param("tname")String tname, @Param("needtime") Integer needtime,@Param("speed")Integer speed);
    ArrayList<Citytocity> ccList();
    int orderSum();
    ArrayList<userInfo> userInfoList(@Param("receivecity") String receivecity);
    int addorder(@Param("oid") String oid, @Param("uid")String uid, @Param("receivecity") String receivecity, @Param("gid")String gid, @Param("amount") Integer amount);
    ArrayList<orderinfo> orderinfoList();
    Integer findAmountByUidAndGid(@Param("uid")String uid,@Param("gid") String gid);
    ArrayList<Transport> findTransport(@Param("gid") String gid,@Param("toid")String toid,@Param("amount")Integer amount,@Param("needtime") Integer needtime);
    ArrayList<Allocation> allocationList();
    int addallocation(@Param("alid") String alid,@Param("fromid")String fromid,@Param("toid") String toid,@Param("tsid")String tsid,@Param("gid")String gid,@Param("amount")Integer amount);
    int allocationSum();
    int allocationsum();
}
