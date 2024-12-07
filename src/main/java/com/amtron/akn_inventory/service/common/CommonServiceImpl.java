package com.amtron.akn_inventory.service.common;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.amtron.akn_inventory.enums.Status;

@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public List<String> getStatusList() {
        return EnumSet.allOf(Status.class)
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
