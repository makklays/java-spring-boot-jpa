package com.techmatrix18.web.views;

import com.techmatrix18.model.Permission;
import com.techmatrix18.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/list")
    public String listPermissions(Model model) {
        List<Permission> permissions = permissionService.getAllPermissions();
        model.addAttribute("permissions", permissions);
        return "permission/list";
    }

    @GetMapping("/edit/{id}")
    public String editPermission(@PathVariable Long id, Model model) {
        Permission permission = permissionService.getPermissionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid permission Id:" + id));
        model.addAttribute("permission", permission);
        return "permission/edit";
    }

    @PostMapping("/update")
    public String updatePermission(@ModelAttribute Permission permission) {
        permissionService.updatePermission(permission);
        return "redirect:/permission/list";
    }

    @GetMapping("/add")
    public String addPermissionForm(Model model) {
        model.addAttribute("permission", new Permission());
        return "permission/edit";
    }

    @PostMapping("/add")
    public String addPermission(@ModelAttribute Permission permission) {
        permissionService.createPermission(permission);
        return "redirect:/permission/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return "redirect:/permission/list";
    }
}

