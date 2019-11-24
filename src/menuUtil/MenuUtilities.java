package menuUtil;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuUtilities {

    public static MenuBar prepareMenu() {
        MenuBar menuBar = new MenuBar();

        Menu plikMenu = new Menu("Plik");
        MenuItem plikZamknij = new MenuItem("Zamknij");

        plikMenu.getItems().add(plikZamknij);
        menuBar.getMenus().add(plikMenu);

        return menuBar;
    }
}
