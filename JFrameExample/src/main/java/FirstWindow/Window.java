package FirstWindow;

import com.github.fedy2.weather.YahooWeatherService;
import com.github.fedy2.weather.data.Channel;
import com.github.fedy2.weather.data.Forecast;
import com.github.fedy2.weather.data.unit.DegreeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Window extends JFrame { //Наследуя от JFrame мы получаем всю функциональность окна

    public Window() {
        super("My FirstWindow.Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JMenuBar bar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        menuFile.addSeparator();

        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuFile.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuFile.add(itemExit);

        JMenu aboutMenu = new JMenu("About");

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Create by Saosha");
            }
        });

        aboutMenu.add(aboutItem);

        bar.add(menuFile);
        bar.add(aboutMenu);

        JPanel panelRight = new JPanel();
        panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));

        Button btnRed = new Button("Show red/default");
        btnRed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (getContentPane().getBackground() != Color.RED)
                    getContentPane().setBackground(Color.RED);
                else
                    getContentPane().setBackground(null);
            }
        });

        Button btnTime = new Button("Show time");
        btnTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
            }
        });


        Button btnWeather = new Button("Show some weather");

        final JTextField textField = new JTextField();
        btnWeather
                .addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String cityName = JOptionPane.showInputDialog(
                                textField,
                                "Введите город",
                                "Weather",
                                JOptionPane.PLAIN_MESSAGE);

                        if (cityName != null) {
                            getWeatherWithYahoo(cityName);
//                            String weather = getWeather(cityName);
//                            if (weather == null)
//                                JOptionPane.showMessageDialog(null, "Wrong city name");
//                            else JOptionPane.showMessageDialog(null, weather);

                        }

                    }
                });


        panelRight.add(btnRed);
        panelRight.add(btnTime);
        panelRight.add(btnWeather);
        getContentPane().add(panelRight, BorderLayout.EAST);

        setJMenuBar(bar);
        setBounds(300, 300, 500, 500);

        setVisible(true);

    }

    private static String getWeather(String city) {

        DefaultHttpClient httpclient = new DefaultHttpClient();
        String status = "";
        try {
            city = city.replaceAll("\\s", "").toLowerCase();

            String request = "/data/2.5/weather?appid=d861a21c649202d007ad36a608accf0d&units=metric&q=" + city;
            HttpHost target = new HttpHost("api.openweathermap.org", 80, "http");
            HttpGet getRequest = new HttpGet(request);

            HttpResponse httpResponse = httpclient.execute(target, getRequest);
            HttpEntity entity = httpResponse.getEntity();
            status = httpResponse.getStatusLine().toString();


            if (entity != null) {
                JSONObject jsonObj = new JSONObject(EntityUtils.toString(entity));
                String result = jsonObj.getString("name") + "\n";

                result += "Description: " +
                        jsonObj.getJSONArray("weather").getJSONObject(0).getString("description") + "\n";
                JSONObject jsonObject = jsonObj.getJSONObject("main");
                result += "Current temperature: " + jsonObject.getInt("temp") + "°C" + "\n";
                result += "Humidity: " + jsonObject.getInt("humidity") + "%" + "\n";
                result += "Pressure: " + jsonObject.getInt("pressure") + "mmhg" + "\n";
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!status.equals(""))
                return null;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return "Couldn't connect to the server";
    }

    private static void getWeatherWithYahoo(String cityName) {
        try {
            YahooWeatherService service = new YahooWeatherService();
            Channel channel = null;
            String city = null;
            if (cityName.length() > 0) {
                channel = service.getForecastForLocation(cityName + ", Russia", DegreeUnit.CELSIUS).all().iterator().next();
                if (channel.getLocation().getCountry().contains("United States")) {
                    channel = service.getForecastForLocation("Kazan, Russia", DegreeUnit.CELSIUS).all().iterator().next();
                }
            } else {
                channel = service.getForecastForLocation("Kazan, Russia", DegreeUnit.CELSIUS).all().iterator().next();
            }

            city = channel.getLocation().getCity();
            Forecast fore = channel.getItem().getForecasts().iterator().next();

            JOptionPane.showMessageDialog(
                    null,
                    "Weather for " + city + "\n" +
                            "Today is " + fore.getDay() + "\n" +
                            "Higher temp: " + fore.getHigh() + " °C\n" +
                            "Lower temp: " + fore.getLow() + " °C\n" +
                            fore.getText() + "\n"

            );

        } catch (JAXBException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
