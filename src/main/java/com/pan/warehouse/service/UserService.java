package com.pan.warehouse.service;

import com.pan.warehouse.pojo.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.ArrayList;

public interface UserService {
    User login(String username , String password);
    User findUserById(String id);
    int update(String id,String password,String phone);
    ArrayList<Shelve> shelveList(String id);
    int shelveSum( String id);
    int addShelve(String sid,String uid);
    Shelve findShelveById(String sid);
    void updateShelve(String sid,String gptid);
    ArrayList<goodInfo> goodInfoList();
    int addproductstorage(String psid, String gid, String uid, Integer amount, Date badtime);
    int productstorageSum();
    ArrayList<Productstorage> prodList(String uid);
    int productstorageSumById(String uid);
    int addputgood(String psid,String sid,Integer amount);
    int addputgoodremeber(String psid,String sid,Integer amount,String gptid);
    int updateshelvestate(String sid);
    void addputgoodinfo(String psid,String sid,Integer amount,String gptid);
    int gptSum();
    ArrayList<putGoodInfo> pgs(String uid);

    goodPut findPsById(String sid);
    void takegoodremeber(String psid,String sid,Integer amount,String gptid);
    ArrayList<putGoodRemeber> pgts(String uid);
    ArrayList<Productstorage> lookbadtime(String uid);
    int applygoodSum();
    int applygood(String appid,String fromid,String gid,Integer amount,Integer needtime);
    ArrayList<Applygood> appList(String fromid);
    ArrayList<orderinfo> orderList(String uid);
    ArrayList<orderinfo> orderListByGid(String gid,String uid);
    ArrayList<putGoodInfo> goods(String oid,String uid);
    void doorder(String oid,String uid);
    int orderSum(String uid);
    int allocationSum(String uid);
    ArrayList<Allocation> allList(String uid);

    ArrayList<putGoodInfo> searchAllocation(String alid,String uid);
    void doallocation(String alid,String uid);
    int findShelveState(String uid,String state);
    int findSumByMonth(String uid,Integer time);
}
