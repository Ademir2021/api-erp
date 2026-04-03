package br.com.centroinfo.api.repository.branch;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centroinfo.api.entities.branchs.Branch;

public interface BranchRepository extends JpaRepository <Branch, Long> {

}
