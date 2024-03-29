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
 * Sensor Ambiental Java Implementation.
 * 
 * Generated by AMF for model: modelo.metaabm in project: proyecto_agentes 
 */
public class SensorAmbiental extends CellOccupant {

	/**
	 * Constructs a new Sensor Ambiental.
	 */
	public SensorAmbiental() {
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
			SensorAmbiental clone = (SensorAmbiental) super.clone();
			clone.uniqueID = 0;
			return clone;
		} catch (Exception e) {
			throw new RuntimeException("Unexpected cloning exception: " + e);
		}
	}

	public Modelo getModelo() {
		return (Modelo) getScape().getScape();
	}

	/**
	 * Initialize Initialization. Executed once at the beginning of each model run.
	 */
	public void intializeNonFramework() {
		Object sensorAmbiental = (Object) ((org.ascape.model.space.Discrete) getModelo()
				.getEspacio().getSpace()).findRandomAvailable();
		if (sensorAmbiental != null) {
			if (getHostScape() != ((Agent) sensorAmbiental).getScape()) {
				die();
				getModelo().getSensorAmbientalScape().add(this);
			}
			moveTo(((HostCell) sensorAmbiental));
		}
	}
	public String getName() {
		if (name == null) {
			return "Sensor Ambiental " + getUID();
		} else {
			return name;
		}
	}
}
