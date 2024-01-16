package com.rentx.businessservices;

import com.rentx.dataaccess.repository.AreaRepository;
import com.rentx.dtos.AreaDto;
import com.rentx.mappers.AreaMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import com.rentx.businessservices.interfaces.IAreaService;

@RequiredArgsConstructor
@Service
@Setter
public class AreaService implements IAreaService {
    /**
     * private Area Repository
     */
    private AreaRepository areaRepository;

    /**
     * area service constructor
     * @param areaRepository area Repository
     */
    @Autowired
    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    /**
     * method to find all in area dto
     * @return list of area mapper to area dto
     */
    @Override
    public List<AreaDto> findAll() {
        return AreaMapper.toAreaDtos(areaRepository.findAll());
    }
}