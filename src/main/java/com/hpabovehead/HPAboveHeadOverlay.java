package com.hpabovehead;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.api.Skill;

import javax.inject.Inject;
import java.awt.*;

public class HPAboveHeadOverlay extends Overlay
{
    private final Client client;
    private final HPAboveHeadConfig config;

    @Inject
    public HPAboveHeadOverlay(Client client, HPAboveHeadConfig config)
    {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.MED);
    }

    @Override
    public Dimension render(Graphics2D g)
    {
        Player localPlayer = client.getLocalPlayer();
        if (localPlayer == null)
        {
            return null;
        }

        if (localPlayer.getHealthScale() <= 0)
        {
            return null;
        }

        int maxHp = client.getRealSkillLevel(Skill.HITPOINTS);
        int currentHp = client.getBoostedSkillLevel(Skill.HITPOINTS);
        String text = currentHp + "/" + maxHp;

        // setting font
        g.setFont(new Font(config.font().name(), Font.PLAIN, config.fontSize()));

        // calc height as player height + config offset
        int height = localPlayer.getLogicalHeight() + config.textHeightOffset();

        // get local player pos
        net.runelite.api.coords.LocalPoint localLocation = localPlayer.getLocalLocation();

        // convert 3D point to 2D canvas coords
        net.runelite.api.Point canvasPoint = Perspective.localToCanvas(client, localLocation, client.getPlane(), height);

        if (canvasPoint != null)
        {
            // horizontal offset
            int x = canvasPoint.getX() + config.textXOffset();

            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);

            // centre text horizontally on x
            int drawX = x - (textWidth / 2);
            int drawY = canvasPoint.getY();

            // shadow
            g.setColor(Color.BLACK);
            g.drawString(text, drawX + 1, drawY + 1);

            // text
            g.setColor(config.color());
            g.drawString(text, drawX, drawY);
        }

        return null;
    }
}
