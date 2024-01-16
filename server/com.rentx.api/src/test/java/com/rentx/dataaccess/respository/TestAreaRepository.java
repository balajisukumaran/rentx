package com.rentx.dataaccess.respository;

import com.rentx.entities.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestAreaRepository extends JpaRepository<Area, Long> {

    List<Area> findAll();

    Area findByAreaID(int areaID);

}
