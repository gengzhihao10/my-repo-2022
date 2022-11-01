package my.repo.api.user.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.repo.api.base.BasePageInput;

@Getter
@Setter
@AllArgsConstructor
public class RestUserPageQuery extends BasePageInput {



    @Override
    public void check() {

    }
}
