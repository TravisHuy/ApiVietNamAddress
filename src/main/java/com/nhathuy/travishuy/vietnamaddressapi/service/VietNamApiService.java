package com.nhathuy.travishuy.vietnamaddressapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhathuy.travishuy.vietnamaddressapi.model.District;
import com.nhathuy.travishuy.vietnamaddressapi.model.Province;
import com.nhathuy.travishuy.vietnamaddressapi.model.Ward;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VietNamApiService {
    private List<Province> provinces = new ArrayList<>();

    @PostConstruct
    public void init(){
        try {
            // này dùng để chuyển đổi object giữa json trong java
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("/vietnamAddress.json");
            provinces = mapper.readValue(resource.getInputStream(),mapper.getTypeFactory().constructCollectionType(List.class, Province.class));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<Province> getAllProvinces(){
        return provinces;
    }

    public Province getProvinceById(String provinceId){
        return provinces.stream()
                .filter(p -> p.getId().equals(provinceId))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Province not found"));
    }
    public List<District> getDistrictsByProvinceId(String provinceId){
        Province province = getProvinceById(provinceId);
        return province.getDistricts();
    }

    public District getDistrictById(String districtId){
        return provinces.stream()
                .flatMap(p -> p.getDistricts().stream())
                .filter(d -> d.getId().equals(districtId))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("District not found"));
    }
    public List<Ward> getWardsByDistrictId(String districtId){
        District district = getDistrictById(districtId);
        return district.getWards();
    }
    public Ward getWardById(String wardId) {
        return provinces.stream()
                .flatMap(p -> p.getDistricts().stream())
                .flatMap(d -> d.getWards().stream())
                .filter(w -> w.getId().equals(wardId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ward not found"));
    }
}
