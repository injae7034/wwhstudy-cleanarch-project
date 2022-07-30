package injae.AddressBook.member.adapter.in.api.register;

import injae.AddressBook.member.application.port.in.RegisterMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RegisterMemberApiController {

    private final RegisterMemberUseCase useCase;

    @PostMapping("/members")
    public ResponseEntity<RegisterMemberRequest> registerMember(@RequestBody @Valid
                                                         RegisterMemberRequest request) {
        Long savedId = useCase.registerMember(request.getEmail(), request.getPassword(), request.getName());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
