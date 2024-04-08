package pokedex_swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author danil
 */
public class PokedexGUI extends JFrame {
    
    private JTextField textField;
    private JTextArea textArea;
    
    public PokedexGUI() {
        super("Pokedex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pokemonName = textField.getText();
                fetchPokemonData(pokemonName);
                
            }
        });
        textArea = new JTextArea();
        textArea.setEditable(false);
        
        add(textField, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        
        setSize(400, 300);
        setVisible(true);
    }
    
    private void fetchPokemonData(String pokemonName) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonName.toLowerCase();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responseCode = conn.getResponseCode();            //Log
            System.out.println("Response Code: " + responseCode); //Log

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                
                textArea.setText(response.toString());
            } else {
                textArea.setText("Pokemon não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
