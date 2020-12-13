package tsp;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class TSP extends JFrame {

	private JLabel cityCountLabel = new JLabel();
	private JTextField cityCountField = new JTextField();
	private JLabel widthLabel = new JLabel();
	private JTextField widthField = new JTextField();
	private JLabel heightLabel = new JLabel();
	private JTextField heightField = new JTextField();
	
	private JLabel evolutionCountLabel = new JLabel();
	private JTextField evolutionCountField = new JTextField();
	private JLabel genCountLabel = new JLabel();
	private JTextField genCountField = new JTextField();
	private JLabel maxGenerationLabel = new JLabel();
	private JTextField maxGenerationField = new JTextField();
	private JLabel pCrossoverLabel = new JLabel();
	private JTextField pCrossoverField = new JTextField();
	private JLabel pMutationLabel = new JLabel();
	private JTextField pMutationField = new JTextField();
	private JButton calculateButton = new JButton();
	private JSpinner evolutionSpinner = new JSpinner();
	private JSpinner generationSpinner = new JSpinner();
	private JButton printButton = new JButton();
	private JButton printCities = new JButton();
	private JButton useCities = new JButton();
	private JList outputList = new JList();
	private DefaultListModel listModel = new DefaultListModel();
	private JScrollPane outputScrollPane = new JScrollPane(outputList);
	
	public TSP() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 525);
		setLocationRelativeTo(null);
		setTitle("TSP");
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		
		cityCountLabel.setBounds(10, 10, 100, 30);
		cityCountLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		cityCountLabel.setText("cityCount");
		cp.add(cityCountLabel);
		cityCountField.setBounds(120, 10, 50, 30);
		cityCountField.setFont(new Font("Dialog", Font.BOLD, 10));
		cityCountField.setHorizontalAlignment(SwingConstants.CENTER);
		cityCountField.setText("");
		cp.add(cityCountField);
		
		widthLabel.setBounds(10, 50, 100, 30);
		widthLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		widthLabel.setText("Map-Width");
		cp.add(widthLabel);
		widthField.setBounds(120, 50, 50, 30);
		widthField.setFont(new Font("Dialog", Font.BOLD, 10));
		widthField.setHorizontalAlignment(SwingConstants.CENTER);
		widthField.setText("");
		cp.add(widthField);
		
		heightLabel.setBounds(10, 90, 100, 30);
		heightLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		heightLabel.setText("Map-Height");
		cp.add(heightLabel);
		heightField.setBounds(120, 90, 50, 30);
		heightField.setFont(new Font("Dialog", Font.BOLD, 10));
		heightField.setHorizontalAlignment(SwingConstants.CENTER);
		heightField.setText("");
		cp.add(heightField);
		
		evolutionCountLabel.setBounds(10, 130, 100, 30);
		evolutionCountLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		evolutionCountLabel.setText("evolutionCount");
		cp.add(evolutionCountLabel);
		evolutionCountField.setBounds(120, 130, 50, 30);
		evolutionCountField.setFont(new Font("Dialog", Font.BOLD, 10));
		evolutionCountField.setHorizontalAlignment(SwingConstants.CENTER);
		evolutionCountField.setText("");
		cp.add(evolutionCountField);
		
		genCountLabel.setBounds(10, 170, 100, 30);
		genCountLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		genCountLabel.setText("genCount");
		cp.add(genCountLabel);
		genCountField.setBounds(120, 170, 50, 30);
		genCountField.setFont(new Font("Dialog", Font.BOLD, 10));
		genCountField.setHorizontalAlignment(SwingConstants.CENTER);
		genCountField.setText("");
		cp.add(genCountField);
		
		maxGenerationLabel.setBounds(10, 210, 100, 30);
		maxGenerationLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		maxGenerationLabel.setText("maxGeneration");
		cp.add(maxGenerationLabel);
		maxGenerationField.setBounds(120, 210, 50, 30);
		maxGenerationField.setFont(new Font("Dialog", Font.BOLD, 10));
		maxGenerationField.setHorizontalAlignment(SwingConstants.CENTER);
		maxGenerationField.setText("");
		cp.add(maxGenerationField);
		
		pCrossoverLabel.setBounds(10, 250, 100, 30);
		pCrossoverLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		pCrossoverLabel.setText("Crossover-Chance");
		cp.add(pCrossoverLabel);
		pCrossoverField.setBounds(120, 250, 50, 30);
		pCrossoverField.setFont(new Font("Dialog", Font.BOLD, 10));
		pCrossoverField.setHorizontalAlignment(SwingConstants.CENTER);
		pCrossoverField.setText("");
		cp.add(pCrossoverField);
		
		pMutationLabel.setBounds(10, 290, 100, 30);
		pMutationLabel.setFont(new Font("Dialog", Font.BOLD, 10));
		pMutationLabel.setText("Mutation-Chance");
		cp.add(pMutationLabel);
		pMutationField.setBounds(120, 290, 50, 30);
		pMutationField.setFont(new Font("Dialog", Font.BOLD, 10));
		pMutationField.setHorizontalAlignment(SwingConstants.CENTER);
		pMutationField.setText("");
		cp.add(pMutationField);
		
		calculateButton.setBounds(10, 330, 160, 30);
		calculateButton.setFont(new Font("Dialog", Font.BOLD, 12));
		calculateButton.setText("calculateEvolution");
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				calculateEvolution(evt);
			}
		});
		cp.add(calculateButton);
		
		evolutionSpinner.setBounds(10, 370, 75, 30);
		evolutionSpinner.setValue(1);
		evolutionSpinner.setEnabled(false);
		cp.add(evolutionSpinner);
		
		generationSpinner.setBounds(95, 370, 75, 30);
		generationSpinner.setValue(1);
		generationSpinner.setEnabled(false);
		cp.add(generationSpinner);
		
		printButton.setBounds(10, 410, 75, 30);
		printButton.setFont(new Font("Dialog", Font.BOLD, 12));
		printButton.setText("Show");
		printButton.setEnabled(false);
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				printGeneration(evt);
			}
		});
		cp.add(printButton);
		
		printCities.setBounds(95, 410, 75, 30);
		printCities.setFont(new Font("Dialog", Font.BOLD, 12));
		printCities.setText("Cities");
		printCities.setEnabled(false);
		printCities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				printCities(evt);
			}
		});
		cp.add(printCities);
		
		useCities.setBounds(10, 450, 160, 30);
		useCities.setFont(new Font("Dialog", Font.BOLD, 12));
		useCities.setText("Reuse Cities");
		useCities.setEnabled(false);
		useCities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				useSameCities(evt);
			}
		});
		cp.add(useCities);
		
		outputList.setModel(listModel);
		outputList.setFont(new Font("Dialog", Font.BOLD, 10));
		outputScrollPane.setBounds(180, 10, 300, 470);
		cp.add(outputScrollPane);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TSP();
	}
    
    public static final long serialVersionUID = 1L;

	private Gen[][][] evolutionCollection;
	private FitnessCalculator fitnessCalculator;
	
	public void calculateEvolution(ActionEvent evt) {
		fitnessCalculator = new RandomCities(Integer.parseInt(cityCountField.getText()), Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText())).getFittingFitnessCalculator();
		Evolution thisEvolution = new Evolution(Integer.parseInt(cityCountField.getText()), fitnessCalculator, Integer.parseInt(genCountField.getText()), Integer.parseInt(maxGenerationField.getText()), Double.parseDouble(pCrossoverField.getText()), Double.parseDouble(pMutationField.getText()));
		evolutionCollection = thisEvolution.getEvolutions(Integer.parseInt(evolutionCountField.getText()));
		
		evolutionSpinner.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(evolutionCountField.getText()), 1));
		evolutionSpinner.setEnabled(true);
		generationSpinner.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(maxGenerationField.getText()), 1));
		generationSpinner.setEnabled(true);
		printButton.setEnabled(true);
		printCities.setEnabled(true);
		useCities.setEnabled(true);
	}
	
	public void useSameCities(ActionEvent evt) {
		Evolution thisEvolution = new Evolution(Integer.parseInt(cityCountField.getText()), fitnessCalculator, Integer.parseInt(genCountField.getText()), Integer.parseInt(maxGenerationField.getText()), Double.parseDouble(pCrossoverField.getText()), Double.parseDouble(pMutationField.getText()));
		evolutionCollection = thisEvolution.getEvolutions(Integer.parseInt(evolutionCountField.getText()));
		
		evolutionSpinner.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(evolutionCountField.getText()), 1));
		generationSpinner.setModel(new SpinnerNumberModel(1, 1, Integer.parseInt(maxGenerationField.getText()), 1));
	}
	
	public void printGeneration(ActionEvent evt) {
		int evolutionSelected = (int)evolutionSpinner.getValue();
		int generationSelected = (int)generationSpinner.getValue();
		listModel.removeAllElements();
		listModel.addElement("--==Evolution " + evolutionSelected + " - Generation " + generationSelected + "==--");
		listModel.addElement("");
		for (int i = 0; i < evolutionCollection[evolutionSelected - 1][generationSelected - 1].length; i++) {
			listModel.addElement(Arrays.toString(evolutionCollection[evolutionSelected-1][generationSelected-1][i].getSolution()) + " - " + evolutionCollection[evolutionSelected-1][generationSelected-1][i].getFitnessValue());
		}
	}
	
	public void printCities(ActionEvent evt) {
		listModel.removeAllElements();
		listModel.addElement("-=Cities=-");
		listModel.addElement("");
		City[] cities = fitnessCalculator.getCities();
		for (int i = 0; i < cities.length; i++) {
			listModel.addElement("City " + i + ": (" + cities[i].getXPos() + "|" + cities[i].getYPos() + ")");
		}
	}
	
}
