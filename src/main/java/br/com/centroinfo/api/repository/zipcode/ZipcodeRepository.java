package br.com.centroinfo.api.repository.zipcode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.centroinfo.api.dtos.zipcodeDTO.ZipcodeDTO;
import br.com.centroinfo.api.entities.address.zipcode.ZipCode;

public interface ZipcodeRepository extends JpaRepository<ZipCode, Long> {

    @Query("""
            SELECT new br.com.centroinfo.api.dtos.zipcodeDTO.ZipcodeDTO(
            z.id, z.code
            ) FROM ZipCode z ORDER BY z.id ASC
            """)
    List<ZipcodeDTO> findSummary();
}


