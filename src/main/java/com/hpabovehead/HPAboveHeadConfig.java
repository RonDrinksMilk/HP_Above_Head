package com.hpabovehead;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

import java.awt.*;

@ConfigGroup("settings")
public interface HPAboveHeadConfig extends Config
{
	@ConfigItem(
			keyName = "fontSize",
			name = "Font Size",
			description = "Size of the text above the HP bar"
	)
	@Range(min = 8, max = 40)
	default int fontSize()
	{
		return 14;
	}

	public enum FontOption
	{
		ARIAL,
		VERDANA,
		TIMES_NEW_ROMAN,
		MONOSPACED,
		UNICA_ONE,
		TAHOMA,
		CALIBRI,
		COURIER_NEW,
		GEORGIA,
		COMIC_SANS_MS,
		SEGOE_UI,
		LUCIDA_CONSOLE,
		ROBOTO

	}

	@ConfigItem(
			keyName = "font",
			name = "Font",
			description = "Font used for the text"
	)
	default FontOption font()
	{
		return FontOption.ARIAL;
	}

	@ConfigItem(
			keyName = "color",
			name = "Text Color",
			description = "Color of the text above the HP bar"
	)
	default Color color()
	{
		return Color.WHITE;
	}

	@ConfigItem(
			keyName = "textHeightOffset",
			name = "Text vertical offset",
			description = "How high above the HP bar to render the HP text"
	)
	default int textHeightOffset()
	{
		return 30;
	}

	@ConfigItem(
			keyName = "textXOffset",
			name = "Text horizontal offset",
			description = "Horizontal offset for the HP text relative to the player's centre"
	)
	default int textXOffset()
	{
		return 0;
	}
}
