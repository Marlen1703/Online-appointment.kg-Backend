package diplomabackend.dto;

import lombok.Data;

@Data
public class PolicyDTO {


    private String policy;

    public PolicyDTO(String policy) {
        this.policy = policy;
    }
}
