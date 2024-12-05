package com.amtron.akn_inventory.model.user;

import java.util.List;

import com.amtron.akn_inventory.model.common.Audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Audit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Column(unique = true)
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	private String fullName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "USERS_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLES_ID", referencedColumnName = "ID") })
	private List<Role> roles;

	@Builder.Default
	@Column(columnDefinition = "tinyint(1)")
	private Boolean enabled = true;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String mobileNo;
}
