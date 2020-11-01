package simulator.launcher;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import simulator.control.Controller;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.Factory;
import simulator.factories.MostCrowdedStrategyBuilder;
import simulator.factories.MoveAllStrategyBuilder;
import simulator.factories.MoveFirstStrategyBuilder;
import simulator.factories.NewCityRoadEventBuilder;
import simulator.factories.NewInterCityRoadEventBuilder;
import simulator.factories.NewJunctionEventBuilder;
//import simulator.factories.NewRoadEventBuilder;
import simulator.factories.NewVehicleEventBuilder;
import simulator.factories.RoundRobinStrategyBuilder;
import simulator.factories.SetContClassEventBuilder;
import simulator.factories.SetWeatherEventBuilder;
import simulator.model.DequeingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
//import simulator.model.NewCityRoadEvent;
import simulator.model.TrafficSimulator;
import simulator.view.MainWindow;
import simulator.view.TrafficSimObserver;

public class Main {

	private final static Integer _timeLimitDefaultValue = 10;
	private static Integer ticks;
	private static String _inFile = null;
	private static String _outFile = null;
	private static String _mode = null;
	private static Factory<Event> _eventsFactory = null;
	//private static Controller controller;
	//private static TrafficSimulator trs;

	
	private static void parseArgs(String[] args) {

		// define the valid command line options
		Options cmdLineOptions = buildOptions();
		
		// parse the command line as provided in args
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseModeOption(line);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			if(_mode.equalsIgnoreCase("console")) {
				parseOutFileOption(line);
			}
			
			ticks = parseTicksOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}
	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(
				Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("ticks").hasArg().desc("Ticks to the simulator�s main loop (default value is 10)").build());
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("Select application mode").build());
		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line){
		if(line.hasOption("i")) {
			_inFile = line.getOptionValue("i");
		}
	}

	private static void parseOutFileOption(CommandLine line){
			_outFile = line.getOptionValue("o");
	}
	
	private static void parseModeOption(CommandLine line){
		if(line.hasOption("m")) {
			_mode = line.getOptionValue("m");
		}
		else {
			_mode = "gui";
		}
}
	
	private static int parseTicksOption(CommandLine line) throws ParseException {
		if(line.hasOption("t")) {
			Integer.parseInt(line.getOptionValue("t"));
			return Integer.parseInt(line.getOptionValue("t"));
		}
		else {
			return _timeLimitDefaultValue;
		}
	}

	private static void initFactories() {

		// TODO complete this method to initialize _eventsFactory
		// TODO complete this method to initialize _eventsFactory
		List<Builder<LightSwitchingStrategy>> lsbs = new ArrayList<>();
		lsbs.add( new RoundRobinStrategyBuilder());
		lsbs.add( new MostCrowdedStrategyBuilder());
		Factory<LightSwitchingStrategy> lssFactory = new BuilderBasedFactory
		<>(lsbs);
		
		List<Builder<DequeingStrategy>> dqbs = new ArrayList<>();
		dqbs.add(new MoveFirstStrategyBuilder());
		dqbs.add(new MoveAllStrategyBuilder());
		Factory<DequeingStrategy> dqsFactory = new BuilderBasedFactory<>(
		dqbs);
		
		List<Builder<Event>> ebs = new ArrayList<>();		
		ebs.add(new NewJunctionEventBuilder(lssFactory, dqsFactory));
		ebs.add(new NewCityRoadEventBuilder());
		ebs.add(new NewInterCityRoadEventBuilder());
		//ebs.add(new NewRoadEventBuilder());
		ebs.add(new NewVehicleEventBuilder());
		ebs.add(new SetContClassEventBuilder());
		ebs.add(new SetWeatherEventBuilder());
		_eventsFactory = new BuilderBasedFactory<>(ebs);
	}

	private static void startBatchMode() throws Exception {
		// TODO complete this method to start the simulation

		InputStream in = new FileInputStream(_inFile);
		OutputStream out;
		if(_outFile == null)
			 out = System.out;
		else
			 out = new FileOutputStream(_outFile);
		
		
		TrafficSimulator trs = new TrafficSimulator();
		Controller controller = new Controller(trs, _eventsFactory);
		if(_inFile != null)controller.loadEvents(in);
		controller.run(ticks, out);
		
		in.close();
	}

	private static void start(String[] args) throws Exception {
		initFactories();
		parseArgs(args);
		
		if(_mode.equalsIgnoreCase("console")) {
			startBatchMode();
		}
		else {
			startGUIMode();
		}
	
	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void startGUIMode() throws Exception {
		TrafficSimulator t = new TrafficSimulator();
		//Controller ctrl = null;
		InputStream in = null;
		
		if(_inFile != null) {
			in = new FileInputStream(_inFile);
		}
		
		//t = new TrafficSimulator();
		final Controller ctrl = new Controller(t, _eventsFactory);
		if(_inFile != null) ctrl.loadEvents(in);
		//ctrl.run(ticks);
		
		SwingUtilities.invokeLater( new Runnable() {
			@ Override
			public void run() {
			new MainWindow(ctrl);
			}
		});
	}

}
