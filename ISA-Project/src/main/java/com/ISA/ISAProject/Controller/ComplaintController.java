package com.ISA.ISAProject.Controller;

import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.ComplaintDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Services.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService _complaintService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ComplaintDto>> getAllComplaints(){
        List<ComplaintDto> allComplaints = _complaintService.getAllComplaints();
        return new ResponseEntity<>(allComplaints, HttpStatus.OK);
    }

    @GetMapping("/getAllByAdminId/{systemAdminId}")
    public ResponseEntity<List<ComplaintDto>> getAllComplaintsByAdminId(@PathVariable Integer systemAdminId){
        List<ComplaintDto> allComplaintsByAdminId = _complaintService.getAllComplaintsByAdminId(systemAdminId);
        return new ResponseEntity<>(allComplaintsByAdminId, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/update")
    public ResponseEntity<Void> updateComplaint(@Valid @RequestBody ComplaintDto complaintDto) {
        Complaint complaint = _complaintService.updateComplaint(complaintDto);
        if (complaint != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
