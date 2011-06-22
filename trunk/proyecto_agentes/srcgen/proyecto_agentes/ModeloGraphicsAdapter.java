package proyecto_agentes;

import org.eclipse.amp.agf.IGraphicsAdapter;
import org.eclipse.amp.agf.GraphicsAdapter;
import org.eclipse.amp.agf.gef.IFigureProvider;
import org.eclipse.core.runtime.IAdapterFactory;

import org.eclipse.amp.agf.gef.IFigureProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProvider;

/**
 * modelo Java Implementation.
 * 
 * Generated by AMF for model: modelo.metaabm in project: proyecto_agentes 
 */
public class ModeloGraphicsAdapter extends GraphicsAdapter {

	private static IGraphicsAdapter singleton;

	IColorProvider[] modeloColorProviders = new IColorProvider[]{};
	IColorProvider[] sensorAmbientalColorProviders = new IColorProvider[]{SensorAmbientalStyle2DColorProvider
			.getDefault()};

	IFigureProvider[] modeloFigureProviders = new IFigureProvider[]{};
	IFigureProvider[] sensorAmbientalFigureProviders = new IFigureProvider[]{SensorAmbientalStyle2DFigureProvider
			.getDefault()};

	/**
	 * @param type
	 * @return
	 * @see org.eclipse.amp.agf.IGraphicsAdapter#getColorsForClass(java.lang.Class)
	 */
	public IColorProvider[] getColorsForClass(Class type) {
		if (type == Modelo.class) {
			return modeloColorProviders;
		}
		if (type == SensorAmbiental.class) {
			return sensorAmbientalColorProviders;
		}
		return null;
	}

	/**
	 * @param type
	 * @return
	 * @see org.eclipse.amp.agf.IGraphicsAdapter#getFiguresForClass(java.lang.Class)
	 */
	public IFigureProvider[] getFiguresForClass(Class type) {
		if (type == Modelo.class) {
			return modeloFigureProviders;
		}
		if (type == SensorAmbiental.class) {
			return sensorAmbientalFigureProviders;
		}
		return null;
	}

	/**
	 * @param type
	 * @return
	 * @see org.eclipse.amp.agf.IGraphicsAdapter#getLabelsForClass(java.lang.Class)
	 */
	public ILabelProvider[] getLabelsForClass(Class type) {
		return null;
	}

	public static IGraphicsAdapter getDefault() {
		if (singleton == null) {
			singleton = new ModeloGraphicsAdapter();
		}
		return singleton;
	}
}
