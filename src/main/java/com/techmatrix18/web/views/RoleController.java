package com.techmatrix18.web.views;

import com.techmatrix18.model.Role;
import com.techmatrix18.repository.PermissionRepository;
import com.techmatrix18.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/role")
public class RoleController {


    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    public RoleController(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @GetMapping("/list")
    public String listRoles(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "role/list";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {
        Role role = roleRepository.findById(id).orElseThrow();
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissionRepository.findAll());
        return "role/edit";
    }

    @PostMapping("/update")
    public String updateRole(@ModelAttribute Role role, @RequestParam Long permissions) {
        // Update permissions
        role.setPermissions(permissionRepository.findAllById(permissions));
        roleRepository.save(role);
        return "redirect:/role/list";
    }

    @GetMapping("/add")
    public String addRoleForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", permissionRepository.findAll());
        return "role/edit";
    }

    @PostMapping("/add")
    public String addRole(@ModelAttribute Role role, @RequestParam Long permissions) {
        role.setPermissions(permissionRepository.findAllById(permissions));
        roleRepository.save(role);
        return "redirect:/role/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return "redirect:/role/list";
    }
}
