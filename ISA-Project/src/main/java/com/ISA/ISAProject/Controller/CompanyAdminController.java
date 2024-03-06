package com.ISA.ISAProject.Controller;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Model.CompanyAdmin;
import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.Customer;
import io.swagger.annotations.ApiParam;
import com.ISA.ISAProject.Dto.CompanyAdminDto;
import com.ISA.ISAProject.Services.EmailService;
import com.ISA.ISAProject.Services.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping(value = "/api/companyAdmin")
//SecurityRequirement(name = "bearerAuth")
public class CompanyAdminController {
    @Autowired
    private CompanyAdminService _companyAdminService;
    @Autowired
    private EmailService _emailService;

    /*
    @PostMapping("/createAdmin/{companyId}")
    public ResponseEntity<CompanyAdminDto> createCompanyAdmin(
            @PathVariable Integer companyId,
            @RequestBody CompanyAdminDto companyAdminDto) {

        CompanyAdminDto createdAdmin = _companyAdminService.createCompanyAdmin(companyAdminDto, companyId);

        if (createdAdmin != null) {
            return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    */


    @PostMapping(value = "/registerCompanyAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> registerCompanyAdmin(@Valid @RequestBody CompanyAdminDto registrationDto) {
        try {
            if (_emailService.isEmailUnique(registrationDto.getEmail())) {

                System.out.println(registrationDto.getCompanyId());
                System.out.println(registrationDto.getCity());
                System.out.println(registrationDto.getCountry());
                System.out.println(registrationDto.getNumber());
                System.out.println(registrationDto.getPassword());
                System.out.println(registrationDto.getFirstName());
                System.out.println(registrationDto.getLastName());
                System.out.println(registrationDto.getEmail());

                CompanyAdminDto newCompanyAdmin = _companyAdminService.registerCompanyAdmin(registrationDto);
                System.out.println(newCompanyAdmin);

                if (newCompanyAdmin == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
    @CrossOrigin
    @PutMapping("/updateAdmin/{adminId}/{companyId}")
    public ResponseEntity<CompanyAdminDto> updateCompanyAdmin(
            @PathVariable Integer adminId,
            @PathVariable Integer companyId,
            @RequestBody CompanyAdminDto updatedAdminDto) {

        CompanyAdminDto updatedAdmin = _companyAdminService.updateCompanyAdmin(adminId, updatedAdminDto, companyId);

        if (updatedAdmin != null) {
            return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */
    @GetMapping(value = "/{adminId}")
    public ResponseEntity<CompanyAdminDto> getAdminById(@PathVariable Integer adminId){
        CompanyAdmin admin = _companyAdminService.getById(adminId);
        CompanyAdminDto adminDto = new CompanyAdminDto(admin.getUser(), admin.getCompany().getId());
        return new ResponseEntity<>(adminDto, HttpStatus.OK);
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<List<CompanyAdminDto>> getAAllCompanyAdmins(){
        List<CompanyAdmin> allAdmins = _companyAdminService.getAllCompanyAdmins();
        List<CompanyAdminDto> adminDtos = allAdmins.stream()
                .map(admin -> new CompanyAdminDto(admin.getUser(), admin.getCompany().getId()))
                .collect(Collectors.toList());


        //CompanyAdminDto adminDto = new CompanyAdminDto(admin.getUser(), admin.getCompany().getId());
        return new ResponseEntity<>(adminDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/company/{companyId}")
    @PreAuthorize("hasRole('COMPANYADMIN')")
    public ResponseEntity<List<CompanyAdminDto>> getCompanyAdmins(@PathVariable Integer companyId){
        List<CompanyAdmin> allAdmins = _companyAdminService.getAllCompanyAdmins();
        List<CompanyAdminDto> adminDtos = allAdmins.stream()
                .filter(admin -> admin.getCompany().getId().equals(companyId))
                .map(admin -> new CompanyAdminDto(admin.getUser(), admin.getCompany().getId()))
                .collect(Collectors.toList());


        //CompanyAdminDto adminDto = new CompanyAdminDto(admin.getUser(), admin.getCompany().getId());
        return new ResponseEntity<>(adminDtos, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/updateAdmin")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CompanyAdminDto adminDto) {
        CompanyAdmin admin = _companyAdminService.updateAdmin(adminDto);
        if (admin != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

