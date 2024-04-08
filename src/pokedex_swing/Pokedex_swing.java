package pokedex_swing;

import javax.swing.SwingUtilities;

/**
 *
 * @author danil
 */
public class Pokedex_swing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new PokedexGUI();
            }
        });
    }

}
