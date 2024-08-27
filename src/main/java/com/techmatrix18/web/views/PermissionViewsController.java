package com.techmatrix18.web.views;

import com.techmatrix18.model.Permission;
import com.techmatrix18.model.Role;
import com.techmatrix18.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/permissions")
public class PermissionViewsController {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final PermissionService permissionService;

    @Autowired
    public PermissionViewsController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/list")
    public String listPermissions(Model model) {
        List<Permission> permissions = permissionService.getAllPermissions();
        model.addAttribute("permissions", permissions);
        return "permissions/list";
    }

    @GetMapping("/edit/{id}")
    public String editPermission(@PathVariable Long id, Model model) {
        Permission permission = permissionService.getPermissionById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid permission Id:" + id));
        model.addAttribute("permission", permission);
        return "permissions/edit";
    }

    @PostMapping("/update")
    public String updatePermission(@ModelAttribute Permission permission) {
        permissionService.updatePermission(permission);
        return "redirect:/permissions/list";
    }

    @GetMapping("/add")
    public String addPermissionForm(Model model) {
        model.addAttribute("permission", new Permission());
        return "permissions/add";
    }

    @PostMapping("/add")
    public String addPermission(@ModelAttribute Permission permission) {
        permissionService.createPermission(permission);
        return "redirect:/permissions/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return "redirect:/permissions/list";
    }

    @GetMapping("/{permissionId}")
    public String view(Model model, @PathVariable String permissionId) {
        Permission permission = permissionService.getPermissionById(Long.parseLong(permissionId)).orElseThrow();
        model.addAttribute("permissions", permissionService.getAllPermissions());
        if (permission.getId() != null) {
            model.addAttribute("permission", permission);
            logger.info("Role found..");
        } else {
            model.addAttribute("permission", null);
            logger.info("Error! Role not found..");
        }

        return "permissions/view";
    }
}

