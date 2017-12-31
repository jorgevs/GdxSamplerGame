package com.mygdx.game.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import common.SampleFactory;
import common.SampleInfos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;

public class GxdSampleLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;

    // AWT - Abstract Window Toolkit
    // enable us to embed libgdx app/game into java desktop app
    private LwjglAWTCanvas lwjglAWTCanvas;

    private JPanel controlPanel;
    private JList sampleList;


    public GxdSampleLauncher() throws HeadlessException {
        init();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GxdSampleLauncher();
            }
        });
    }


    private void init() {
        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (null != lwjglAWTCanvas) {
                    // stop will call our dispose and stop libgdx application
                    lwjglAWTCanvas.stop();
                }
            }
        });
        setTitle(GxdSampleLauncher.class.getName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(new Dimension(WIDTH, HEIGHT));
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // constraints for scroll panel
        c.gridx = 0; // column
        c.gridy = 0; // row
        c.fill = GridBagConstraints.VERTICAL; // fill vertically
        c.weighty = 1; // weight used when filling empty space

        //sampleList = new JList(new String[]{"com.mygdx.game.InputPollingSample"});
        sampleList = new JList(SampleInfos.getSampleNames().toArray());



        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    launchSelectedSample();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, c);

        // constraints for button
        c.gridx = 0; // column
        c.gridy = 1; // row
        c.fill = GridBagConstraints.HORIZONTAL; // fill horizontally
        c.weighty = 0; // weight used when filling empty space

        JButton launchButton = new JButton("Launch Sample");
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchSelectedSample();
            }
        });

        controlPanel.add(launchButton, c);
    }

    private void launchSelectedSample() {
        String sampleName = (String) sampleList.getSelectedValue();
        if (null == sampleName || sampleName.isEmpty()) {
            System.out.println("Sample name is empty, cannot launch");
            return;
        }

        launchSample(sampleName);
    }

    private void launchSample(String name) {
        System.out.println("Launching: " + name);

        Container container = this.getContentPane();
        if (null != lwjglAWTCanvas) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        /*ApplicationListener sample;
        try {
            // get class object by name
            Class<ApplicationListener> clazz = ClassReflection.forName(name);
            // create new instance of out sample class
            sample = ClassReflection.newInstance(clazz);
        } catch (Exception e) {
            throw new RuntimeException("Cannot create sample: " + name, e);
        }*/

        ApplicationListener sample = SampleFactory.newSample(name);

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        this.pack();
    }
}
