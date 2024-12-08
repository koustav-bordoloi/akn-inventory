package com.amtron.akn_inventory.controller.admin;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amtron.akn_inventory.dto.admin.MasterCategoryDto;
import com.amtron.akn_inventory.dto.admin.MasterItemDto;
import com.amtron.akn_inventory.model.common.Payload;
import com.amtron.akn_inventory.service.admin.AdminService;

import jakarta.validation.Valid;
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

    @GetMapping("/category/all")
    public ResponseEntity<Payload> getAllCategories() {
        List<MasterCategoryDto> masterCategoryDto = adminService.getAllCategoriesDto();

        Payload payload = Payload.builder()
                .msg("Master Category List Fetched.")
                .data(masterCategoryDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Payload> getCategoryDtoById(@PathVariable Integer id) {
        MasterCategoryDto masterCategoryDto = adminService.getCategoryDtoById(id);

        Payload payload = Payload.builder()
                .msg("Master Category Fetched.")
                .data(masterCategoryDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

    @PutMapping("/category/add")
    public ResponseEntity<Payload> saveCategory(@Valid @RequestBody MasterCategoryDto masterCategoryDto) {
        masterCategoryDto = adminService.saveCategory(masterCategoryDto);

        Payload payload = Payload.builder()
                .msg("Category added successfully.")
                .data(masterCategoryDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

    // ----------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------

    @GetMapping("/item")
    public ModelAndView itemPage() {
        ModelAndView mv = new ModelAndView("admin/master-item");
        mv.addObject("statusList", adminService.getStatusList());
        mv.addObject("categoryList", adminService.getAllActiveCategoriesDto());
        return mv;
    }

    @GetMapping("/item/all")
    public ResponseEntity<Payload> getAllItems() {
        List<MasterItemDto> masterItemDto = adminService.getAllItemsDto();

        Payload payload = Payload.builder()
                .msg("Master Item List Fetched.")
                .data(masterItemDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Payload> getItemDtoById(@PathVariable Integer id) {
        MasterItemDto masterItemDto = adminService.getItemDtoById(id);

        Payload payload = Payload.builder()
                .msg("Master Item Fetched.")
                .data(masterItemDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

    @PutMapping("/item/add")
    public ResponseEntity<Payload> saveItem(@Valid @RequestBody MasterItemDto masterItemDto) {
        masterItemDto = adminService.saveItem(masterItemDto);

        Payload payload = Payload.builder()
                .msg("Item added successfully.")
                .data(masterItemDto)
                .build();

        return ResponseEntity.ok().body(payload);
    }

}
