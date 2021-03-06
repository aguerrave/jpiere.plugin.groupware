/******************************************************************************
 * Product: JPiere                                                            *
 * Copyright (C) Hideaki Hagiwara (h.hagiwara@oss-erp.co.jp)                  *
 *                                                                            *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY.                          *
 * See the GNU General Public License for more details.                       *
 *                                                                            *
 * JPiere is maintained by OSS ERP Solutions Co., Ltd.                        *
 * (http://www.oss-erp.co.jp)                                                 *
 *****************************************************************************/
package jpiere.plugin.groupware.form;

import java.util.ArrayList;

import org.zkoss.zul.ListModel;
import org.zkoss.zul.event.ListDataListener;

import jpiere.plugin.groupware.model.I_ToDoReminder;


/**
*
* JPIERE-0480 Reminder Menu Popup Window - Reminder List
*
*
* @author h.hagiwara
*
*/
public class ReminderMenuListModel implements ListModel<Object>
{

	ArrayList<I_ToDoReminder> list ;

	public ReminderMenuListModel(ArrayList<I_ToDoReminder> list )
	{
		this.list=list;
	}

	@Override
	public I_ToDoReminder getElementAt(int index)
	{
		return list.get(index);
	}

	@Override
	public int getSize()
	{
		return list.size();
	}

	@Override
	public void addListDataListener(ListDataListener l)
	{
		;
	}

	@Override
	public void removeListDataListener(ListDataListener l)
	{
		;
	}

}
