package com.advanceacademy.moonlighthotel.repository.user;

import com.advanceacademy.moonlighthotel.entity.user.UserRoleAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository  extends JpaRepository<UserRoleAsset, Long> {

}
