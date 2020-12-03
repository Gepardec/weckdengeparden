package com.gepardec.wdg.guiRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RestGUI extends JFrame{
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
    private JTextField titelTextField;
    private JTextField otherSourceTextField;
    private JTextField telefonTextField;
    private JTextField linkedInTextField;
    private JTextField xingTextField;
    private JTextField CVTextField;
    private JLabel CVLabel;
    private JLabel xingLabel;
    private JButton submitButton;
    private JTextArea messageTextArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bewerbung Formular");

        /*JLabel background;
        ImageIcon img = new ImageIcon("gepardec_icon.jpg");
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,330,500);
        frame.add(background);*/

        ImageIcon pic = new ImageIcon("gepardec_icon.jpg");
        frame.setIconImage(pic.getImage());
        frame.setContentPane(new RestGUI().Basic);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(400,500);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public RestGUI(){
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

                String jsonInputString = "{\r\n   \"jobId\": \""+jobIdTextField.getText()+"\","+
                        "\r\n   \"firstName\": \""+vornameTextField.getText()+"\"," +
                        "\r\n   \"lastName\": \""+nachnameTextField.getText()+"\"," +
                        "\r\n   \"email\": \""+emailTextField.getText()+"\"," +
                        "\r\n   \"answer\": \""+gitHubTextField.getText()+"\"," +
                        "\r\n   \"source\": \""+sourceTextField.getText()+"\"," +
                        "\r\n   \"messageToGepardec\": \""+messageTextArea.getText()+"\"," +
                        "\r\n   \"otherSource\": \""+otherSourceTextField.getText()+"\"," +
                        "\r\n   \"title\": \""+titelTextField.getText()+"\"," +
                        "\r\n   \"phone\": \""+telefonTextField.getText()+"\"," +
                        "\r\n   \"linkedInLink\": \""+linkedInTextField.getText()+"\"," +
                        "\r\n   \"xingLink\": \""+xingTextField.getText()+"\"," +
                        "\r\n   \"cv\": \""+CVTextField.getText()+"\"\r\n}";

                System.out.println(jsonInputString);

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

        jobIdTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(jobIdTextField.getText().equalsIgnoreCase("<JobId der Website>"))
                {
                    jobIdTextField.setText("");
                }
            }
        });

        vornameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(vornameTextField.getText().equalsIgnoreCase("<Vorname>"))
                {
                    vornameTextField.setText("");
                }
            }
        });

        nachnameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(nachnameTextField.getText().equalsIgnoreCase("<Nachname>"))
                {
                    nachnameTextField.setText("");
                }
            }
        });

        emailTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(emailTextField.getText().equalsIgnoreCase("<E-Mail Adresse>"))
                {
                    emailTextField.setText("");
                }
            }
        });

        gitHubTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(gitHubTextField.getText().equalsIgnoreCase("<Github-Link>"))
                {
                    gitHubTextField.setText("");
                }
            }
        });

        sourceTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(sourceTextField.getText().equalsIgnoreCase("<Woher hast du von uns erfahren; siehe Source.java>"))
                {
                    sourceTextField.setText("");
                }
            }
        });

        titelTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(titelTextField.getText().equalsIgnoreCase("<Titel vorangestellt>"))
                {
                    titelTextField.setText("");
                }
            }
        });

        messageTextArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(messageTextArea.getText().equalsIgnoreCase("<Was kann dein Beitrag zur Umsetzung der Vision von gepardec sein?>"))
                {
                    messageTextArea.setText("");
                }
            }
        });

        otherSourceTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(otherSourceTextField.getText().equalsIgnoreCase("<Wenn Source auf EMPFEHLUNG oder SONSTIGES gesetzt ist, dann hier Details angeben>"))
                {
                    otherSourceTextField.setText("");
                }
            }
        });

        telefonTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(telefonTextField.getText().equalsIgnoreCase("<Telefonnummer>"))
                {
                    telefonTextField.setText("");
                }
            }
        });

        linkedInTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(linkedInTextField.getText().equalsIgnoreCase("<LinkedIn Profil URL>"))
                {
                    linkedInTextField.setText("");
                }
            }
        });

        xingTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(xingTextField.getText().equalsIgnoreCase("<Xing Profil URL>"))
                {
                    xingTextField.setText("");
                }
            }
        });

        CVTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(CVTextField.getText().equalsIgnoreCase("<Lebenslauf in Base64 encodiert>"))
                {
                    CVTextField.setText("");
                }
            }
        });
    }
}
