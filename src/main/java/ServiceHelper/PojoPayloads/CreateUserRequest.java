package ServiceHelper.PojoPayloads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserRequest {

    private String name;
    private String job;

}
