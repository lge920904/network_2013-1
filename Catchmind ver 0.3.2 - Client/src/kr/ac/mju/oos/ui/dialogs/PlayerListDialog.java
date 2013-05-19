package kr.ac.mju.oos.ui.dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class PlayerListDialog extends JDialog{
	JLabel jlb = new JLabel("");
    public PlayerListDialog(String str){
            getContentPane().add(jlb);
           
            jlb.setText(str.toString());
           
            this.setSize(200,100);
            this.setModal(true);
            this.setVisible(true);
           
    }

}
