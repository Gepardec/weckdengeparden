package com.gepardec.wdg.guiRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RestGUI {
    private JPanel Basic;
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
    private JTextField messageToGepardecTextField;
    private JTextField titelTextField;
    private JTextField otherSourceTextField;
    private JTextField telefonTextField;
    private JTextField linkedInTextField;
    private JTextField xingTextField;
    private JTextField CVTextField;
    private JLabel CVLabel;
    private JLabel xingLabel;
    private JButton submitButton;

    public RestGUI() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                URL url = null;
                try {
                    url = new URL("https://weckdengeparden-57-services.cloud.itandtel.at/challenge/1/answer");
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }

                HttpURLConnection con = null;
                try {
                    con = (HttpURLConnection)url.openConnection();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    con.setRequestMethod("POST");
                } catch (ProtocolException protocolException) {
                    protocolException.printStackTrace();
                }

                con.setRequestProperty("Content-Type", "application/json; utf-8");
                con.setRequestProperty("Accept", "application/json");

                con.setDoOutput(true);

                //JSON String need to be constructed for the specific resource.
                //We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
                /* Funktioniert nicht =>
                String jsonInputStringGetText = "{\r\n   \"jobId\": " +
                        jobIDTextField.getText()+"," +
                        "\r\n   \"firstName\": " +
                        vornameTextField.getText()+"," +
                        "\r\n   \"lastName\": " +
                        nachnameTextField.getText()+"," +
                        "\r\n   \"email\": " +
                        emailTextField.getText()+"," +
                        "\r\n   \"answer\": " +
                        gitHubTextField.getText()+"," +
                        "\r\n   \"source\": " +
                        sourceTextField.getText()+"," +
                        "\r\n   \"messageToGepardec\": " +
                        messageToGepardecTextField.getText()+"," +
                        "\r\n   \"otherSource\": " +
                        otherSourceTextField.getText()+"," +
                        "\r\n   \"title\": " +
                        titelTextField.getText()+"," +
                        "\r\n   \"phone\": " +
                        telefonTextField.getText()+"," +
                        "\r\n   \"linkedInLink\": " +
                        linkedInTextField.getText()+"," +
                        "\r\n   \"xingLink\": " +
                        xingTextField.getText()+"," +
                        "\r\n   \"cv\": " +
                        CVTextField.getText()+"\r\n}";
                    */
                String jsonInputString = "{\r\n   \"jobId\": \"196500\","+
                        "\r\n   \"firstName\": \"Test für WIN Projekt\"," +
                        "\r\n   \"lastName\": \"Test\"," +
                        "\r\n   \"email\": \"p.testasdf@gmx.at\"," +
                        "\r\n   \"answer\": \"50\"," +
                        "\r\n   \"source\": \"EMPFEHLUNG\"," +
                        "\r\n   \"messageToGepardec\": \"Uniprojekt\"," +
                        "\r\n   \"otherSource\": \"Uniprojekt\"," +
                        "\r\n   \"title\": \"\",\r\n   \"phone\": \"\"," +
                        "\r\n   \"linkedInLink\": \"\"," +
                        "\r\n   \"xingLink\": \"\"," +
                        "\r\n   \"cv\": \"VGVzdEJld2VyYnVuZw==\"\r\n}";

                try(OutputStream os = con.getOutputStream()){
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                int code = 0;
                try {
                    code = con.getResponseCode();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println(code);



                try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println(response.toString());
                    JOptionPane.showMessageDialog(null,response.toString());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null,"Fehler");
                    ioException.printStackTrace();
                }

            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bewerbung");
        frame.setContentPane(new RestGUI().Basic);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
