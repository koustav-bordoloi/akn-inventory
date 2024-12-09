package com.amtron.akn_inventory.model.manager;

import com.amtron.akn_inventory.model.admin.MasterItem;
import com.amtron.akn_inventory.model.common.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransItemStock extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_item_id")
    private MasterItem item;

    @Column(nullable = false)
    private Integer quantity;
}
