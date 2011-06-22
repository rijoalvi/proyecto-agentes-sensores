package proyecto_agentes;

import java.util.List;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.amp.agf.IGraphicsAdapted;
import org.eclipse.amp.agf.IGraphicsAdapter;

import org.eclipse.swt.graphics.Color;
import org.eclipse.jface.viewers.IColorProvider;

import org.ascape.model.Agent;
import org.ascape.model.Cell;
import org.ascape.model.CellOccupant;
import org.ascape.model.HostCell;
import org.ascape.model.LocatedAgent;
import org.ascape.model.Scape;
import org.ascape.model.event.ScapeEvent;
import org.ascape.model.rule.Rule;
import org.ascape.model.rule.ExecuteThenUpdate;
import org.ascape.model.space.CollectionSpace;
import org.ascape.model.space.Coordinate;
import org.ascape.model.space.Coordinate2DDiscrete;
import org.ascape.model.space.Graph;
import org.ascape.model.space.Location;
import org.ascape.runtime.NonGraphicRunner;
import org.ascape.util.Conditional;
import org.ascape.util.data.DataPoint;
import org.ascape.util.data.DataPointConcrete;
import org.ascape.util.vis.ColorFeature;
import org.ascape.util.vis.ColorFeatureConcrete;
import org.ascape.view.vis.ChartView;
import org.ascape.view.vis.GEFView;
import org.ascape.view.vis.GraphView;

/**
 * modelo Java Implementation.
 * 
 * Generated by AMF for model: modelo.metaabm in project: proyecto_agentes 
 */
public class Modelo extends Scape implements IGraphicsAdapted {

	/**
	 * The number of sensor ambientals to create.
	 */
	private int sensorAmbientalCount = 100;

	/**
	 * The horizontal extent of the space.
	 */
	private int espacioAncho = 100;

	/**
	 * The vertical extent of the space.
	 */
	private int espacioLargo = 100;

	/**
	 * 
	 */
	private Scape espacio = null;

	/**
	 * Constructs a new modelo.
	 */
	public Modelo() {
	}

	//todo, make this a useful value for evaluating compatibility of different versions of generated classes
	private static final long serialVersionUID = 89989998L;

	private static long nextUniqueID;

	private long uniqueID;

	public long getUID() {
		if (uniqueID == 0) {
			uniqueID = nextUniqueID++;
		}
		return uniqueID;
	}

	/**
	 * Clones the agent, ensuring that a unique id is assigned.
	 */
	public Object clone() {
		try {
			Modelo clone = (Modelo) super.clone();
			clone.uniqueID = 0;
			return clone;
		} catch (Exception e) {
			throw new RuntimeException("Unexpected cloning exception: " + e);
		}
	}

	org.ascape.model.Scape sensorAmbientalScape;

	/**
	 * Create an instance of Sensor Ambiental that will be used to populate the sensorAmbientalScape.
	 * Overide to customize the prototype, for example to change the views created for a member scape.
	 */
	protected SensorAmbiental createSensorAmbientalPrototype() {
		return new SensorAmbiental();
	}

	static int next_vm_id;

	int vm_unique_id;

	/**
	 * Creates the members of modelo. 
	 */
	public void createScape() {
		super.createScape();

		setName("modelo");
		SensorAmbiental sensorAmbientalProto = createSensorAmbientalPrototype();
		sensorAmbientalScape = new Scape();
		sensorAmbientalScape.setName("Sensores Ambientales");
		sensorAmbientalScape.setPrototypeAgent(sensorAmbientalProto);
		sensorAmbientalScape.setExecutionOrder(Scape.RULE_ORDER);
		sensorAmbientalScape.setSize(sensorAmbientalCount);
		espacio = new Scape(new org.ascape.model.space.Array2DMoore());
		espacio.setExecutionOrder(Scape.RULE_ORDER);
		espacio.setPrototypeAgent(new HostCell());
		espacio.setExtent(new org.ascape.model.space.Coordinate2DDiscrete(
				getEspacioWidth(), getEspacioHeight()));

		espacio.setName("Espacios");
		((CollectionSpace) espacio.getSpace()).setPeriodic(true);
		add(espacio);

		add(sensorAmbientalScape);
		sensorAmbientalProto.setHostScape(espacio);
		espacio.getRules().clear();

		sensorAmbientalScape
				.addStatCollector(new org.ascape.util.data.StatCollectorCond(
						getName() + " Population") {
					private static final long serialVersionUID = 684614444640209893L;

					@SuppressWarnings("unused")
					public final boolean meetsCondition(Object object) {
						return true;
					}
				});

		sensorAmbientalScape.addInitialRule(new Rule("Initialize") {
			private static final long serialVersionUID = 6846144446402098982L;
			public void execute(Agent a) {
				((proyecto_agentes.SensorAmbiental) a).intializeNonFramework();
			}
		});

	}

	/**
	 * Creates UI views for modelo.
	 */
	public void createGraphicViews() {
		super.createGraphicViews();
		ChartView chart = new ChartView();

		final Modelo sensorAmbientalScape = new Modelo();
		sensorAmbientalScape.setRunner(new NonGraphicRunner());
		SensorAmbiental sensorAmbiental = new SensorAmbiental() {
			public void requestUpdate() {
			}
			public Modelo getModelo() {
				return sensorAmbientalScape;
			}
		};
		sensorAmbientalScape.add(sensorAmbiental);
		HostCell sensorAmbientalHost = new HostCell() {
			public void requestUpdate() {
			}
		};
		sensorAmbientalHost.setOccupant((CellOccupant) sensorAmbiental);

		IColorProvider sensorAmbientalStyle2DColorProvider = new SensorAmbientalStyle2DColorProvider();
		addView(chart);
		addView(new GEFView());
	}

	public void scapeSetup(ScapeEvent scapeEvent) {
		//Set the size of the scape populations, e.g. for mutable scapes that need to have their size reset

		sensorAmbientalScape.setSize(sensorAmbientalCount);
	}

	/**
	 * Returns the Scape containing Sensores Ambientales.
	 */
	public org.ascape.model.Scape getSensorAmbientalScape() {
		return sensorAmbientalScape;
	}

	public IGraphicsAdapter getGraphicsAdapter() {
		return ModeloGraphicsAdapter.getDefault();
	}

	private Color getInferredChartColor(Object agent,
			IColorProvider colorProvider) {
		Color color = colorProvider.getForeground(agent);
		if (color == ColorFeature.BLACK) {
			color = ColorFeatureConcrete.createHSB(
					(float) java.lang.Math.random() * 360f, 1.0f, 0.8f);
		}
		return color;
	}

	public static void main(String[] args) {
		(new NonGraphicRunner()).openInstance("proyecto_agentes.Modelo");
	}
	/**
	 * Gets the Sensor Ambiental Count property for modelo.
	 * @return The number of sensor ambientals to create.
	 */
	public int getSensorAmbientalCount() {
		return sensorAmbientalCount;
	}

	/**
	 * Sets the Sensor Ambiental Count property for modelo.
	 * The number of sensor ambientals to create.
	 * @param _sensorAmbientalCount the new Sensor Ambiental Count value
	 */
	public void setSensorAmbientalCount(int _sensorAmbientalCount) {
		sensorAmbientalCount = _sensorAmbientalCount;
	}

	/**
	 * Gets the Ancho property for Espacio.
	 * @return The horizontal extent of the space.
	 */
	public int getEspacioAncho() {
		return espacioAncho;
	}

	/**
	 * Sets the Ancho property for Espacio.
	 * The horizontal extent of the space.
	 * @param _espacioAncho the new Ancho value
	 */
	public void setEspacioAncho(int _espacioAncho) {
		espacioAncho = _espacioAncho;
	}

	/**
	 * Gets the Largo property for Espacio.
	 * @return The vertical extent of the space.
	 */
	public int getEspacioLargo() {
		return espacioLargo;
	}

	/**
	 * Sets the Largo property for Espacio.
	 * The vertical extent of the space.
	 * @param _espacioLargo the new Largo value
	 */
	public void setEspacioLargo(int _espacioLargo) {
		espacioLargo = _espacioLargo;
	}

	/**
	 * Gets the Espacio property for modelo.
	 * @return 
	 */
	public Scape getEspacio() {
		return espacio;
	}

	/**
	 * Sets the Espacio property for modelo.
	 * 
	 * @param _espacio the new Espacio value
	 */
	public void setEspacio(Scape _espacio) {
		espacio = _espacio;
	}

	public String getName() {
		if (name == null) {
			return "modelo " + getUID();
		} else {
			return name;
		}
	}
}
