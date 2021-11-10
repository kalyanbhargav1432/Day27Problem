package addressbook;

import java.util.Comparator;

public enum SortOption {
    CITY(Comparator.comparing(PersonDetail::getCity)),
    STATE(Comparator.comparing(PersonDetail::getState)),
    ZIP(Comparator.comparing(PersonDetail::getZip));

    public final Comparator<? super PersonDetail> comparator;

    SortOption(Comparator<? super PersonDetail> comparator) {
        this.comparator = comparator;
    }
}