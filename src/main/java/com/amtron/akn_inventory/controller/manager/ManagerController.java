package com.amtron.akn_inventory.controller.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amtron.akn_inventory.dto.manager.TransItemStockDto;
import com.amtron.akn_inventory.model.common.Payload;
import com.amtron.akn_inventory.service.manager.ManagerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping
    public ModelAndView managerDashboard() {
        ModelAndView mv = new ModelAndView("manager/dashboard");
        return mv;
    }

    @GetMapping("/add-item-stock")
    public ModelAndView addStockPage() {
        ModelAndView mv = new ModelAndView("manager/add-item-stock");
        return mv;
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchItems(@RequestParam String q) {
        List<String> masterItemDtos = managerService.searchItems(q);
        return ResponseEntity.ok().body(masterItemDtos);
    }

    @GetMapping("/get-item-stock-details")
    public ResponseEntity<Payload> getItemStockDetails(@RequestParam String item) {
        TransItemStockDto transItemStockDto = managerService.getItemStockDetails(item);

        Payload payload = Payload.builder()
                .msg("Item stock details fetched.")
                .data(transItemStockDto)
                .build();
        return ResponseEntity.ok().body(payload);
    }

    @PutMapping("/add-item-stock")
    public ResponseEntity<Payload> addStock(@Valid @RequestBody TransItemStockDto transItemStockDto) {

        transItemStockDto = managerService.addStock(transItemStockDto);
        Payload payload = Payload.builder()
                .msg("Item stock details saved.")
                .data(transItemStockDto)
                .build();
        return ResponseEntity.ok().body(payload);
    }

    @GetMapping("/issue-item-stock")
    public ModelAndView issueStockPage() {
        ModelAndView mv = new ModelAndView("manager/issue-item-stock");
        return mv;
    }
}
