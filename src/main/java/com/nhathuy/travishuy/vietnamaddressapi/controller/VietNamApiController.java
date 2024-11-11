package com.nhathuy.travishuy.vietnamaddressapi.controller;

import com.nhathuy.travishuy.vietnamaddressapi.model.District;
import com.nhathuy.travishuy.vietnamaddressapi.model.Province;
import com.nhathuy.travishuy.vietnamaddressapi.model.Ward;
import com.nhathuy.travishuy.vietnamaddressapi.service.VietNamApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/travishuy/vietnamaddress")
@RestController
@CrossOrigin("*")
public class VietNamApiController {

    @Autowired
    private VietNamApiService vietNamApiService;

    @GetMapping("/provinces")
    public ResponseEntity<List<Province>> getAllProvinces(){
        return ResponseEntity.ok(vietNamApiService.getAllProvinces());
    }
    @GetMapping("/provinces/{provinceId}")
    public ResponseEntity<Province> getProvinceById(@PathVariable String provinceId) {
        return ResponseEntity.ok(vietNamApiService.getProvinceById(provinceId));
    }
    @GetMapping("/provinces/{provinceId}/districts")
    public ResponseEntity<List<District>> getDistrictsByProvinceId(@PathVariable String provinceId) {
        return ResponseEntity.ok(vietNamApiService.getDistrictsByProvinceId(provinceId));
    }

    @GetMapping("/districts/{districtId}")
    public ResponseEntity<District> getDistrictById(@PathVariable String districtId) {
        return ResponseEntity.ok(vietNamApiService.getDistrictById(districtId));
    }

    @GetMapping("/districts/{districtId}/wards")
    public ResponseEntity<List<Ward>> getWardsByDistrictId(@PathVariable String districtId) {
        return ResponseEntity.ok(vietNamApiService.getWardsByDistrictId(districtId));
    }

    @GetMapping("/wards/{wardId}")
    public ResponseEntity<Ward> getWardById(@PathVariable String wardId) {
        return ResponseEntity.ok(vietNamApiService.getWardById(wardId));
    }
}
