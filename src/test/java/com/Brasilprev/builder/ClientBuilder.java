package com.Brasilprev.builder;

import com.Brasilprev.entity.Address;
import com.Brasilprev.entity.Client;
import lombok.Builder;

@Builder
public class ClientBuilder {
    @Builder.Default
    private String cpf = "86952640824";

    @Builder.Default
    private String name = "Marcelo";

    @Builder.Default
    private String email = "marcelo@gmail.com";

    @Builder.Default
    private String password = "123";

    @Builder.Default
    private Address address = Address.builder().build();

    public Client client() {
        return new Client(cpf, name, email, password, address);
    }
}
