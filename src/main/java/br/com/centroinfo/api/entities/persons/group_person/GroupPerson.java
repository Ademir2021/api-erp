package br.com.centroinfo.api.entities.persons.group_person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "group_persons")
@Entity
@Setter
@Getter
@NoArgsConstructor
public class GroupPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Ex: Fornecedor, Cliente, Funcionario, etc.

}
