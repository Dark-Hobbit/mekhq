/*
 * Copyright (C) 2024-2025 The MegaMek Team. All Rights Reserved.
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
package mekhq.gui.campaignOptions.contents;

import static mekhq.campaign.parts.enums.PartQuality.QUALITY_F;
import static mekhq.gui.campaignOptions.CampaignOptionsUtilities.createParentPanel;
import static mekhq.gui.campaignOptions.CampaignOptionsUtilities.createTipPanelUpdater;
import static mekhq.gui.campaignOptions.CampaignOptionsUtilities.getImageDirectory;

import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import megamek.client.ui.comboBoxes.MMComboBox;
import megamek.common.annotations.Nullable;
import mekhq.campaign.Campaign;
import mekhq.campaign.campaignOptions.CampaignOptions;
import mekhq.campaign.finances.enums.FinancialYearDuration;
import mekhq.campaign.parts.enums.PartQuality;
import mekhq.gui.campaignOptions.components.CampaignOptionsCheckBox;
import mekhq.gui.campaignOptions.components.CampaignOptionsGridBagConstraints;
import mekhq.gui.campaignOptions.components.CampaignOptionsHeaderPanel;
import mekhq.gui.campaignOptions.components.CampaignOptionsLabel;
import mekhq.gui.campaignOptions.components.CampaignOptionsSpinner;
import mekhq.gui.campaignOptions.components.CampaignOptionsStandardPanel;

/**
 * The FinancesTab class represents a UI tab within a larger financial management system for a campaign. It provides
 * panels, checkboxes, spinners, combo boxes, and other controls to manage and configure various financial options,
 * payments, sales, taxes, shares, and price multipliers for the campaign.
 * <p>
 * It is primarily composed of multiple `JPanel` sections organized using `GroupLayout` for modularity and clarity.
 */
public class FinancesTab {
    private final CampaignOptions campaignOptions;

    //start General Options
    private CampaignOptionsHeaderPanel financesGeneralOptions;
    private JPanel pnlGeneralOptions;
    private JCheckBox useLoanLimitsBox;
    private JCheckBox usePercentageMaintenanceBox;
    private JCheckBox useExtendedPartsModifierBox;
    private JCheckBox usePeacetimeCostBox;
    private JCheckBox showPeacetimeCostBox;
    private JLabel lblFinancialYearDuration;
    private MMComboBox<FinancialYearDuration> comboFinancialYearDuration;
    private JCheckBox newFinancialYearFinancesToCSVExportBox;
    private JCheckBox chkSimulateGrayMonday;

    private JPanel pnlPayments;
    private JCheckBox payForPartsBox;
    private JCheckBox payForRepairsBox;
    private JCheckBox payForUnitsBox;
    private JCheckBox payForSalariesBox;
    private JCheckBox payForOverheadBox;
    private JCheckBox payForMaintainBox;
    private JCheckBox payForTransportBox;
    private JCheckBox payForRecruitmentBox;
    private JCheckBox payForFoodBox;
    private JCheckBox payForHousingBox;


    private JPanel pnlSales;
    private JCheckBox sellUnitsBox;
    private JCheckBox sellPartsBox;

    private JPanel pnlOtherSystems;

    private JPanel pnlTaxes;
    private JCheckBox chkUseTaxes;
    private JLabel lblTaxesPercentage;
    private JSpinner spnTaxesPercentage;

    private JPanel pnlShares;
    private JCheckBox chkUseShareSystem;
    private JCheckBox chkSharesForAll;
    //end General Options

    //start Price Multipliers
    private CampaignOptionsHeaderPanel priceMultipliersHeader;
    private JPanel pnlGeneralMultipliersBuy;
    private JLabel lblCommonPartPriceMultiplierBuy;
    private JSpinner spnCommonPartPriceMultiplierBuy;
    private JLabel lblInnerSphereUnitPriceMultiplierBuy;
    private JSpinner spnInnerSphereUnitPriceMultiplierBuy;
    private JLabel lblInnerSpherePartPriceMultiplierBuy;
    private JSpinner spnInnerSpherePartPriceMultiplierBuy;
    private JLabel lblClanUnitPriceMultiplierBuy;
    private JSpinner spnClanUnitPriceMultiplierBuy;
    private JLabel lblClanPartPriceMultiplierBuy;
    private JSpinner spnClanPartPriceMultiplierBuy;
    private JLabel lblMixedTechUnitPriceMultiplierBuy;
    private JSpinner spnMixedTechUnitPriceMultiplierBuy;

    private JPanel pnlGeneralMultipliersSell;
    private JLabel lblCommonPartPriceMultiplierSell;
    private JSpinner spnCommonPartPriceMultiplierSell;
    private JLabel lblInnerSphereUnitPriceMultiplierSell;
    private JSpinner spnInnerSphereUnitPriceMultiplierSell;
    private JLabel lblInnerSpherePartPriceMultiplierSell;
    private JSpinner spnInnerSpherePartPriceMultiplierSell;
    private JLabel lblClanUnitPriceMultiplierSell;
    private JSpinner spnClanUnitPriceMultiplierSell;
    private JLabel lblClanPartPriceMultiplierSell;
    private JSpinner spnClanPartPriceMultiplierSell;
    private JLabel lblMixedTechUnitPriceMultiplierSell;
    private JSpinner spnMixedTechUnitPriceMultiplierSell;

    private JPanel pnlUsedPartsMultipliers;
    private JLabel[] lblUsedPartPriceMultipliers;
    private JSpinner[] spnUsedPartPriceMultipliers;

    private JPanel pnlOtherMultipliers;
    private JLabel lblDamagedPartsValueMultiplier;
    private JSpinner spnDamagedPartsValueMultiplier;
    private JLabel lblUnrepairablePartsValueMultiplier;
    private JSpinner spnUnrepairablePartsValueMultiplier;
    private JLabel lblCancelledOrderRefundMultiplier;
    private JSpinner spnCancelledOrderRefundMultiplier;
    //end Price Multipliers

    /**
     * Constructs a `FinancesTab` instance which manages the financial settings and configurations for a specific
     * campaign.
     *
     * @param campaign The `Campaign` object that this `FinancesTab` will be associated with. Provides access to
     *                 campaign-related options and data.
     */
    public FinancesTab(Campaign campaign) {
        this.campaignOptions = campaign.getCampaignOptions();

        initialize();
    }

    /**
     * Initializes the primary components and subcomponents of the `FinancesTab`. Specifically, sets up the 'General
     * Options' and 'Price Multipliers' tabs through their respective initialization methods. This method ensures that
     * the tabs are prepared prior to being displayed or used.
     */
    private void initialize() {
        initializeGeneralOptionsTab();
        initializePriceMultipliersTab();
    }

    /**
     * Initializes the General Options tab within the application's UI.
     * <p>
     * This method sets up various UI components and panels that provide configurable options for general settings,
     * payments, sales, other systems, taxes, and shares. Components include checkboxes, labels, spinners, and combo
     * boxes that allow the user to interact with and configure these settings.
     * <p>
     * All UI components are initialized, but additional configuration such as layout placements, listeners, or actual
     * visibility might need to be completed separately.
     */
    private void initializeGeneralOptionsTab() {
        // General Options
        pnlGeneralOptions = new JPanel();
        useLoanLimitsBox = new JCheckBox();
        usePercentageMaintenanceBox = new JCheckBox();
        useExtendedPartsModifierBox = new JCheckBox();
        usePeacetimeCostBox = new JCheckBox();
        showPeacetimeCostBox = new JCheckBox();

        lblFinancialYearDuration = new JLabel();
        comboFinancialYearDuration = new MMComboBox<>("comboFinancialYearDuration", FinancialYearDuration.values());

        newFinancialYearFinancesToCSVExportBox = new JCheckBox();

        chkSimulateGrayMonday = new JCheckBox();

        // Payments
        pnlPayments = new JPanel();
        payForPartsBox = new JCheckBox();
        payForRepairsBox = new JCheckBox();
        payForUnitsBox = new JCheckBox();
        payForSalariesBox = new JCheckBox();
        payForOverheadBox = new JCheckBox();
        payForMaintainBox = new JCheckBox();
        payForTransportBox = new JCheckBox();
        payForRecruitmentBox = new JCheckBox();
        payForFoodBox = new JCheckBox();
        payForHousingBox = new JCheckBox();

        // Sales
        pnlSales = new JPanel();
        sellUnitsBox = new JCheckBox();
        sellPartsBox = new JCheckBox();

        pnlOtherSystems = new JPanel();

        // Taxes
        pnlTaxes = new JPanel();
        chkUseTaxes = new JCheckBox();
        lblTaxesPercentage = new JLabel();
        spnTaxesPercentage = new JSpinner();

        // Shares
        pnlShares = new JPanel();
        chkUseShareSystem = new JCheckBox();
        chkSharesForAll = new JCheckBox();
    }

    /**
     * Creates and configures the Finances General Options tab, assembling its components, layout, and panels which
     * include general options, other systems, payments, and sales. This method initializes required sub-panels and
     * arranges them within the overall structure to create a fully constructed tab for financial general options.
     *
     * @return A fully configured JPanel representing the Finances General Options tab.
     */
    public JPanel createFinancesGeneralOptionsTab() {
        // Header
        financesGeneralOptions = new CampaignOptionsHeaderPanel("FinancesGeneralTab",
              getImageDirectory() + "logo_star_league.png", 10);

        // Contents
        pnlGeneralOptions = createGeneralOptionsPanel();
        pnlOtherSystems = createOtherSystemsPanel();

        pnlPayments = createPaymentsPanel();
        pnlSales = createSalesPanel();

        // Layout the Panel
        final JPanel panelTransactions = new CampaignOptionsStandardPanel("FinancesGeneralTabTransactions");
        GridBagConstraints layoutTransactions = new CampaignOptionsGridBagConstraints(panelTransactions);

        layoutTransactions.gridwidth = 2;
        layoutTransactions.gridy = 0;
        layoutTransactions.gridx = 0;
        panelTransactions.add(pnlPayments, layoutTransactions);
        layoutTransactions.gridx += 2;
        panelTransactions.add(pnlSales, layoutTransactions);

        final JPanel panel = new CampaignOptionsStandardPanel("FinancesGeneralTab", true);
        GridBagConstraints layoutParent = new CampaignOptionsGridBagConstraints(panel);

        layoutParent.gridwidth = 5;
        layoutParent.gridy = 0;
        panel.add(financesGeneralOptions, layoutParent);

        layoutParent.gridx = 0;
        layoutParent.gridy++;
        layoutParent.gridwidth = 1;
        panel.add(pnlGeneralOptions, layoutParent);
        layoutParent.gridx++;
        panel.add(pnlOtherSystems, layoutParent);

        layoutParent.gridwidth = 2;
        layoutParent.gridx = 0;
        layoutParent.gridy++;
        panel.add(panelTransactions, layoutParent);

        // Create Parent Panel and return
        return createParentPanel(panel, "FinancesGeneralTab");
    }

    /**
     * Creates and configures a payments panel with various checkbox options for payment categories such as parts,
     * repairs, units, salaries, overhead, maintenance, transport, and recruitment. The layout of the panel organizes
     * the checkboxes in a grid-based format.
     *
     * @return a JPanel instance containing the configured payment options checkboxes.
     */
    private JPanel createPaymentsPanel() {
        // Contents
        payForPartsBox = new CampaignOptionsCheckBox("PayForPartsBox");
        payForPartsBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForPartsBox"));
        payForRepairsBox = new CampaignOptionsCheckBox("PayForRepairsBox");
        payForRepairsBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForRepairsBox"));
        payForUnitsBox = new CampaignOptionsCheckBox("PayForUnitsBox");
        payForUnitsBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForUnitsBox"));
        payForSalariesBox = new CampaignOptionsCheckBox("PayForSalariesBox");
        payForSalariesBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForSalariesBox"));
        payForOverheadBox = new CampaignOptionsCheckBox("PayForOverheadBox");
        payForOverheadBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForOverheadBox"));
        payForMaintainBox = new CampaignOptionsCheckBox("PayForMaintainBox");
        payForMaintainBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForMaintainBox"));
        payForTransportBox = new CampaignOptionsCheckBox("PayForTransportBox");
        payForTransportBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForTransportBox"));
        payForRecruitmentBox = new CampaignOptionsCheckBox("PayForRecruitmentBox");
        payForRecruitmentBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForRecruitmentBox"));
        payForFoodBox = new CampaignOptionsCheckBox("PayForFoodBox");
        payForRecruitmentBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForFoodBox"));
        payForHousingBox = new CampaignOptionsCheckBox("PayForHousingBox");
        payForRecruitmentBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "PayForHousingBox"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("PaymentsPanel", true, "PaymentsPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(payForPartsBox, layout);
        layout.gridx++;
        panel.add(payForRepairsBox, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(payForUnitsBox, layout);
        layout.gridx++;
        panel.add(payForSalariesBox, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(payForOverheadBox, layout);
        layout.gridx++;
        panel.add(payForMaintainBox, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(payForTransportBox, layout);
        layout.gridx++;
        panel.add(payForRecruitmentBox, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(payForFoodBox, layout);
        layout.gridx++;
        panel.add(payForHousingBox, layout);

        return panel;
    }

    /**
     * Constructs and returns a {@link JPanel} for the 'Other Systems Panel'. This panel combines two sub-panels: 'Taxes
     * Panel' and 'Shares Panel'. Each sub-panel is added sequentially to the main panel using a grid-bag layout. These
     * panels are organized vertically in the resulting panel.
     *
     * @return {@link JPanel} representing the 'Other Systems Panel', containing the 'Taxes Panel' and 'Shares Panel'.
     */
    private JPanel createOtherSystemsPanel() {
        // Contents
        pnlTaxes = createTaxesPanel();
        pnlShares = createSharesPanel();

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("OtherSystemsPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(pnlTaxes, layout);

        layout.gridy++;
        panel.add(pnlShares, layout);

        return panel;
    }

    /**
     * Creates and initializes the General Options Panel with various configurable options related to loan limits,
     * maintenance, parts modifiers, peacetime costs, and financial year settings. The panel includes checkboxes and
     * labels for easy user interaction and configuration of these parameters.
     *
     * @return A JPanel containing the general options components laid out in a structured format.
     */
    private JPanel createGeneralOptionsPanel() {
        // Contents
        useLoanLimitsBox = new CampaignOptionsCheckBox("UseLoanLimitsBox");
        useLoanLimitsBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "UseLoanLimitsBox"));
        usePercentageMaintenanceBox = new CampaignOptionsCheckBox("UsePercentageMaintenanceBox");
        usePercentageMaintenanceBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions,
              "UsePercentageMaintenanceBox"));
        useExtendedPartsModifierBox = new CampaignOptionsCheckBox("UseExtendedPartsModifierBox");
        useExtendedPartsModifierBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions,
              "UseExtendedPartsModifierBox"));
        usePeacetimeCostBox = new CampaignOptionsCheckBox("UsePeacetimeCostBox");
        usePeacetimeCostBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "UsePeacetimeCostBox"));
        showPeacetimeCostBox = new CampaignOptionsCheckBox("ShowPeacetimeCostBox");
        showPeacetimeCostBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "ShowPeacetimeCostBox"));

        lblFinancialYearDuration = new CampaignOptionsLabel("FinancialYearDuration");
        lblFinancialYearDuration.addMouseListener(createTipPanelUpdater(financesGeneralOptions,
              "FinancialYearDuration"));
        comboFinancialYearDuration.addMouseListener(createTipPanelUpdater(financesGeneralOptions,
              "FinancialYearDuration"));

        newFinancialYearFinancesToCSVExportBox = new CampaignOptionsCheckBox("NewFinancialYearFinancesToCSVExportBox");
        newFinancialYearFinancesToCSVExportBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions,
              "NewFinancialYearFinancesToCSVExportBox"));

        chkSimulateGrayMonday = new CampaignOptionsCheckBox("SimulateGrayMonday");
        chkSimulateGrayMonday.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "SimulateGrayMonday"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("GeneralOptionsPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(useLoanLimitsBox, layout);

        layout.gridy++;
        panel.add(usePercentageMaintenanceBox, layout);

        layout.gridy++;
        panel.add(useExtendedPartsModifierBox, layout);

        layout.gridy++;
        panel.add(usePeacetimeCostBox, layout);

        layout.gridy++;
        panel.add(showPeacetimeCostBox, layout);

        layout.gridy++;
        layout.gridwidth = 1;
        panel.add(lblFinancialYearDuration, layout);
        layout.gridx++;
        panel.add(comboFinancialYearDuration, layout);

        layout.gridx = 0;
        layout.gridy++;
        layout.gridwidth = 2;
        panel.add(newFinancialYearFinancesToCSVExportBox, layout);

        layout.gridy++;
        panel.add(chkSimulateGrayMonday, layout);

        return panel;
    }

    /**
     * Creates and configures the sales panel within the finance tab. The panel contains checkboxes for options related
     * to sales, including "Sell Units" and "Sell Parts". These checkboxes are added to a layout that organizes the
     * components vertically.
     *
     * @return A JPanel instance containing the configured sales options.
     */
    private JPanel createSalesPanel() {
        // Contents
        sellUnitsBox = new CampaignOptionsCheckBox("SellUnitsBox");
        sellUnitsBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "SellUnitsBox"));
        sellPartsBox = new CampaignOptionsCheckBox("SellPartsBox");
        sellPartsBox.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "SellPartsBox"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("SalesPanel", true, "SalesPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(sellUnitsBox, layout);

        layout.gridy++;
        panel.add(sellPartsBox, layout);

        return panel;
    }

    /**
     * Creates and returns a JPanel representing the taxes panel in the campaign options. This panel includes a checkbox
     * to enable or disable taxes and a spinner to set the percentage of taxes, along with corresponding labels.
     *
     * @return the configured JPanel containing the components for the taxes panel.
     */
    private JPanel createTaxesPanel() {
        // Contents
        chkUseTaxes = new CampaignOptionsCheckBox("UseTaxesBox");
        chkUseTaxes.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "UseTaxesBox"));

        lblTaxesPercentage = new CampaignOptionsLabel("TaxesPercentage");
        lblTaxesPercentage.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "TaxesPercentage"));
        spnTaxesPercentage = new CampaignOptionsSpinner("TaxesPercentage", 30, 1, 100, 1);
        spnTaxesPercentage.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "TaxesPercentage"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("TaxesPanel", true, "TaxesPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 2;
        panel.add(chkUseTaxes, layout);

        layout.gridy++;
        layout.gridwidth = 1;
        panel.add(lblTaxesPercentage, layout);
        layout.gridx++;
        panel.add(spnTaxesPercentage, layout);

        return panel;
    }

    /**
     * Creates and returns a JPanel representing the 'Shares Panel' within the finance tab.
     * <p>
     * The panel is laid out using grid-based constraints to position the components in a structured vertical
     * arrangement.
     *
     * @return A JPanel containing the configured components for the 'Shares Panel'.
     */
    private JPanel createSharesPanel() {
        // Contents
        chkUseShareSystem = new CampaignOptionsCheckBox("UseShareSystem");
        chkUseShareSystem.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "UseShareSystem"));
        chkSharesForAll = new CampaignOptionsCheckBox("SharesForAll");
        chkSharesForAll.addMouseListener(createTipPanelUpdater(financesGeneralOptions, "SharesForAll"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("SharesPanel", true, "SharesPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(chkUseShareSystem, layout);

        layout.gridy++;
        panel.add(chkSharesForAll, layout);

        return panel;
    }

    /**
     * Initializes the components and layout for the price multipliers tab. This tab includes controls for setting
     * various price multipliers such as - General multipliers for unit and part prices. - Multipliers for used parts. -
     * Miscellaneous multipliers for damaged, unrepairable parts, and order refunds.
     * <p>
     * The method creates and assigns UI components including panels, labels, and spinners to their respective class
     * fields. Each field corresponds to a specific category of price multiplier.
     */
    private void initializePriceMultipliersTab() {
        pnlGeneralMultipliersBuy = new JPanel();
        lblCommonPartPriceMultiplierBuy = new JLabel();
        spnCommonPartPriceMultiplierBuy = new JSpinner();
        lblInnerSphereUnitPriceMultiplierBuy = new JLabel();
        spnInnerSphereUnitPriceMultiplierBuy = new JSpinner();
        lblInnerSpherePartPriceMultiplierBuy = new JLabel();
        spnInnerSpherePartPriceMultiplierBuy = new JSpinner();
        lblClanUnitPriceMultiplierBuy = new JLabel();
        spnClanUnitPriceMultiplierBuy = new JSpinner();
        lblClanPartPriceMultiplierBuy = new JLabel();
        spnClanPartPriceMultiplierBuy = new JSpinner();
        lblMixedTechUnitPriceMultiplierBuy = new JLabel();
        spnMixedTechUnitPriceMultiplierBuy = new JSpinner();

        pnlGeneralMultipliersSell = new JPanel();
        lblCommonPartPriceMultiplierSell = new JLabel();
        spnCommonPartPriceMultiplierSell = new JSpinner();
        lblInnerSphereUnitPriceMultiplierSell = new JLabel();
        spnInnerSphereUnitPriceMultiplierSell = new JSpinner();
        lblInnerSpherePartPriceMultiplierSell = new JLabel();
        spnInnerSpherePartPriceMultiplierSell = new JSpinner();
        lblClanUnitPriceMultiplierSell = new JLabel();
        spnClanUnitPriceMultiplierSell = new JSpinner();
        lblClanPartPriceMultiplierSell = new JLabel();
        spnClanPartPriceMultiplierSell = new JSpinner();
        lblMixedTechUnitPriceMultiplierSell = new JLabel();
        spnMixedTechUnitPriceMultiplierSell = new JSpinner();

        pnlUsedPartsMultipliers = new JPanel();
        lblUsedPartPriceMultipliers = new JLabel[1]; // we initialize this properly later
        spnUsedPartPriceMultipliers = new JSpinner[1]; // we initialize this properly later

        pnlOtherMultipliers = new JPanel();
        lblDamagedPartsValueMultiplier = new JLabel();
        spnDamagedPartsValueMultiplier = new JSpinner();
        lblUnrepairablePartsValueMultiplier = new JLabel();
        spnUnrepairablePartsValueMultiplier = new JSpinner();
        lblCancelledOrderRefundMultiplier = new JLabel();
        spnCancelledOrderRefundMultiplier = new JSpinner();
    }

    /**
     * Creates and returns a JPanel representing the "Price Multipliers" tab in the user interface. The method includes
     * a header section, general multipliers panel, used parts multipliers panel, and other multipliers panel. These
     * components are arranged using a specific layout and added to a parent panel.
     *
     * @return a JPanel representing the "Price Multipliers" tab with all its components and layout configured
     */
    public JPanel createPriceMultipliersTab() {
        // Header
        priceMultipliersHeader = new CampaignOptionsHeaderPanel("PriceMultipliersTab",
              getImageDirectory() + "logo_clan_stone_lion.png", true, true, 2);

        // Contents
        pnlGeneralMultipliersBuy = createGeneralMultipliersBuyPanel();
        pnlGeneralMultipliersSell = createGeneralMultipliersSellPanel();
        pnlUsedPartsMultipliers = createUsedPartsMultiplierPanel();
        pnlOtherMultipliers = createOtherMultipliersPanel();

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("PriceMultipliersTab", true);
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridwidth = 5;
        layout.gridx = 0;
        layout.gridy = 0;
        panel.add(priceMultipliersHeader, layout);

        layout.gridy++;
        layout.gridwidth = 1;
        panel.add(pnlGeneralMultipliersBuy, layout);
        layout.gridx++;
        panel.add(pnlGeneralMultipliersSell, layout);
        layout.gridx++;
        panel.add(pnlUsedPartsMultipliers, layout);
        layout.gridx++;
        panel.add(pnlOtherMultipliers, layout);

        // Create Parent Panel and return
        return createParentPanel(panel, "PriceMultipliersTab");
    }

    /**
     * Creates and configures the general multipliers panel, which includes labels and spinners for various pricing
     * multipliers such as common parts, Inner Sphere units, Inner Sphere parts, Clan units, Clan parts, and mixed tech
     * units. The panel is structured using a grid layout for organized placement of components.
     *
     * @return a JPanel containing the components for setting general multipliers.
     *
     * @deprecated see {@link #createGeneralMultipliersBuyPanel()} and {@link #createGeneralMultipliersSellPanel()}
     */
    @Deprecated(since = "0.50.10", forRemoval = true)
    private JPanel createGeneralMultipliersPanel() {
        return createGeneralMultipliersBuyPanel();
    }

    /**
     * Creates and configures the "buy" part of the general multipliers panel, which includes labels and spinners for
     * various pricing multipliers such as common parts, Inner Sphere units, Inner Sphere parts, Clan units, Clan
     * parts, and mixed tech units. The panel is structured using a grid layout for organized placement of components.
     *
     * @return a JPanel containing the components for setting general buy price multipliers.
     */
    private JPanel createGeneralMultipliersBuyPanel() {
        // Contents
        lblCommonPartPriceMultiplierBuy = new CampaignOptionsLabel("CommonPartPriceMultiplierBuy");
        lblCommonPartPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "CommonPartPriceMultiplierBuy"));
        spnCommonPartPriceMultiplierBuy = new CampaignOptionsSpinner("CommonPartPriceMultiplierBuy",
              1.0,
              0.1,
              100,
              0.1);
        spnCommonPartPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "CommonPartPriceMultiplierBuy"));

        lblInnerSphereUnitPriceMultiplierBuy = new CampaignOptionsLabel("InnerSphereUnitPriceMultiplierBuy");
        lblInnerSphereUnitPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSphereUnitPriceMultiplierBuy"));
        spnInnerSphereUnitPriceMultiplierBuy = new CampaignOptionsSpinner("InnerSphereUnitPriceMultiplierBuy",
              1.0,
              0.1,
              100,
              0.1);
        spnInnerSphereUnitPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSphereUnitPriceMultiplierBuy"));

        lblInnerSpherePartPriceMultiplierBuy = new CampaignOptionsLabel("InnerSpherePartPriceMultiplierBuy");
        lblInnerSpherePartPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSpherePartPriceMultiplierBuy"));
        spnInnerSpherePartPriceMultiplierBuy = new CampaignOptionsSpinner("InnerSpherePartPriceMultiplierBuy",
              1.0,
              0.1,
              100,
              0.1);
        spnInnerSpherePartPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSpherePartPriceMultiplierBuy"));

        lblClanUnitPriceMultiplierBuy = new CampaignOptionsLabel("ClanUnitPriceMultiplierBuy");
        lblClanUnitPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanUnitPriceMultiplierBuy"));
        spnClanUnitPriceMultiplierBuy = new CampaignOptionsSpinner("ClanUnitPriceMultiplierBuy",
              1.0,
              0.1,
              100,
              0.1);
        spnClanUnitPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanUnitPriceMultiplierBuy"));

        lblClanPartPriceMultiplierBuy = new CampaignOptionsLabel("ClanPartPriceMultiplierBuy");
        lblClanPartPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanPartPriceMultiplierBuy"));
        spnClanPartPriceMultiplierBuy = new CampaignOptionsSpinner("ClanPartPriceMultiplierBuy",
              1.0,
              0.1,
              100,
              0.1);
        spnClanPartPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanPartPriceMultiplierBuy"));

        lblMixedTechUnitPriceMultiplierBuy = new CampaignOptionsLabel("MixedTechUnitPriceMultiplierBuy");
        lblMixedTechUnitPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "MixedTechUnitPriceMultiplierBuy"));
        spnMixedTechUnitPriceMultiplierBuy = new CampaignOptionsSpinner("MixedTechUnitPriceMultiplierBuy",
              1.0,
              0.1,
              100,
              0.1);
        spnMixedTechUnitPriceMultiplierBuy.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "MixedTechUnitPriceMultiplierBuy"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("GeneralMultipliersBuyPanel",
              true,
              "GeneralMultipliersBuyPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(lblCommonPartPriceMultiplierBuy, layout);
        layout.gridx++;
        panel.add(spnCommonPartPriceMultiplierBuy, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblMixedTechUnitPriceMultiplierBuy, layout);
        layout.gridx++;
        panel.add(spnMixedTechUnitPriceMultiplierBuy, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblInnerSphereUnitPriceMultiplierBuy, layout);
        layout.gridx++;
        panel.add(spnInnerSphereUnitPriceMultiplierBuy, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblInnerSpherePartPriceMultiplierBuy, layout);
        layout.gridx++;
        panel.add(spnInnerSpherePartPriceMultiplierBuy, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblClanUnitPriceMultiplierBuy, layout);
        layout.gridx++;
        panel.add(spnClanUnitPriceMultiplierBuy, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblClanPartPriceMultiplierBuy, layout);
        layout.gridx++;
        panel.add(spnClanPartPriceMultiplierBuy, layout);

        return panel;
    }

    /**
     * Creates and configures the "sell" part of the general multipliers panel, which includes labels and spinners for
     * various pricing multipliers such as common parts, Inner Sphere units, Inner Sphere parts, Clan units, Clan
     * parts, and mixed tech units. The panel is structured using a grid layout for organized placement of components.
     *
     * @return a JPanel containing the components for setting general sell price multipliers.
     */
    private JPanel createGeneralMultipliersSellPanel() {
        // Contents
        lblCommonPartPriceMultiplierSell = new CampaignOptionsLabel("CommonPartPriceMultiplierSell");
        lblCommonPartPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "CommonPartPriceMultiplierSell"));
        spnCommonPartPriceMultiplierSell = new CampaignOptionsSpinner("CommonPartPriceMultiplierSell",
              1.0,
              0.1,
              100,
              0.1);
        spnCommonPartPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "CommonPartPriceMultiplierSell"));

        lblInnerSphereUnitPriceMultiplierSell = new CampaignOptionsLabel("InnerSphereUnitPriceMultiplierSell");
        lblInnerSphereUnitPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSphereUnitPriceMultiplierSell"));
        spnInnerSphereUnitPriceMultiplierSell = new CampaignOptionsSpinner("InnerSphereUnitPriceMultiplierSell",
              1.0,
              0.1,
              100,
              0.1);
        spnInnerSphereUnitPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSphereUnitPriceMultiplierSell"));

        lblInnerSpherePartPriceMultiplierSell = new CampaignOptionsLabel("InnerSpherePartPriceMultiplierSell");
        lblInnerSpherePartPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSpherePartPriceMultiplierSell"));
        spnInnerSpherePartPriceMultiplierSell = new CampaignOptionsSpinner("InnerSpherePartPriceMultiplierSell",
              1.0,
              0.1,
              100,
              0.1);
        spnInnerSpherePartPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "InnerSpherePartPriceMultiplierSell"));

        lblClanUnitPriceMultiplierSell = new CampaignOptionsLabel("ClanUnitPriceMultiplierSell");
        lblClanUnitPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanUnitPriceMultiplierSell"));
        spnClanUnitPriceMultiplierSell = new CampaignOptionsSpinner("ClanUnitPriceMultiplierSell",
              1.0,
              0.1,
              100,
              0.1);
        spnClanUnitPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanUnitPriceMultiplierSell"));

        lblClanPartPriceMultiplierSell = new CampaignOptionsLabel("ClanPartPriceMultiplierSell");
        lblClanPartPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanPartPriceMultiplierSell"));
        spnClanPartPriceMultiplierSell = new CampaignOptionsSpinner("ClanPartPriceMultiplierSell",
              1.0,
              0.1,
              100,
              0.1);
        spnClanPartPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "ClanPartPriceMultiplierSell"));

        lblMixedTechUnitPriceMultiplierSell = new CampaignOptionsLabel("MixedTechUnitPriceMultiplierSell");
        lblMixedTechUnitPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "MixedTechUnitPriceMultiplierSell"));
        spnMixedTechUnitPriceMultiplierSell = new CampaignOptionsSpinner("MixedTechUnitPriceMultiplierSell",
              1.0,
              0.1,
              100,
              0.1);
        spnMixedTechUnitPriceMultiplierSell.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "MixedTechUnitPriceMultiplierSell"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("GeneralMultipliersSellPanel",
              true,
              "GeneralMultipliersSellPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(lblCommonPartPriceMultiplierSell, layout);
        layout.gridx++;
        panel.add(spnCommonPartPriceMultiplierSell, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblMixedTechUnitPriceMultiplierSell, layout);
        layout.gridx++;
        panel.add(spnMixedTechUnitPriceMultiplierSell, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblInnerSphereUnitPriceMultiplierSell, layout);
        layout.gridx++;
        panel.add(spnInnerSphereUnitPriceMultiplierSell, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblInnerSpherePartPriceMultiplierSell, layout);
        layout.gridx++;
        panel.add(spnInnerSpherePartPriceMultiplierSell, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblClanUnitPriceMultiplierSell, layout);
        layout.gridx++;
        panel.add(spnClanUnitPriceMultiplierSell, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblClanPartPriceMultiplierSell, layout);
        layout.gridx++;
        panel.add(spnClanPartPriceMultiplierSell, layout);

        return panel;
    }



    /**
     * Creates and returns a JPanel for configuring used parts price multipliers based on part quality. Each part
     * quality level is represented with a label and a spinner for adjusting the multiplier value.
     * <p>
     * The spinners are initialized with a range of values from 0.00 to 1.00, incrementing by 0.05, and include
     * formatting for two decimal places. Additionally, the alignment of the spinner text fields is set to left.
     * <p>
     * The panel is arranged using GridBagLayout to ensure proper alignment between labels and spinners for each quality
     * level.
     *
     * @return A JPanel containing labels and spinners for used parts price multipliers.
     */
    private JPanel createUsedPartsMultiplierPanel() {
        // Contents
        lblUsedPartPriceMultipliers = new JLabel[QUALITY_F.ordinal() + 1];
        spnUsedPartPriceMultipliers = new JSpinner[QUALITY_F.ordinal() + 1];

        for (PartQuality partQuality : PartQuality.values()) {
            final String qualityLevel = partQuality.toName(false);
            int ordinal = partQuality.ordinal();

            lblUsedPartPriceMultipliers[ordinal] = new JLabel(qualityLevel);
            lblUsedPartPriceMultipliers[ordinal].setName("lbl" + qualityLevel);

            spnUsedPartPriceMultipliers[ordinal] = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 1.00, 0.05));
            spnUsedPartPriceMultipliers[ordinal].setName("spn" + qualityLevel);
            spnUsedPartPriceMultipliers[ordinal].setEditor(new NumberEditor(spnUsedPartPriceMultipliers[ordinal],
                  "0.00"));

            DefaultEditor editor = (DefaultEditor) spnUsedPartPriceMultipliers[ordinal].getEditor();
            editor.getTextField().setHorizontalAlignment(JTextField.LEFT);
        }

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("UsedPartsMultiplierPanel",
              true,
              "UsedPartsMultiplierPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridwidth = 1;

        for (int i = 0; i < 6; i++) {
            layout.gridx = 0;
            layout.gridy = i;
            panel.add(lblUsedPartPriceMultipliers[i], layout);
            layout.gridx++;
            panel.add(spnUsedPartPriceMultipliers[i], layout);
        }

        return panel;
    }

    /**
     * Creates and returns a JPanel configured with components for adjusting multipliers related to damaged parts value,
     * unrepairable parts value, and cancelled order refunds. Each multiplier is represented with a label and an
     * associated configurable spinner control.
     *
     * @return a JPanel instance containing the components for configuring the multipliers.
     */
    private JPanel createOtherMultipliersPanel() {
        // Contents
        lblDamagedPartsValueMultiplier = new CampaignOptionsLabel("DamagedPartsValueMultiplier");
        lblDamagedPartsValueMultiplier.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "DamagedPartsValueMultiplier"));
        spnDamagedPartsValueMultiplier = new CampaignOptionsSpinner("DamagedPartsValueMultiplier",
              0.33,
              0.00,
              1.00,
              0.05);
        spnDamagedPartsValueMultiplier.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "DamagedPartsValueMultiplier"));

        lblUnrepairablePartsValueMultiplier = new CampaignOptionsLabel("UnrepairablePartsValueMultiplier");
        lblUnrepairablePartsValueMultiplier.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "UnrepairablePartsValueMultiplier"));
        spnUnrepairablePartsValueMultiplier = new CampaignOptionsSpinner("UnrepairablePartsValueMultiplier",
              0.10,
              0.00,
              1.00,
              0.05);
        spnUnrepairablePartsValueMultiplier.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "UnrepairablePartsValueMultiplier"));

        lblCancelledOrderRefundMultiplier = new CampaignOptionsLabel("CancelledOrderRefundMultiplier");
        lblCancelledOrderRefundMultiplier.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "CancelledOrderRefundMultiplier"));
        spnCancelledOrderRefundMultiplier = new CampaignOptionsSpinner("CancelledOrderRefundMultiplier",
              0.50,
              0.00,
              1.00,
              0.05);
        spnCancelledOrderRefundMultiplier.addMouseListener(createTipPanelUpdater(priceMultipliersHeader,
              "CancelledOrderRefundMultiplier"));

        // Layout the Panel
        final JPanel panel = new CampaignOptionsStandardPanel("OtherMultipliersPanel", true, "OtherMultipliersPanel");
        final GridBagConstraints layout = new CampaignOptionsGridBagConstraints(panel);

        layout.gridx = 0;
        layout.gridy = 0;
        layout.gridwidth = 1;
        panel.add(lblDamagedPartsValueMultiplier, layout);
        layout.gridx++;
        panel.add(spnDamagedPartsValueMultiplier, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblUnrepairablePartsValueMultiplier, layout);
        layout.gridx++;
        panel.add(spnUnrepairablePartsValueMultiplier, layout);

        layout.gridx = 0;
        layout.gridy++;
        panel.add(lblCancelledOrderRefundMultiplier, layout);
        layout.gridx++;
        panel.add(spnCancelledOrderRefundMultiplier, layout);

        return panel;
    }

    /**
     * Applies the specified campaign options to the corresponding campaign settings. If no campaign options are
     * provided, default options are used instead.
     *
     * @param presetCampaignOptions The campaign options to be applied. If null, default campaign options are applied.
     */
    public void applyCampaignOptionsToCampaign(@Nullable CampaignOptions presetCampaignOptions) {
        CampaignOptions options = presetCampaignOptions;
        if (presetCampaignOptions == null) {
            options = this.campaignOptions;
        }

        // General Options
        options.setLoanLimits(useLoanLimitsBox.isSelected());
        options.setUsePercentageMaintenance(usePercentageMaintenanceBox.isSelected());
        options.setUseExtendedPartsModifier(useExtendedPartsModifierBox.isSelected());
        options.setUsePeacetimeCost(usePeacetimeCostBox.isSelected());
        options.setShowPeacetimeCost(showPeacetimeCostBox.isSelected());
        options.setFinancialYearDuration(comboFinancialYearDuration.getSelectedItem());
        options.setNewFinancialYearFinancesToCSVExport(newFinancialYearFinancesToCSVExportBox.isSelected());
        options.setSimulateGrayMonday(chkSimulateGrayMonday.isSelected());
        options.setPayForParts(payForPartsBox.isSelected());
        options.setPayForRepairs(payForRepairsBox.isSelected());
        options.setPayForUnits(payForUnitsBox.isSelected());
        options.setPayForSalaries(payForSalariesBox.isSelected());
        options.setPayForOverhead(payForOverheadBox.isSelected());
        options.setPayForMaintain(payForMaintainBox.isSelected());
        options.setPayForTransport(payForTransportBox.isSelected());
        options.setPayForRecruitment(payForRecruitmentBox.isSelected());
        options.setPayForFood(payForFoodBox.isSelected());
        options.setPayForHousing(payForHousingBox.isSelected());
        options.setSellUnits(sellUnitsBox.isSelected());
        options.setSellParts(sellPartsBox.isSelected());
        options.setUseTaxes(chkUseTaxes.isSelected());
        options.setTaxesPercentage((int) spnTaxesPercentage.getValue());
        options.setUseShareSystem(chkUseShareSystem.isSelected());
        options.setSharesForAll(chkSharesForAll.isSelected());

        // Price Multipliers
        options.setCommonPartPriceMultiplierBuy((double) spnCommonPartPriceMultiplierBuy.getValue());
        options.setInnerSphereUnitPriceMultiplierBuy((double) spnInnerSphereUnitPriceMultiplierBuy.getValue());
        options.setInnerSpherePartPriceMultiplierBuy((double) spnInnerSpherePartPriceMultiplierBuy.getValue());
        options.setClanUnitPriceMultiplierBuy((double) spnClanUnitPriceMultiplierBuy.getValue());
        options.setClanPartPriceMultiplierBuy((double) spnClanPartPriceMultiplierBuy.getValue());
        options.setMixedTechUnitPriceMultiplierBuy((double) spnMixedTechUnitPriceMultiplierBuy.getValue());
        options.setCommonPartPriceMultiplierSell((double) spnCommonPartPriceMultiplierSell.getValue());
        options.setInnerSphereUnitPriceMultiplierSell((double) spnInnerSphereUnitPriceMultiplierSell.getValue());
        options.setInnerSpherePartPriceMultiplierSell((double) spnInnerSpherePartPriceMultiplierSell.getValue());
        options.setClanUnitPriceMultiplierSell((double) spnClanUnitPriceMultiplierSell.getValue());
        options.setClanPartPriceMultiplierSell((double) spnClanPartPriceMultiplierSell.getValue());
        options.setMixedTechUnitPriceMultiplierSell((double) spnMixedTechUnitPriceMultiplierSell.getValue());
        for (int i = 0; i < spnUsedPartPriceMultipliers.length; i++) {
            options.getUsedPartPriceMultipliers()[i] = (Double) spnUsedPartPriceMultipliers[i].getValue();
        }
        options.setDamagedPartsValueMultiplier((double) spnDamagedPartsValueMultiplier.getValue());
        options.setUnrepairablePartsValueMultiplier((double) spnUnrepairablePartsValueMultiplier.getValue());
        options.setCancelledOrderRefundMultiplier((double) spnCancelledOrderRefundMultiplier.getValue());
    }

    /**
     * Loads configuration values from the current campaign options to populate the financial settings and related UI
     * components in the `FinancesTab`.
     * <p>
     * This method is a convenience overload that invokes the overloaded
     * {@link #loadValuesFromCampaignOptions(CampaignOptions)} method with a `null` parameter, ensuring that default
     * campaign options will be loaded.
     */
    public void loadValuesFromCampaignOptions() {
        loadValuesFromCampaignOptions(null);
    }

    /**
     * Loads and applies the values from the provided campaign options or the default campaign options if the provided
     * options are null. Updates various UI components and internal variables based on the configuration of the campaign
     * options.
     *
     * @param presetCampaignOptions the campaign options to load values from; if null, the default campaign options will
     *                              be used
     */
    public void loadValuesFromCampaignOptions(@Nullable CampaignOptions presetCampaignOptions) {
        CampaignOptions options = presetCampaignOptions;
        if (presetCampaignOptions == null) {
            options = this.campaignOptions;
        }

        // General Options
        useLoanLimitsBox.setSelected(options.isUseLoanLimits());
        usePercentageMaintenanceBox.setSelected(options.isUsePercentageMaintenance());
        useExtendedPartsModifierBox.setSelected(options.isUseExtendedPartsModifier());
        usePeacetimeCostBox.setSelected(options.isUsePeacetimeCost());
        showPeacetimeCostBox.setSelected(options.isShowPeacetimeCost());
        comboFinancialYearDuration.setSelectedItem(options.getFinancialYearDuration());
        newFinancialYearFinancesToCSVExportBox.setSelected(options.isNewFinancialYearFinancesToCSVExport());
        chkSimulateGrayMonday.setSelected(options.isSimulateGrayMonday());
        payForPartsBox.setSelected(options.isPayForParts());
        payForRepairsBox.setSelected(options.isPayForRepairs());
        payForUnitsBox.setSelected(options.isPayForUnits());
        payForSalariesBox.setSelected(options.isPayForSalaries());
        payForOverheadBox.setSelected(options.isPayForOverhead());
        payForMaintainBox.setSelected(options.isPayForMaintain());
        payForTransportBox.setSelected(options.isPayForTransport());
        payForRecruitmentBox.setSelected(options.isPayForRecruitment());
        payForFoodBox.setSelected(options.isPayForFood());
        payForHousingBox.setSelected(options.isPayForHousing());
        sellUnitsBox.setSelected(options.isSellUnits());
        sellPartsBox.setSelected(options.isSellParts());
        chkUseTaxes.setSelected(options.isUseTaxes());
        spnTaxesPercentage.setValue(options.getTaxesPercentage());
        chkUseShareSystem.setSelected(options.isUseShareSystem());
        chkSharesForAll.setSelected(options.isSharesForAll());

        // Price Multipliers
        spnCommonPartPriceMultiplierBuy.setValue(options.getCommonPartPriceMultiplierBuy());
        spnInnerSphereUnitPriceMultiplierBuy.setValue(options.getInnerSphereUnitPriceMultiplierBuy());
        spnInnerSpherePartPriceMultiplierBuy.setValue(options.getInnerSpherePartPriceMultiplierBuy());
        spnClanUnitPriceMultiplierBuy.setValue(options.getClanUnitPriceMultiplierBuy());
        spnClanPartPriceMultiplierBuy.setValue(options.getClanPartPriceMultiplierBuy());
        spnMixedTechUnitPriceMultiplierBuy.setValue(options.getMixedTechUnitPriceMultiplierBuy());
        spnCommonPartPriceMultiplierSell.setValue(options.getCommonPartPriceMultiplierSell());
        spnInnerSphereUnitPriceMultiplierSell.setValue(options.getInnerSphereUnitPriceMultiplierSell());
        spnInnerSpherePartPriceMultiplierSell.setValue(options.getInnerSpherePartPriceMultiplierSell());
        spnClanUnitPriceMultiplierSell.setValue(options.getClanUnitPriceMultiplierSell());
        spnClanPartPriceMultiplierSell.setValue(options.getClanPartPriceMultiplierSell());
        spnMixedTechUnitPriceMultiplierSell.setValue(options.getMixedTechUnitPriceMultiplierSell());
        for (int i = 0; i < spnUsedPartPriceMultipliers.length; i++) {
            spnUsedPartPriceMultipliers[i].setValue(options.getUsedPartPriceMultipliers()[i]);
        }
        spnDamagedPartsValueMultiplier.setValue(options.getDamagedPartsValueMultiplier());
        spnUnrepairablePartsValueMultiplier.setValue(options.getUnrepairablePartsValueMultiplier());
        spnCancelledOrderRefundMultiplier.setValue(options.getCancelledOrderRefundMultiplier());
    }
}
