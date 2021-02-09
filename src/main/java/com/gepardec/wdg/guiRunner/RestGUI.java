package com.gepardec.wdg.guiRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gepardec.wdg.challenge.model.ConstraintViolationResponse;
import com.gepardec.wdg.client.personio.Source;
import org.jboss.logging.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RestGUI extends JFrame {
    private JPanel basic;
    private JLabel jobIdLabel;
    private JLabel vornameLabel;
    private JLabel nachnameLabel;
    private JLabel emailLabel;
    private JLabel gitHubLinkLabel;
    private JLabel sourceLabel;
    private JLabel messageToGepardecLabel;
    private JLabel titelLabel;
    private JLabel otherSourceLabel;
    private JLabel telefonLabel;
    private JLabel linkedInLabel;
    private JTextField jobIdTextField;
    private JTextField vornameTextField;
    private JTextField nachnameTextField;
    private JTextField emailTextField;
    private JTextField gitHubTextField;
    private JTextField sourceTextField;
    private JTextField titelTextField;
    private JTextField otherSourceTextField;
    private JTextField telefonTextField;
    private JTextField linkedInTextField;
    private JTextField xingTextField;
    private JTextField cvTextField;
    private JLabel cvLabel;
    private JLabel xingLabel;
    private JButton submitButton;
    private JTextArea messageTextArea;
    private JComboBox sourceComboBox;
    final static String utf8 = "utf-8";
    final static String WDG_ITANDTEL = "https://weckdengeparden-57-services.cloud.itandtel.at";

    private static final org.jboss.logging.Logger log = Logger.getLogger(RestGUI.class.getName());

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bewerbung Formular");
        ImageIcon pic = new ImageIcon("gepardec_icon.jpg");
        frame.setIconImage(pic.getImage());
        frame.setContentPane(new RestGUI().basic);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(475, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public RestGUI() {
        sourceComboBox.setModel(new DefaultComboBoxModel(Source.values()));
        submitButton.addActionListener(e -> {


                URL url;
                try {
                    url = new URL(WDG_ITANDTEL+"/challenge/2/url/");
                } catch (MalformedURLException malformedURLException) {
                    log.error("Error while assign the URL." + malformedURLException.getMessage(), malformedURLException);
                    JOptionPane.showMessageDialog(null, "Es ist ein Fehler beim Verbindungsaufbau aufgetreten\n"+malformedURLException.getMessage());
                    return;
                }


            HttpURLConnection con;
            try {
                con = (HttpURLConnection) url.openConnection();
            } catch (IOException ioException) {
                log.error("Error while opening the HttpURLConnection." + ioException.getMessage(), ioException);
                JOptionPane.showMessageDialog(null, "Es ist ein Fehler beim Verbindungsaufbau aufgetreten\n"+ioException.getMessage());
                return;
            }

            try {
                con.setRequestMethod("POST");
            } catch (ProtocolException protocolException) {
                log.error("Error while setting the request Method of the HttpURLConnection to 'POST'." + protocolException.getMessage(), protocolException);
                return;
            }

            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonInputString = "{\r\n   \"jobId\": \"" + jobIdTextField.getText().trim() + "\"," +
                    "\r\n   \"firstName\": \"" + vornameTextField.getText().trim() + "\"," +
                    "\r\n   \"lastName\": \"" + nachnameTextField.getText().trim() + "\"," +
                    "\r\n   \"email\": \"" + emailTextField.getText().trim() + "\"," +
                    "\r\n   \"url\": \"" + gitHubTextField.getText().trim() + "\"," +
                    "\r\n   \"source\": \"" + sourceComboBox.getSelectedItem() + "\"," +
                    "\r\n   \"messageToGepardec\": \"" + messageTextArea.getText().trim() + "\"," +
                    "\r\n   \"otherSource\": \"" + otherSourceTextField.getText().trim() + "\"," +
                    "\r\n   \"title\": \"" + titelTextField.getText().trim() + "\"," +
                    "\r\n   \"phone\": \"" + telefonTextField.getText().trim() + "\"," +
                    "\r\n   \"linkedInLink\": \"" + linkedInTextField.getText().trim() + "\"," +
                    "\r\n   \"xingLink\": \"" + xingTextField.getText().trim() + "\"," +
                    "\r\n   \"cv\": \"" + cvTextField.getText().trim() + "\"\r\n}";

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(utf8);
                os.write(input, 0, input.length);
            } catch (IOException ioException) {
                log.error("Error while trying to get the OutputStream from the HttpURLConnection." + ioException.getMessage(), ioException);
                return;
            }

            String response = "";

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), utf8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response = response + responseLine.trim();
                }
                JOptionPane.showMessageDialog(null, response.toString());
            } catch (IOException ioException) {
                String errorResponse = "";
                try {
                    String inputLine;
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), utf8));
                    while ((inputLine = br.readLine()) != null) {
                        response = response + inputLine;
                    }
                    br.close();
                    ObjectMapper objectMapper = new ObjectMapper();
                    ConstraintViolationResponse constraintViolationResponse = objectMapper.readValue(response, ConstraintViolationResponse.class);
                    for (String message : constraintViolationResponse.getViolations()) {
                        errorResponse = errorResponse + message + "\n";
                    }
                    JOptionPane.showMessageDialog(null, errorResponse);
                } catch (UnsupportedEncodingException unsupportedEncodingException) {
                    log.error("Error while trying to get the ErrorStream." + unsupportedEncodingException.getMessage(), unsupportedEncodingException);
                    return;
                } catch (IOException exception) {
                    log.error("Error while trying to get the ErrorStream." + exception.getMessage(), exception);
                    return;
                }
            }
        });

        jobIdTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (jobIdTextField.getText().equalsIgnoreCase("<JobId der Website>")) {
                    jobIdTextField.setText("");
                }
            }
        });

        vornameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (vornameTextField.getText().equalsIgnoreCase("<Vorname>")) {
                    vornameTextField.setText("");
                }
            }
        });

        nachnameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (nachnameTextField.getText().equalsIgnoreCase("<Nachname>")) {
                    nachnameTextField.setText("");
                }
            }
        });

        emailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (emailTextField.getText().equalsIgnoreCase("<E-Mail Adresse>")) {
                    emailTextField.setText("");
                }
            }
        });

        gitHubTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (gitHubTextField.getText().equalsIgnoreCase("<Github-Link>")) {
                    gitHubTextField.setText("");
                }
            }
        });

        titelTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (titelTextField.getText().equalsIgnoreCase("<Titel vorangestellt>")) {
                    titelTextField.setText("");
                }
            }
        });

        messageTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (messageTextArea.getText().equalsIgnoreCase("<Was kann dein Beitrag zur Umsetzung der Vision von gepardec sein?>")) {
                    messageTextArea.setText("");
                }
            }
        });

        otherSourceTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (otherSourceTextField.getText().equalsIgnoreCase("<Wenn Source auf EMPFEHLUNG oder SONSTIGES gesetzt ist, dann hier Details angeben>")) {
                    otherSourceTextField.setText("");
                }
            }
        });

        telefonTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (telefonTextField.getText().equalsIgnoreCase("<Telefonnummer>")) {
                    telefonTextField.setText("");
                }
            }
        });

        linkedInTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (linkedInTextField.getText().equalsIgnoreCase("<LinkedIn Profil URL>")) {
                    linkedInTextField.setText("");
                }
            }
        });

        xingTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (xingTextField.getText().equalsIgnoreCase("<Xing Profil URL>")) {
                    xingTextField.setText("");
                }
            }
        });

        cvTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                if (cvTextField.getText().equalsIgnoreCase("<Lebenslauf in Base64 encodiert>")) {
                    cvTextField.setText("");
                }
            }
        });
    }

}
