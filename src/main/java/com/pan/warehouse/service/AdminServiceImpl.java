package com.pan.warehouse.service;

import com.pan.warehouse.dao.AdminDao;
import com.pan.warehouse.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDao adminDao;


    @Override
    public Admin login(String username, String password) {
        return adminDao.login(username,password);
    }

    @Override
    public Admin findAdminById(String id) {
        return adminDao.findAdminById(id);
    }

    @Override
    public int updateAdmin(String id, String password, String phone) {
        int row =adminDao.updateAdmin(id, password, phone);
        return row;
    }

    @Override
    public int userSum() {
        return adminDao.userSum();
    }

    @Override
    public ArrayList<User> userList() {
        return adminDao.userList();
    }

    @Override
    public int addWarehouse(String id, String name, String username, String password, String phone, String address, String city) {
        return adminDao.addWarehouse(id, name, username, password, phone, address, city);
    }

    @Override
    public User findUserByUsername(String username) {
        return adminDao.findUserByUsername(username);
    }

    @Override
    public ArrayList<goodInfo> goodInfoList() {
        return adminDao.goodInfoList();
    }

    @Override
    public ArrayList<Factory> factoryList() {
        return adminDao.factoryList();
    }

    @Override
    public int goodSum() {
        return adminDao.goodSum();
    }

    @Override
    public int addGood(String gid, String gname, String fid) {
        return adminDao.addGood(gid,gname,fid);
    }

    @Override
    public goodInfo findGoodById(String gid) {
        return adminDao.findGoodById(gid);
    }

    @Override
    public int updateGoodName(String gid, String gname) {
        return adminDao.updateGoodById(gid,gname);
    }

    @Override
    public ArrayList<Ticket> ticketList() {
        return adminDao.ticketList();
    }

    @Override
    public int ticketSum() {
        return adminDao.ticketSum();
    }

    @Override
    public int addticket(String id, String name) {
        return adminDao.addticket(id, name);
    }

    @Override
    public Ticket findTicketByName(String name) {
        return adminDao.findTicketByName(name);
    }

    @Override
    public ArrayList<ticketGood> ticketGoodList(String tid) {
        return adminDao.ticketGoodList(tid);
    }

    @Override
    public ArrayList<Ticket> ticketListNotGood(String gid) {
        return adminDao.ticketListByGood(gid);
    }

    @Override
    public int addgoodticket(String tid, String gid) {
        return adminDao.addgoodticket(tid,gid);
    }

    @Override
    public int factorySum() {
        return adminDao.factorySum();
    }

    @Override
    public int addfactory(String fid, String fname, String faddress, String fphone) {
        return adminDao.addfactory(fid, fname, faddress, fphone);
    }

    @Override
    public ArrayList<productionInfo> proList() {
        return adminDao.proList();
    }

    @Override
    public ArrayList<regularorderInfo> regList() {
        return adminDao.regList();
    }

    @Override
    public int addproduction(String pid, String gid, Integer amount) {
        return adminDao.addproduction(pid, gid, amount);
    }

    @Override
    public int addregularorder(String rid, String gid, Integer amount, Integer rtime, Integer rnumber) {
        return adminDao.addregularorder(rid, gid, amount, rtime, rnumber);
    }

    @Override
    public int productionSum() {
        return adminDao.productionSum();
    }

    @Override
    public int regularorderSum() {
        return adminDao.regularorderSum();
    }

    @Override
    public ArrayList<adminGoodPutInfo> gpis() {
        return adminDao.gpis();
    }

    @Override
    public ArrayList<Applygood> appList() {
        return adminDao.appList();
    }

    @Override
    public int accessapplygood(String appid) {
        return adminDao.accessapplygood(appid);
    }

    @Override
    public int refuseapplygood(String appid) {
        return adminDao.refuseapplygood(appid);
    }

    @Override
    public ArrayList<Transport> transportList() {
        return adminDao.transportList();
    }

    @Override
    public int addtransport(String tsid, String fromid, String toid, String tname, Integer needtime, Integer speed) {
        return adminDao.addtransport(tsid, fromid, toid, tname, needtime, speed);
    }

    @Override
    public int transportSum() {
        return adminDao.transportSum();
    }

    @Override
    public Transport findTransportById(String tsid) {
        return adminDao.findTransportById(tsid);
    }

    @Override
    public int deletetransport(String tsid) {
        return adminDao.deletetransport(tsid);
    }

    @Override
    public int updatetransport(String tsid, String fromid, String toid, String tname, Integer needtime, Integer speed) {
        return adminDao.updatetransport(tsid, fromid, toid, tname, needtime, speed);
    }

    @Override
    public ArrayList<Citytocity> ccList() {
        return adminDao.ccList();
    }

    @Override
    public int orderSum() {
        return adminDao.orderSum();
    }
    @Override
    public String addorderFromcityId(String receivecity,Integer amount,String gid) {
        ArrayList<userInfo> users=adminDao.userInfoList(receivecity);
        int min=100000;
        String uid="";
        for (userInfo u:users){

            if (u.getDistance()<min && adminDao.findAmountByUidAndGid(u.getUid(),gid)!=null){
                int temp=adminDao.findAmountByUidAndGid(u.getUid(),gid);
                if (temp>=amount){
                    uid=u.getUid();
                    min=u.getDistance();
                }
            }
        }
        return uid;
    }

    @Override
    public int addorder(String oid, String uid, String receivecity, String gid, Integer amount) {
        return adminDao.addorder(oid, uid, receivecity, gid, amount);
    }

    @Override
    public ArrayList<orderinfo> orderinfoList() {
        return adminDao.orderinfoList();
    }

    @Override
    public ArrayList<Transport> findAllocationById(String gid,String toid,Integer amount,Integer needtime) {

        return adminDao.findTransport(gid, toid, amount, needtime);
    }

    @Override
    public int addallocation(String alid, String fromid, String toid, String tsid, String gid, Integer amount) {
        return adminDao.addallocation(alid, fromid, toid, tsid, gid, amount);
    }

    @Override
    public ArrayList<Allocation> allocationList() {
        return adminDao.allocationList();
    }

    @Override
    public int allocationSum() {
        return adminDao.allocationSum();
    }

    @Override
    public int allocationsum() {
        return adminDao.allocationsum();
    }

}
