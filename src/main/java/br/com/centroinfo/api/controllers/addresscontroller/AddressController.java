package br.com.centroinfo.api.controllers.addresscontroller;

import br.com.centroinfo.api.dtos.addressDTO.AddressDTO;
import br.com.centroinfo.api.dtos.addressDTO.AddressResponseDTO;
import br.com.centroinfo.api.entities.address.address.Address;
import br.com.centroinfo.api.services.address.AddressService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/address")
    public Address create(@RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponseDTO>> getAddressesByPersonId() {
        List<AddressResponseDTO> addresses = addressService.findAllAddresses();
        if (addresses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/address")
    public ResponseEntity<String> update(@RequestBody AddressDTO addressDTO) {
        Address address = addressService.update(addressDTO);
        return ResponseEntity.ok().body("Endereço atualizado com sucesso " + "(ID:" + address.getId() + ")");
    }

    @DeleteMapping("/address/{id}")
    public void delete(@PathVariable("id") Long id) {
        addressService.delete(id);
    }
}
