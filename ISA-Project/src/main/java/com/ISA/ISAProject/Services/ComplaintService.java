package com.ISA.ISAProject.Services;

import com.ISA.ISAProject.Dto.CompanyEquipmentDto;
import com.ISA.ISAProject.Dto.ComplaintDto;
import com.ISA.ISAProject.Dto.CustomerDto;
import com.ISA.ISAProject.Mapper.CompanyAdminMapper;
import com.ISA.ISAProject.Mapper.ComplaintMapper;
import com.ISA.ISAProject.Mapper.UserMapper;
import com.ISA.ISAProject.Model.Company;
import com.ISA.ISAProject.Model.Complaint;
import com.ISA.ISAProject.Model.Customer;
import com.ISA.ISAProject.Model.User;
import com.ISA.ISAProject.Repository.CompanyRepository;
import com.ISA.ISAProject.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository _complaintRepository;

    @Autowired
    private final ComplaintMapper _complaintMapper;

    public ComplaintService(ComplaintMapper complaintMapper) {
        _complaintMapper = complaintMapper;
    }

    @Transactional
    public List<ComplaintDto> getAllComplaints() {
        List<Complaint> complaints = _complaintRepository.findAll();
        return _complaintMapper.mapComplaintToDto(complaints);
    }

    @Transactional
    public List<ComplaintDto> getAllComplaintsByAdminId(Integer companyAdminId) {
        List<Complaint> complaints = _complaintRepository.findByCompanyAdminId(companyAdminId);
        return _complaintMapper.mapComplaintToDto(complaints);
    }


    public Complaint updateComplaint(ComplaintDto complaintDto) {
        Complaint complaint = _complaintMapper.dtoToComplaint(complaintDto);


        Complaint updatedComplaint = _complaintRepository.findById(complaint.getId())
                .orElseThrow(() -> new IllegalArgumentException("Complaint not found with id: " + complaint.getId()));

        updatedComplaint.setContent(complaint.getContent());
        updatedComplaint.setReplay(complaint.getReplay());

        return _complaintRepository.save(updatedComplaint);
    }

}
