package ui;

import model.Movie;
import model.MovieGenre;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
 * The panel in which the movie menu is displayed
 * CITATION: Oracle Java Documentation: using swing components demos
 */


public class MoviePanel extends JPanel {
    private MovieList ml;
    private JList list;
    private DefaultListModel listmodel;
    private Movie m2;
    private Movie m1;
    private ImageIcon image;

    private static final String JSON_STORE = "./data/movielist.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private static final String removeString = "Remove Movie";
    private static final String addString = "Add Movie";
    private static final String filterString = "Animated movies only";
    private static final String loadString = "Load workspace";
    private static final String saveString = "Save workspace";

    private JButton removeButton;
    private JButton filterButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton addButton;
    private JTextField movieName;
    private JTextField movieGenre;
    private JTextField movieDuration;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MoviePanel() {
        super(new BorderLayout());
        listmodel = new DefaultListModel();
        makeList();
        list = new JList(listmodel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddListener(addButton));

        movieName = new JTextField(10);
        movieName.addActionListener(addListener);

        movieGenre = new JTextField(10);
        movieGenre.addActionListener(addListener);

        movieDuration = new JTextField(10);
        movieDuration.addActionListener(addListener);

        JButton filterButton = new JButton(filterString);
        filterButton.setActionCommand(filterString);
        filterButton.addActionListener(new FilterListener());

        JButton loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());

        JButton saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(removeButton);
        buttonPane.add(addButton);
        buttonPane.add(movieName);
        buttonPane.add(movieGenre);
        buttonPane.add(movieDuration);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonPane.add(filterButton);
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // EFFECTS: initializes and shows the list
    public void makeList() {
        ml = new MovieList("Aurora's list");
        m1 = new Movie("Spirited Away", MovieGenre.ANIME, 200);
        m2 = new Movie("Perfect Blue", MovieGenre.HORROR, 100);
        ml.addWatchedMovie(m1);
        ml.addToWatchMovie(m2);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        for (Movie m : ml.getToWatchList()) {
            listmodel.addElement(m.getName());
        }

        for (Movie m : ml.getAlreadyWatchedList()) {
            listmodel.addElement(m.getName());
        }
    }

    // EFFECTS: implements removeListener
    // MODIFIES: this, towatchlist and watched list
    class RemoveListener implements ActionListener {

        @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listmodel.remove(index);

            /*
            Object o = listmodel.getElementAt(index);
            String name = o.toString();

            for (Movie m : ml.getToWatchList()) {
                if (m.getName() == name) {
                    ml.removeToWatchMovie(m);
                }
            }
            for (Movie m : ml.getAlreadyWatchedList()) {
                if (m.getName() == name) {
                    ml.removeWatchedMovie(m);
                }
            }

             */

            int size = listmodel.getSize();

            if (size == 0) { //cant remove anything
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listmodel.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // EFFECTS: implements AddListener: adds inputted movie into towatch list
    // MODIFIES: this, towatchlist
    class AddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            String name = movieName.getText();
            String genreinput = movieGenre.getText();
            String durationinput = movieDuration.getText();
            int duration = Integer.parseInt(durationinput);
            MovieGenre genre = MovieGenre.valueOf(genreinput);

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                movieName.requestFocusInWindow();
                movieName.selectAll();
                return;
            }
            listmodel.addElement(movieName.getText());

            Movie m = new Movie(name, genre, duration);
            ml.addToWatchMovie(m);

            //Reset the text field.
            movieName.requestFocusInWindow();
            movieName.setText("");
            movieGenre.setText("");
            movieDuration.setText("");
        }
    }

    // EFFECTS: implements FilterListener
    // MODIFIES: this
    class FilterListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (Movie m : ml.getToWatchList()) {
                if (m.getGenre() != MovieGenre.ANIME) {
                    listmodel.removeElement(m.getName());
                }
            }

            for (Movie m : ml.getAlreadyWatchedList()) {
                if (m.getGenre() != MovieGenre.ANIME) {
                    listmodel.removeElement(m.getName());
                }
            }
            loadImage();
        }
    }

    // EFFECTS: implements SaveListener
    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                resetList();
                jsonWriter.open();
                jsonWriter.write(ml);
                jsonWriter.close();
            } catch (IOException ie) {
                System.out.println("null");
            }
        }
    }

    class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                resetList();
                ml = jsonReader.read();
                for (Movie m : ml.getAlreadyWatchedList()) {
                    listmodel.addElement(m.getName());
                }
                for (Movie m : ml.getToWatchList()) {
                    listmodel.addElement(m.getName());
                }
            } catch (IOException ie) {
                System.out.println("null");
            }
        }
    }

    public boolean alreadyInList(String name) {
        boolean b;
        b = false;
        for (Movie m : ml.getAlreadyWatchedList()) {
            if (m.getName() == name) {
                b = true;
            }
        }
        for (Movie m : ml.getToWatchList()) {
            if (m.getName() == name) {
                b = true;
            }
        }
        return b;
    }

    // EFFECTS: loads image into new JFrame
    public void loadImage() {
        JFrame imageframe = new JFrame();
        String sep = System.getProperty("file.separator");
        image = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "killua.jpeg");

        JLabel lbl = new JLabel(image);
        imageframe.add(lbl); //puts label inside the jframe

        imageframe.setSize(image.getIconWidth(), image.getIconHeight());
        imageframe.setVisible(true);
    }

    // EFFECTS: resets lists
    public void resetList() {
        for (Movie m : ml.getAlreadyWatchedList()) {
            listmodel.removeElement(m.getName());
        }
        for (Movie m : ml.getToWatchList()) {
            listmodel.removeElement(m.getName());
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    // EFFECTS: create and display the GUI
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MoviePanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new MoviePanel();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: runs application GUI
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
