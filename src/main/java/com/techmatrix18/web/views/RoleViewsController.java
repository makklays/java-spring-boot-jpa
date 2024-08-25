package com.techmatrix18.web.views;

import com.techmatrix18.model.Role;
import com.techmatrix18.model.Storehouse;
import com.techmatrix18.repository.PermissionRepository;
import com.techmatrix18.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleViewsController {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    public RoleViewsController(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @GetMapping("/list")
    public String listRoles(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "roles/list";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {
        Role role = roleRepository.findById(id).orElseThrow();
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissionRepository.findAll());
        return "roles/edit";
    }

    @PostMapping("/update")
    public String updateRole(@ModelAttribute Role role, @RequestParam Long permissions) {
        // Update permissions
        role.setPermissions(permissionRepository.findAllById(permissions));
        roleRepository.save(role);
        return "redirect:/roles/list";
    }

    @GetMapping("/add")
    public String addRoleForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", permissionRepository.findAll());
        return "roles/add";
    }

    @PostMapping("/add")
    public String addRole(@ModelAttribute Role role, @RequestParam Long permissions) {
        role.setPermissions(permissionRepository.findAllById(permissions));
        roleRepository.save(role);
        return "redirect:/roles/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleRepository.deleteById(id);
        return "redirect:/roles/list";
    }

    @GetMapping("/{roleId}")
    public String view(Model model, @PathVariable String roleId) {
        Role role = roleRepository.findById(Long.parseLong(roleId)).orElseThrow();
        model.addAttribute("permissions", permissionRepository.findAll());
        if (role.getId() != null) {
            model.addAttribute("role", role);
            logger.info("Role found..");
        } else {
            model.addAttribute("role", null);
            logger.info("Error! Role not found..");
        }

        return "roles/view";
    }
}

