package kr.co.kesti.controller;

import kr.co.kesti.service.TestApiService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/test")
public class TestApiController {
    @Resource(name = "testApiService")
    private TestApiService testApiService;

    @GetMapping("/select")
    public void testSelect() {
        this.testApiService.selectTest();
    }

    @PostMapping("/insert")
    public void testInsert() {
        this.testApiService.insertTest();
    }

    @PutMapping("/update")
    public void testUpdate() {
        this.testApiService.updateTest();
    }

    @DeleteMapping("/delete")
    public void testDelete() {
        this.testApiService.deleteTest();
    }
}