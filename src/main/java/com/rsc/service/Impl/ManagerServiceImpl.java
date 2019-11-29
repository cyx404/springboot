package com.rsc.service.Impl;

import com.rsc.entity.Manager;
import com.rsc.repository.ManagerRepository;
import com.rsc.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public String managerToLogin(String phone, String password, Model model, HttpSession session) {
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        Manager manager = managerRepository.findManagerByMphoneAndMpassword(phone, md5Password);
        if (null == manager) {
            model.addAttribute("merror", "手机号或密码错误！");
            return "admin/adminLogin.html";
        } else {
            session.setAttribute("manager", manager);
            model.addAttribute("mname", manager.getMname());
            return "admin/adminIndex.html";
        }
    }
}
