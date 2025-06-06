/*
 * Copyright (C) 2019-2025 The MegaMek Team. All Rights Reserved.
 *
 * This file is part of MekHQ.
 *
 * MekHQ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL),
 * version 3 or (at your option) any later version,
 * as published by the Free Software Foundation.
 *
 * MekHQ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * A copy of the GPL should have been included with this project;
 * if not, see <https://www.gnu.org/licenses/>.
 *
 * NOTICE: The MegaMek organization is a non-profit group of volunteers
 * creating free software for the BattleTech community.
 *
 * MechWarrior, BattleMech, `Mech and AeroTech are registered trademarks
 * of The Topps Company, Inc. All Rights Reserved.
 *
 * Catalyst Game Labs and the Catalyst Game Labs logo are trademarks of
 * InMediaRes Productions, LLC.
 */
package mekhq.campaign.parts;

import java.io.PrintWriter;

import megamek.common.Compute;
import megamek.common.Entity;
import megamek.common.Jumpship;
import megamek.common.SimpleTechLevel;
import megamek.common.TechAdvancement;
import megamek.common.annotations.Nullable;
import megamek.logging.MMLogger;
import mekhq.campaign.Campaign;
import mekhq.campaign.finances.Money;
import mekhq.campaign.personnel.skills.SkillType;
import mekhq.utilities.MHQXMLUtility;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author MKerensky
 */
public class GravDeck extends Part {
    private static final MMLogger logger = MMLogger.create(GravDeck.class);

    static final TechAdvancement TA_GRAV_DECK = new TechAdvancement(TECH_BASE_ALL)
            .setAdvancement(DATE_ES, DATE_ES, DATE_ES)
            .setTechRating(RATING_B)
            .setAvailability(RATING_C, RATING_C, RATING_C, RATING_C)
            .setStaticTechLevel(SimpleTechLevel.STANDARD);

    private int deckType;
    private int deckNumber;

    public static final int GRAV_DECK_TYPE_STANDARD = 0;
    public static final int GRAV_DECK_TYPE_LARGE = 1;
    public static final int GRAV_DECK_TYPE_HUGE = 2;

    public GravDeck() {
        this(0, 0, null, GRAV_DECK_TYPE_STANDARD);
    }

    public GravDeck(int tonnage, int deckNumber, Campaign c, int deckType) {
        super(tonnage, c);
        this.deckNumber = deckNumber;
        this.deckType = deckType;
        this.name = "Grav Deck";
        if (deckType == GRAV_DECK_TYPE_STANDARD) {
            name += " (Standard)";
        } else if (deckType == GRAV_DECK_TYPE_LARGE) {
            name += " (Large)";
        } else if (deckType == GRAV_DECK_TYPE_HUGE) {
            name += " (Huge)";
        }
    }

    public int getDeckNumber() {
        return deckNumber;
    }

    @Override
    public GravDeck clone() {
        GravDeck clone = new GravDeck(0, deckNumber, campaign, deckType);
        clone.copyBaseData(this);
        return clone;
    }

    public int getDeckType() {
        return deckType;
    }

    @Override
    public void updateConditionFromEntity(boolean checkForDestruction) {
        int priorHits = hits;
        if (null != unit && unit.getEntity() instanceof Jumpship) {
            hits = ((Jumpship) unit.getEntity()).getGravDeckDamageFlag(deckNumber);

            if (checkForDestruction
                    && hits > priorHits
                    && Compute.d6(2) < campaign.getCampaignOptions().getDestroyPartTarget()) {
                remove(false);
            }
        }
    }

    @Override
    public int getBaseTime() {
        if (isSalvaging()) {
            return 4800;
        }
        return 1440;
    }

    @Override
    public int getDifficulty() {
        if (isSalvaging()) {
            return 3;
        }
        return 2;
    }

    @Override
    public void updateConditionFromPart() {
        if (null != unit && unit.getEntity() instanceof Jumpship) {
            ((Jumpship) unit.getEntity()).setGravDeckDamageFlag(deckNumber, hits);
        }
    }

    @Override
    public void fix() {
        super.fix();
        if (null != unit && unit.getEntity() instanceof Jumpship) {
            ((Jumpship) unit.getEntity()).setGravDeckDamageFlag(deckNumber, 0);
        }
    }

    @Override
    public void remove(boolean salvage) {
        if (unit.getEntity() instanceof Jumpship) {
            ((Jumpship) unit.getEntity()).setGravDeckDamageFlag(deckNumber, 1);

            Part spare = campaign.getWarehouse().checkForExistingSparePart(this);
            if (!salvage) {
                campaign.getWarehouse().removePart(this);
            } else if (null != spare) {
                spare.incrementQuantity();
                campaign.getWarehouse().removePart(this);
            }
            unit.removePart(this);
            Part missing = getMissingPart();
            unit.addPart(missing);
            campaign.getQuartermaster().addPart(missing, 0);
        }
        setUnit(null);
        updateConditionFromEntity(false);
    }

    @Override
    public MissingPart getMissingPart() {
        return new MissingGravDeck(0, deckNumber, campaign, deckType);
    }

    @Override
    public @Nullable String checkFixable() {
        return null;
    }

    @Override
    public boolean needsFixing() {
        return (hits > 0);
    }

    @Override
    public Money getStickerPrice() {
        if (deckType == GRAV_DECK_TYPE_STANDARD) {
            return Money.of(5000000);
        } else if (deckType == GRAV_DECK_TYPE_LARGE) {
            return Money.of(10000000);
        } else {
            return Money.of(40000000);
        }
    }

    @Override
    public double getTonnage() {
        // TO tables p 407
        if (deckType == GRAV_DECK_TYPE_STANDARD) {
            return 50;
        } else if (deckType == GRAV_DECK_TYPE_LARGE) {
            return 100;
        } else {
            return 500;
        }
    }

    @Override
    public boolean isSamePartType(Part part) {
        return (part instanceof GravDeck)
                && (deckType == ((GravDeck) part).deckType);
    }

    @Override
    public void writeToXML(final PrintWriter pw, int indent) {
        indent = writeToXMLBegin(pw, indent);
        MHQXMLUtility.writeSimpleXMLTag(pw, indent, "deckType", deckType);
        MHQXMLUtility.writeSimpleXMLTag(pw, indent, "deckNumber", deckNumber);
        writeToXMLEnd(pw, indent);
    }

    @Override
    protected void loadFieldsFromXmlNode(Node wn) {
        NodeList nl = wn.getChildNodes();

        for (int x = 0; x < nl.getLength(); x++) {
            Node wn2 = nl.item(x);

            try {
                if (wn2.getNodeName().equalsIgnoreCase("deckType")) {
                    deckType = Integer.parseInt(wn2.getTextContent());
                } else if (wn2.getNodeName().equalsIgnoreCase("deckNumber")) {
                    deckNumber = Integer.parseInt(wn2.getTextContent());
                }
            } catch (Exception ex) {
                logger.error("", ex);
            }
        }
    }

    @Override
    public boolean isRightTechType(String skillType) {
        return skillType.equals(SkillType.S_TECH_VESSEL);
    }

    @Override
    public String getLocationName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getLocation() {
        return Entity.LOC_NONE;
    }

    @Override
    public TechAdvancement getTechAdvancement() {
        return TA_GRAV_DECK;
    }
}
