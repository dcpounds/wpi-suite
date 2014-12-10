package edu.wpi.cs.wpisuitetng.modules.taskmanager.view.tab;
import org.jfree.chart.renderer.category.BarRenderer;
import java.awt.Paint;

public class CustomRenderer extends BarRenderer {
	
	private static final long serialVersionUID = 3531998166389518654L;
	/** The colors. */
    private Paint[] colors;

    /**
     * Creates a new renderer.
     *
     * @param colors  the colors.
     */
    public CustomRenderer(final Paint[] colors) {
        this.colors = colors;
    }

    /**
     * Returns the paint for an item.  Overrides the default behaviour inherited from
     * AbstractSeriesRenderer.
     *
     * @param row  the series.
     * @param column  the category.
     *
     * @return The item color.
     */
    public Paint getItemPaint(final int row, final int column) {
        return this.colors[column % this.colors.length];
    }
	

}
