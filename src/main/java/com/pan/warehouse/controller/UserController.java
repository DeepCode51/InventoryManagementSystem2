package com.pan.warehouse.controller;

import com.pan.warehouse.pojo.Good;
import com.pan.warehouse.pojo.User;
import com.pan.warehouse.service.AdminService;
import com.pan.warehouse.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private  UserService userService;

    @RequestMapping ("/login")
    public String login(String username, String password, HttpSession session){
            User user=userService.login(username,password);
            if (user!=null){
                session.setAttribute("userId",user.getUid());
                return "redirect:/user/index";
            }else{
                return "redirect:/index";
            }
    }
    @RequestMapping("/index")
    public  String index(Model model,HttpSession session){
        model.addAttribute("shelveSum",userService.shelveSum((String) session.getAttribute("userId")));
        model.addAttribute("prodSum",userService.productstorageSumById((String)session.getAttribute("userId")));
        model.addAttribute("orderSum",userService.orderSum(session.getAttribute("userId").toString()));
        model.addAttribute("allocationSum",userService.allocationSum(session.getAttribute("userId").toString()));
        int state1=userService.findShelveState((String) session.getAttribute("userId"),"非空");
        int state2=userService.findShelveState((String) session.getAttribute("userId"),"空闲");
        model.addAttribute("state1",state1);
        model.addAttribute("state2",state2);
//        int sum1=userService.findSumByMonth((String) session.getAttribute("userId"),2);
//        int sum2=userService.findSumByMonth((String) session.getAttribute("userId"),1);
//        int sum3=userService.findSumByMonth((String) session.getAttribute("userId"),0);
        ArrayList<Integer> sums=new ArrayList<>();
        sums.add(userService.findSumByMonth((String) session.getAttribute("userId"),2));
        sums.add(userService.findSumByMonth((String) session.getAttribute("userId"),1));
        sums.add(userService.findSumByMonth((String) session.getAttribute("userId"),0));
        ArrayList<String> days=new ArrayList<>();
        Calendar ca = Calendar.getInstance();
        ca.setTime(new java.util.Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        ca.add(Calendar.MONTH, -2);
        days.add(format.format(ca.getTime()));
        ca.add(Calendar.MONTH, 1);
        days.add(format.format(ca.getTime()));
        ca.add(Calendar.MONTH, 1);
        days.add(format.format(ca.getTime()));
        model.addAttribute("sums",sums);
        model.addAttribute("days",days);
        return "/user";
    }
    @RequestMapping("/info")
    public String info(HttpSession session, Model model){
        String id =(String) session.getAttribute("userId");
        model.addAttribute("user",userService.findUserById(id));
        if(session.getAttribute("userUpdateError")!=null){
            model.addAttribute("userUpdateError",session.getAttribute("userUpdateError"));
            session.setAttribute("userUpdateError",null);
        }
        return "/userinfo";
    }
    @RequestMapping("/update")
    public String update(String password,String phone,HttpSession session){
        String id=(String) session.getAttribute("userId");
        if (userService.update(id,password,phone)>0){
            return "redirect:/user/index";
        }else {
            session.setAttribute("userUpdateError","修改失败！");
            return "redirect:/user/info";
        }

    }
    @RequestMapping("/shelvelist")
    public String shelvelist(HttpSession session,Model model){
        model.addAttribute("shelves",userService.shelveList((String) session.getAttribute("userId")));
        return "/usershelvelist";
    }
    @RequestMapping("/addshelve")
    public String addshelve(Model model,HttpSession session){
        if (session.getAttribute("addShelveError")!=null){
            model.addAttribute("addShelveError",session.getAttribute("addShelveError"));
            session.setAttribute("addShelveError",null);
        }
        return "/useraddshelve";
    }
    @RequestMapping("/addshelveid")
    public String addshelveid(HttpSession session,String id){
        String uid=(String) session.getAttribute("userId");
        String sid=uid+id;
        if (userService.findUserById(sid)!=null){
            session.setAttribute("addShelveError","添加货架失败,该编号已存在");
            return "redirect:/user/useraddshelve";
        }
        if (userService.addShelve(sid,uid)>0){
            return "redirect:/user/shelvelist";
        }else {
            session.setAttribute("addShelveError","添加货架失败");
            return "redirect:/user/useraddshelve";
        }
    }
    @RequestMapping("/shelvestatechange")
    public String shelvestatechange(@RequestParam("sid")String sid){
        int len=Integer.toString(userService.gptSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String gptid="gpt"+temp+Integer.toString(userService.gptSum()+1);
        userService.updateShelve(sid,gptid);
        return "redirect:/user/shelvelist";
    }
    @RequestMapping("/productstoragepage")
    public String productstoragepage(Model model){
        model.addAttribute("goods",userService.goodInfoList());
        return "/useraddproductstorage";
    }
    @RequestMapping("/addproductstorage")
    public String productstorage(String gid, Integer amount, Date badtime, HttpSession session){
        int len=Integer.toString(userService.productstorageSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String psid="ps"+temp+Integer.toString(userService.productstorageSum()+1);
        String uid=(String) session.getAttribute("userId");
        userService.addproductstorage(psid,gid,uid,amount,badtime);
        return "redirect:/user/productstoragelist";
    }
    @RequestMapping("/productstoragelist")
    public String productstoragelist(Model model, HttpSession  session){
        String uid=(String) session.getAttribute("userId");
        model.addAttribute("prods",userService.prodList(uid));
        return "/userproductstoragelist";
    }
    @RequestMapping("/putgoodpage")
    public String putgoodpage(Model model,HttpSession session){
        model.addAttribute("pgs",userService.pgs((String) session.getAttribute("userId")));
        return "/userputgood";
    }
    @RequestMapping("/addputgoodpage")
    public String addputgoodpage(@RequestParam("sid") String sid,Model model,HttpSession session){
        model.addAttribute("sid",sid);
        model.addAttribute("prods",userService.prodList((String) session.getAttribute("userId")));
        return "/useraddputgood";
    }
    @RequestMapping("/addputgood")
    public String addputgood(String sid,String psid,Integer amount){
        int len=Integer.toString(userService.gptSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String gptid="gpt"+temp+Integer.toString(userService.gptSum()+1);
//        userService.addputgood(psid,sid,amount);
//        userService.addputgoodremeber(psid,sid,amount,gptid);
//        userService.updateshelvestate(sid);
        userService.addputgoodinfo(psid,sid,amount,gptid);
        return "redirect:/user/putgoodpage";
    }
    @RequestMapping("/takegoodpage")
    public String takegoodpage(@RequestParam("sid") String sid,Model model,HttpSession session){
        model.addAttribute("sid",sid);
        model.addAttribute("ps",userService.findPsById(sid));
        return "/usertakegood";
    }
    @RequestMapping("/takegood")
    public String takegood(String sid,String psid,Integer amount){
        int len=Integer.toString(userService.gptSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String gptid="gpt"+temp+Integer.toString(userService.gptSum()+1);
        userService.takegoodremeber(psid,sid,amount,gptid);
        return "redirect:/user/putgoodpage";
    }
    @RequestMapping("/putgoodremeberlist")
    public String putgoodremeberlist(Model model,HttpSession session){
        model.addAttribute("pgts",userService.pgts((String) session.getAttribute("userId")));
        return "/userputgoodremeberlist";
    }
    @RequestMapping("/putgoodbadtimelist")
    public String putgoodbadtimelist(Model model,HttpSession session){
        String uid=(String) session.getAttribute("userId");
        model.addAttribute("pgbs",userService.lookbadtime(uid));
        return "/userputgoodbadtimelist";
    }
    @RequestMapping("/applygoodpage")
    public String applygoodpage(Model model){
        model.addAttribute("goods",userService.goodInfoList());
        return "/userapplygood";
    }
    @RequestMapping("/applygood")
    public String applygood(HttpSession session,String gid,Integer amount, Integer needtime){
        String fromid=session.getAttribute("userId").toString();
        int len=Integer.toString(userService.applygoodSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String appid="app"+temp+Integer.toString(userService.applygoodSum()+1);
        userService.applygood(appid,fromid,gid,amount,needtime);
        return "redirect:/user/applygoodlist";
    }
    @RequestMapping("/applygoodlist")
    public String applygoodlist(Model model,HttpSession session){
        model.addAttribute("applys",userService.appList(session.getAttribute("userId").toString()));
        return "/userapplygoodlist";
    }
    @RequestMapping("/orderlist")
    public String orderlist(HttpSession session ,Model model){
        model.addAttribute("orders",userService.orderList((String) session.getAttribute("userId")));
        return "/userorderlist";
    }
    @RequestMapping("/addordertopage")
    public String ordertopage(Model model,HttpSession session){
        model.addAttribute("orders",userService.orderList(session.getAttribute("userId").toString()));
        return "/userorderto";
    }
    @RequestMapping("/addorderto")
    public String addorderto(String oid,Model model,HttpSession session){
        model.addAttribute("pgs",userService.goods(oid,session.getAttribute("userId").toString()));
        model.addAttribute("oid",oid);
        return "/useraddorderto";
    }
    @RequestMapping("/doorder")
    public String doorder(String oid,Model model,HttpSession session){
        userService.doorder(oid,session.getAttribute("userId").toString());
        return "redirect:/user/orderlist";
    }
    @RequestMapping("/allocationlist")
    public String allocationlist(Model model,HttpSession session) {
        model.addAttribute("alls",userService.allList(session.getAttribute("userId").toString()));

        return "/userallocationlist";
    }
    @RequestMapping("/searchallocation")
    public String searchallocation(@RequestParam("alid") String alid,Model model,HttpSession session){
        model.addAttribute("pgs",userService.searchAllocation(alid,session.getAttribute("userId").toString()));
        model.addAttribute("alid",alid);
        return "/usersearchallocation";
    }
    @RequestMapping("/doallocation")
    public String doallocation(String alid,Model model,HttpSession session){
        userService.doallocation(alid,session.getAttribute("userId").toString());
        return "redirect:/user/allocationlist";
    }

}
