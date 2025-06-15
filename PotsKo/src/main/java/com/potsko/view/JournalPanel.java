package com.potsko.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.potsko.db.JournalEntryDAO;
import com.potsko.model.JournalEntry;

public class JournalPanel extends JPanel {
    private int userId;
    private DefaultListModel<String> listModel;
    private JList<String> entryList;
    private NavBarPanel navBar;

    public JournalPanel(MainFrame mainFrame, int userId) {
        this.userId = userId;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 246, 240));

        // NavBar
        navBar = new NavBarPanel(e -> {
            JButton src = (JButton) e.getSource();
            navBar.setActiveNavButton(src);

            if (src == navBar.getHomeButton()) mainFrame.showHome();
            if (src == navBar.getDashboardButton()) mainFrame.showDashboard();
            if (src == navBar.getJournalButton()) mainFrame.showJournal();
            if (src == navBar.getAvaiplantsButton()) mainFrame.showAvailablePlants();
            if (src == navBar.getProfileButton()) mainFrame.showProfile();
        });
        navBar.setActiveNavButton(navBar.getJournalButton());
        add(navBar, BorderLayout.WEST);

        // --- All journal content in a separate panel ---
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setOpaque(false);

        JLabel title = new JLabel("My Journal");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
        contentPanel.add(title, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        entryList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(entryList);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Entry input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add New Entry"));
        inputPanel.setBackground(new Color(245, 246, 240));

        JTextField titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        JTextArea contentArea = new JTextArea(3, 30);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScroll = new JScrollPane(contentArea);

        JButton addButton = new JButton("Add Entry");
        addButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(new JLabel("Content:"));
        inputPanel.add(contentScroll);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(addButton);

        contentPanel.add(inputPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String entryTitle = titleField.getText().trim();
            String entryContent = contentArea.getText().trim();
            if (entryTitle.isEmpty() && entryContent.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a title or content.");
                return;
            }
            boolean ok = JournalEntryDAO.addEntry(userId, null, entryTitle, entryContent);
            if (ok) {
                titleField.setText("");
                contentArea.setText("");
                refreshEntries();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add entry.");
            }
        });

        refreshEntries();
    }

    private void refreshEntries() {
        listModel.clear();
        List<JournalEntry> entries = JournalEntryDAO.getEntries(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (JournalEntry entry : entries) {
            String dateStr = sdf.format(new java.util.Date(entry.getDateWritten() * 1000L));
            String display = "<html><b>" + entry.getTitle() + "</b> (" + dateStr + ")<br>" + entry.getContent() + "</html>";
            listModel.addElement(display);
        }
    }
}