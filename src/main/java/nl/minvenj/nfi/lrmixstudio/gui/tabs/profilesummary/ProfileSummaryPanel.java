/**
 * Copyright (C) 2013, 2014 Netherlands Forensic Institute
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package nl.minvenj.nfi.lrmixstudio.gui.tabs.profilesummary;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTable.PrintMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.minvenj.nfi.lrmixstudio.domain.Sample;
import nl.minvenj.nfi.lrmixstudio.gui.ApplicationSettings;
import nl.minvenj.nfi.lrmixstudio.gui.ApplicationStateChangeListener;
import nl.minvenj.nfi.lrmixstudio.gui.SessionData;
import nl.minvenj.nfi.lrmixstudio.model.ConfigurationDataChangeListener;
import nl.minvenj.nfi.lrmixstudio.model.ConfigurationDataElement;

/**
 *
 * @author dejong
 */
public class ProfileSummaryPanel extends javax.swing.JPanel implements ConfigurationDataChangeListener, ApplicationStateChangeListener {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileSummaryPanel.class);
    private SessionData session;

    /**
     * Creates new form ProfileSummaryPanel
     */
    public ProfileSummaryPanel() {
        initComponents();
        LOG.info("ProfileSummaryPanel");

        errorWrappingPanel.setVisible(false);

        decoratorList.setListData(new Vector());
        decoratorList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
                final Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (!isSelected) {
                    component.setBackground((index % 2) == 0 ? ApplicationSettings.getEvenRowColor(list.getName()) : ApplicationSettings.getOddRowColor(list.getName()));
                }
                return component;
            }
        });

        highlightColor.setBackground(ApplicationSettings.getHighlightColor());
        highlightBackgroundColor.setBackground(ApplicationSettings.getHighlightBackgroundColor());
        textColorCheckBox.setSelected(ApplicationSettings.isSetHighlightColor());
        backgroundColorCheckBox.setSelected(ApplicationSettings.isSetHighlightBackgroundColor());
        boldCheckbox.setSelected(ApplicationSettings.getHighlightBold());
        italicCheckbox.setSelected(ApplicationSettings.getHighlightItalic());
        underlinedCheckbox.setSelected(ApplicationSettings.getHighlightUnderlined());

        profileSummaryTable.setAlleleCountListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                highlightCountLabel.setText("Highlighted " + evt.getNewValue() + " alleles");
            }
        });

        try {
            final InputStream is = getClass().getResourceAsStream("icon.png");
            final BufferedImage imgOrg = ImageIO.read(is);
            final Image imgScaled = imgOrg.getScaledInstance(140, 149, Image.SCALE_SMOOTH);
            final Icon icon = new ImageIcon(imgScaled);
            iconLabel.setIcon(icon);
        } catch (final IOException ex) {
            LOG.warn("Could not read icon!", ex);
        } catch (final IllegalArgumentException iae) {
            LOG.warn("Could not read icon!", iae);
        }
    }

    public void setContext(final SessionData sessionData) {
        sessionData.addDataChangeListener(this);
        sessionData.addStateChangeListener(this);
        this.session = sessionData;
        profileSummaryTable.setContext(session);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profileSummarySplitPane1 = new javax.swing.JSplitPane();
        decoratorPanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        decoratorList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        underlinedCheckbox = new javax.swing.JCheckBox();
        highlightColor = new javax.swing.JPanel();
        boldCheckbox = new javax.swing.JCheckBox();
        italicCheckbox = new javax.swing.JCheckBox();
        highlightBackgroundColor = new javax.swing.JPanel();
        backgroundColorCheckBox = new javax.swing.JCheckBox();
        textColorCheckBox = new javax.swing.JCheckBox();
        highlightCountLabel = new javax.swing.JLabel();
        profileDetailPanel = new javax.swing.JPanel();
        profileSummaryScrollPane = new javax.swing.JScrollPane();
        profileSummaryTable = new nl.minvenj.nfi.lrmixstudio.gui.tabs.profilesummary.ProfileSummaryTable();
        errorWrappingPanel = new javax.swing.JPanel();
        errorLabel = new javax.swing.JLabel();

        profileSummarySplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        iconLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        exportButton.setText("Print...");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        decoratorList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        decoratorList.setName("decoratorList"); // NOI18N
        decoratorList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(final javax.swing.event.ListSelectionEvent evt) {
                decoratorListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(decoratorList);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName(""); // NOI18N

        underlinedCheckbox.setText("<html><u>Underlined");
        underlinedCheckbox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                underlinedCheckboxActionPerformed(evt);
            }
        });

        highlightColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        highlightColor.setToolTipText("Click here to set the colour used to highlight matching alleles");
        highlightColor.setAlignmentX(0.2F);
        highlightColor.setMaximumSize(new java.awt.Dimension(20, 20));
        highlightColor.setMinimumSize(new java.awt.Dimension(20, 20));
        highlightColor.setPreferredSize(new java.awt.Dimension(20, 20));
        highlightColor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                highlightColorMouseClicked(evt);
            }
        });

        final javax.swing.GroupLayout highlightColorLayout = new javax.swing.GroupLayout(highlightColor);
        highlightColor.setLayout(highlightColorLayout);
        highlightColorLayout.setHorizontalGroup(
            highlightColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        highlightColorLayout.setVerticalGroup(
            highlightColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        boldCheckbox.setText("<html><b>Bold");
        boldCheckbox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                boldCheckboxActionPerformed(evt);
            }
        });

        italicCheckbox.setText("<html><i>Italic");
        italicCheckbox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                italicCheckboxActionPerformed(evt);
            }
        });

        highlightBackgroundColor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        highlightBackgroundColor.setToolTipText("Click here to set the background colour used to highlight matching alleles");
        highlightBackgroundColor.setAlignmentX(0.2F);
        highlightBackgroundColor.setMaximumSize(new java.awt.Dimension(20, 20));
        highlightBackgroundColor.setMinimumSize(new java.awt.Dimension(20, 20));
        highlightBackgroundColor.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(final java.awt.event.MouseEvent evt) {
                highlightBackgroundColorMouseClicked(evt);
            }
        });

        final javax.swing.GroupLayout highlightBackgroundColorLayout = new javax.swing.GroupLayout(highlightBackgroundColor);
        highlightBackgroundColor.setLayout(highlightBackgroundColorLayout);
        highlightBackgroundColorLayout.setHorizontalGroup(
            highlightBackgroundColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        highlightBackgroundColorLayout.setVerticalGroup(
            highlightBackgroundColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        backgroundColorCheckBox.setText("Background Colour");
        backgroundColorCheckBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                backgroundColorCheckBoxActionPerformed(evt);
            }
        });

        textColorCheckBox.setText("Text Colour");
        textColorCheckBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(final java.awt.event.ActionEvent evt) {
                textColorCheckBoxActionPerformed(evt);
            }
        });

        final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(underlinedCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boldCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backgroundColorCheckBox)
                            .addComponent(textColorCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(highlightColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(highlightBackgroundColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(italicCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(highlightColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textColorCheckBox))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backgroundColorCheckBox)
                    .addComponent(highlightBackgroundColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boldCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(italicCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(underlinedCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        final javax.swing.GroupLayout decoratorPanelLayout = new javax.swing.GroupLayout(decoratorPanel);
        decoratorPanel.setLayout(decoratorPanelLayout);
        decoratorPanelLayout.setHorizontalGroup(
            decoratorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decoratorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(decoratorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(highlightCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(decoratorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        decoratorPanelLayout.setVerticalGroup(
            decoratorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decoratorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(decoratorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(decoratorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(highlightCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        profileSummarySplitPane1.setLeftComponent(decoratorPanel);

        profileSummaryTable.setRowSelectionAllowed(false);
        profileSummaryTable.getTableHeader().setReorderingAllowed(false);
        profileSummaryScrollPane.setViewportView(profileSummaryTable);

        errorLabel.setBackground(new java.awt.Color(255, 255, 255));
        errorLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        errorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_alert.gif"))); // NOI18N
        errorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 2));

        final javax.swing.GroupLayout errorWrappingPanelLayout = new javax.swing.GroupLayout(errorWrappingPanel);
        errorWrappingPanel.setLayout(errorWrappingPanelLayout);
        errorWrappingPanelLayout.setHorizontalGroup(
            errorWrappingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorWrappingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                .addContainerGap())
        );
        errorWrappingPanelLayout.setVerticalGroup(
            errorWrappingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(errorWrappingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        final javax.swing.GroupLayout profileDetailPanelLayout = new javax.swing.GroupLayout(profileDetailPanel);
        profileDetailPanel.setLayout(profileDetailPanelLayout);
        profileDetailPanelLayout.setHorizontalGroup(
            profileDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profileSummaryScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(errorWrappingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        profileDetailPanelLayout.setVerticalGroup(
            profileDetailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profileDetailPanelLayout.createSequentialGroup()
                .addComponent(errorWrappingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(profileSummaryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
        );

        profileSummarySplitPane1.setBottomComponent(profileDetailPanel);

        final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileSummarySplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profileSummarySplitPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportButtonActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        try {
            profileSummaryTable.print(PrintMode.FIT_WIDTH, new MessageFormat("Profile overview for case " + session.getCaseNumber()), new MessageFormat("Page {0}"));
        } catch (final PrinterException ex) {
            LOG.error("Error printing table", ex);
        }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void decoratorListValueChanged(final javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_decoratorListValueChanged
        LOG.info("decoratorListValueChanged");
        if (decoratorList.getModel().getSize() > 0) {
            profileSummaryTable.setDecorator((AlleleDecorator) decoratorList.getSelectedValue());
        }
    }//GEN-LAST:event_decoratorListValueChanged

    private void highlightColorMouseClicked(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highlightColorMouseClicked
        Component cmp = getParent();
        while (cmp != null && !(cmp instanceof Frame)) {
            cmp = cmp.getParent();
        }
        final ColorPickerDialog dlg = new ColorPickerDialog((Frame) cmp, true, "Please select the color used to highlight alleles", ApplicationSettings.getHighlightColor());
        dlg.setVisible(true);
        if (dlg.isOK()) {
            final Color c = dlg.getSelectedColor();
            highlightColor.setBackground(c);
            ApplicationSettings.setHighlightColor(c);
            profileSummaryTable.update();
        }
    }//GEN-LAST:event_highlightColorMouseClicked

    private void boldCheckboxActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boldCheckboxActionPerformed
        ApplicationSettings.setHighlightBold(boldCheckbox.isSelected());
        profileSummaryTable.update();
    }//GEN-LAST:event_boldCheckboxActionPerformed

    private void underlinedCheckboxActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_underlinedCheckboxActionPerformed
        ApplicationSettings.setHighlightUnderlined(underlinedCheckbox.isSelected());
        profileSummaryTable.update();
    }//GEN-LAST:event_underlinedCheckboxActionPerformed

    private void italicCheckboxActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_italicCheckboxActionPerformed
        ApplicationSettings.setHighlightItalic(italicCheckbox.isSelected());
        profileSummaryTable.update();
    }//GEN-LAST:event_italicCheckboxActionPerformed

    private void highlightBackgroundColorMouseClicked(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highlightBackgroundColorMouseClicked
        Component cmp = getParent();
        while (cmp != null && !(cmp instanceof Frame)) {
            cmp = cmp.getParent();
        }
        final ColorPickerDialog dlg = new ColorPickerDialog((Frame) cmp, true, "Please select the background color used to highlight alleles", ApplicationSettings.getHighlightBackgroundColor());
        dlg.setVisible(true);
        if (dlg.isOK()) {
            final Color c = dlg.getSelectedColor();
            highlightBackgroundColor.setBackground(c);
            ApplicationSettings.setHighlightBackgroundColor(c);
            profileSummaryTable.update();
        }
    }//GEN-LAST:event_highlightBackgroundColorMouseClicked

    private void backgroundColorCheckBoxActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundColorCheckBoxActionPerformed
        if (backgroundColorCheckBox.isSelected()) {
            ApplicationSettings.setHighlightBackgroundColor(highlightBackgroundColor.getBackground());
        } else {
            ApplicationSettings.setHighlightBackgroundColor(null);
        }
        profileSummaryTable.update();
    }//GEN-LAST:event_backgroundColorCheckBoxActionPerformed

    private void textColorCheckBoxActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textColorCheckBoxActionPerformed
        if (textColorCheckBox.isSelected()) {
            ApplicationSettings.setHighlightColor(highlightColor.getBackground());
        } else {
            ApplicationSettings.setHighlightColor(null);
        }
        profileSummaryTable.update();
    }//GEN-LAST:event_textColorCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox backgroundColorCheckBox;
    private javax.swing.JCheckBox boldCheckbox;
    private javax.swing.JList decoratorList;
    private javax.swing.JPanel decoratorPanel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel errorWrappingPanel;
    private javax.swing.JButton exportButton;
    private javax.swing.JPanel highlightBackgroundColor;
    private javax.swing.JPanel highlightColor;
    private javax.swing.JLabel highlightCountLabel;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JCheckBox italicCheckbox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel profileDetailPanel;
    private javax.swing.JScrollPane profileSummaryScrollPane;
    private javax.swing.JSplitPane profileSummarySplitPane1;
    private nl.minvenj.nfi.lrmixstudio.gui.tabs.profilesummary.ProfileSummaryTable profileSummaryTable;
    private javax.swing.JCheckBox textColorCheckBox;
    private javax.swing.JCheckBox underlinedCheckbox;
    // End of variables declaration//GEN-END:variables

    @Override
    public void dataChanged(final ConfigurationDataElement element) {
        LOG.debug("dataChanged {}", element);
        switch (element) {
            case ACTIVELOCI:
            case ACTIVEREPLICATES:
            case ACTIVEPROFILES:
            case PROFILES:
            case REPLICATES:
                profileSummaryTable.clear();
                final int idx = Math.max(decoratorList.getSelectedIndex(), 0);
                decoratorList.setListData(new Object[0]);
                final DefaultListModel model = new DefaultListModel<>();
                // Add decorators for dropped in and dropped out alleles
                model.addElement(new SampleOnlyAllelesDecorator(session));
                model.addElement(new DroppedOutAllelesDecorator(session));
                profileSummaryTable.addReplicates(session.getAllReplicates());
                // Add decorators to highlight matching alleles between replicate and profile
                final Collection<Sample> activeProfiles = session.getActiveProfiles();
                for (final Sample profile : activeProfiles) {
                    model.addElement(new ReplicateAndProfileMatchingAllelesDecorator(session, profile));
                }
                // Add decorator to highlight alleles shared by all profiles (if we have more than 1 profile)
                if (activeProfiles.size() > 1) {
                    model.addElement(new InterProfileMatchingAllelesDecorator(session, activeProfiles));
                }

                profileSummaryTable.addProfiles(activeProfiles);
                decoratorList.setModel(model);
                decoratorList.setSelectedIndex(idx);
                break;
        }
    }

    @Override
    public void applicationStateChanged(final APP_STATE newState) {
        LOG.debug("Application state changed: {}", newState);
        if (session != null) {
            if (session.getStatusMessage() == null || session.getStatusMessage().isEmpty() || session.getApplicationState() != APP_STATE.READY_FOR_OVERVIEW) {
                LOG.debug("Hiding error message");
                errorWrappingPanel.setVisible(false);
            } else {
                LOG.debug("Showning error message '{}'", session.getStatusMessage());
                errorLabel.setText(session.getStatusMessage());
                errorWrappingPanel.setVisible(true);
            }
        }
        setEnabled(newState == APP_STATE.READY_FOR_OVERVIEW || newState == APP_STATE.READY_FOR_ANALYSIS);
    }
}
