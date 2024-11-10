package com.pan.warehouse.service;

import com.pan.warehouse.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.ArrayList;

public interface AdminService {
    Admin login(String username , String password);
    Admin findAdminById(String id);
    int updateAdmin(String id,String password,String phone);
    int userSum();
    ArrayList<User> userList();
    int addWarehouse(String id ,String name,String username,String password,String phone,String address,String city);
    User findUserByUsername(String username);
    ArrayList<goodInfo> goodInfoList();
    ArrayList<Factory> factoryList();
    int goodSum();
    int addGood(String gid,String gname,String fid);
    goodInfo findGoodById(String gid);
    int updateGoodName(String gid ,String gname);
    ArrayList<Ticket> ticketList();
    int ticketSum();
    int addticket(String id,String name );
    Ticket findTicketByName(String name);
    ArrayList<ticketGood> ticketGoodList(String tid);
    ArrayList<Ticket> ticketListNotGood(String gid);
    int addgoodticket(String tid,String gid);
    int factorySum();
    int addfactory(String fid,String fname,String faddress,String fphone);
    ArrayList<productionInfo> proList();
    ArrayList<regularorderInfo> regList();
    int addproduction(String pid, String gid,  Integer amount);
    int addregularorder( String rid, String gid,  Integer amount,  Integer rtime, Integer rnumber);
    int productionSum();
    int regularorderSum();
    ArrayList<adminGoodPutInfo> gpis();
    ArrayList<Applygood> appList();
    int accessapplygood(String appid);
    int refuseapplygood(String appid);
    ArrayList<Transport> transportList();
    int addtransport(String tsid,String fromid,String toid,String tname,Integer needtime,Integer speed);
    int transportSum();
    Transport findTransportById(String tsid);
    int deletetransport(String tsid);
    int updatetransport(String tsid,String fromid,String toid,String tname,Integer needtime,Integer speed);
    ArrayList<Citytocity> ccList();
    int orderSum();
    String addorderFromcityId(String receivecity,Integer amount,String gid);
    int addorder(String oid,String uid,String receivecity,String gid,Integer amount);
    ArrayList<orderinfo> orderinfoList();
    ArrayList<Transport> findAllocationById(String gid,String toid,Integer amount,Integer needtime);
    int addallocation(String alid,String fromid,String toid,String tsid,String gid,Integer amount);
    ArrayList<Allocation> allocationList();
    int allocationSum();
    int allocationsum();
}
