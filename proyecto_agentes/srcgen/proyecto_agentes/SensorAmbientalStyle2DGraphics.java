package proyecto_agentes;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import org.ascape.model.Agent;
import org.ascape.model.CellOccupant;
import org.ascape.model.HostCell;

import org.ascape.util.vis.DrawFeature;

/**
 * Sensor Ambiental Style 2D Java Implementation.
 * 
 * Generated by AMF for model: modelo.metaabm in project: proyecto_agentes 
 */
public class SensorAmbientalStyle2DGraphics extends DrawFeature {

	enum Shapes {
		rectangle, oval, marker
	};

	Shapes shape = Shapes.rectangle;

	public void draw(Graphics g, Object object, int width, int height) {

		HostCell actionsCell = (HostCell) object;
		if (actionsCell.getOccupant() instanceof SensorAmbiental) {
			SensorAmbiental actions = (SensorAmbiental) actionsCell
					.getOccupant();
			SensorAmbiental defaultmodeloAgentStyle = actions;
			Color defaultmodeloAgentColor = ColorConstants.blue;

			g.setForegroundColor(defaultmodeloAgentColor);
			g.setBackgroundColor(defaultmodeloAgentColor);
			shape = shape.oval;
			if (shape == Shapes.oval) {
				g.fillOval(0, 0, width, height);
			} else if (shape == Shapes.marker) {
				int d = (int) (width * .25 - 1);
				int d2 = (int) (width * .5 - 1);
				g.fillOval(d, d, d2, d2);
			} else if (shape == Shapes.rectangle) {
				g.fillRectangle(0, 0, width, height);
			}
		}
	}
}
