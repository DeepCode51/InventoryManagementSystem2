package com.pan.warehouse.controller;

import com.pan.warehouse.pojo.Admin;

import com.pan.warehouse.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping ("/login")
    public String login(String username, String password, HttpSession session){
        Admin admin=adminService.login(username,password);
        System.out.println(admin);
        if (admin!=null){
            session.setAttribute("AdminId",admin.getAid());
            return "redirect:/admin/index";
        }else{
            return "redirect:/adminindex";
        }
    }
    @RequestMapping ("/index")
    public String index(Model model){
        int sum=adminService.userSum();
        int goodSum=adminService.goodSum();
        int ticketSum=adminService.ticketSum();
        model.addAttribute("sum",sum);
        model.addAttribute("goodSum",goodSum);
        model.addAttribute("ticketSum",ticketSum);
        model.addAttribute("allocationsum",adminService.allocationsum());
        return "/admin";
    }
    @RequestMapping("/house")
    public String house(HttpSession session,Model model){
        Object id=session.getAttribute("AdminId");
        Admin admin=adminService.findAdminById((String) id);
        model.addAttribute("admin",admin);
        if (session.getAttribute("updateAdminError")!=null){
            model.addAttribute("updateAdminError",session.getAttribute("updateAdminError"));
            session.setAttribute("updateAdminError",null);
        }
        return "/adminhouse";
    }
    @RequestMapping("/update")
    public String update(String password,String phone,String id,HttpSession session){
        int row=adminService.updateAdmin(id,password, phone);
        if (row>0){
            return "redirect:/admin/index";
        }else{
            session.setAttribute("updateAdminError","修改操作失败");
            return "redirect:/admin/house";
        }
    }
    @RequestMapping("/warehouselist")
    public String warehouseList(Model model){
        model.addAttribute("users",adminService.userList());
        return "/adminwarehouselist";
    }
    @RequestMapping("/add")
    public  String add(String name, String username, String password, String phone, String address, String city,HttpSession session){
        int i=adminService.userSum()+1;
        int len=Integer.toString(i).length();
        String temp="";
        for(int j=0;j<5-len;j++){
            temp=temp+'0';
        }
        String s="u"+temp+Integer.toString(i);
        if(adminService.findUserByUsername(username)==null){
            int row=adminService.addWarehouse(s,name,username,password,phone,address,city);
            if (row>0){
                session.setAttribute("addUserMsg","添加成功");
                return "redirect:/admin/addwarehouse";
            }else {
                session.setAttribute("addUserMsg","添加失败");
                return "redirect:/admin/addwarehouse";
            }
        }
        session.setAttribute("addUserMsg","添加失败,该账号已存在");
        return "redirect:/admin/addwarehouse";
    }
    @RequestMapping("/addwarehouse")
    public String addwarehouse(HttpSession session,Model model){
        if (session.getAttribute("addUserMsg")!=null){
            model.addAttribute("addUserMsg",session.getAttribute("addUserMsg"));
            session.setAttribute("addUserMsg",null);
        }
        return "/adminaddwarehouse";
    }
    @RequestMapping("goodlist")
    public String goodlist(Model model){
        model.addAttribute("goodInfos",adminService.goodInfoList());
        return "/admingoodlist";
    }
    @RequestMapping("/addgoodpage")
    public String addgoodpage(Model model){
        model.addAttribute("factorys",adminService.factoryList());
        return "/adminaddgood";
    }
    @RequestMapping("/addgood")
    public String addgood(String gname,String fid){
        int goodSum=adminService.goodSum()+1;
        int len=Integer.toString(goodSum).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String gid='g'+temp+Integer.toString(goodSum);
        if (adminService.addGood(gid,gname,fid)>0){
            return "redirect:/admin/goodlist";
        }
        return "redirect:/admin/addgoodpage";
    }
    @RequestMapping("/updategoodnamepage")
    public String updategoodnamepage(Model model,@RequestParam("gid")String gid){
        model.addAttribute("goodInfo",adminService.findGoodById(gid));
        return "/adminupdategoodname";
    }
    @RequestMapping("/updategoodname")
    public String updategoodname(Model model,String gid,String gname){
        adminService.updateGoodName(gid,gname);
        return "redirect:/admin/goodlist";
    }
    @RequestMapping("/ticketlist")
    public String ticketlist(Model model){
        model.addAttribute("tickets",adminService.ticketList());
        return "/adminticketlist";
    }
    @RequestMapping("/addticketpage")
    public String addticketpage(Model model,HttpSession session){
        String msg=(String) session.getAttribute("addTicketError");
        if(msg!=null){
            model.addAttribute("addTicketError",msg);
            session.setAttribute("addTicketError",null);
        }
        return "adminaddticket";
    }
    @RequestMapping("/addticket")
    public String addticket(String tname,HttpSession session){
        if (adminService.findTicketByName(tname)!=null){
            session.setAttribute("addTicketError","添加失败，该标签名已存在");
            return "redirect:/admin/addticketpage";
        }
        int len=Integer.toString(adminService.ticketSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String tid='t'+temp+Integer.toString(adminService.ticketSum()+1);
        if (adminService.addticket(tid,tname)>0){
            return "redirect:/admin/ticketlist";
        }
        return "redirect:/admin/addticketpage";
    }
    @RequestMapping("/ticketgoodlistpage")
    public String ticketgoodlistpage(Model model,HttpSession session){
        model.addAttribute("tickets",adminService.ticketList());
        if (session.getAttribute("tid")!=null){
            model.addAttribute("ticketgoods",adminService.ticketGoodList((String) session.getAttribute("tid")));
            session.setAttribute("tid",null);
        }
        return "/adminticketgoodlist";
    }
    @RequestMapping("/ticketgoodlist")
    public String ticketgoodlist(@RequestParam("tid") String tid,HttpSession session){
        session.setAttribute("tid",tid);
        return "redirect:/admin/ticketgoodlistpage";
    }
    @RequestMapping("/addgoodticketpage")
    public String goodticketpage(Model model ,@RequestParam("gid") String gid,@RequestParam("gname") String gname){
        model.addAttribute("tickets",adminService.ticketListNotGood(gid));
        model.addAttribute("gname",gname);
        model.addAttribute("gid",gid);
        return "/adminaddgoodticket";
    }
    @RequestMapping("/addgoodticket")
    public  String addgoodticket(String tid,String gid){
        adminService.addgoodticket(tid,gid);
        return "redirect:/admin/goodlist";
    }
    @RequestMapping("/addfactorypage")
    public String addfactorypage(){
        return "/adminaddfactory";
    }
    @RequestMapping("/addfactory")
    public String addfactory(String fname,String faddress, String fphone){
        int len=Integer.toString(adminService.factorySum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String fid='f'+temp+Integer.toString(adminService.factorySum()+1);
        adminService.addfactory(fid,fname,faddress,fphone);
        return "redirect:/admin/addfactorypage";
    }
    @RequestMapping("/productionlist")
    public String productionlist(Model model){
        model.addAttribute("pros",adminService.proList());
        model.addAttribute("regs",adminService.regList());
        return "/adminproductionlist";
    }
    @RequestMapping("/addproductionpage")
    public String addproductionpage(Model model){
        model.addAttribute("goods",adminService.goodInfoList());
        return "/adminaddproduction";
    }
    @RequestMapping("/addproduction")
    public String addproduction(String gid,Integer amount){
        int len=Integer.toString(adminService.productionSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String pid='p'+temp+Integer.toString(adminService.productionSum()+1);
        adminService.addproduction(pid,gid,amount);
        return "redirect:/admin/productionlist";
    }

    @RequestMapping("/addregularorder")
    public String addregularorder(String gid,Integer amount,Integer rtime,Integer rnumber){
        int len=Integer.toString(adminService.regularorderSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String rid='r'+temp+Integer.toString(adminService.regularorderSum()+1);
        adminService.addregularorder(rid,gid,amount,rtime,rnumber);
        return "redirect:/admin/productionlist";
    }
    @RequestMapping("/goodputlist")
    public String goodputlist(Model model){
        model.addAttribute("gpis",adminService.gpis());
        return "/admingoodputlist";
    }
    @RequestMapping("/applygoodlist")
    public  String applygoodlist(Model model){
        model.addAttribute("apps",adminService.appList());
        return "/adminapplygoodlist";
    }
    @RequestMapping("/accessapplygood")
    public String accessapplygood(String appid){
        adminService.accessapplygood(appid);
        return "redirect:/admin/applygoodlist";
    }
    @RequestMapping("/refuseapplygood")
    public String refuseapplygood(String appid){
        adminService.refuseapplygood(appid);
        return "redirect:/admin/applygoodlist";
    }
    @RequestMapping("/addallocationpage")
    public String addallocationpage(Model model){
        model.addAttribute("goods",adminService.goodInfoList());
        model.addAttribute("users",adminService.userList()) ;
        return "/adminaddallocation";
    }
    @RequestMapping("/addallocationlistpage")
    public String addallocationlistpage(Model model,String gid,String toid,Integer amount,Integer needtime){
        model.addAttribute("trans",adminService.findAllocationById(gid,toid,amount,needtime));
        model.addAttribute("gid",gid);
        model.addAttribute("amount",amount);
        return "adminaddallocationlist";
    }
    @RequestMapping("/transportlist")
    public String transportlist(Model model){
        model.addAttribute("trans",adminService.transportList());
        return "/admintransportlist";
    }
    @RequestMapping("/addtransportpage")
    public String addtransport(Model model){
        model.addAttribute("users",adminService.userList());
        return "/adminaddtransport";
    }
    @RequestMapping("/addtransport")
    public String addtransport( String fromid, String toid, String tname, Integer needtime, Integer speed){
        int len=Integer.toString(adminService.transportSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String tsid="ts"+temp+Integer.toString(adminService.transportSum()+1);
        adminService.addtransport(tsid,fromid,toid,tname,needtime,speed);
        return "redirect:/admin/transportlist";
    }
    @RequestMapping("/updatetransprotpage")
    public String updatetransportpage(Model model,@RequestParam("tsid") String tsid){
        model.addAttribute("transportInfo",adminService.findTransportById(tsid));
        return "/adminupdatetransport";
    }
    @RequestMapping("/updatetransport")
    public String updatetransport(String tsid, String fromid, String toid, String tname, Integer needtime, Integer speed){
        adminService.updatetransport(tsid,fromid,toid,tname,needtime,speed);
        return "redirect:/admin/transportlist";
    }
    @RequestMapping("/deletetransport")
    public String deletetransport(@RequestParam("tsid") String tsid){
        adminService.deletetransport(tsid);
        return "redirect:/admin/transportlist";
    }
    @RequestMapping("/citytocitylist")
    public String citytocitylist(Model model){
        model.addAttribute("ccs",adminService.ccList());
        return "/admincitytocitylist";
    }
    @RequestMapping("/addorderpage")
    public String addorderpage(Model model){
        model.addAttribute("goodInfos",adminService.goodInfoList());
        return "/adminaddorder";
    }
    @RequestMapping("/addorder")
    public String addorder(String receivecity,String gid,Integer amount){
        int len=Integer.toString(adminService.orderSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String oid ='o'+temp+Integer.toString(adminService.orderSum()+1);
        String uid=adminService.addorderFromcityId(receivecity,amount,gid);
        adminService.addorder(oid,uid,receivecity,gid,amount);
        return "redirect:/admin/orderlist";
    }
    @RequestMapping("/orderlist")
    public String orderlist(Model model){
        model.addAttribute("orderinfos",adminService.orderinfoList());
        return "/adminorderlist";
    }
    @RequestMapping("/addallocation")
    public String addallocation(@RequestParam("tsid")String tsid,@RequestParam("fromid")String fromid,@RequestParam("toid")String toid,@RequestParam("gid")String gid,@RequestParam("amount")Integer amount){
        int len=Integer.toString(adminService.allocationSum()+1).length();
        String temp="";
        for(int i=0;i<5-len;i++){
            temp=temp+'0';
        }
        String alid= "al"+temp+Integer.toString(adminService.allocationSum()+1);
        adminService.addallocation(alid,fromid,toid,tsid,gid,amount);
        return "redirect:/admin/allocationlist";
    }
    @RequestMapping("/allocationlist")
    public String allocationlist(Model model){
        model.addAttribute("alls",adminService.allocationList());
        return "/adminallocationlist";
    }
}
