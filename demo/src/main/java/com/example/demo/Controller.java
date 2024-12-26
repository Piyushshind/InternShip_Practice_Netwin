package com.example.demo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class Controller {

    @Autowired
    private EmpRepository empRepository;

    private static final Path STORAGE_LOCATION = Paths.get("uploads");

    @GetMapping("/showAll")
    public List<EmpEntity> getAllEmployees() {
        return empRepository.findAll();
    }

    @PostMapping("/addEmp")
    public ResponseEntity<String> addEmployee(@RequestBody EmpEntity empEntity) {
        empRepository.save(empEntity);
        return ResponseEntity.ok("Employee added successfully with ID: " + empEntity.getId());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmpEntity> updateEmployee(@PathVariable Long id, @RequestBody EmpEntity empEntity) {
        empEntity.setId(id);
        return ResponseEntity.ok(empRepository.save(empEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        empRepository.deleteById(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("EmpName") String empName,
            @RequestParam("age") int age,
            @RequestParam("empSalary") double empSalary,
            @RequestParam("EmpAddress") String empAddress) {
        try {
            Files.createDirectories(STORAGE_LOCATION);
            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = STORAGE_LOCATION.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), filePath);

            EmpEntity empEntity = new EmpEntity();
            empEntity.setEmpName(empName);
            empEntity.setAge(age);
            empEntity.setEmpSalary(empSalary);
            empEntity.setEmpAddress(empAddress);
            empEntity.setFileName(file.getOriginalFilename());
            empEntity.setUrl(filePath.toString());

            return ResponseEntity.ok(empRepository.save(empEntity));
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    // @Autowired
    // private EmpRepository empRepository;

    // @GetMapping("/check")
    // public String check() {
    //     return "Working.........";
    // }

    // @GetMapping("/showAll")
    // public List<EmpEntity> getAllEmp() {
    //     return empRepository.findAll();
    // }

    // @PostMapping("/addEmp")
    // public ResponseEntity<String> addEmp(@RequestBody EmpEntity Empentity) {
    //     System.out.println(Empentity);
    //     empRepository.save(Empentity);
    //     return ResponseEntity.ok("Employee added successfully with ID: " + Empentity.getId());
    // }

    // @PostMapping("/addMultipleEmp")
    // public ResponseEntity<List<EmpEntity>> addMultipleEmp(@RequestBody List<EmpEntity> employees) {
    //     List<EmpEntity> savedEmployees = empRepository.saveAll(employees);
    //     return ResponseEntity.ok(savedEmployees);
    // }

    // @PutMapping("/update/{EmpId}")
    // public ResponseEntity<EmpEntity> updateEmpById(@PathVariable Long EmpId, @RequestBody EmpEntity empEntity) {
    //     System.out.println("Data updated successfully");
    //     return ResponseEntity.ok(empRepository.save(empEntity));
    // }

    // @DeleteMapping("delete/{EmpId}")
    // public void deleteEmpById(@PathVariable Long EmpId) {
    //     empRepository.deleteById(EmpId);
    // }
    // // file methods

    // @PostMapping("/uploadFile")
    // public ResponseEntity<?> uploadMedia(
    //         @RequestParam("file") MultipartFile file,
    //         @RequestParam("EmpName") String empName,
    //         @RequestParam("age") int age,
    //         @RequestParam("empSalary") double empSalary,
    //         @RequestParam("EmpAddress") String empAddress) throws Exception {
    
    //     final Path storageLocation = Paths.get("uploads");
    //     Files.createDirectories(storageLocation);
    
    //     try {
    //         String uniqueName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
    //         Path targetPath = storageLocation.resolve(uniqueName);
    //         Files.copy(file.getInputStream(), targetPath);
    
        
            
    //         EmpEntity empEntity = new EmpEntity();
    //         empEntity.setEmpName(empName);
    //         empEntity.setAge(age);
    //         empEntity.setEmpSalary(empSalary);
    //         empEntity.setEmpAddress(empAddress);
    //         empEntity.setFileName(file.getOriginalFilename());
    //         empEntity.setUrl(targetPath.toString());
    
    //         return ResponseEntity.ok(empRepository.save(empEntity));
    //     } catch (Exception e) {
    //         throw new RuntimeException("Failed to store file", e);
    //     }
    // }
    

    // @GetMapping("showMedia")
    // public ResponseEntity<List<EmpEntity>> getAllMedia() {
    // return ResponseEntity.ok(mediaService.getAllMedia());
    // }

}
