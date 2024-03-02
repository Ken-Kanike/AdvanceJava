
package ajpprexam_qbsoln;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/*
<applet code="Q3" width="300" height="300"> </applet>
*/
public class Q3 extends Applet implements  ItemListener{
List cityList;

    public void init() {
        setSize(300, 300);
        setLayout(null);
        setBackground(Color.CYAN);
        

        Label label = new Label("City List:");
        label.setBounds(30, 20, 60, 20);
        add(label);

        cityList = new List();
        cityList.setBounds(30, 50, 150, 120);
        add(cityList);

         cityList.add("Mumbai" );
         cityList.add("Kolkata" );
         cityList.add("Pune" );
         cityList.add("Surat" );
         cityList.add("Mumbai" );
         cityList.add("Mumbai" );
         cityList.add("Mumbai" );
         cityList.add("Mumbai" );
         cityList.add("Mumbai" );
         cityList.add("Mumbai" );
   
         // Add item listener to the list
        cityList.addItemListener(this);
    }

 public void itemStateChanged(ItemEvent e) {
        // Check if the event is caused by selecting an item
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Get the selected city name
            String selectedCity = cityList.getSelectedItem();

            // Print the selected city
            showStatus("Selected City: " + selectedCity);
        }
    }
}

//package ajpprexam_qbsoln;
//
//import java.applet.Applet;
//import java.awt.Button;
//import java.awt.Label;
//import java.awt.List;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///*
//<applet code="Q3" width="300" height="300"> </applet>
//*/
//
//public class Q3 extends Applet implements ActionListener{
//List cityList;
//Button addButton, clearButton;
//  
//    public void init() {
//        setSize(300, 300);
//        setLayout(null);
//
//        Label label = new Label("City List:");
//        label.setBounds(30, 20, 60, 20);
//        add(label);
//
//        cityList = new List();
//        cityList.setBounds(30, 50, 150, 120);
//        add(cityList);
//
//        addButton = new Button("Add City");
//        addButton.setBounds(30, 180, 70, 30);
//        add(addButton);
//        addButton.addActionListener(this);
//
//        clearButton = new Button("Clear List");
//        clearButton.setBounds(110, 180, 70, 30);
//        add(clearButton);
//        clearButton.addActionListener(this);
//       
//    }
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == addButton) {
//            addCity();
//        } else if (e.getSource() == clearButton) {
//            clearList();
//        }
//    }
//    
//    private void addCity() {
//        // Add a city to the list
//        String cityName = "City " + (cityList.getItemCount() + 1);
//        cityList.add(cityName);
//    }
//    
//     private void clearList() {
//        // Clear all cities from the list
//        cityList.removeAll();
//    }
//}
