import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public class SolarSystemAnimation extends JPanel implements ActionListener {
    private final Timer timer;
    private final Image sunImage;
    private final List<Planet> planets = new ArrayList<>();
    private final int centerX = 400;
    private final int centerY = 300;

    public SolarSystemAnimation() {
        sunImage = new ImageIcon(new ImageIcon("C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\Sun.png")
                .getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)).getImage();

        // Добавление планет с уменьшенными радиусами орбит
        planets.add(new Planet("Mercury.png", 30, 40, 0.05, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));
        planets.add(new Planet("Mars.png", 40, 70, 0.04, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));
        planets.add(new Planet("Earth.png", 50, 100, 0.03, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));
        planets.add(new Planet("Jupiter.png", 70, 140, 0.02, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));
        planets.add(new Planet("Saturn.png", 60, 170, 0.015, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));
        planets.add(new Planet("Uranus.png", 55, 200, 0.01, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));
        planets.add(new Planet("Neptune.png", 60, 230, 0.008, "C:\\Users\\tanymet\\IdeaProjects\\SolarSystem\\src\\"));

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Рисуем Солнце
        int sunWidth = sunImage.getWidth(this);
        int sunHeight = sunImage.getHeight(this);
        g.drawImage(sunImage, centerX - sunWidth / 2, centerY - sunHeight / 2, this);

        // Рисуем планеты
        for (Planet planet : planets) {
            int planetX = centerX + (int) (planet.orbitRadius * Math.cos(planet.angle)) - planet.image.getWidth(this) / 2;
            int planetY = centerY + (int) (planet.orbitRadius * Math.sin(planet.angle)) - planet.image.getHeight(this) / 2;
            g.drawImage(planet.image, planetX, planetY, this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Planet planet : planets) {
            planet.angle += planet.speed;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Солнечная система");
        SolarSystemAnimation animationPanel = new SolarSystemAnimation();

        frame.add(animationPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class Planet {
        Image image;
        int size;
        int orbitRadius;
        double speed;
        double angle = 0;

        Planet(String imageName, int size, int orbitRadius, double speed, String path) {
            this.size = size;
            this.orbitRadius = orbitRadius;
            this.speed = speed;
            this.image = new ImageIcon(new ImageIcon(path + imageName)
                    .getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)).getImage();
        }
    }
}
