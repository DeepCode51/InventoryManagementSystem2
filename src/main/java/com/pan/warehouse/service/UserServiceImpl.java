package com.pan.warehouse.service;

import com.pan.warehouse.dao.UserDao;
import com.pan.warehouse.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username, String password) {
        return userDao.login(username,password);
    }

    @Override
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }

    @Override
    public int update(String id, String password, String phone) {
        return userDao.updateUser(id, password, phone);
    }

    @Override
    public ArrayList<Shelve> shelveList(String id) {
        return userDao.shelveList(id);
    }

    @Override
    public int shelveSum(String id) {
        return userDao.shelveSum(id);
    }

    @Override
    public int addShelve(String sid, String uid) {
        return userDao.addShelve(sid,uid);
    }

    @Override
    public Shelve findShelveById(String sid) {
        return userDao.findShelveById(sid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateShelve(String sid,String gptid) {
        userDao.updateShelve(sid);
        userDao.adddeleteputgoodremeber(sid,gptid);
        userDao.updatedeleteputgoodremeber(sid,gptid);
        userDao.updatedeleteputgoodremeberAmount(sid,gptid);
        userDao.deleteShelvePutGood(sid);
    }

    @Override
    public ArrayList<goodInfo> goodInfoList() {
        return userDao.goodInfoList();
    }

    @Override
    public int addproductstorage(String psid, String gid, String uid, Integer amount, Date badtime) {
        return userDao.addproductstorage(psid, gid, uid, amount, badtime);
    }

    @Override
    public int productstorageSum() {
        return userDao.productstorageSum();
    }

    @Override
    public ArrayList<Productstorage> prodList(String uid) {
        return userDao.prodList(uid);
    }

    @Override
    public int productstorageSumById(String uid) {
        return userDao.productstorageSumById(uid);
    }

    @Override
    public int addputgood(String psid, String sid, Integer amount) {
        return userDao.addputgood(psid, sid, amount);
    }

    @Override
    public int addputgoodremeber(String psid, String sid, Integer amount, String gptid) {
        return userDao.addputgoodremeber(gptid, psid, sid, amount);
    }

    @Override
    public int updateshelvestate(String sid) {
        return userDao.updateshelvestate(sid);
    }

    @Override
    public int gptSum() {
        return userDao.gptSum();
    }

    @Override
    public ArrayList<putGoodInfo> pgs(String uid) {
        return userDao.pgs(uid);
    }

    @Override
    public goodPut findPsById(String sid) {
        return userDao.findPsById(sid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void takegoodremeber(String psid, String sid, Integer amount, String gptid) {
        if (amount == userDao.findAmountById(sid)){
            userDao.updateShelve(sid);
            userDao.addtakegoodremeber(gptid, psid, sid, amount);
            userDao.deleteShelvePutGood(sid);
        }
        else if(amount==0) {

        }
        else {
            int i=userDao.findAmountById(sid)-amount;
            userDao.addtakegoodremeber(gptid, psid, sid, amount);
            userDao.updateShelvePutGood(sid,i);
        }
    }

    @Override
    public ArrayList<putGoodRemeber> pgts(String uid) {
        return userDao.pgts(uid);
    }

    @Override
    public ArrayList<Productstorage> lookbadtime(String uid) {

        return userDao.psBadList(uid);
    }

    @Override
    public int applygoodSum() {
        return userDao.applygoodSum();
    }

    @Override
    public int applygood(String appid, String fromid, String gid, Integer amount, Integer needtime) {
        return userDao.applygood(appid, fromid, gid, amount, needtime);
    }

    @Override
    public ArrayList<Applygood> appList(String fromid) {
        return userDao.appList(fromid);
    }

    @Override
    public ArrayList<orderinfo> orderList(String uid) {
        return userDao.orderList(uid);
    }

    @Override
    public ArrayList<orderinfo> orderListByGid(String gid, String uid) {
        return userDao.orderListByGid(gid,uid);
    }

    @Override
    public ArrayList<putGoodInfo> goods(String oid,String uid) {
        ArrayList<putGoodInfo> arr=new ArrayList<>();
        ArrayList<putGoodInfo> temps=userDao.pgs(uid);
        Order order=userDao.findOrderById(oid);
        int sum=order.getAmount();
        int i=0;
        for (putGoodInfo temp:temps){
            if(temp.getSstate().equals("空闲"))
                continue;
            if (temp.getAmount()>0&&temp.getGid().equals(order.getGid())){
                if(temp.getAmount()>sum-i){
                    temp.setAmount(sum-i);
                    arr.add(temp);
                    break;
                } else if(temp.getAmount()==sum-i) {
                    arr.add(temp);
                    break;
                }
                else {
                    arr.add(temp);
                    i=i+temp.getAmount();
                }
            }
        }
        return arr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void doorder(String oid,String uid) {
        ArrayList<putGoodInfo> arr=new ArrayList<>();
        ArrayList<putGoodInfo> temps=userDao.pgs(uid);
        Order order=userDao.findOrderById(oid);
        int sum=order.getAmount();
        int i=0;
        for (putGoodInfo temp:temps){
            if(temp.getSstate().equals("空闲"))
                continue;
            if (temp.getAmount()>0&&temp.getGid().equals(order.getGid())){
                if(temp.getAmount()>sum-i){
                    temp.setAmount(sum-i);
                    arr.add(temp);
                    break;
                } else if(temp.getAmount()==sum-i) {
                    arr.add(temp);
                    break;
                }
                else {
                    arr.add(temp);
                    i=i+temp.getAmount();
                }
            }
        }
        for (putGoodInfo a:arr){
            String gptid="gpt"+(userDao.gptSum()+1);
            if (a.getAmount() == userDao.findAmountById(a.getSid())){
                userDao.updateShelve(a.getSid());
                userDao.addorderremeber(gptid,a.getPsid(), a.getSid(), a.getAmount());
                userDao.deleteShelvePutGood(a.getSid());
                userDao.addorderto(oid,a.getPsid());
            }else if(a.getAmount()==0) {

            }else {
                int j=userDao.findAmountById(a.getSid())-a.getAmount();
                userDao.addorderremeber(gptid, a.getPsid(), a.getSid(), a.getAmount());
                userDao.updateShelvePutGood(a.getSid(),j);
                userDao.addorderto(oid,a.getPsid());
            }
        }
        userDao.updateorderstate(oid);
    }

    @Override
    public int orderSum(String uid) {
        return userDao.orderSum(uid);
    }

    @Override
    public int allocationSum(String uid) {
        return userDao.allocationSum(uid);
    }

    @Override
    public ArrayList<Allocation> allList(String uid) {
        return userDao.allList(uid);
    }

    @Override
    public ArrayList<putGoodInfo> searchAllocation(String alid, String uid) {
        ArrayList<putGoodInfo> arr=new ArrayList<>();
        ArrayList<putGoodInfo> temps=userDao.pgs(uid);
        Allocation allocation=userDao.findAllocationById(alid);
        int sum=allocation.getAmount();
        int i=0;
        for (putGoodInfo temp:temps){
            if(temp.getSstate().equals("空闲"))
                continue;
            if (temp.getAmount()>0&&temp.getGid().equals(allocation.getGid())){
                if(temp.getAmount()>sum-i){
                    temp.setAmount(sum-i);
                    arr.add(temp);
                    break;
                } else if(temp.getAmount()==sum-i) {
                    arr.add(temp);
                    break;
                } else {
                    arr.add(temp);
                    i=i+temp.getAmount();
                }
            }
        }
        return arr;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void doallocation(String alid, String uid) {
        ArrayList<putGoodInfo> arr=new ArrayList<>();
        ArrayList<putGoodInfo> temps=userDao.pgs(uid);
        Allocation allocation=userDao.findAllocationById(alid);
        int sum=allocation.getAmount();
        int i=0;
        for (putGoodInfo temp:temps){
            if(temp.getSstate().equals("空闲"))
                continue;
            if (temp.getAmount()>0&&temp.getGid().equals(allocation.getGid())){
                if(temp.getAmount()>sum-i){
                    temp.setAmount(sum-i);
                    arr.add(temp);
                    break;
                } else if(temp.getAmount()==sum-i) {
                    arr.add(temp);
                    break;
                } else {
                    arr.add(temp);
                    i=i+temp.getAmount();
                }
            }
        }
        for (putGoodInfo a:arr){
            String gptid="gpt"+(userDao.gptSum()+1);
            if (a.getAmount() == userDao.findAmountById(a.getSid())){
                userDao.updateShelve(a.getSid());
                userDao.addallocationremeber(gptid,a.getPsid(), a.getSid(), a.getAmount());
                userDao.deleteShelvePutGood(a.getSid());
            }else if(a.getAmount()==0) {

            }else {
                int j=userDao.findAmountById(a.getSid())-a.getAmount();
                userDao.addallocationremeber(gptid, a.getPsid(), a.getSid(), a.getAmount());
                userDao.updateShelvePutGood(a.getSid(),j);
            }
        }
        userDao.updateallocationstate(alid);
    }

    @Override
    public int findShelveState(String uid, String state) {

        return userDao.findShelveState(uid,state);
    }

    @Override
    public int findSumByMonth(String uid, Integer time) {
            return userDao.findSumByMonth(uid,time);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addputgoodinfo(String psid,String sid,Integer amount,String gptid){
        userDao.addputgood(psid, sid, amount);
        userDao.addputgoodremeber(gptid, psid, sid, amount);
        userDao.updateshelvestate(sid);
    }
}
