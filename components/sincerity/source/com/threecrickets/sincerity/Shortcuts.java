package com.threecrickets.sincerity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.threecrickets.sincerity.exception.SincerityException;
import com.threecrickets.sincerity.exception.UnknownShortcutException;

public class Shortcuts extends AbstractList<String>
{
	//
	// Constants
	//

	public static final String SHORTCUT_PREFIX = "@";

	public static final int SHORTCUT_PREFIX_LENGTH = SHORTCUT_PREFIX.length();

	//
	// Construction
	//

	public Shortcuts( File file ) throws SincerityException
	{
		this.file = file;
		validate();
	}

	//
	// Attributes
	//

	public String[] get( String shortcut ) throws SincerityException
	{
		Object value = properties.get( shortcut );

		if( value == null )
			throw new UnknownShortcutException( shortcut );

		ArrayList<String> items = new ArrayList<String>();
		for( String argument : value.toString().split( " " ) )
			addArgument( argument, items );
		return items.toArray( new String[items.size()] );
	}

	public void addArgument( String argument, List<String> arguments ) throws SincerityException
	{
		if( argument.startsWith( SHORTCUT_PREFIX ) )
		{
			String shortcut = argument.substring( SHORTCUT_PREFIX_LENGTH );
			String[] items = get( shortcut );
			if( items == null )
				throw new UnknownShortcutException( shortcut );

			for( String a : items )
				addArgument( a, arguments );
		}
		else
			arguments.add( argument );
	}

	//
	// AbstractList
	//

	@Override
	public int size()
	{
		return properties.size();
	}

	@Override
	public String get( int index )
	{
		ArrayList<String> keys = new ArrayList<String>( properties.size() );
		for( Object key : properties.keySet() )
			keys.add( key.toString() );
		return keys.get( index );
	}

	// //////////////////////////////////////////////////////////////////////////
	// Private

	private final File file;

	private Properties properties;

	private void validate() throws SincerityException
	{
		if( properties == null )
		{
			properties = new Properties();
			try
			{
				FileInputStream stream = new FileInputStream( file );
				try
				{
					try
					{
						properties.load( stream );
					}
					finally
					{
						stream.close();
					}
				}
				catch( IOException x )
				{
					throw new SincerityException( "Could not read shortcuts configuration: " + file, x );
				}
			}
			catch( FileNotFoundException x )
			{
			}
		}
	}
}