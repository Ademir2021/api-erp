package br.com.centroinfo.api.entities.persons;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import br.com.centroinfo.api.entities.address.address.Address;
import br.com.centroinfo.api.entities.branchs.Branch;
import br.com.centroinfo.api.entities.users.User;
import br.com.centroinfo.api.entities.persons.group_person.GroupPerson;
import lombok.Getter;
import lombok.Setter;

@Table(name = "persons")
@Entity
@Getter
@Setter
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;
  @Column(name = "type_person")
  private TypePerson typePerson; // 0 -Fisica, 1 - Juridica
  @ManyToOne
  @JoinColumn(name = "group_person_id")
  private GroupPerson groupPerson;// Ex: Fornecedor, Cliente, Funcionario, etc.
  @ManyToOne
  @JoinColumn(name = "branch_id")
  private Branch branch;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private String name;
  private int age;
  @Column(name = "date_birth")
  private LocalDate dateOfBirth;
  private Gender gender;
  private String cpf;
  private String rg;
  private String email;
  private String phone;
  private String cnpj;
  @Column(name = "inscric_state")
  private String inscricState;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;
  public int calcAge() {
    return LocalDate.now().getYear() - dateOfBirth.getYear();
  }

}
