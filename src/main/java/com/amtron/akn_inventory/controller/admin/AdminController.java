package com.amtron.akn_inventory.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.model.common.Payload;
import com.amtron.akn_inventory.service.admin.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public ModelAndView adminDashboard() {
        ModelAndView mv = new ModelAndView("admin/dashboard");
        return mv;
    }

    @GetMapping("/category")
    public ModelAndView categoryPage() {
        ModelAndView mv = new ModelAndView("admin/master-category");
        mv.addObject("statusList", adminService.getStatusList());
        return mv;
    }

    @PutMapping("/category/add")
    public ResponseEntity<Payload> saveCategory(@RequestBody MasterCategoryDto masterCategoryDto) {
        adminService.saveCategory(masterCategoryDto);

        Payload payload = Payload.builder()
                .msg("Category added successfully.")
                .data(masterCategoryDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

}
