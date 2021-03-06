package jpiere.plugin.groupware.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.webui.component.Label;
import org.adempiere.webui.editor.WEditor;
import org.compiere.model.MForm;
import org.compiere.model.Query;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zkoss.calendar.Calendars;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;

import jpiere.plugin.groupware.form.ToDoCalendarEvent;
import jpiere.plugin.groupware.model.MTeam;
import jpiere.plugin.groupware.model.MToDo;

public class GroupwareToDoUtil {


	/***********
	 * CONST
	 ***********/

	//JP_REPETITION_INTERVAL
	public final static String JP_REPETITION_INTERVAL_EVERYDAYS = "D";
	public final static String JP_REPETITION_INTERVAL_EVERYMONTHS = "M";
	public final static String JP_REPETITION_INTERVAL_EVERYWEEKS = "W";
	public final static String JP_REPETITION_INTERVAL_EVERYYEARS = "Y";

	//Calendar Event
	public final static String CALENDAR_EVENT_DAY = "onDayClick";
	public final static String CALENDAR_EVENT_WEEK = "onWeekClick";
	public final static String CALENDAR_EVENT_MONTH = "onMonthkClick";

	//Type of Calendar Veiw
	public final static String CALENDAR_ONEDAY_VIEW = "ONE";
	public final static String CALENDAR_FIVEDAYS_VIEW = "FIVE";
	public final static String CALENDAR_SEVENDAYS_VIEW = "SEVEN";
	public final static String CALENDAR_MONTH_VIEW = "MONTH";

	public final static String DEFAULT_COLOR1 = "#7EAAC6";
	public final static String DEFAULT_COLOR2 = "#ACD5EE";

	private static final int MAX_TITLE_LENGTH = 20;

	public static String trimName(String name)
	{
		if (name == null)
			return null;
		if (name.length() <= MAX_TITLE_LENGTH)
		{
			return name;
		}
		else
		{
			name = name.substring(0, MAX_TITLE_LENGTH-3) + "...";
			return name;
		}
	}


	//CSS
	public static final String STYLE_ZOOMABLE_LABEL = "cursor: pointer; text-decoration: underline;";

	static public MForm getToDoCallendarForm()
	{
		StringBuilder whereClause = new StringBuilder("classname=?");

		List<MForm> list = new Query(Env.getCtx(), MForm.Table_Name, whereClause.toString(), null)
							.setParameters("jpiere.plugin.groupware.form.ToDoCalendar")
							.list();


		if(list.size() > 0)
			return list.get(0);

		return null;
	}



	static public Div getDividingLine()
	{
		Div div = new Div();
		div.appendChild(new Html("&nbsp;"));
		div.setStyle("display: inline-block; border-left: 1px dotted #888888;margin: 5px 2px 0px 2px;");
		return div;
	}


	static public Div createSpaceDiv()
	{
		Div div = new Div();
		div.appendChild(new Html("&nbsp;"));

		return div;
	}



	static public Div createLabelDiv(WEditor editor, String string, boolean isPositionAdjust )
	{
		Label label = new Label(string);
		return createLabelDiv(editor, label , isPositionAdjust);
	}

	static public Div createLabelDiv(WEditor editor, Label label , boolean isPositionAdjust )
	{
		label.rightAlign();
		label.setMandatory(editor==null? false : editor.isMandatory());//TODO

		if(editor != null && (editor.getColumnName().equals(MToDo.COLUMNNAME_AD_User_ID)
				|| editor.getColumnName().equals(MToDo.COLUMNNAME_JP_ToDo_Category_ID) || editor.getColumnName().equals(MTeam.COLUMNNAME_JP_Team_ID) ))
		{
			label.setStyle(STYLE_ZOOMABLE_LABEL);
		}

		Div div = new Div();
		div.setSclass("form-label");
		if(isPositionAdjust)
			div.setStyle("padding-top:4px");
		div.appendChild(label);
		if(editor==null? false : editor.isMandatory())
			div.appendChild(label.getDecorator());

		return div;
	}

	static public Div createLabelDiv(Label label, boolean isMandatory )
	{
		label.setMandatory(isMandatory);
		Div div = new Div();
		div.setSclass("form-label");
		div.appendChild(label);
		if(isMandatory)
			div.appendChild(label.getDecorator());

		return div;
	}

	static public Div createEditorDiv(WEditor editor, boolean isPositionAdjust )
	{
		Div div = new Div();
		if(isPositionAdjust)
			div.setStyle("padding-top:4px");
		div.appendChild(editor.getComponent());

		return div;
	}

	static public List<ToDoCalendarEvent> getToDoCalendarEvents(Calendars calendars, boolean isDisplayName, String whereClause, String orderClause, Object ...parameters)
	{

		List<MToDo> list_ToDoes = new Query(Env.getCtx(), MToDo.Table_Name, whereClause.toString(), null)
										.setParameters(parameters)
										.setOrderBy(orderClause)
										.list();

		ArrayList<ToDoCalendarEvent> list_Events = new ArrayList<ToDoCalendarEvent>();
		for(MToDo toDo : list_ToDoes)
		{
			list_Events.add(new ToDoCalendarEvent(toDo));
		}

		return list_Events;
	}


	public static String dateFormat(LocalDate localDate)
	{
		SimpleDateFormat dateFormater = DisplayType.getDateFormat();

		Date date =new Date(Timestamp.valueOf(LocalDateTime.of(localDate, LocalTime.MIN)).getTime());

		return dateFormater.format(date);
	}
}
