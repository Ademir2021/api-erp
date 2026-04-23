package br.com.centroinfo.api.repository.group;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.centroinfo.api.entities.items.group.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {}
