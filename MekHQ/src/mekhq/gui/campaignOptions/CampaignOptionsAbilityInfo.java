/*
 * Copyright (C) 2025 The MegaMek Team. All Rights Reserved.
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
 *
 * MechWarrior Copyright Microsoft Corporation. MekHQ was created under
 * Microsoft's "Game Content Usage Rules"
 * <https://www.xbox.com/en-US/developers/rules> and it is not endorsed by or
 * affiliated with Microsoft.
 */
package mekhq.gui.campaignOptions;

import mekhq.campaign.personnel.SpecialAbility;
import mekhq.utilities.spaUtilities.enums.AbilityCategory;

/**
 * The {@code AbilityInfo} class represents information about a specific ability, encapsulating its name, the associated
 * {@link SpecialAbility}, its active status, and its category.
 */
public class CampaignOptionsAbilityInfo {
    private String name;
    private SpecialAbility ability;
    private boolean isEnabled;
    private AbilityCategory category;

    /**
     * Constructs an {@code AbilityInfo} object with all fields initialized.
     *
     * @param name      the name of the ability
     * @param ability   the {@link SpecialAbility} associated with this ability
     * @param isEnabled {@code true} if the ability is enabled, otherwise {@code false}
     * @param category  the category of the ability, represented as an {@link AbilityCategory}
     */
    public CampaignOptionsAbilityInfo(String name, SpecialAbility ability, boolean isEnabled,
          AbilityCategory category) {
        this.name = name;
        this.ability = ability;
        this.isEnabled = isEnabled;
        this.category = category;
    }

    /**
     * Returns the name of the ability.
     *
     * @return the name of the ability
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the ability.
     *
     * @param name the new name of the ability
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the {@link SpecialAbility} object associated with this ability.
     *
     * @return the associated {@link SpecialAbility}
     */
    public SpecialAbility getAbility() {
        return ability;
    }

    /**
     * Sets the {@link SpecialAbility} object associated with this ability.
     *
     * @param ability the new {@link SpecialAbility} object to associate
     */
    public void setAbility(SpecialAbility ability) {
        this.ability = ability;
    }

    /**
     * Returns whether the ability is enabled or active.
     *
     * @return {@code true} if the ability is enabled, otherwise {@code false}
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Sets the enabled/active status of the ability.
     *
     * @param enabled {@code true} to enable the ability, {@code false} to disable it
     */
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    /**
     * Returns the category of the ability.
     *
     * @return the {@link AbilityCategory} of the ability
     */
    public AbilityCategory getCategory() {
        return category;
    }

    /**
     * Sets the category of the ability.
     *
     * @param category the new {@link AbilityCategory} for the ability
     */
    public void setCategory(AbilityCategory category) {
        this.category = category;
    }

    /**
     * Returns a string representation of the ability, displaying only its name.
     *
     * @return the name of the ability
     */
    @Override
    public String toString() {
        return name;
    }
}
