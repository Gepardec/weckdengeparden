package com.gepardec.wdg.guiRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;
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

        ImageIcon pic = new ImageIcon("gepardec_icon.jpg");
        frame.setIconImage(pic.getImage());
        frame.setContentPane(new RestGUI().Basic);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(475,500);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public RestGUI(){
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                URL url = null;
                try {
                    url = new URL("https://weckdengeparden-57-services.cloud.itandtel.at/challenge/1/answer/");
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

                String jsonInputString = "{\r\n   \"jobId\": \""+jobIdTextField.getText().trim()+"\","+
                        "\r\n   \"firstName\": \""+vornameTextField.getText().trim()+"\"," +
                        "\r\n   \"lastName\": \""+nachnameTextField.getText().trim()+"\"," +
                        "\r\n   \"email\": \""+emailTextField.getText().trim()+"\"," +
                        "\r\n   \"answer\": \""+gitHubTextField.getText().trim()+"\"," +
                        "\r\n   \"source\": \""+sourceTextField.getText().trim()+"\"," +
                        "\r\n   \"messageToGepardec\": \""+messageTextArea.getText().trim()+"\"," +
                        "\r\n   \"otherSource\": \""+otherSourceTextField.getText().trim()+"\"," +
                        "\r\n   \"title\": \""+titelTextField.getText().trim()+"\"," +
                        "\r\n   \"phone\": \""+telefonTextField.getText().trim()+"\"," +
                        "\r\n   \"linkedInLink\": \""+linkedInTextField.getText().trim()+"\"," +
                        "\r\n   \"xingLink\": \""+xingTextField.getText().trim()+"\"," +
                        "\r\n   \"cv\": \""+CVTextField.getText().trim()+"\"\r\n}";

                /*
                Von Philipp Engelbrechtsmüller an alle:  07:10 PM
                    {
                       "jobId": "196500",
                       "firstName": "Test für WIN Projekt",
                       "lastName": "Test",
                       "email": "p324234.awf@gmx.at",
                       "answer": "50",
                       "source": "EMPFEHLUNG",
                       "messageToGepardec": "Uniprojekt",
                       "otherSource": "Uniprojekt",
                       "title": "",
                       "phone": "",
                       "linkedInLink": "",
                       "xingLink": "",
                       "cv": "VGVzdEJld2VyYnVuZw=="
                    }
                     */

                try(OutputStream os = con.getOutputStream()){
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                int code = 0;

                try {
                    code = con.getResponseCode();
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }

                StringBuilder response = new StringBuilder();

                try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")))
                {

                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null)
                    {
                        response.append(responseLine.trim());
                    }
                    JOptionPane.showMessageDialog(null,response.toString());
                } catch (IOException ioException)
                {
                    try {
                        String inputLine;
                        BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
                        while ((inputLine = br.readLine()) != null) {
                            response.append(inputLine);
                        }
                        br.close();
                        System.out.println("Response Body:");
                        System.out.println(response.toString());
                        JOptionPane.showMessageDialog(null,response.toString());
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
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
