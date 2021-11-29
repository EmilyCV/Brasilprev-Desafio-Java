package com.Brasilprev.builder;

import com.Brasilprev.entity.Address;
import lombok.Builder;

@Builder
public class AddressBuilder {
    //Creating a fixed address for testing
    @Builder.Default
    private int id = 1;

    @Builder.Default
    private String address = "Rua Flores";

    @Builder.Default
    private String cep = "06090050";

    @Builder.Default
    private String state = "SP";

    @Builder.Default
    private String number = "230";

    @Builder.Default
    private String complement = "Casa";

    public Address address() {
        return new Address(id,address,cep,state,number,complement);
    }
}
