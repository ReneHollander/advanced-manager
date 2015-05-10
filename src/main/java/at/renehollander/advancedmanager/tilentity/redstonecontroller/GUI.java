package at.renehollander.advancedmanager.tilentity.redstonecontroller;

import at.renehollander.advancedmanager.network.MessageRedstoneControllerUpdateScript;
import at.renehollander.advancedmanager.network.NetworkHandler;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    private TileEntityRedstoneController terc;

    private JPanel contentPane;

    private JButton saveButton;
    private RSyntaxTextArea codeArea;

    public GUI(TileEntityRedstoneController terc) {
        this.terc = terc;
    }

    public void display() {
        this.contentPane = new JPanel(new BorderLayout());

        this.codeArea = new RSyntaxTextArea(20, 60);
        this.codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        this.codeArea.setCodeFoldingEnabled(true);
        this.codeArea.setAutoIndentEnabled(true);
        this.codeArea.setText(this.terc.getProps().getScript());

        RTextScrollPane sp = new RTextScrollPane(this.codeArea);
        this.contentPane.add(sp, BorderLayout.CENTER);

        this.saveButton = new JButton("Save");
        this.saveButton.addActionListener(this);
        this.contentPane.add(this.saveButton, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setTitle("Redstone Controller Code Editor");
        pack();
        setLocationRelativeTo(null);
        setAutoRequestFocus(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.saveButton) {
            handleSaveButton();
        }
    }

    private void handleSaveButton() {
        NetworkHandler.sendToServer(new MessageRedstoneControllerUpdateScript(terc.getPos(), this.codeArea.getText()));
    }
}
