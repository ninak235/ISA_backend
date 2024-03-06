package com.ISA.ISAProject.Repository;
import com.ISA.ISAProject.Model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ContractRepository extends JpaRepository<Contract,Integer> {
    Contract findContractByHospitalName(String hospitalName);

    List<Contract> findContractsByCompanyName(String companyName);
}
