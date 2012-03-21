/**
 * Copyright 2011-2012 Three Crickets LLC.
 * <p>
 * The contents of this file are subject to the terms of the LGPL version 3.0:
 * http://www.gnu.org/copyleft/lesser.html
 * <p>
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly from Three Crickets
 * at http://threecrickets.com/
 */

package com.threecrickets.sincerity.plugin.gui.internal;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * See: http://www.devx.com/tips/Tip/5342
 */
public class CheckBoxList extends JList
{
	//
	// Construction
	//

	public CheckBoxList( Object[] listData )
	{
		super( listData );
		setCellRenderer( new CellRenderer() );
		addMouseListener( new MouseAdapter()
		{
			@Override
			public void mousePressed( MouseEvent e )
			{
				int index = locationToIndex( e.getPoint() );
				if( index != -1 )
				{
					JCheckBox checkbox = (JCheckBox) getModel().getElementAt( index );
					checkbox.setSelected( !checkbox.isSelected() );
					repaint();
				}
			}
		} );

		setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	}

	// //////////////////////////////////////////////////////////////////////////
	// Protected

	protected static Border noFocusBorder = new EmptyBorder( 1, 1, 1, 1 );

	protected class CellRenderer implements ListCellRenderer
	{
		public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus )
		{
			JCheckBox checkbox = (JCheckBox) value;
			checkbox.setBackground( isSelected ? getSelectionBackground() : getBackground() );
			checkbox.setForeground( isSelected ? getSelectionForeground() : getForeground() );
			checkbox.setEnabled( isEnabled() );
			checkbox.setFont( getFont() );
			checkbox.setFocusPainted( false );
			checkbox.setBorderPainted( true );
			checkbox.setBorder( isSelected ? UIManager.getBorder( "List.focusCellHighlightBorder" ) : noFocusBorder );
			return checkbox;
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private static final long serialVersionUID = 1L;
}