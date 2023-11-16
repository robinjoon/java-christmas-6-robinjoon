package christmas.domain.menu;

import java.util.List;

public final class SelectedMenusDTO {
    private final List<SelectedMenuDTO> selectedMenuDTOS;

    SelectedMenusDTO(List<SelectedMenuDTO> selectedMenuDTOS) {
        this.selectedMenuDTOS = selectedMenuDTOS;
    }

    public List<SelectedMenuDTO> selectedMenuDTOS() {
        return selectedMenuDTOS;
    }
}
