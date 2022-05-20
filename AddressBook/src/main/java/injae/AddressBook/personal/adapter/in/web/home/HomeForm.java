package injae.AddressBook.personal.adapter.in.web.home;

import injae.AddressBook.personal.domain.Personal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeForm {

    private boolean isArrangeChecked;
    private List<Personal> personals;

}
